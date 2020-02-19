//
//  Article.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/18.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import Foundation
import HandyJSON

class Article: NSObject,HandyJSON {
    var id:Int = 0
    var title:String? = nil
    
//    override static func getPrimaryKey() -> String {
//        return "id"
//    }
//    
//    override static func getTableName() -> String {
//        return "Article"
//    }
    
    required override init() {}
}
