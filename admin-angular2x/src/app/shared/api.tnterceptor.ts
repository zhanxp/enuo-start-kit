import { Injectable } from '@angular/core';
import { Request, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { constants } from '../app.constants';
import { NotificationService } from './notification.service';

import { HttpInterceptor } from 'angular2-http-interceptor';

@Injectable()
export class APIInterceptor implements HttpInterceptor {
    // constructor(@Inject(Router) private router: Router, @Inject(CookieService) private cookie: CookieService) {
    // }

    constructor(private notify:NotificationService) {
    
    }

    before(request: Request): Request {
        //do something ...

        // if (request.method !== RequestMethod.Get && !(request.getBody() instanceof FormData)) {
        //     request.headers.set('Content-Type', 'application/json;charset=UTF-8');
        // }
        // let token: string = this.cookie.get('token');
        // if (!token && (request.url.indexOf('/login') === -1 || request.url.indexOf('/register')) === -1) {
        //     this.router.navigate(['/login']);
        // }
        // token && request.headers.set('token', token);

        return request;
    }

    after(response: Observable<Response>): Observable<any> {
        //do something ...
        // return response.catch(res => {
        //     let json = res.json();
        //     console.log(json);
        //     if (res.status === 403) {
        //
        //     }
        //     return Observable.throw(res);
        // });

        return response.pipe(map((res:any) => {
            let json = res.json();
            if (res.status === 200) {
               
            } else {
                this.notify.error('服务或网络异常！');
            }

            if (constants.debug === true) {
                console.log(json);
            }

            return json;
        }));
    }
}