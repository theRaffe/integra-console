import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule } from '@angular/http';
import {PathLocationStrategy, LocationStrategy} from '@angular/common';

import { PanelMenuModule } from 'primeng/primeng';
import {SlideMenuModule} from 'primeng/primeng';
import {ButtonModule} from 'primeng/primeng';
import { OverlayPanelModule } from 'primeng/primeng';


// used to create fake backend
import { fakeBackendProvider } from './_helpers/index';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions } from '@angular/http';
 
import { AuthGuard } from './_guards/index';
import { AppComponent }  from './app.component';
import { routing }        from './app.routing';
 
import { LoginComponent } from './login/index';
import { AuthenticationService, UserService } from './_services/index';
import { HomeComponent } from './home/index';
import { TestDemo } from './test/index';
import { MenuComponent } from './_menu/menu.component';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing,
        PanelMenuModule,
        OverlayPanelModule,
        SlideMenuModule,
        ButtonModule
    ],
    declarations: [
        AppComponent,
        LoginComponent,
        HomeComponent,
        TestDemo,
        MenuComponent
    ],
    providers: [
        AuthGuard,
        AuthenticationService,
        UserService,
        // providers used to create fake backend
        fakeBackendProvider,
        MockBackend,
        BaseRequestOptions,
        {provide: LocationStrategy, useClass: PathLocationStrategy}
    ],  
    bootstrap: [AppComponent]
})
 
export class AppModule { }