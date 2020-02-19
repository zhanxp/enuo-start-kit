//
//  AccountService.m
//  ios
//
//  Created by Peter Zhan on 2017/10/16.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "AccountService.h"

@implementation AccountService
-(void)login:(NSString*)username
    password:(NSString*)password
     success:(APIObjectHandle)success_callback
     failure:(APIErrorHandle)failure_callback{

    NSMutableDictionary *parameters = [self UserInfoparameters];
    [parameters setObject:username  forKey:@"username"];
    [parameters setObject:password  forKey:@"password"];
    
    [self PostRequestWithFunction:@"login" parameters:parameters success:^(APIResult *result, NSDictionary *dict) {
        
        UserInfo *info = nil;
        if(result.success){
            NSDictionary *data = [dict objectForKey:@"data"];
            info = [data toModel:[UserInfo class]];
            info.ID = [data stringForKey:@"id"];
        }
        
        if (success_callback) {
            success_callback(result,info);
        }
    } failure:failure_callback];
}
@end
