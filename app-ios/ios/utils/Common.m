//
//  Common.m
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "Common.h"
#import <objc/runtime.h>
#import "MBProgressHUD.h"
#import "Config.h"
#import "NSData+AES256.h"

#define Date_Location  @"zh_CN"

@implementation Common
BOOL stringIsNullOrEmpty(id object) {
    
    //登录号码校验,输入号码如果不是字符串或者输入内容小于0,即判定为空
    return ![object isKindOfClass:[NSString class]] || ![(NSString*)object length] > 0;
}

+ (UIImage*) createImageWithColor: (UIColor*) color
{
    CGRect rect= CGRectMake(0.0f, 0.0f, 1.0f, 1.0f);
    UIGraphicsBeginImageContext(rect.size);
    CGContextRef context = UIGraphicsGetCurrentContext();
    CGContextSetFillColorWithColor(context, [color CGColor]);
    CGContextFillRect(context, rect);
    UIImage*theImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return theImage;
}

+ (NSString *)dateToString:(NSDate *)date format:(NSString*)format
{
    if(date == nil)
        return @"";
    
    NSString *timeString=@"";
    NSDateFormatter* fmt = [[NSDateFormatter alloc] init];
    fmt.locale = [[NSLocale alloc] initWithLocaleIdentifier:Date_Location];
    fmt.dateFormat = format;
    timeString = [fmt stringFromDate:date];
    return timeString;
}

+(NSDate*) dateFromString:(NSString*)str format:(NSString*)format
{
    //[dateFormatter setDateFormat:@"yyyy'-'MM'-'dd'T'HH':'mm':'ss'Z'"];
    NSDateFormatter* dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setTimeStyle:NSDateFormatterFullStyle];
    [dateFormatter setDateFormat:format];
    NSString *datestr = [NSString stringWithFormat:@"%@",str];
    return [dateFormatter dateFromString:datestr];
}

+(NSString*)HloveyRC4:(NSString*)aInput key:(NSString*)aKey
{
    
    NSMutableArray *iS = [[NSMutableArray alloc] initWithCapacity:256];
    NSMutableArray *iK = [[NSMutableArray alloc] initWithCapacity:256];
    
    for (int i= 0; i<256; i++) {
        [iS addObject:[NSNumber numberWithInt:i]];
    }
    
    int j=1;
    
    for (short i=0; i<256; i++) {
        
        UniChar c = [aKey characterAtIndex:i%aKey.length];
        
        [iK addObject:[NSNumber numberWithChar:c]];
    }
    
    j=0;
    
    for (int i=0; i<255; i++) {
        int is = [[iS objectAtIndex:i] intValue];
        UniChar ik = (UniChar)[[iK objectAtIndex:i] charValue];
        
        j = (j + is + ik)%256;
        NSNumber *temp = [iS objectAtIndex:i];
        [iS replaceObjectAtIndex:i withObject:[iS objectAtIndex:j]];
        [iS replaceObjectAtIndex:j withObject:temp];
        
    }
    
    int i=0;
    j=0;
    
    NSString *result = aInput;
    
    for (short x=0; x<[aInput length]; x++) {
        i = (i+1)%256;
        
        int is = [[iS objectAtIndex:i] intValue];
        j = (j+is)%256;
        
        int is_i = [[iS objectAtIndex:i] intValue];
        int is_j = [[iS objectAtIndex:j] intValue];
        
        int t = (is_i+is_j) % 256;
        int iY = [[iS objectAtIndex:t] intValue];
        
        UniChar ch = (UniChar)[aInput characterAtIndex:x];
        UniChar ch_y = ch^iY;
        
        result = [result stringByReplacingCharactersInRange:NSMakeRange(x, 1) withString:[NSString stringWithCharacters:&ch_y length:1]];
    }
    
    //    [iS release];
    //    [iK release];
    
    return result;
}

+(MBProgressHUD*)showHUDAddedTo:(UIView*)view animated:(BOOL)animated{
    //    NSMutableArray *imagesArray = [[NSMutableArray alloc] init];
    //    for (int i=1; i<=67; i++) {
    //        NSString *img =  [NSString stringWithFormat:@"%d.png",i];
    //        [imagesArray addObject:[UIImage imageNamed:img]];
    //    }
    //
    //    UIImageView *animationImageView = [[UIImageView alloc]init];
    //    [animationImageView setFrame:CGRectMake(0, 0, 60, 60)];
    //    animationImageView.animationImages = imagesArray;//将序列帧数组赋给UIImageView的animationImages属性
    //    animationImageView.animationDuration = 3/10.0;//设置动画时间
    //    animationImageView.animationRepeatCount = 0;//设置动画次数 0 表示无限
    
    MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:view animated:animated];
    //    hud.mode = MBProgressHUDModeCustomView;
    //hud.color = [UIColor clearColor]; //RGBACOLOR(0, 0, 0, 0.2);
    //hud.margin = 0;
    //hud.customView = animationImageView;
    //[animationImageView startAnimating];//开始播放动画
    return hud;
}

+(void) makeToast:(NSString*)msg  view:(UIView*)view{
    if(view== nil){
        view = [Common getCurrentVC].view;
    }
    [view makeToast:msg duration:0.3 position:CSToastPositionTop];
}

+ (UIViewController *)getCurrentVC
{
    UIViewController *result = nil;
    
    UIWindow * window = [[UIApplication sharedApplication] keyWindow];
    if (window.windowLevel != UIWindowLevelNormal)
    {
        NSArray *windows = [[UIApplication sharedApplication] windows];
        for(UIWindow * tmpWin in windows)
        {
            if (tmpWin.windowLevel == UIWindowLevelNormal)
            {
                window = tmpWin;
                break;
            }
        }
    }
    
    UIView *frontView = [[window subviews] objectAtIndex:0];
    id nextResponder = [frontView nextResponder];
    
    if ([nextResponder isKindOfClass:[UIViewController class]])
        result = nextResponder;
    else
        result = window.rootViewController;
    
    return result;
}

# pragma mark Image Transformation
+ (UIImage *)scaledImage:(UIImage *)source toSize:(CGSize)size withQuality:(CGInterpolationQuality)quality
{
    if(kVersion<7){
        UIGraphicsBeginImageContext(size);
        [source drawInRect: CGRectMake(0, 0, size.width, size.height)];
        UIImage  *smallImage = UIGraphicsGetImageFromCurrentImageContext();
        UIGraphicsEndImageContext();
        return smallImage;
    }else{
        CGImageRef cgImage  = [Common newScaledImage:source.CGImage withOrientation:source.imageOrientation toSize:size withQuality:quality];
        UIImage * result = [UIImage imageWithCGImage:cgImage scale:1.0 orientation:UIImageOrientationUp];
        CGImageRelease(cgImage);
        return result;
    }
}


+ (CGImageRef)newScaledImage:(CGImageRef)source withOrientation:(UIImageOrientation)orientation toSize:(CGSize)size withQuality:(CGInterpolationQuality)quality
{
    CGSize srcSize = size;
    CGFloat rotation = 0.0;
    
    switch(orientation)
    {
        case UIImageOrientationUp: {
            rotation = 0;
        } break;
        case UIImageOrientationDown: {
            rotation = M_PI;
        } break;
        case UIImageOrientationLeft:{
            rotation = M_PI_2;
            srcSize = CGSizeMake(size.height, size.width);
        } break;
        case UIImageOrientationRight: {
            rotation = -M_PI_2;
            srcSize = CGSizeMake(size.height, size.width);
        } break;
        default:
            break;
    }
    
    CGContextRef context = CGBitmapContextCreate(NULL,
                                                 size.width,
                                                 size.height,
                                                 8, //CGImageGetBitsPerComponent(source),
                                                 0,
                                                 CGImageGetColorSpace(source),
                                                 CGImageGetBitmapInfo(source)
                                                 );
    
    //kCGImageAlphaNoneSkipFirst//
    
    CGContextSetInterpolationQuality(context, quality);
    CGContextTranslateCTM(context,  size.width/2,  size.height/2);
    CGContextRotateCTM(context,rotation);
    
    CGContextDrawImage(context, CGRectMake(-srcSize.width/2 ,
                                           -srcSize.height/2,
                                           srcSize.width,
                                           srcSize.height),
                       source);
    
    CGImageRef resultRef = CGBitmapContextCreateImage(context);
    CGContextRelease(context);
    
    return resultRef;
}

+ (CGImageRef)newTransformedImage:(CGAffineTransform)transform
                      sourceImage:(CGImageRef)sourceImage
                       sourceSize:(CGSize)sourceSize
                sourceOrientation:(UIImageOrientation)sourceOrientation
                      outputWidth:(CGFloat)outputWidth
                         cropSize:(CGSize)cropSize
                    imageViewSize:(CGSize)imageViewSize
{
    CGImageRef source = [Common newScaledImage:sourceImage
                               withOrientation:sourceOrientation
                                        toSize:sourceSize
                                   withQuality:kCGInterpolationLow];
    
    CGFloat aspect = cropSize.height/cropSize.width;
    CGSize outputSize = CGSizeMake(outputWidth, outputWidth*aspect);
    
    CGContextRef context = CGBitmapContextCreate(NULL,
                                                 outputSize.width,
                                                 outputSize.height,
                                                 CGImageGetBitsPerComponent(source),
                                                 0,
                                                 CGImageGetColorSpace(source),
                                                 CGImageGetBitmapInfo(source));
    CGContextSetFillColorWithColor(context,  [[UIColor clearColor] CGColor]);
    CGContextFillRect(context, CGRectMake(0, 0, outputSize.width, outputSize.height));
    
    CGAffineTransform uiCoords = CGAffineTransformMakeScale(outputSize.width/cropSize.width,
                                                            outputSize.height/cropSize.height);
    uiCoords = CGAffineTransformTranslate(uiCoords, cropSize.width/2.0, cropSize.height/2.0);
    uiCoords = CGAffineTransformScale(uiCoords, 1.0, -1.0);
    CGContextConcatCTM(context, uiCoords);
    
    CGContextConcatCTM(context, transform);
    CGContextScaleCTM(context, 1.0, -1.0);
    
    CGContextDrawImage(context, CGRectMake(-imageViewSize.width/2.0,
                                           -imageViewSize.height/2.0,
                                           imageViewSize.width,
                                           imageViewSize.height)
                       ,source);
    
    CGImageRef resultRef = CGBitmapContextCreateImage(context);
    CGContextRelease(context);
    CGImageRelease(source);
    return resultRef;
}

+(BOOL)isPureInt:(NSString*)string{
    NSScanner* scan = [NSScanner scannerWithString:string];
    int val;
    return[scan scanInt:&val] && [scan isAtEnd];
}

+(BOOL)isPureFloat:(NSString*)string{
    NSScanner* scan = [NSScanner scannerWithString:string];
    float val;
    BOOL h = [scan scanFloat:&val] && [scan isAtEnd];
    return h;
}

+(NSString*)toMoneyString:(NSNumber*)val{
    NSLocale *locale = [[NSLocale alloc] initWithLocaleIdentifier:@"zh_CN"];
    
    NSNumberFormatter *formatter = [[NSNumberFormatter alloc] init];
    formatter.numberStyle = kCFNumberFormatterCurrencyStyle;
    formatter.locale = locale;
    NSString *string = [formatter stringFromNumber:val];
    //string = [string stringByReplacingOccurrencesOfString:@"$" withString:@"￥"];
    return string;
}

+(NSString*)encryptString:(NSString*)input{
    return [NSData AES256EncryptWithPlainText:input];
}

+(NSString*)decryptString:(NSString*)input{
    return [NSData AES256DecryptWithCiphertext:input];
}
@end

@implementation NSObject(reflect)
- (void)deafultValueForString{
    unsigned int outCount, i;
    objc_property_t *properties = class_copyPropertyList([self class], &outCount);
    for (i = 0; i < outCount; i++) {
        objc_property_t property = properties[i];
        //DLog("%s %s\n", property_getName(property), property_getAttributes(property));
        NSString *attributes = [NSString stringWithFormat:@"%s", property_getAttributes(property)];
        if ([attributes rangeOfString:@"NSString"].location != NSNotFound) {
            NSString *key = [NSString stringWithFormat:@"%s", property_getName(property)];
            [self setValue:@"" forKey:key];
        }
    }
    free(properties);
}

- (NSArray*)propertyKeys{
    unsigned int outCount, i;
    objc_property_t *properties = class_copyPropertyList([self class], &outCount);
    NSMutableArray *keys = [[NSMutableArray alloc] initWithCapacity:outCount];
    for (i = 0; i < outCount; i++) {
        objc_property_t property = properties[i];
        NSString *propertyName = [[NSString alloc] initWithCString:property_getName(property)encoding:NSUTF8StringEncoding];
        [keys addObject:propertyName];
    }
    free(properties);
    return keys;
}

- (BOOL)reflectDataFromOtherObject:(NSObject*)dataSource{
    BOOL ret = NO;
    for (NSString *key in [self propertyKeys]) {
        if ([dataSource isKindOfClass:[NSDictionary class]]) {
            ret = ([dataSource valueForKey:key]==nil)?NO:YES;
        }
        else
        {
            ret = [dataSource respondsToSelector:NSSelectorFromString(key)];
        }
        if (ret) {
            id propertyValue = [dataSource valueForKey:key];
            //该值不为NSNULL，并且也不为nil
            if (![propertyValue isKindOfClass:[NSNull class]] && propertyValue!=nil) {
                [self setValue:propertyValue forKey:key];
            }
        }
    }
    return ret;
}
@end

@implementation NSString(URLEncoded)
-(NSString*)trim
{
    return [self stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
}

-(NSString *)URLEncodedString
{
    NSString *encodedString = (__bridge_transfer NSString*)CFURLCreateStringByAddingPercentEscapes(kCFAllocatorDefault,(CFStringRef)self,NULL,CFSTR(":/?#[]@!$ &'()*+,;=\"<>%{}|\\^~`"),CFStringConvertNSStringEncodingToEncoding(NSUTF8StringEncoding));
    return encodedString;
}
@end

@implementation UITableView (TableHelper)
- (void)setFooterLine{
    UIView *line = [[UIView alloc] initWithFrame:CGRectMake(0, 0, kScreenWidth, 0.5)];
    [line setBackgroundColor:kColor_Line];
    [self setTableFooterView:line];
}
@end

@implementation NSDictionary (DefaultValue)
-(NSString*) stringForKey:(NSString*)key
{
    if(![[self allKeys] containsObject:key])
        return @"";
    
    id val = [self objectForKey:key];
    if(!stringIsNullOrEmpty(val) || [val isKindOfClass:[NSNumber class]])
    {
        return [NSString stringWithFormat:@"%@",val];
    }
    return @"";
}

-(int) intForKey:(NSString*)key
{
    if(![[self allKeys] containsObject:key])
        return 0;
    
    id val = [self objectForKey:key];
    if(nil == val || val == [NSNull null])
        return 0;
    
    return [val intValue];
}

-(double) doubleForKey:(NSString*)key{
    if(![[self allKeys] containsObject:key])
        return 0;
    
    id val = [self objectForKey:key];
    if(nil == val || val == [NSNull null])
        return 0;
    return  [val doubleValue];
}

-(NSDate*) dateForKey:(NSString*)key
{
    if(![[self allKeys] containsObject:key])
        return [NSDate date];
    
    id val = [self objectForKey:key];
    if(nil == val || val == [NSNull null])
        return [NSDate date];
    NSDateFormatter* dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setTimeStyle:NSDateFormatterFullStyle];
    [dateFormatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    NSString *datestr = [NSString stringWithFormat:@"%@",val];
    return [dateFormatter dateFromString:datestr];
}

-(bool) boolForKey:(NSString*)key
{
    if(![[self allKeys] containsObject:key])
        return NO;
    
    id val = [self objectForKey:key];
    if(nil == val|| val == [NSNull null])
        return NO;
    return  [val boolValue];
}
@end
