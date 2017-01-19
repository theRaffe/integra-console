import { Component, OnInit } from '@angular/core';

import { SelectItem } from 'primeng/primeng';

import { AuthenticationService, DropDownService, LogBookService } from '../_services/index';
import { DropDownResult, DropDownValue, LogMessageResponse, LogMessage, FilterLogBook } from '../_models/index';

@Component({
    templateUrl: 'logbook.component.html',
    styleUrls: ['logbook.component.css', '../shared/styles/common.css']
})

export class LogBookComponent implements OnInit {

    filterLogBook: FilterLogBook = new FilterLogBook();
    serviceList: SelectItem[];
    logTypeList: SelectItem[];
    selectedService: any = { id: null, code: null };
    selectedLogType: any = { "id": 3, "code": "INFO" };

    logMessageResponse: LogMessageResponse;

    constructor(private authenticationService: AuthenticationService,
        private dropDownService: DropDownService,
        private logBookService: LogBookService) { }

    ngOnInit() {
        this.dropDownService.getServices()
            .subscribe(
            data => {
                this.serviceList = data;
                this.serviceList.splice(0, 0, { label: "Servicios", value: { id: null, code: null } });
            },
            err => console.error(err),
            () => console.log('Request Complete')
            );
        this.logTypeList = this.dropDownService.getLogType();
    }

    onSearch() {
        console.log("selectedService=" + JSON.stringify(this.selectedService));

        this.filterLogBook.processId = this.selectedService.code;
        this.filterLogBook.logTypeId = this.selectedLogType.code;

        this.logBookService.searchLogMessage(this.filterLogBook)
            .subscribe(
            data => {
                //console.log(data.menuItems)
                this.logMessageResponse = data;
            },
            err => console.error(err),
            () => console.log('Request Complete')
            );
    }
}