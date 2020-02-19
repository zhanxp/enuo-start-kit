//
//  BaseService.h
//  ios
//
//  Created by Peter Zhan on 2017/10/16.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "YYJSONHelper.h"

@class AFHTTPRequestOperation;

typedef void(^APIObjectHandle)(APIResult* result,NSObject *obj);
typedef void(^APIArryHandle)(APIResult* result,NSMutableArray *obj);
typedef void(^APIPageDataHandle)(APIResult* result,APIPageResult *obj);
typedef void(^APISuccessHandle)(APIResult* result,NSDictionary *dict);
typedef void(^APIErrorHandle)(NSError *error);

@interface BaseService : NSObject

-(NSMutableDictionary*)UserInfoparameters;

// url 签名格式化
-(NSString*)SignUrl:(NSString*)url;

//get 方法
-(void)GetRequestWithFunction:(NSString*)function
                   parameters:(NSMutableDictionary*)parameters
                      success:(APISuccessHandle)success
                      failure:(APIErrorHandle)failure;

//post 方法
-(void)PostRequestWithFunction:(NSString*)function
                    parameters:(NSMutableDictionary*)parameters
                       success:(APISuccessHandle)success_callback
                       failure:(APIErrorHandle)failure_callback;

//文件上传方法
-(void)FileRequestWithFunction:(NSString *)function
                    parameters:(NSMutableDictionary *)parameters
                      filedata:(NSData *)filedata
                      fileName:(NSString *)fileName
                      mimeType:(NSString *)mimeType
                  successblock:(APISuccessHandle)success_callback
                    errorblock:(APIErrorHandle)failure_callback;

//文件下载方法
-(void)FileDownLoad:(NSString*)url
          localPath:(NSURL*)localPath
            success:(APISuccessHandle)success_callback
         errorblock:(APIErrorHandle)failure_callback;

// 文件下载（chat）
- (void)downloadFileWithOption:(NSDictionary *)paramDic
                 withInferface:(NSString*)requestURL
                     savedPath:(NSString*)savedPath
               downloadSuccess:(void (^)(AFHTTPRequestOperation *operation, id responseObject))success
               downloadFailure:(void (^)(AFHTTPRequestOperation *operation, NSError *error))failure
                      progress:(void (^)(float progress))progress;

@end
