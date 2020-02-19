import { Injectable } from '@angular/core';
import { NotificationService } from './notification.service';
import { environment } from '../../environments/environment';
import { HttpService } from './http.service';

@Injectable()
export class AccountService {
    constructor(
        private http: HttpService) {
    }

    public login(account: any): Promise<any> {
        return this.http.http_post("/login", account);
    }


    public logout = (): Promise<any> => {
        return this.http.http_get("/logout");
    }

    public loadProfile(): Promise<any> {
        return this.http.http_get("/profile");
    }
}
