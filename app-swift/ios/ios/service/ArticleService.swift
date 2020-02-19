//
//  ArticleService.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/20.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import Foundation

class ArticleService: BaseService {
    func pagelist(pageIndex:Int,pageSize:Int,success:@escaping APIPageDataHandle,failure:@escaping APIErrorHandle) {
        var parameters = self.UserInfoparameters()
        parameters["pageIndex"] = String(pageIndex)
        parameters["pageSize"] = String(pageSize)
        
        self.RequestWithFunction(url: "article/list", parameters: parameters, method: .get, successHandle: { (result, json) in
            let pageList = ApiPageResult.init()
            pageList.items = []
            pageList.total = pageSize
            pageList.pageIndex = pageIndex
            pageList.pageSize = pageSize
            if(result.success){
                let data = json["data"]
                pageList.total = data["total"].intValue
                let array = data["items"].arrayValue
                for item in array{
                    let ent = Article.deserialize(from: item.rawString())
                    pageList.items.append(ent!)
                }
            }
            success(result,pageList)
        }, failureHandle: failure)
    }
}
