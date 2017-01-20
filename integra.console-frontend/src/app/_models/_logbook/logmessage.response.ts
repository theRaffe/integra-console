import { LogMessage } from './logmessage';

export class LogMessageResponse {
    success: boolean = false;
    message: string = "";
    logMessageList: LogMessage[] = [];
}