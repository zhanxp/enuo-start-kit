//
//  ApiPageResult.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/18.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import Foundation
import HandyJSON

class ApiPageResult: NSObject,HandyJSON {
    var pageCount:Int = 1
    var pageIndex:Int = 1
    var pageSize:Int = 10
    var items:Array<Any> = []
    var total:Int = 10

     required override init() {}
    
    init(_ _pageIndex:Int,_ _pageSize:Int) {
        pageIndex = _pageIndex
        pageSize = _pageSize
    }
}
