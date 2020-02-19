//
//  APIPageResult.h
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface APIPageResult : NSObject
@property (nonatomic,assign) NSInteger pageCount;
@property (nonatomic,assign) NSInteger pageIndex;
@property (nonatomic,assign) NSInteger pageSize;
@property (nonatomic,strong) NSMutableArray* items;
@property (nonatomic,assign) NSInteger total;

@property (nonatomic, copy) NSString *startID;// 聊天记录开始id
@property (nonatomic, copy) NSString *nextID;// 下一次起始id
@end
