//
//  AppInfo.m
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "AppInfo.h"
#import "OpenUDID.h"

@interface AppInfo(){
    int _clientType;
    int _versionCode;
    NSString* _versionName;
    NSString* _udid;
}
@end

@implementation AppInfo
static AppInfo* _shareAppInfo;
+(AppInfo*) shareAppInfo
{
    if(_shareAppInfo==nil)
    {
        _shareAppInfo = [AppInfo FindByID:@"APP"];
        if(_shareAppInfo==nil)
        {
            _shareAppInfo = [[AppInfo alloc] init];
            _shareAppInfo.ID = @"APP";
            _shareAppInfo.isFirst = YES;
            _shareAppInfo.appTime = [NSDate date];
            [_shareAppInfo saveOrUpdateDB];
        }
    }
    return _shareAppInfo;
}

-(int)clientType{
    if(_clientType<=0){
        NSDictionary *infoDic = [[NSBundle mainBundle] infoDictionary];
        _clientType = [[infoDic objectForKey:@"ClientType"] intValue];
    }
    return  _clientType;
}

-(NSString*)deviceId{
    if(stringIsNullOrEmpty(_udid)){
        _udid = [OpenUDID value];
        _udid = [_udid stringByReplacingOccurrencesOfString:@"-" withString:@""];
    }
    return _udid;
}

-(int)versionCode{
    if(_versionCode<=0){
        NSDictionary *infoDic = [[NSBundle mainBundle] infoDictionary];
        _versionCode = [[infoDic objectForKey:@"CFBundleVersion"] intValue];
    }
    return  _versionCode;
}

-(NSString*)versionName{
    if(stringIsNullOrEmpty(_versionName)){
        NSDictionary *infoDic = [[NSBundle mainBundle] infoDictionary];
        _versionName = [infoDic objectForKey:@"CFBundleShortVersionString"];
    }
    return  _versionName;
}

+(NSString *)getPrimaryKey
{
    return @"ID";
}

+(int)getTableVersion
{
    return 1;
}
@end
