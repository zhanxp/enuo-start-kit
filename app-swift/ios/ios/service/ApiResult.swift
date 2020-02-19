//
//  ApiResult.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/18.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import Foundation
import HandyJSON

enum KerrorCodeType : Int {
    case KErrorCode_NONE = 200,
     KErrorCode_NOT_LOGIN = 401,
     KErrorCode_NOT_VALID = 402,
     KErrorCode_ERROR = 505,
     KErrorCode_DENIED = 403,
     KErrorCode_NOT_COMPANY = 4031,
     KErrorCode_ALLOW_QUOTATION = 4032
}

class ApiResult: NSObject,HandyJSON {
    var code:Int = 500
    var success:Bool = false
    var msg:String? = nil
    var title:String? = nil
    
     required override init() {}
}
