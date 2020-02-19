import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { CategoryService } from './category.service';

import { CategoryComponent } from './category.component';
import { CategoryListComponent } from './list.component';

import categoryRoutes from './category.routes';

@NgModule({
    declarations: [
        CategoryComponent,
        CategoryListComponent
    ],
    imports: [
        categoryRoutes,
        CommonModule,
        FormsModule,
    ],
    exports: [
        CategoryComponent
    ],
    providers: [
        CategoryService
    ]
})
export default class CategoryModule { }