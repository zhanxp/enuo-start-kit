//
//  BaseService.m
//  ios
//
//  Created by Peter Zhan on 2017/10/16.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "BaseService.h"
#import "Config.h"
#import "AFNetworking.h"
#import "YYJSONHelper.h"
#import "NSString+MD5.h"
#import "NSData+AES256.h"

@implementation BaseService
-(NSMutableDictionary*)UserInfoparameters
{
    UserInfo *uinfo = [UserInfo getCurrentInstance];
    NSMutableDictionary *parameters = [[NSMutableDictionary alloc] init];
    if(!stringIsNullOrEmpty(uinfo.ID))
    {
        [parameters setObject:[NSString stringWithFormat:@"%@",uinfo.ID] forKey:@"userID"];
    }
    
    if(!stringIsNullOrEmpty(uinfo.ticket))
    {
        [parameters setObject:[NSString stringWithFormat:@"%@",uinfo.ticket] forKey:@"ticket"];
    }
    
    AppInfo *appinfo = [AppInfo shareAppInfo];
    [parameters setObject:[NSString stringWithFormat:@"%d", appinfo.versionCode] forKey:@"versionCode"];
    [parameters setObject:[NSString stringWithFormat:@"%d", appinfo.clientType] forKey:@"clientType"];
    return parameters;
}

-(NSMutableDictionary*)SignParameters:(NSMutableDictionary*)parameters{
    
    if(![[parameters allKeys] containsObject:@"timeSpan"]){
        NSString *key = @"timeSpan";
        NSString *timeSpan = [Common dateToString:[NSDate date] format:@"yyyy-MM-dd HH:mm:ss"];
        [parameters setObject:timeSpan forKey:key];
    }
    
    NSMutableArray *stringArray = [NSMutableArray arrayWithArray:parameters.allKeys];
    [stringArray sortUsingComparator: ^NSComparisonResult (NSString *str1, NSString *str2) {
        return [str1 compare:str2];
    }];
    
    
    NSString *url = @"";
    NSMutableDictionary *result = [[NSMutableDictionary alloc] init];
    for (NSString* key in stringArray) {
        id val = [parameters objectForKey:key];
        [result setObject:val forKey:key];
        NSString* itemUrl = [NSString stringWithFormat:@"%@=%@",key,[NSString stringWithFormat:@"%@",val]];
        url = [NSString stringWithFormat:@"%@%@&",url,itemUrl];
    }
    
    AppInfo *appinfo = [AppInfo shareAppInfo];
    url = [NSString stringWithFormat:@"%@%@",url,appinfo.deviceId];
    
    //add sign
    NSString *sign = [url MD5];
    NSData *signData =  [sign dataUsingEncoding:NSUTF8StringEncoding];
    NSString *singStr = [NSData AESBase64Encode:signData];
    [result setObject:singStr forKey:@"sign"];
    return result;
}


#pragma mark - url 签名格式化
-(NSString*)SignUrl:(NSString*)url{
    
    //将url切割成数组
    NSArray *separatedURLArray = [url componentsSeparatedByString:@"?"];
    NSString *baseURL = separatedURLArray.firstObject;
    
    AppInfo *appinfo = [AppInfo shareAppInfo];
    NSString *timeSpan = [Common dateToString:[NSDate date] format:@"yyyy-MM-dd HH:mm:ss"];
    NSMutableDictionary *parameters = [NSMutableDictionary dictionary];
    
    if(separatedURLArray.count == 2){
        for (NSString *param in [separatedURLArray.lastObject componentsSeparatedByString:@"&"]) {
            NSArray *elts = [param componentsSeparatedByString:@"="];
            if([elts count] < 2) continue;
            [parameters setObject:[elts lastObject] forKey:[elts firstObject]];
        }
    }
    
    if(![[parameters allKeys] containsObject:@"timeSpan"]){
        [parameters setObject:timeSpan forKey:@"timeSpan"];
    }
    
    UserInfo *uinfo = [UserInfo getCurrentInstance];
    if(uinfo!=nil && ![[parameters allKeys] containsObject:@"ticket"]){
        [parameters setObject:uinfo.ticket forKey:@"ticket"];
    }
    
    if ( uinfo!= nil && ![[parameters allKeys] containsObject:@"userID"]) {
        [parameters setObject:uinfo.ID forKey:@"userID"];
    }
    
    if(![[parameters allKeys] containsObject:@"format"]){
        [parameters setObject:@"html" forKey:@"format"];
    }
    
    //sort
    NSMutableArray *stringArray = [NSMutableArray arrayWithArray:parameters.allKeys];
    [stringArray sortUsingComparator: ^NSComparisonResult (NSString *str1, NSString *str2) {
        return [str1 compare:str2];
    }];
    
    NSString *urlString = @"";
    for (NSString* key in stringArray) {
        id val = [parameters objectForKey:key];
        NSString* itemUrl = [NSString stringWithFormat:@"%@=%@",key,[NSString stringWithFormat:@"%@",val]];
        urlString = [NSString stringWithFormat:@"%@%@&",urlString,itemUrl];
    }
    
    urlString = [NSString stringWithFormat:@"%@%@",urlString,appinfo.deviceId];
    NSString *sign = [urlString MD5];
    NSData *signData =  [sign dataUsingEncoding:NSUTF8StringEncoding];
    NSString *singStr = [NSData AESBase64Encode:signData];
    [parameters setObject:singStr forKey:@"sign"];
    [stringArray addObject:@"sign"];
    
    NSString *completeURL = @"";
    for (NSString* key in stringArray) {
        id val = [parameters objectForKey:key];
        NSString *valStr = [NSString stringWithFormat:@"%@",val];
        NSString* itemUrl = [NSString stringWithFormat:@"%@=%@",key,[valStr URLEncodedString]];
        completeURL = [NSString stringWithFormat:@"%@%@&",completeURL,itemUrl];
    }
    
    completeURL = [NSString stringWithFormat:@"%@?%@",baseURL,completeURL];
    DLog(@"%@",completeURL);
    
    return completeURL;
}

-(void)PostRequestWithFunction:(NSString*)function
                    parameters:(NSMutableDictionary*)paras
                       success:(APISuccessHandle)success_callback
                       failure:(APIErrorHandle)failure_callback
{
    NSMutableDictionary *parameters = [self SignParameters:paras];
    NSString *url = [NSString stringWithFormat:@"%@%@",API_ROOT,function];
    
    ////// debug output //////
    if(parameters!= nil && parameters.count > 0)
    {
        NSMutableString *sb = [[NSMutableString alloc] init];
        [sb appendFormat:@"%@?",url];
        for (id key in [parameters allKeys]) {
            [sb appendFormat:@"%@=%@&",key,[parameters objectForKey:key]];
        }
        DLog(@"%@",sb);
    }
    else
    {
        DLog(@"%@",url);
    }
    
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    manager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"application/json", @"text/json", @"text/javascript",@"text/plain",@"text/html", nil];
    
    //发送请求
    [manager POST:url parameters:parameters
          success:^(AFHTTPRequestOperation *operation, id responseObject) {
              APIResult* result =  [responseObject toModel:[APIResult class]];
              //             if(!result.result && result.errorCode == KErrorCode_NOT_LOGIN){
              //                 [ShareAppDelegate fnShowLoginViewController];
              //             }
              if(success_callback)
              {
                  success_callback(result,responseObject);
              }
          } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
              DLog(@"Error: %@", [error description]);
              if(failure_callback)
              {
                  failure_callback(error);
              }
              [Common makeToast:@"服务器通信异常，请检查您的网络设置，稍后再试！" view:nil];
          }];
}

-(void)GetRequestWithFunction:(NSString*)function
                   parameters:(NSMutableDictionary*)paras
                      success:(APISuccessHandle)success_callback
                      failure:(APIErrorHandle)failure_callback
{
    NSMutableDictionary *parameters = [self SignParameters:paras];
    
    //准备 url的前半部,服务器地址和路径
    NSString *url = [NSString stringWithFormat:@"%@%@",API_ROOT, function];
    
    ////// debug output //////
    if(parameters!= nil && parameters.count > 0)
    {
        NSMutableString *sb = [[NSMutableString alloc] init];
        [sb appendFormat:@"%@?",url];
        for (id key in [parameters allKeys]) {
            [sb appendFormat:@"%@=%@&",key,[parameters objectForKey:key]];
        }
        DLog(@"%@",sb);
    }
    else
    {
        DLog(@"%@",url);
    }
    
    //访问接口,进行数据请求
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    manager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"application/json", @"text/json", @"text/javascript",@"text/plain",@"text/html", nil];
    
    //发送请求
    [manager GET:url parameters:parameters
         success:^(AFHTTPRequestOperation *operation, id responseObject) {
             
             APIResult* result =  [responseObject toModel:[APIResult class]];
             if(!result.success && (result.code == KErrorCode_NOT_LOGIN || result.code == KErrorCode_NOT_VALID)){
                 [UserInfo setCurrentUserID:@""];
                 //                 [ShareAppDelegate fnShowLoginViewController];
             }
             if(success_callback)
             {
                 success_callback(result,responseObject);
             }
         } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
             DLog(@"Error: %@", [error description]);
             if(failure_callback)
             {
                 failure_callback(error);
             }
             [Common makeToast:@"服务器通信异常，请检查您的网络设置，稍后再试！" view:nil];
         }];
}

- (void)FileRequestWithFunction:(NSString *)function
                     parameters:(NSMutableDictionary *)paras
                       filedata:(NSData *)filedata
                       fileName:(NSString *)fileName
                       mimeType:(NSString *)mimeType
                   successblock:(APISuccessHandle)success_callback
                     errorblock:(APIErrorHandle)failure_callback{
    
    NSMutableDictionary *parameters = [self SignParameters:paras];
    NSString *url = [NSString stringWithFormat:@"%@%@",API_ROOT, function];
    
    ////// debug output //////
    if(parameters!= nil && parameters.count > 0)
    {
        NSMutableString *sb = [[NSMutableString alloc] init];
        [sb appendFormat:@"%@?",url];
        for (id key in [parameters allKeys]) {
            [sb appendFormat:@"%@=%@&",key,[parameters objectForKey:key]];
        }
        DLog(@"%@",sb);
    }
    else
    {
        DLog(@"%@",url);
    }
    
    NSArray *nameArry = [fileName componentsSeparatedByString:@"."];
    NSString *name = nameArry[0];
    
    AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager manager];
    manager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"application/json", @"text/json", @"text/javascript",@"text/plain",@"text/html", nil];
    [manager POST:url parameters:parameters constructingBodyWithBlock:^(id<AFMultipartFormData> formData) {
        if(filedata!=nil){
            [formData appendPartWithFileData:filedata name:name fileName:fileName mimeType:mimeType];
        }
    } success:^(AFHTTPRequestOperation *operation, id responseObject) {
        APIResult* result =  [responseObject toModel:[APIResult class]];
        if(!result.success && result.code == KErrorCode_NOT_LOGIN){
            //[ShareAppDelegate fnShowLoginViewController];
        }
        
        if(success_callback)
        {
            success_callback(result,responseObject);
        }
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        DLog(@"Error: %@", [error description]);
        if(failure_callback)
        {
            failure_callback(error);
        }
        [Common makeToast:@"服务器通信异常，请检查您的网络设置，稍后再试！" view:nil];
    }];
}

-(void)FileDownLoad:(NSString*)url
          localPath:(NSURL*)localPath
            success:(APISuccessHandle)success_callback
         errorblock:(APIErrorHandle)failure_callback{
    
    NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration defaultSessionConfiguration];
    AFURLSessionManager *manager = [[AFURLSessionManager alloc] initWithSessionConfiguration:configuration];
    
    NSURL *URL = [NSURL URLWithString:url];
    NSURLRequest *request = [NSURLRequest requestWithURL:URL];
    
    NSURLSessionDownloadTask *downloadTask = [manager downloadTaskWithRequest:request progress:nil destination:^NSURL *(NSURL *targetPath, NSURLResponse *response) {
        //        NSURL *documentsDirectoryURL = [[NSFileManager defaultManager] URLForDirectory:NSDocumentDirectory inDomain:NSUserDomainMask appropriateForURL:nil create:NO error:nil];
        //        return [documentsDirectoryURL URLByAppendingPathComponent:[response suggestedFilename]];
        return localPath;
    } completionHandler:^(NSURLResponse *response, NSURL *filePath, NSError *error) {
        NSLog(@"File downloaded to: %@", filePath);
        APIResult *result = [[APIResult alloc] init];
        if(error!= nil){
            result.success = NO;
        }
        else{
            result.success = YES;
        }
        if(success_callback){
            success_callback(result,nil);
        }
    }];
    [downloadTask resume];
}



/**
 *  @author Jakey
 *
 *  @brief  下载文件
 *
 *  @param paramDic   附加post参数
 *  @param requestURL 请求地址
 *  @param savedPath  保存 在磁盘的位置
 *  @param success    下载成功回调
 *  @param failure    下载失败回调
 *  @param progress   实时下载进度回调
 */
- (void)downloadFileWithOption:(NSDictionary *)paramDic
                 withInferface:(NSString*)requestURL
                     savedPath:(NSString*)savedPath
               downloadSuccess:(void (^)(AFHTTPRequestOperation *operation, id responseObject))success
               downloadFailure:(void (^)(AFHTTPRequestOperation *operation, NSError *error))failure
                      progress:(void (^)(float progress))progress

{
    
    //沙盒路径
    //NSString *savedPath = [NSHomeDirectory() stringByAppendingString:@"/Documents/xxx.zip"];
    AFHTTPRequestSerializer *serializer = [AFHTTPRequestSerializer serializer];
    NSMutableURLRequest *request =[serializer requestWithMethod:@"POST" URLString:requestURL parameters:paramDic error:nil];
    AFHTTPRequestOperation *operation = [[AFHTTPRequestOperation alloc]initWithRequest:request];
    [operation setOutputStream:[NSOutputStream outputStreamToFileAtPath:savedPath append:NO]];
    [operation setDownloadProgressBlock:^(NSUInteger bytesRead, long long totalBytesRead, long long totalBytesExpectedToRead) {
        float p = (float)totalBytesRead / totalBytesExpectedToRead;
        progress(p);
        NSLog(@"download：%f", (float)totalBytesRead / totalBytesExpectedToRead);
        
    }];
    
    [operation setCompletionBlockWithSuccess:^(AFHTTPRequestOperation *operation, id responseObject) {
        success(operation,responseObject);
    } failure:^(AFHTTPRequestOperation *operation, NSError *error) {
        success(operation,error);
    }];
    
    [operation start];
    
}

@end
