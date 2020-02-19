//
//  CategoryService.m
//  ios
//
//  Created by Peter Zhan on 2017/10/16.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "CategoryService.h"

@implementation CategoryService
-(void)list:(APIArryHandle)success_callback failure:(APIErrorHandle)failure_callback{
    NSMutableDictionary *parameters = [self UserInfoparameters];
    [self GetRequestWithFunction:@"category/list" parameters:parameters success:^(APIResult *result, NSDictionary *dict) {
        
        NSMutableArray *list = [[NSMutableArray alloc] init];
        if(result.success){
            NSMutableArray *arry = [dict objectForKey:@"data"];
            for (NSDictionary *item  in arry) {
                Categorys *ent = [item toModel:[Categorys class]];
                ent.ID = [item stringForKey:@"id"];
                [list addObject:ent];
            }
        }
        
        if (success_callback) {
            success_callback(result,list);
        }
    } failure:failure_callback];
}
@end
