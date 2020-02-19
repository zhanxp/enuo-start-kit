//
//  CategoryTableViewController.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/18.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import UIKit
import MJRefresh

class CategoryTableViewController: UITableViewController {
    
    let categoryService = CategoryService.init()
    var dataList:Array<Category> = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.header = MJRefreshNormalHeader.init(refreshingBlock: {
            self.loadData()
        })
        
        self.loadData()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func loadData() {
       
        
        let hud = Common.showHUDAddedTo(view: self.view, animated: true)
        categoryService.list(success: { (result, list) in
            
            if(result.success){
                self.dataList.removeAll()
                for item in list{
                    let ent = item as! Category
                    self.dataList.append(ent)
                }
                self.tableView.reloadData()
            }
            self.tableView.header.endRefreshing()
            hud.hide(animated: false)
        }) { (error) in
             hud.hide(animated: false)
        }
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dataList.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "category_cell", for: indexPath)
        let ent = dataList[indexPath.row]
        cell.textLabel?.text = ent.title
        return cell
    }
}
