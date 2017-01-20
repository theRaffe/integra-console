import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { IConsoleRoutingModule } from './iconsole.routing';
import { TestDemo, TestDemo2 } from '../test/index';
import { NoContentComponent } from '../no-content/index';

import { HomeComponent, TitleSearchComponent } from './index';
import { MenuComponent, MenuProfileComponent } from '../_menu/index';
import { LogBookComponent } from '../logbook/index';
import { UserService, DropDownService, LogBookService } from '../_services/index';

import { PanelMenuModule } from 'primeng/primeng';
import { SlideMenuModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { OverlayPanelModule } from 'primeng/primeng';
import { GrowlModule } from 'primeng/primeng';
import { InputTextModule } from 'primeng/primeng';
import { DropdownModule } from 'primeng/primeng';
import { CalendarModule } from 'primeng/primeng';
import { DataTableModule, SharedModule } from 'primeng/primeng';



import { provideAuth } from 'angular2-jwt';
import { AuthenticationService } from '../_services/index';

@NgModule({
    imports: [
        IConsoleRoutingModule,
        BrowserModule,
        PanelMenuModule,
        OverlayPanelModule,
        SlideMenuModule,
        ButtonModule,
        FormsModule,
        DropdownModule,
        CalendarModule,
        DataTableModule,
        SharedModule
    ],
    declarations: [
        HomeComponent,
        TestDemo,
        MenuComponent,
        TestDemo2,
        NoContentComponent,
        MenuProfileComponent,
        TitleSearchComponent,
        LogBookComponent,
    ],
    providers: [
        UserService,
        AuthenticationService,
        DropDownService,
        LogBookService,
        provideAuth({
            headerName: 'Authorization',
            headerPrefix: '',
            tokenName: 'id_token',
            tokenGetter: (() => {
                console.log(localStorage.getItem('currentUser'));
                return JSON.parse(localStorage.getItem('currentUser')).token;
            }),
            globalHeaders: [{ 'Content-Type': 'application/json' }],
            noJwtError: true,
            noTokenScheme: true
        })
    ]
})

export class IConsoleModule { } 