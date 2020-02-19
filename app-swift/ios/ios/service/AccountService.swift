//
//  AccountService.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/20.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import Foundation

class AccountService: BaseService {
    func login(username:String,password:String,success:@escaping APIObjectHandle,failure:@escaping APIErrorHandle) {
        var parameters = self.UserInfoparameters()
        parameters["username"] = username
        parameters["password"] = password
        
        self.RequestWithFunction(url: "login", parameters: parameters, method: .post, successHandle: { (result, json) in
            var info:UserInfo? = nil
            if(result.success){
                info = UserInfo.deserialize(from: json["data"].rawString())
            }
            success(result,info)
        }, failureHandle: failure)
    }
}
