import { RouterModule } from "@angular/router";
import { CategoryComponent } from './category.component';
import { CategoryListComponent } from './list.component';
const categoryRoutes = [
    {
        path: '',
        component: CategoryComponent,
        children: [
            { path: '', component: CategoryListComponent },
            { path: 'list', component: CategoryListComponent },
        ]
    }
];
export default RouterModule.forChild(categoryRoutes);