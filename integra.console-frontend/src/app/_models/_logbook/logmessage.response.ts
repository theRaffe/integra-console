import { LogMessage } from './logmessage';

export class LogMessageResponse {
    success: boolean;
    message: string;
    logMessageList:  LogMessage[];
}