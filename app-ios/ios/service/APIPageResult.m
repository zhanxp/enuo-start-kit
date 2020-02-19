//
//  APIPageResult.m
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "APIPageResult.h"

@implementation APIPageResult
-(id)init
{
    self = [super init];
    if(self)
    {
        self.items = [[NSMutableArray alloc] init];
    }
    return self;
}
@end
