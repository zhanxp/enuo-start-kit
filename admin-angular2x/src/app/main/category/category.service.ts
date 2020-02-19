import { Injectable } from '@angular/core';
import { HttpService } from '../../shared/http.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpService) {

  }

  public getList(): Promise<any> {
    return this.http.http_get("/category/list");
  }
}
