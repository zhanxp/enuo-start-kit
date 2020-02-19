import { Injectable } from '@angular/core';
import { HttpService } from '../../shared/http.service';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(private http: HttpService) {

  }

  public getPageList(pageIndex: number, pageSize: number): Promise<any> {
    var url = "/article/list?pageIndex=" + pageIndex + "&pageSize="+pageSize; 
    return this.http.http_get(url);
  }
}
