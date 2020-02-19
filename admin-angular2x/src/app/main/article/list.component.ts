import { Component, OnInit } from '@angular/core';
import { ArticleService } from './article.service'

@Component({
    selector: 'app-article-list',
    templateUrl: './list.component.html'
})
export class ArticleListComponent implements OnInit {

    list = {
        pageIndex:1,
        paegSize:10,
        items:[]
    };

    constructor(
        private articleService: ArticleService
    ) { }

    loadList() {
        var _this = this;
        this.articleService.getPageList(this.list.pageIndex,this.list.paegSize).then(function (res) {
            _this.list = res.data;
        });
    }


    ngOnInit() {
        this.loadList();
    }

}
