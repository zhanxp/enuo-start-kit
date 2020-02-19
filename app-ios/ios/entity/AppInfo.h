//
//  AppInfo.h
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface AppInfo : NSObject
@property (nonatomic,copy) NSString* ID;
@property (nonatomic,strong) NSDate* lastCodeTime;
@property (nonatomic,strong) NSDate* appTime;
@property (nonatomic,assign) BOOL isFirst;

-(int)versionCode;
-(NSString*)versionName;

-(int)clientType;
-(NSString*)deviceId;

+(AppInfo*) shareAppInfo;
@end
