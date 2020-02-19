//
//  SecondViewController.m
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "AboutViewController.h"

@interface AboutViewController ()
- (IBAction)btnLogoutClick:(id)sender;

@end

@implementation AboutViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (IBAction)btnLogoutClick:(id)sender {
    [UserInfo setCurrentUserID:nil];
    [ShareAppDelegate fnShowLoginViewController];
}
@end
