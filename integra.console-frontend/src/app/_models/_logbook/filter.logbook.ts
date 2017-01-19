import { DropDownValue } from '../dropdown.result';

export class FilterLogBook {
    logId: string;
    businessMsgId: string;
    processId: string;
    logTypeId: string;
    dateFrom: Date;
    dateTo: Date;

    constructor(){
        this.logId = null;
        this.businessMsgId = null;
        this.processId = null;
        this.logTypeId = null;
        this.dateTo = null;
        this.dateFrom = null;
    }

}