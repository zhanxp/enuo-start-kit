import { Injectable } from '@angular/core';
//import { NotificationService } from './notification.service';
import { environment } from '../../environments/environment';
// import { HttpService } from './http.service';

@Injectable()
export class AuthService {
    constructor( 
       //private notif: NotificationService ,
        // private http: HttpService 
    ) {
    }

    // public login(account: any): Promise<any> {
    //     return this.http.http_post("/login", account);
    // }

    public saveLogin = (profile: any): void => {
        localStorage.setItem("profile", JSON.stringify(profile));
        //this.notif.success('You successfully login');
    }

    public logout = (): void => {
        localStorage.removeItem('profile');
        //this.notif.success('You successfully loged out');
    }

    public isAuthenticated = (): boolean => {
        return this.getProfile() !== null;
    }

    public getProfile = (): any => {
        let profile = localStorage.getItem('profile');

        if (profile) {
            return JSON.parse(profile);
        }

        return null;
    }
    
    // public loadProfile (): Promise<any> {
    //     return this.http.http_post("/profile",{});
    // }
}
