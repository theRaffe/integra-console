import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule, RequestOptions } from '@angular/http';
import { PathLocationStrategy, LocationStrategy } from '@angular/common';

import { PanelMenuModule } from 'primeng/primeng';
import { SlideMenuModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';
import { OverlayPanelModule } from 'primeng/primeng';
import { GrowlModule } from 'primeng/primeng';


// used to create fake backend
import { ENV_PROVIDERS } from './environment';

import { AppComponent }  from './app.component';
import { routing }        from './app.routing';
 
import { LoginComponent } from './login/index';
import { IConsoleModule } from './home/index';

import { LoginRoutingModule } from './login/index';

@NgModule({
    imports: [
        routing,
        LoginRoutingModule,
        IConsoleModule,
        BrowserModule,
        FormsModule,
        HttpModule,        
        PanelMenuModule,
        OverlayPanelModule,
        SlideMenuModule,
        ButtonModule,
        GrowlModule
    ],
    declarations: [
        AppComponent,
        LoginComponent,
    ],
    providers: [
        ENV_PROVIDERS
    ],  
    bootstrap: [AppComponent]
})
 
export class AppModule { }
