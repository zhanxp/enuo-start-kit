import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './account/login.component'
import { ErrorComponent } from './error/error.component';
import { AppComponent } from './app.component';
import { AuthGuard } from './shared/authGuard.service';
import { MainComponent } from './main/main.component'

export const ROUTES: Routes = [
    { 
        path: '', 
        component: AppComponent,
        children: [
            { 
                path: '', 
                component: MainComponent,
                canActivate: [AuthGuard],
                children:[
                    {
                        path: '', 
                        loadChildren: './main/home/home.module',
                    }, 
                    {
                        path: 'category',
                        loadChildren: './main/category/category.module',
                    },
                    {
                        path: 'article',
                        loadChildren: './main/article/article.module',
                    },
                ]
            },
            { 
                path: 'login', 
                component: LoginComponent 
            },
       ]
    },
    { path: '**', component: ErrorComponent },
];
