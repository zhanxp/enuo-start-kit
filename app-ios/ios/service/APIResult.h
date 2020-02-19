//
//  APIResult.h
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef enum{
    KErrorCode_NONE = 200,                // 没有错误
    KErrorCode_NOT_LOGIN = 401,        // 没有登录
    KErrorCode_NOT_VALID = 402,        // 安全验证失败
    KErrorCode_ERROR = 505,            // 网络错误
    KErrorCode_DENIED = 403,            // 没有权限
    KErrorCode_NOT_COMPANY = 4031,        // 没有所属公司
    KErrorCode_ALLOW_QUOTATION = 4032,    // 不允许参与报价（具体原因自定义）
} KerrorCodeType;

@interface APIResult : NSObject
@property (nonatomic,assign) BOOL success;
@property (nonatomic,assign) NSInteger code;//错误信息
@property (nonatomic,copy) NSString* msg;
@property (nonatomic,copy) NSString* title;
@end
