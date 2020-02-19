//
//  ArticleService.h
//  ios
//
//  Created by Peter Zhan on 2017/10/16.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BaseService.h"

@interface ArticleService : BaseService
-(void)pageList:(NSInteger)pageIndex
       pageSize:(NSInteger)paegSize
        success:(APIPageDataHandle)success_callback
        failure:(APIErrorHandle)failure_callback;
@end
