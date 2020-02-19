import { RouterModule } from "@angular/router";
import { ArticleComponent } from './article.component';
import { ArticleListComponent } from './list.component';
const articleRoutes = [
    {
        path: '',
        component: ArticleComponent,
        children: [
            { path: '', component: ArticleListComponent },
            { path: 'list', component: ArticleListComponent },
        ]
    }
];
export default RouterModule.forChild(articleRoutes);