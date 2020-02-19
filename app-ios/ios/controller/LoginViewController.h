//
//  LoginViewController.h
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface LoginViewController : UITableViewController
@property (strong, nonatomic) IBOutlet UITextField *txtUserName;
@property (strong, nonatomic) IBOutlet UITextField *txtPassword;
- (IBAction)onLoginClick:(id)sender;

@end
