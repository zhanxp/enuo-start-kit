//
//  Common.h
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import <Foundation/Foundation.h>

@class MBProgressHUD;
@interface Common : NSObject
BOOL stringIsNullOrEmpty(id object);
+(UIImage*) createImageWithColor: (UIColor*) color;
+(NSString *)dateToString:(NSDate *)date format:(NSString*)format;
+(NSDate*) dateFromString:(NSString*)str format:(NSString*)format;
+(NSString*)HloveyRC4:(NSString*)aInput key:(NSString*)aKey;
+(MBProgressHUD*)showHUDAddedTo:(UIView*)view animated:(BOOL)animated;
+(void)makeToast:(NSString*)msg view:(UIView*)view;
+(UIViewController *)getCurrentVC;
+(BOOL)isPureInt:(NSString*)string;
+(BOOL)isPureFloat:(NSString*)string;
+(NSString*)toMoneyString:(NSNumber*)val;
+(UIImage *)scaledImage:(UIImage *)source toSize:(CGSize)size withQuality:(CGInterpolationQuality)quality;
+(CGImageRef)newScaledImage:(CGImageRef)source withOrientation:(UIImageOrientation)orientation toSize:(CGSize)size withQuality:(CGInterpolationQuality)quality;
+(CGImageRef)newTransformedImage:(CGAffineTransform)transform
                     sourceImage:(CGImageRef)sourceImage
                      sourceSize:(CGSize)sourceSize
               sourceOrientation:(UIImageOrientation)sourceOrientation
                     outputWidth:(CGFloat)outputWidth
                        cropSize:(CGSize)cropSize
                   imageViewSize:(CGSize)imageViewSize;
+(NSString*)encryptString:(NSString*)input;
+(NSString*)decryptString:(NSString*)input;

@end

@interface NSObject(reflect)
- (void)deafultValueForString;
- (NSArray*)propertyKeys;
- (BOOL)reflectDataFromOtherObject:(NSObject*)dataSource;
@end

@interface NSString(URLEncoded)
-(NSString*)trim;
-(NSString *)URLEncodedString;
@end

@interface NSDictionary (DefaultValue)
-(NSString*) stringForKey:(NSString*)key;
-(int) intForKey:(NSString*)key;
-(double) doubleForKey:(NSString*)key;
-(NSDate*) dateForKey:(NSString*)key;
-(bool) boolForKey:(NSString*)key;
//-(long long) longlongForKey:(NSString*)key;
@end


@interface UITableView (TableHelper)
- (void)setFooterLine;
@end
