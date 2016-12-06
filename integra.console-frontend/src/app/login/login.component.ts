import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Message } from 'primeng/primeng';

import { AuthenticationService } from '../_services/index';

@Component({
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.css']
})

export class LoginComponent implements OnInit {
    model: any = {};
    loading = false;
    error = '';
    appVersionNumber = APP_VERSION;
    environment = ENV;
    msgs: Message[] = [];
    
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
        ) { }

    ngOnInit() {
        // reset login status
        this.authenticationService.logout();
    }
    
    login() {
        this.loading = true;
        console.log("do authentication");
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe( result => {
                if (result === true) {
                    // login successful
                    this.router.navigate(['/']);
                } else {
                    this.error = 'Username or password is incorrect';
                    this.msgs.push({severity:'error', summary:'Error Login', detail: this.error});
                    this.loading = false;
                }
            },            
            error => {
                //console.error('onError: %s', <any>error);
                this.error = <any>error;
                this.loading = false;
                this.msgs = [];
                this.msgs.push({severity:'error', summary:'Error Login', detail: error});
            }
            
            );
    
    }

}
