import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { constants } from '../app.constants'
import { AuthService } from './auth.service';

@Injectable()
export class HttpService {
   
    constructor(
        private http: Http,
        private authService: AuthService
    ) { }

    private headers () : any {
        var p = this.authService.getProfile() || {};
        var h = new Headers({
            'Content-Type': 'application/json',
            'ticket':p.ticket || ''
        });
        return h;
    }; 

    public http_post(url: string, data: any): Promise<any> {
        var query_body = JSON.stringify(data);
        return this.http
            .post(constants.baseUrl + url, query_body, { headers: this.headers() })
            .toPromise()
            .then(function (res) {
                return res.json();
            })
            .catch(this.handleError);
    }

    public http_get(url): Promise<any> {
        return this.http
            .get(constants.baseUrl +url, { headers: this.headers() })
            .toPromise()
            .then(function(res){
                return res.json();
            })
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}
