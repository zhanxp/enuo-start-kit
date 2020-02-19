//
//  BaseService.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/18.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import HandyJSON
import SwiftyJSON
import Alamofire
import Toaster

typealias APISuccessHandle = (_ result: ApiResult,_ json:JSON) -> Void
typealias APIErrorHandle = (_ error: ApiError) -> Void
typealias APIObjectHandle = (_ result: ApiResult,_ obj:Any?) -> Void
typealias APIArryHandle = (_ result: ApiResult,_ list:Array<Any>) -> Void
typealias APIPageDataHandle = (_ result: ApiResult,_ page:ApiPageResult) -> Void


class BaseService: NSObject {
    func UserInfoparameters() -> Dictionary<String, String> {
        var dict: Dictionary<String, String> = [:]
        let user = UserInfo.sharedInstance()
     
        if(!user.id.isEmpty){
            dict["userID"] = user.id
        }
        if(user.ticket != nil){
            dict["ticket"] = user.ticket
        }
        
        let appinfo = AppInfo.sharedInstance()
        dict["versionCode"] = String(appinfo.versionCode())
        dict["clientType"] = String(appinfo.clientType())
        return dict
    }
    
    func SignParameters(_ parameters:Dictionary<String, String> ) -> Dictionary<String, String> {
        var dict = parameters;
        if(dict["timeSpan"]  == nil){
            dict["timeSpan"] = Common.dateToString(Date(), format: "yyyy-MM-dd HH:mm:ss")
        }
        
        let arrTemp = dict.sorted(by: { (t1, t2) -> Bool in
             return t1.0 > t2.0 ? true:false
        })

        var url = ""
        var result:Dictionary<String, String> = [:];
        for (key, value) in arrTemp {
            result[key] = value
            url +=  key + "=" + value + "&";
        }

        let appinfo = AppInfo.sharedInstance()
        url += appinfo.deviceId()

        let sign = url.MD5()
        let signData = sign.data(using: String.Encoding.utf8, allowLossyConversion: true)
        let singStr = NSData.aesBase64Encode(signData)
        result["sign"] = singStr
        
        return result
    }
    
    
    func SignUrl(_ url:String) -> String {
        let separatedURLArray : [String] = url.components(separatedBy: "?")
        let baseURL = separatedURLArray[0]
        let appinfo = AppInfo.sharedInstance()
        let timeSpan = Common.dateToString(Date(), format: "yyyy-MM-dd HH:mm:ss")
        var parameters:Dictionary<String, String> = [:];
        if(separatedURLArray.count == 2){
            let querys = separatedURLArray[1].components(separatedBy: "&")
            for param in querys{
                let elts = param.components(separatedBy: "=")
                if(elts.count < 2) {
                    continue
                }
                parameters[elts[0]] = elts[1]
            }
        }
        if(parameters["timeSpan"]==nil){
            parameters["timeSpan"] = timeSpan
        }
        let uinfo = UserInfo.sharedInstance()
        if(parameters["token"]==nil){
            parameters["token"] = uinfo.ticket
        }
        if(parameters["userID"]==nil){
            parameters["userID"] = uinfo.id
        }
        if(parameters["format"]==nil){
            parameters["format"] = "html"
        }
        let arrTemp = parameters.sorted(by: { (t1, t2) -> Bool in
            return t1.0 > t2.0 ? true:false
        })
        
        
        var url = ""
        var result:Dictionary<String, String> = [:];
        for (key, value) in arrTemp {
            result[key] = value
            url +=  key + "=" + value + "&";
        }
        
        url += appinfo.deviceId()
        
        let sign = url.MD5()
        let signData = sign.data(using: String.Encoding.utf8, allowLossyConversion: true)
        let singStr = NSData.aesBase64Encode(signData)
        result["sign"] = singStr
        
        var completeURL = "";
        for (key, value) in result {
            let urlCodeVal = value.addingPercentEncoding(withAllowedCharacters: .urlHostAllowed)
            let itemUrl = key + "=" + urlCodeVal!
            completeURL = completeURL + itemUrl + "&"
        }
        
        completeURL = baseURL + "?" + completeURL
      
        return completeURL;
    }
    

    func RequestWithFunction(url:String,
                                 parameters:Dictionary<String,String>,
                                 method:HTTPMethod,
                                 successHandle:@escaping APISuccessHandle ,
                                 failureHandle:@escaping APIErrorHandle) {
        
        let parameters = self.SignParameters(parameters)
        let url = API_ROOT + url
        let headers: HTTPHeaders = [
            "Accept": "application/json"
        ]
        Alamofire.request(url, method:method, parameters: parameters, encoding: URLEncoding.default, headers: headers).responseJSON { (response) in
            
            switch response.result {
                 case .success(let data):
                    if let j = response.result.value {
                        let json = JSON(j)
                        var obj = ApiResult.deserialize(from: json.rawString())
                        if(obj==nil){
                            obj = ApiResult.init()
                        }
                        successHandle(obj!,json)
                    }else{
                        Toast(text: "服务异常！").show()
                        failureHandle(ApiError.init(_code:500,_msg:"服务返回异常！"))
                    }
                case .failure(let error):
                    Toast(text: "服务异常,请稍后再试！").show()
                    failureHandle(ApiError.init(_code:500,_msg:error.localizedDescription))
            }
        }
    }
}
