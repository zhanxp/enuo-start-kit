import { Component, OnInit } from '@angular/core';
import { CategoryService } from './category.service'

@Component({
    selector: 'app-category-list',
    templateUrl: './list.component.html'
})
export class CategoryListComponent implements OnInit {

    list = [];
    
    constructor(
        private  categoryService: CategoryService
    ) { }

    loadList(){
        var _this = this;
        this.categoryService.getList().then(function(res){
            _this.list = res.data;
        });
    }

    ngOnInit() {
        this.loadList();
    }

}
