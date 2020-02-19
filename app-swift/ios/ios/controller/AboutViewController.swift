//
//  AboutViewController.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/18.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import UIKit

class AboutViewController : UIViewController{
    @IBAction func btnLogoutClick(_ sender: Any) {
        let uerinfo = UserInfo.init()
        uerinfo.saveInfo()
        Common.ShareAppDelegate().fnShowLoginViewController()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
