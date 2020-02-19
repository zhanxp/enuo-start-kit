//
//  Config.h
//  ios
//
//  Created by Peter Zhan on 2017/10/18.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#ifndef Config_h
#define Config_h

#define API_ROOT @"http://127.0.0.1:8090/api/"

//网络请求参数key值
#define  API_REQUEST_KEY_PAGE_INDEX  @"pageIndex"
#define  API_REQUEST_KEY_PAGE_SIZE   @"pageSize"
#define  API_REQUEST_KEY_TOKEN   @"ticket"
#define  API_REQUEST_KEY_USER_ID   @"userID"
#define  API_REQUEST_KEY_USER_NAME   @"userName"
#define  API_REQUEST_KEY_USER_PWD   @"userPassword"

//网络响应数据解析key值
#define  API_RESPONSE_KEY_DATA   @"data"
#define  API_RESPONSE_KEY_DATA_RESULT   @"success"
#define  API_RESPONSE_KEY_PAGE_INDEX   @"pageIndex"
#define  API_RESPONSE_KEY_PAGE_SIZE   @"pageSize"
#define  API_RESPONSE_KEY_PAGE_DATA_SIZE   @"total
#define  API_RESPONSE_KEY_PAGE_COUNT   @"pageCount"
#define  API_PASSWORD                  @"2df561fb-48d5-4bb9-af86-b9c264783593"

//数据规格
#define API_DATA_PAGE_SIZE = 6

#endif /* Config_h */
