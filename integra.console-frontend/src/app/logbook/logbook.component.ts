import { Component, OnInit } from '@angular/core';

import { AuthenticationService, DropDownService } from '../_services/index';
import { DropDownResult } from '../_models/index';

@Component({
    templateUrl: 'logbook.component.html',
    styleUrls: ['logbook.component.css', '../shared/styles/common.css']
})

export class LogBookComponent implements OnInit {

    logId: string;
    businessId: string;
    serviceList: DropDownResult[];
    logTypeList: DropDownResult[];
    selectedService: any;
    selectedLogType: any;
    dateFrom: Date;
    dateTo: Date;

    constructor(private authenticationService: AuthenticationService,
        private dropDownService: DropDownService) { }

    ngOnInit() {
        this.serviceList = this.dropDownService.getServices();
        this.logTypeList = this.dropDownService.getLogType();
    }

    onSearch(){
        
    }
}