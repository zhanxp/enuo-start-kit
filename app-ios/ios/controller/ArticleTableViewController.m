//
//  ArticleTableViewController.m
//  ios
//
//  Created by Peter Zhan on 2017/10/15.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

#import "ArticleTableViewController.h"
#import "ArticleService.h"

@interface ArticleTableViewController ()
@property (strong,nonatomic) ArticleService* articleService;
@property (strong,nonatomic) APIPageResult* pageList;
@end

@implementation ArticleTableViewController
- (ArticleService*) articleService{
    if(_articleService==nil){
        _articleService = [[ArticleService alloc] init];
    }
    return _articleService;
}

- (APIPageResult*) pageList{
    if(_pageList==nil){
        _pageList = [[APIPageResult alloc] init];
        _pageList.pageSize = 10;
        _pageList.pageIndex = 1;
    }
    return _pageList;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    __weak typeof(self) vc = self;
    //下拉刷新
    self.tableView.header = [MJRefreshNormalHeader headerWithRefreshingBlock:^{
        [vc loadData:[NSNumber numberWithBool:NO]];
    }];
    
    //加载数据
    [self loadData:[NSNumber numberWithBool:NO]];
    
    //上拉刷新
    self.tableView.footer = [MJRefreshBackNormalFooter footerWithRefreshingBlock:^{
        [vc loadData:[NSNumber numberWithBool:YES]];
    }];
    
}

-(void) loadData:(NSNumber*) more{
    if([more boolValue]){
        self.pageList.pageIndex =  self.pageList.pageIndex + 1;
    }else{
        self.pageList.pageIndex =  1;
        [self.pageList.items removeAllObjects];
    }
    
    __weak typeof(self) vc = self;
    MBProgressHUD *hud = [Common showHUDAddedTo:self.view animated:YES];
    [self.articleService pageList:self.pageList.pageIndex pageSize:self.pageList.pageSize success:^(APIResult *result, APIPageResult *obj) {
        if(result.success){
            [vc.pageList.items addObjectsFromArray:obj.items];
            vc.pageList.total = obj.total;
        }else{
            [vc.view makeToast:result.msg duration:0.3 position:CSToastPositionTop ];
        }
        [hud hideAnimated:NO];
        //header
        if(![more boolValue]){
              [vc.tableView.header endRefreshing];
        }else{
            //footer
            if([vc.pageList.items count] >= vc.pageList.total){
                [vc.tableView.footer endRefreshing];
                [vc.tableView.footer setHidden:YES];
            }else{
                [vc.tableView.footer endRefreshing];
                [vc.tableView.footer setHidden:NO];
            }
        }
        [vc.tableView reloadData];
    } failure:^(NSError *error) {
        [hud hideAnimated:NO];
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
     return [self.pageList.items count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"article_cell" forIndexPath:indexPath];
    Categorys *ent = [self.pageList.items objectAtIndex:indexPath.row];
    UILabel *textLabel = [cell.contentView viewWithTag:11];
    [textLabel setText:ent.title];
    //[cell.textLabel setText:ent.title];
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
