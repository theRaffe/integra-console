import { Component, OnInit, Input } from '@angular/core';
import { MenuItem } from 'primeng/primeng';
import { Router } from '@angular/router';

import { AuthenticationService } from '../_services/index';

@Component({
    selector: 'app-menu-profile',
    templateUrl: './menu.profile.component.html',
    styleUrls: ['./menu.profile.component.css']
})

export class MenuProfileComponent implements OnInit {

    @Input() username: string;
    @Input() profileName: string;

    private menuItems: MenuItem[];

    constructor(private router: Router, private authenticationService: AuthenticationService) { }

    ngOnInit() {
        
        this.menuItems = [
            { label: 'Administrar Cuenta', icon: 'ic-setting-dark', routerLink: [''] },
            {
                label: 'Cerrar Sesion', icon: 'ic-logout-dark', command: (event) => {
                    this.authenticationService.logout();
                    console.log("execute logout()");
                    this.router.navigate(['/login']);
                }
            }
        ]
    }
}