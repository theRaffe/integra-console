import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
 
import { LoginComponent } from './login/index';
import { HomeComponent } from './home/index';
import { AuthGuard } from './_guards/index';
import { TestDemo } from './test/index';

const appRoutes: Routes = [
    { path: 'login', component: LoginComponent, pathMatch: 'full' },
    { path: 'test', component: TestDemo },
    { path: '', component: TestDemo },
    { path: 'home', component: HomeComponent, pathMatch: 'full' }, 
    // otherwise redirect to home
    //{ path: '**', redirectTo: '' }
];
 
export const routing = RouterModule.forRoot(appRoutes, { useHash: true, preloadingStrategy: PreloadAllModules });