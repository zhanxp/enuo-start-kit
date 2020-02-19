//
//  LoginViewController.swift
//  ios
//
//  Created by Peter Zhan on 2017/10/18.
//  Copyright © 2017年 com.enuocms. All rights reserved.
//

import UIKit
import Toaster

class LoginViewController: UITableViewController {
    
    @IBOutlet var txtUserName: UITextField!
    @IBOutlet var txtPassword: UITextField!
    
    let accountService = AccountService.init()
    
    
    @IBAction func btnLoginClick(_ sender: Any) {
       let username = txtUserName.text
       let password = txtPassword.text
        
        if(username == nil || username!.isEmpty ){
            Toast(text: "请输入用户名!").show()
            return
        }
        
        if( password == nil || password!.isEmpty){
            Toast(text: "请输入密码!").show()
            return
        }
     
        let hud = Common.showHUDAddedTo(view: self.view, animated: true)
        accountService.login(username: username!, password: password!, success: { (result, obj) in
            
            if(result.success && obj != nil){
                let uinfo = obj as! UserInfo
                uinfo.saveInfo()
                Common.ShareAppDelegate().fnShowMainViewController()
            }
            hud.hide(animated: false)
        
        }) { (error) in
            hud.hide(animated: false)
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
