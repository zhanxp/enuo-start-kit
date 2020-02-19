//
//  UserInfo.h
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface UserInfo : NSObject
@property (nonatomic,copy) NSString* ID;
@property (nonatomic,copy) NSString* ticket;
@property (nonatomic,copy) NSString* name;

+(UserInfo*)getCurrentInstance;
+(NSString*)getCurrentUserID;
+(BOOL)hasLogin;
+(void)setCurrentUserID:(NSString*)userid;

@end
