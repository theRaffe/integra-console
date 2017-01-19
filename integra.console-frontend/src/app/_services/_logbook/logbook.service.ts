import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Response } from '@angular/http';

import { AuthHttp, JwtHelper } from 'angular2-jwt';

import { AuthenticationService } from '../index';

import { FilterLogBook, LogMessageResponse } from '../../_models/index';
import { app_context } from '../../shared/global';

@Injectable()
export class LogBookService {

    constructor(private authenticationService: AuthenticationService,
        private authHttp: AuthHttp) { }

    searchLogMessage(filter: FilterLogBook): Observable<LogMessageResponse> {
        var request = JSON.stringify(filter);
        console.log("my request=" + request);
        return this.authHttp.post(`${app_context}/getLogMessages`,
            request)
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    private handleError(error: Response | any) {
        // In a real world app, we might use a remote logging infrastructure
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }
}