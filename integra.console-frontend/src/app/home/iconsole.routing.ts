import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';

import { AuthGuard } from '../_guards/index';
import { HomeComponent } from './index';
import { TestDemo, TestDemo2 } from '../test/index';
import { LogBookComponent } from '../logbook/index';
import { NoContentComponent } from '../no-content/index';

const iconsoleRoutes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: 'home', component: HomeComponent, canActivate: [AuthGuard],
    children: [
      { path: '', redirectTo: 'logbook', pathMatch: 'full' },
      { path: 'test', component: TestDemo },
      { path: 'logbook', component: LogBookComponent },
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