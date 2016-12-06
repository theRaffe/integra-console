import { NgModule }             from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';

import { AuthGuard } from '../_guards/index';
import { HomeComponent } from './index';
import { TestDemo, TestDemo2 } from '../test/index';
import { NoContentComponent } from '../no-content/index';

const iconsoleRoutes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard],
        children: [
            { path: 'test', component: TestDemo },
            { path: 'test2', component: TestDemo2 },        
        ]
    },    
    // otherwise redirect to home
    { path: '**', component: NoContentComponent }
];
 
@NgModule({
  imports: [
    RouterModule.forChild(iconsoleRoutes)
  ],
  exports: [
    RouterModule
  ]
})

export class IConsoleRoutingModule { }