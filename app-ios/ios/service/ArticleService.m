//
//  ArticleService.m
//  ios
//
//  Created by Peter Zhan on 2017/10/16.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "ArticleService.h"

@implementation ArticleService
-(void)pageList:(NSInteger)pageIndex
       pageSize:(NSInteger)paegSize
        success:(APIPageDataHandle)success_callback
        failure:(APIErrorHandle)failure_callback{
    
    NSMutableDictionary *parameters = [self UserInfoparameters];
    [parameters setObject:[NSString stringWithFormat:@"%ld" , pageIndex]  forKey:@"pageIndex"];
    [parameters setObject:[NSString stringWithFormat:@"%ld" , paegSize]  forKey:@"paegSize"];
    
    [self GetRequestWithFunction:@"article/list" parameters:parameters success:^(APIResult *result, NSDictionary *dict) {
        
        APIPageResult *page = [[APIPageResult alloc] init];
        if(result.success){
            NSDictionary *data = [dict objectForKey:@"data"];
            page.total = [data intForKey:@"total"];
            NSMutableArray *arry = [data objectForKey:@"items"];
            for (NSDictionary *item  in arry) {
                Article *ent = [item toModel:[Article class]];
                ent.ID = [item stringForKey:@"id"];
                [page.items addObject:ent];
            }
        }
        
        if (success_callback) {
            success_callback(result,page);
        }
    } failure:failure_callback];
}
@end
