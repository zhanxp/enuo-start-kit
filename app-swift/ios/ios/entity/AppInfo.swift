//
//  AppInfo.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/18.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import Foundation
import OpenUDID
import HandyJSON

var sharedAppInfo: AppInfo = AppInfo()


class AppInfo: NSObject,HandyJSON {
    var id:String = ""
    var lastCodeTime:Date? = nil
    var appTime:Date? = nil
    var isFirst:Bool = true
    
    required override init() {}
    
//    override static func getPrimaryKey() -> String {
//        return "id"
//    }
//    
//    override static func getTableName() -> String {
//        return "AppInfo"
//    }
    
    private var _versionCode:String = ""
    func versionCode() -> (String){
        if(_versionCode.isEmpty){
            _versionCode = Bundle.main.infoDictionary!["CFBundleVersion"] as! String
        }
        return _versionCode
    }
    
    private var _clientType:String = ""
    func clientType() -> (String){
        if(_clientType.isEmpty){
            _clientType = Bundle.main.infoDictionary!["CFBundleExecutable"] as! String
        }
        return _clientType
    }
    
    private var _deviceId:String = ""
    func deviceId() -> (String){
        if(_deviceId == ""){
            _deviceId = OpenUDID.value()
        }
        return _deviceId
    }
  
    func saveInfo() {
        let str = self.toJSONString()
        UserDefaults.standard.set(str, forKey: "kAppInfo")
        UserDefaults.standard.synchronize()
    }
    
    static func loadInfo() -> AppInfo? {
        let str = UserDefaults.standard.string(forKey: "kAppInfo")
        return AppInfo.deserialize(from:str)
    }
    
    static func sharedInstance() -> (AppInfo) {
        if(sharedAppInfo.id.isEmpty){
            let app = AppInfo.loadInfo()
            if(app != nil){
                sharedAppInfo = app as! AppInfo
            }
            if(sharedAppInfo.id.isEmpty){
                sharedAppInfo.id = "APP"
                sharedAppInfo.appTime = Date()
                sharedAppInfo.saveInfo()
            }
        }
        return sharedAppInfo
    }
}
