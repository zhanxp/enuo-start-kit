import { RouterModule } from "@angular/router";
import { HomeComponent } from './home.component'
import { AboutComponent } from './about.component'

const homeRoutes = [
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    },
    {
        path: 'home',
        component: HomeComponent,
    },
    {
        path: 'about',
        component: AboutComponent,
    }
];
export default RouterModule.forChild(homeRoutes);