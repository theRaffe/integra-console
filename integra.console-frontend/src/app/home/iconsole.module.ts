import { NgModule }       from '@angular/core';

import { IConsoleRoutingModule } from './iconsole.routing';
import { TestDemo, TestDemo2 } from '../test/index';
import { NoContentComponent } from '../no-content/index';

import { HomeComponent, TitleSearchComponent } from './index';
import { MenuComponent, MenuProfileComponent } from '../_menu/index';
import { UserService }  from '../_services/index';

import { PanelMenuModule } from 'primeng/primeng';
import { SlideMenuModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { OverlayPanelModule } from 'primeng/primeng';
import { GrowlModule } from 'primeng/primeng';

import { provideAuth } from 'angular2-jwt';
import { AuthenticationService }  from '../_services/index';

@NgModule({
    imports: [
        IConsoleRoutingModule,
        PanelMenuModule,
        OverlayPanelModule,
        SlideMenuModule,
        ButtonModule,
    ],
    declarations: [
        HomeComponent,
        TestDemo,
        MenuComponent,
        TestDemo2,
        NoContentComponent,
        MenuProfileComponent,
        TitleSearchComponent,
    ],
    providers: [
        UserService,
        AuthenticationService,
        provideAuth({
            headerName: 'Authorization',
            headerPrefix: '',
            tokenName: 'id_token',
            tokenGetter: (() => {
                console.log(localStorage.getItem('currentUser'));
                return JSON.parse(localStorage.getItem('currentUser')).token;
            }),
            globalHeaders: [{'Content-Type':'application/json'}],
            noJwtError: true,
            noTokenScheme: true
            })
    ]
})

export class IConsoleModule {} 