//
//  ArticleTableViewController.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/18.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import UIKit
import MJRefresh

class ArticleTableViewController: UITableViewController {
    
    let articleService = ArticleService.init()
    var pageList  =  ApiPageResult.init(1,10)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.header = MJRefreshNormalHeader.init(refreshingBlock: {
            self.loadData(false)
        })
        
        self.tableView.footer = MJRefreshBackNormalFooter.init(refreshingBlock: {
             self.loadData(true)
        })
        
        self.loadData(false)
    }
    
    func loadData(_ more:Bool) {
        
        if(more){
            pageList.pageIndex = pageList.pageIndex + 1
        }else{
             pageList.pageIndex = 1
            pageList.items.removeAll()
        }
        
        let hud = Common.showHUDAddedTo(view: self.view, animated: true)
        articleService.pagelist(pageIndex: pageList.pageIndex, pageSize: pageList.pageSize, success: { (result, obj) in
            if(result.success){
                self.pageList.total = obj.total
                for item in obj.items{
                    self.pageList.items.append(item)
                }
                self.tableView.reloadData()
            }
            
            if(more){
                self.tableView.footer.endRefreshing()
                if(self.pageList.items.count >= self.pageList.total){
                    self.tableView.footer.isHidden = true
                }else{
                    self.tableView.footer.isHidden = false
                }
            }else{
                self.tableView.header.endRefreshing()
            }
            
            hud.hide(animated: false)
        }, failure: { (error) in
              hud.hide(animated: false)
        })
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return pageList.items.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "article_cell", for: indexPath)
        let ent = pageList.items[indexPath.row] as! Article
        let label = cell.contentView.viewWithTag(11) as! UILabel
        label.text = ent.title
        //cell.textLabel?.text = ent.title
        return cell
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
