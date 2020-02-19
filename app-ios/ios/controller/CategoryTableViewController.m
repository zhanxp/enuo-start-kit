//
//  CategoryTableViewController.m
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "CategoryTableViewController.h"
#import "CategoryService.h"
@interface CategoryTableViewController ()
@property (strong,nonatomic) CategoryService* categoryService;
@property (strong,nonatomic) NSMutableArray* dataList;
@end

@implementation CategoryTableViewController
- (CategoryService*) categoryService{
    if(_categoryService==nil){
        _categoryService = [[CategoryService alloc] init];
    }
    return _categoryService;
}

- (NSMutableArray*) dataList{
    if(_dataList==nil){
        _dataList = [[NSMutableArray alloc] init];
    }
    return _dataList;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
    [self loadData];
    
    __weak typeof(self) vc = self;
    //下拉刷新
    self.tableView.header = [MJRefreshNormalHeader headerWithRefreshingBlock:^{
        [vc loadData];
    }];
    
}

-(void) loadData{
    __weak typeof(self) vc = self;
    MBProgressHUD *hud = [Common showHUDAddedTo:self.view animated:YES];
    [self.categoryService list:^(APIResult *result, NSMutableArray *obj) {
        if(result.success){
            self.dataList = [[NSMutableArray alloc] initWithArray:obj];
        }else{
            [self.view makeToast:result.msg duration:0.3 position:CSToastPositionTop ];
        }
        [hud hideAnimated:NO];
        [vc.tableView.header endRefreshing];
        [vc.tableView reloadData];
    } failure:^(NSError *error) {
        [hud hideAnimated:NO];
        [vc.tableView.header endRefreshing];
    }];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self.dataList count];
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"category_cell" forIndexPath:indexPath];
    Categorys *ent = [self.dataList objectAtIndex:indexPath.row];
    [cell.textLabel setText:ent.title];
    return cell;
}


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

@end
