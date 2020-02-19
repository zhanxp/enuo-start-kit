//
//  constants.h
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#ifndef constants_h
#define constants_h


//did not show nslog on release
#ifndef __OPTIMIZE__
#define NSLog(...) NSLog(__VA_ARGS__)
#else
#define NSLog(...) {}
#endif

// DLog is almost a drop-in replacement for NSLog
#ifdef DEBUG
#   define DLog(fmt, ...) NSLog((@"%s [Line %d] " fmt), __PRETTY_FUNCTION__, __LINE__, ##__VA_ARGS__);
#   define debugFlag @"0"
#else
#   define DLog(...)
#   define debugFlag @"1"
#endif

//ALog always displays output regardless of the DEBUG setting
#define ALog(fmt, ...) NSLog((@"%s [Line %d] " fmt), __PRETTY_FUNCTION__, __LINE__, ##__VA_ARGS__);


#define kScreenHeight [UIScreen mainScreen].bounds.size.height
#define kScreenWidth  [UIScreen mainScreen].bounds.size.width
#define kVersion [[UIDevice currentDevice].systemVersion floatValue]

#define RGBCOLOR(r,g,b)     [UIColor colorWithRed:(r)/255.0f green:(g)/255.0f blue:(b)/255.0f alpha:1]
#define RGBACOLOR(r,g,b,a)  [UIColor colorWithRed:(r)/255.0f green:(g)/255.0f blue:(b)/255.0f \
alpha:(a)]

#define ShareAppDelegate       ((AppDelegate *)[[UIApplication sharedApplication] delegate])

#define kCOLOR_ViewBackground   RGBCOLOR(234,233,235)
#define kCOLOR_StatausBar       RGBCOLOR(55, 124, 255)
#define kColor_Line   RGBCOLOR(200, 199, 204)

// View 坐标(x,y)和宽高(width,height)
#define X(v)                    (v).frame.origin.x
#define Y(v)                    (v).frame.origin.y
#define WIDTH(v)                (v).frame.size.width
#define HEIGHT(v)               (v).frame.size.height

#define MinX(v)                 CGRectGetMinX((v).frame)
#define MinY(v)                 CGRectGetMinY((v).frame)

#define MidX(v)                 CGRectGetMidX((v).frame)
#define MidY(v)                 CGRectGetMidY((v).frame)

#define MaxX(v)                 CGRectGetMaxX((v).frame)
#define MaxY(v)                 CGRectGetMaxY((v).frame)

#define RECT_CHANGE_x(v,x)          CGRectMake(x, Y(v), WIDTH(v), HEIGHT(v))
#define RECT_CHANGE_y(v,y)          CGRectMake(X(v), y, WIDTH(v), HEIGHT(v))
#define RECT_CHANGE_point(v,x,y)    CGRectMake(x, y, WIDTH(v), HEIGHT(v))
#define RECT_CHANGE_width(v,w)      CGRectMake(X(v), Y(v), w, HEIGHT(v))
#define RECT_CHANGE_height(v,h)     CGRectMake(X(v), Y(v), WIDTH(v), h)
#define RECT_CHANGE_size(v,w,h)     CGRectMake(X(v), Y(v), w, h)


#endif /* constants_h */

