import { Component, OnInit, trigger, state, style, transition, animate } from '@angular/core';

import { User } from '../_models/index';
import { UserService } from '../_services/index';

import { PanelMenuModule, MenuItem } from 'primeng/primeng';

@Component({
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.css'],
  animations: [
    trigger('slideInOut', [
      state('in', style({
        width: "250px",
        opacity: '1'
      })),
      state('out', style({
        width: "0",
        opacity: '0'
      })),
      transition('in => out', animate('400ms ease-in-out')),
      transition('out => in', animate('400ms ease-in-out'))
    ]),
    trigger('contentInOut', [
      state('in', style({
        marginLeft: "250px"
      })),
      state('out', style({
        marginLeft: "0"
      })),
      transition('in => out', animate('400ms ease-in-out')),
      transition('out => in', animate('400ms ease-in-out'))
    ])
  ]
})

export class HomeComponent implements OnInit {
  users: User[] = [];
  private items: MenuItem[];
  private userFullName: string;
  private profileName: string;

  constructor(private userService: UserService) { }

  menuState: string = 'out';

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

    this.userService.getUserInformation()
      .subscribe(
      data => {
        //console.log(data.menuItems)
        this.items = data.menuItems;
        this.profileName = data.profileName;

      },
      err => console.error(err),
      () => console.log('Request Complete')
      );

    this.userFullName = this.userService.getUserFullName();

    console.log('finish ngOnInit');
  }



}