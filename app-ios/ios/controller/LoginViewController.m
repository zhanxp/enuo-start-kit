//
//  LoginViewController.m
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "LoginViewController.h"
#import "AccountService.h"

@interface LoginViewController ()
@property (nonatomic,strong) AccountService* accountService;
@end

@implementation LoginViewController

- (AccountService*) accountService{
    if(_accountService==nil){
        _accountService = [[AccountService alloc] init];
    }
    return _accountService;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    

//    [self.btnLogin addTarget:self action:@selector(onLogin) forControlEvents:UIControlEventTouchUpInside];
    
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

//
//#pragma mark - Table view data source
//
//- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
//#warning Incomplete implementation, return the number of sections
//    return 0;
//}
//
//- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
//#warning Incomplete implementation, return the number of rows
//    return 0;
//}

/*
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:<#@"reuseIdentifier"#> forIndexPath:indexPath];
    
    // Configure the cell...
    
    return cell;
}
*/

/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/

/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    } else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/

/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath {
}
*/

/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (IBAction)onLoginClick:(id)sender {
    NSString *username = self.txtUserName.text;
    NSString *password = self.txtPassword.text;
    if(stringIsNullOrEmpty(username)){
        [self.view makeToast:@"请输入用户名" duration:0.3 position:CSToastPositionTop ];
        return;
    }
    if(stringIsNullOrEmpty(password)){
        [self.view makeToast:@"请输入密码" duration:0.3 position:CSToastPositionTop ];
        return;
    }
    MBProgressHUD *hud = [Common showHUDAddedTo:self.view animated:YES];
    //__weak typeof(self) vc = self;
    [self.accountService login:username password:password success:^(APIResult *result, NSObject *obj) {
        if(result.success){
            UserInfo *ent = (UserInfo*)obj;
            [ent saveOrUpdateDB];
            [UserInfo setCurrentUserID:ent.ID];
            [ShareAppDelegate fnShowMainViewController];
        }else{
            [self.view makeToast:result.msg duration:0.3 position:CSToastPositionTop ];
        }
        [hud hideAnimated:NO];
    } failure:^(NSError *error) {
        [hud hideAnimated:NO];
    }];
}
@end
