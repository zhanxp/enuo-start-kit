import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { ArticleService } from './article.service';

import { ArticleComponent } from './article.component';
import { ArticleListComponent } from './list.component';
import articleRoutes from './article.routes';

@NgModule({
    declarations: [
        ArticleComponent,
        ArticleListComponent
    ],
    imports: [
        articleRoutes,
        CommonModule,
        FormsModule,
    ],
    exports: [
        ArticleComponent
    ],
    providers: [
        ArticleService
    ]
})
export default class ArticleModule { }