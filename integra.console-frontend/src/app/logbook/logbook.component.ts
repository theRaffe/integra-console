import { Component, OnInit } from '@angular/core';

import { SelectItem } from 'primeng/primeng';

import { AuthenticationService, DropDownService, LogBookService } from '../_services/index';
import { DropDownResult, DropDownValue, LogMessageResponse, LogMessage, FilterLogBook } from '../_models/index';
import { strFormatDate } from '../shared/global';

@Component({
    templateUrl: 'logbook.component.html',
    styleUrls: ['logbook.component.css', '../shared/styles/common.css']
})

export class LogBookComponent implements OnInit {

    private formatDate: string = strFormatDate;
    filterLogBook: FilterLogBook = new FilterLogBook();
    serviceList: SelectItem[];
    logTypeList: SelectItem[];
    selectedService: any = { id: null, code: null };
    selectedLogType: any = { id: null, code: null };
    cols: any;

    logMessageResponse: LogMessageResponse = new LogMessageResponse();

    constructor(private authenticationService: AuthenticationService,
        private dropDownService: DropDownService,
        private logBookService: LogBookService) { }

    ngOnInit() {
        this.dropDownService.getServices()
            .subscribe(
            data => {
                this.serviceList = data;
                this.serviceList.splice(0, 0, { label: "Servicios", value: this.selectedService });
            },
            err => console.error(err),
            () => console.log('Request Complete')
            );
        this.logTypeList = this.dropDownService.getLogType();

        this.cols = [
            { field: 'logTypeId', header: 'Tipo' },
            { field: 'logId', header: 'Log Id' },
            { field: 'businessMsgId', header: 'Id Negocio' },
            { field: 'processId', header: 'Id Servicio' },
            { field: 'subprocess', header: 'Subproceso' },
            { field: 'processName', header: 'Informacion' },
            { field: 'processIdentifier', header: 'Id Proceso' }
        ];

    }

    onSearch() {
        console.log("selectedService=" + JSON.stringify(this.selectedService));

        this.filterLogBook.processId = this.selectedService.code;
        this.filterLogBook.logTypeId = this.selectedLogType.code;

        this.logBookService.searchLogMessage(this.filterLogBook)
            .subscribe(
            data => {
                this.logMessageResponse = data;
                console.log("this.logMessageResponse=" + JSON.stringify(this.logMessageResponse));
            },
            err => console.error(err),
            () => console.log('Request Complete')
            );
    }
}