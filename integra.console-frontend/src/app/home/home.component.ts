import { Component, OnInit, trigger, state, style, transition, animate } from '@angular/core';
 
import { User } from '../_models/index';
import { UserService } from '../_services/index';

import {PanelMenuModule,MenuItem} from 'primeng/primeng'; 

@Component({
    templateUrl: 'home.component.html',
    styleUrls: ['home.component.css'],
    animations: [
    /*trigger('slideInOut', [
      state('in', style({
        transform: 'translate3d(0, 0, 0)'
      })),
      state('out', style({
        transform: 'translate3d(100%, 0, 0)'
      })),
      transition('in => out', animate('400ms ease-in-out')),
      transition('out => in', animate('400ms ease-in-out'))
    ]),
    */

    trigger('slideInOut', [
      state('in', style({
        width: "250px"
      })),
      state('out', style({
        width: "0"
      })),
      transition('in => out', animate('400ms ease-in-out')),
      transition('out => in', animate('400ms ease-in-out'))
    ]),
    trigger('contentInOut', [
      state('in', style({
        marginLeft : "250px"
      })),
      state('out', style({
        marginLeft : "0"
      })),
      transition('in => out', animate('400ms ease-in-out')),
      transition('out => in', animate('400ms ease-in-out'))
    ])
  ]
})
 
export class HomeComponent implements OnInit {
    users: User[] = [];
    private items: MenuItem[];

 
    constructor(private userService: UserService) { }

    menuState:string = 'out';

    toggleMenu() {
        // 1-line if statement that toggles the value:
        this.menuState = this.menuState === 'out' ? 'in' : 'out';
    }
 
    ngOnInit() {
        // get users from secure api end point
        this.userService.getUsers()
            .subscribe(users => {
                this.users = users;
            });

        this.items = [
            {
                label: 'File',
                icon: 'fa-file-o',
                items: [{
                        label: 'New', 
                        icon: 'fa-plus',
                        items: [
                            {label: 'Project'},
                            {label: 'Other'},
                        ]
                    },
                    {label: 'Open'},
                    {label: 'Quit'}
                ]
            },
            {
                label: 'Edit',
                icon: 'fa-edit',
                items: [
                    {label: 'Undo', icon: 'fa-mail-forward'},
                    {label: 'Redo', icon: 'fa-mail-reply'}
                ]
            },
            {
                label: 'Help',
                icon: 'fa-question',
                items: [
                    {
                        label: 'Contents'
                    },
                    {
                        label: 'Search', 
                        icon: 'fa-search', 
                        items: [
                            {
                                label: 'Text', 
                                items: [
                                    {
                                        label: 'Workspace'
                                    }
                                ]
                            },
                            {
                                label: 'File'
                            }
                    ]}
                ]
            },
            {
                label: 'Actions',
                icon: 'fa-gear',
                items: [
                    {
                        label: 'Edit',
                        icon: 'fa-refresh',
                        items: [
                            {label: 'Save', icon: 'fa-save'},
                            {label: 'Update', icon: 'fa-save'},
                        ]
                    },
                    {
                        label: 'Other',
                        icon: 'fa-phone',
                        items: [
                            {label: 'Delete', icon: 'fa-minus'}
                        ]
                    }
                ]
            }
        ];
    }
 
}