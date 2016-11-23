import { Routes, RouterModule } from '@angular/router';
 
import { LoginComponent } from './login/index';
import { HomeComponent } from './home/index';
import { AuthGuard } from './_guards/index';
import { TestDemo } from './test/index';

const appRoutes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'test', component: TestDemo },
    { path: '', component: HomeComponent, canActivate: [AuthGuard], pathMatch: 'full' }, 
    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];
 
export const routing = RouterModule.forRoot(appRoutes);