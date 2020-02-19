import { Component, OnInit } from '@angular/core';
import { AuthService } from '../shared/auth.service'
import { AccountService } from '../shared/account.service'

import { ActivatedRoute, Router } from '@angular/router';
import { NotificationService } from '../shared/notification.service'
import { constants } from '../app.constants';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {
    private loginForm: any;
    constructor(
        private router: Router,
        private authService: AuthService,
        private accountService: AccountService,
        private notify: NotificationService
    ) {
        this.loginForm = {};
    }

    ngOnInit() {}

    public doLogin(): void {
        var _this = this;
        this.accountService.login(this.loginForm)
            .then(function(res){
                if(res.success){
                    _this.authService.saveLogin(res.data);
                    _this.router.navigateByUrl(constants.homeview.url);
                }else{
                    _this.notify.warning(res.msg || '登录失败!');
                }
            });
    }
}