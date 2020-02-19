import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { constants } from '../app.constants'
import { AccountService } from '../shared/account.service'
import { AuthService } from '../shared/auth.service'
import { ActivatedRoute, Router } from '@angular/router';


@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
})
export class MainComponent implements OnInit {
    title = constants.name;
    userInfo= {};
    menus = constants.menus;

    constructor(
        private accountService: AccountService,
        private authService: AuthService,
        private router: Router,
    ) {

    }

    onLogout() {
       if(confirm('确定要退出吗？')){
           this.accountService.logout();
           this.authService.logout();
           this.router.navigateByUrl(constants.loginview.url);
       }
    }

    ngOnInit() {
        this.userInfo = this.authService.getProfile();
    }
}
