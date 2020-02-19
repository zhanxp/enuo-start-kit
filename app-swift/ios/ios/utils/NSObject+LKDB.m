//
//  NSObject+LKDB.m
//  fitness
//
//  Created by Peter Zhan on 2017/10/16.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "NSObject+LKDB.h"
#import "LKDBHelper.h"

@implementation NSObject (LKDB)

- (BOOL)stringIsNullOrEmpty:(id) object {
    return ![object isKindOfClass:[NSString class]] || ![(NSString*)object length] > 0;
}

- (BOOL)saveOrUpdateDB
{
    if([self isExistsFromDB])
    {
        return [self.class updateToDB:self where:nil];
    }
    else
    {
        return [self saveToDB];
    }
}

+ (instancetype)FindByID:(NSString *)pvalue
{
    if([self stringIsNullOrEmpty:pvalue])
    {
        return nil;
    }
    
    LKModelInfos *infos = [self getModelInfos];
    NSArray *primaryKeys = infos.primaryKeys;
    NSString *pwhere = @"";
    if (primaryKeys.count > 0) {
        NSString *pk = [primaryKeys objectAtIndex:0];
        if ([LKDBUtils checkStringIsEmpty:pk] == NO) {
            LKDBProperty *property = [infos objectWithSqlColumnName:pk];
            pwhere = [NSString stringWithFormat:@" %@='%@' ", property.sqlColumnName, pvalue];
        }
    }
    
    if([self stringIsNullOrEmpty:pwhere])
    {
        return nil;
    }
    
    return [[self getUsingLKDBHelper] searchSingle:self where:pwhere orderBy:nil];
}
@end


