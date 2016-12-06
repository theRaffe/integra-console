import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
 
import { LoginComponent } from './login/index';
import { AuthGuard } from './_guards/index';

const appRoutes: Routes = [
    
    { path: '', redirectTo: '/home', pathMatch: 'full'},
    { path: 'home', loadChildren: 'app/home/iconsole.module#IConsoleModule'},

];
 
export const routing = RouterModule.forRoot(appRoutes, { useHash: true, preloadingStrategy: PreloadAllModules });