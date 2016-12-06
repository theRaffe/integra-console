import { NgModule }       from '@angular/core';

import { IConsoleRoutingModule } from './iconsole.routing';
import { TestDemo, TestDemo2 } from '../test/index';
import { NoContentComponent } from '../no-content/index';

import { HomeComponent } from './index';
import { MenuComponent } from '../_menu/menu.component';
import { UserService }  from '../_services/index';

import { PanelMenuModule } from 'primeng/primeng';
import { SlideMenuModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { OverlayPanelModule } from 'primeng/primeng';
import { GrowlModule } from 'primeng/primeng';

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
        NoContentComponent
    ],
    providers: [
        UserService
    ]
})

export class IConsoleModule {} 