//
//  UserInfo.m
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "UserInfo.h"

@implementation UserInfo
#define kUserID @"kUserID"
static UserInfo* _shareUserInfo;
+(UserInfo*)getCurrentInstance
{
    if(_shareUserInfo==nil)
    {
        NSString * userid = [UserInfo getCurrentUserID];
        DLog(@"user id %@",userid);
        if(!stringIsNullOrEmpty(userid))
        {
            _shareUserInfo = [UserInfo FindByID:[NSString stringWithFormat:@"%@",userid]];
        }
    }
    return _shareUserInfo;
}

+(BOOL)hasLogin{
    NSString *userID = [UserInfo getCurrentUserID];
    DLog(@"%@",userID);
    return !stringIsNullOrEmpty(userID);
}

+(NSString*)getCurrentUserID
{
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    return [userDefaults stringForKey:kUserID];
}

+(void)setCurrentUserID:(NSString*)userid
{
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    [userDefaults setObject:userid forKey:kUserID];
    [userDefaults synchronize];
    _shareUserInfo = nil;
}

+(NSString *)getPrimaryKey
{
    return @"ID";
}

+(int)getTableVersion
{
    return 1;
}

-(id)init
{
    self = [super init];
    if(self){
        [self deafultValueForString];
    }
    return self;
}
@end
