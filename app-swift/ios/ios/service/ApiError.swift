//
//  ApiErrot.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/20.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import UIKit

class ApiError: NSObject {
    var code:Int = 500
    var msg:String? = nil
    
    init(_code: Int,_msg:String?) {
        code = _code
        msg = _msg
    }
}
