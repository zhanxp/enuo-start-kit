import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { HomeComponent } from './home.component'
import { AboutComponent } from './about.component'
import homeRoutes from './home.routes';

@NgModule({
    declarations: [
        HomeComponent,
        AboutComponent
    ],
    imports: [
        homeRoutes,
        CommonModule,
        FormsModule,
    ],
    exports: [
        HomeComponent
    ],
    providers: [
        
    ]
})
export default class HomeModule { }