//
//  CategoryService.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/20.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import Foundation

class CategoryService: BaseService {
    func list(success:@escaping APIArryHandle,failure:@escaping APIErrorHandle) {
        let parameters = self.UserInfoparameters()
        self.RequestWithFunction(url: "category/list", parameters: parameters, method: .get, successHandle: { (result, json) in
            var list:Array<Category> = []
            if(result.success){
                let array = json["data"].arrayValue
                for item in array{
                    let ent = Category.deserialize(from: item.rawString())
                    list.append(ent!)
                }
            }
            success(result,list)
        }, failureHandle: failure)
    }
}
