//
//  NSObject+LKDB.h
//  fitness
//
//  Created by Peter Zhan on 15-4-7.
//  Copyright (c) 2015年 com.raincoder. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSObject(LKDB)
- (BOOL)saveOrUpdateDB;
+ (instancetype)FindByID:(NSString *)pkid;
@end



