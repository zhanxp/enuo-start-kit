//
//  UserInfo.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/18.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import Foundation
import HandyJSON

var sharedUserInfo: UserInfo = UserInfo()


class UserInfo: NSObject,HandyJSON {
    var id:String = ""
    var name:String? = nil
    var ticket:String? = nil
    
    required override init() {}
    
//    override static func getPrimaryKey() -> String {
//        return "id"
//    }
//    
//    override static func getTableName() -> String {
//        return "UserInfo"
//    }
    
//    static func getCurrentUserID() -> (String?){
//       return UserDefaults.standard.string(forKey: "kUserID")
//    }
//
//    static func setCurrentUserID(_ userid:String){
//        UserDefaults.standard.set(userid, forKey: "kUserID")
//        UserDefaults.standard.synchronize()
//        sharedUserInfo.id = "";
//    }
//
//    static func hasLogin() -> Bool {
//        let userid = UserInfo.getCurrentUserID()
//       return userid != nil && !userid!.isEmpty
//    }
    
    func saveInfo() {
        let str = self.toJSONString()
        UserDefaults.standard.set(str, forKey: "kUserInfo")
        UserDefaults.standard.synchronize()
    }
    
    static func loadInfo() -> UserInfo? {
        let str = UserDefaults.standard.string(forKey: "kUserInfo")
        return UserInfo.deserialize(from:str)
    }
    
    static func sharedInstance() -> (UserInfo) {
        if(sharedUserInfo.id.isEmpty){
            let obj = UserInfo.loadInfo()
            if(obj != nil){
                sharedUserInfo = obj!
            }
        }
        return sharedUserInfo
    }
}
