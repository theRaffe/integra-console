import { Response, ResponseOptions } from '@angular/http';
import { LogMessageResponse } from '../_models/index';

export class FakeLogMessage {


    searchLogMessage(params: any): Response {
        let result: LogMessageResponse = new LogMessageResponse();
        let dtm:Date = new Date();
        result.success = true;
        result.message = 'success';
        result.logMessageList = [
            {
                logId: 1,
                logTypeId: 2,
                processId: "processId-1",
                businessMsgId: "businessMsgId-1",
                processName: "processName-1",
                subprocess: "subprocess-1",
                creationDate: dtm,
                processIdentifier: "processIdentifier-1.1"}
        ];
        console.log("params=" + JSON.stringify(params));
        //console.log("searchLogMessage=" + JSON.stringify(result));
        return new Response(
            new ResponseOptions({ status: 200, body: result })
        )
    }
}