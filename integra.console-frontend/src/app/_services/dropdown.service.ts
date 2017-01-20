import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { Observable } from 'rxjs';

import { AuthHttp, JwtHelper } from 'angular2-jwt';
import { SelectItem } from 'primeng/primeng';

import { AuthenticationService } from '../_services/index';
import { DropDownResult } from '../_models/index';
import { app_context } from '../shared/global';

@Injectable()
export class DropDownService {

    constructor(private authenticationService: AuthenticationService,
        private authHttp: AuthHttp) { }


    public getServices(): Observable<SelectItem[]> {

        return this.authHttp.get(`${app_context}/getServiceDropdown`)
            .map((response: Response) => {
                let result: SelectItem[] = [];
                let getResult: any = response.json();
                console.log("getResult=" + JSON.stringify(getResult));

                for (let objectResult of getResult) {
                    let serviceId = objectResult.application + "-" + objectResult.category + "-" + objectResult.sequence;
                    result.push({ label: objectResult.processId, value: { id: serviceId, code: objectResult.processId, name: objectResult.description } });
                }

                console.log("result=" + JSON.stringify(result));
                return result;
            })
            .catch(this.handleError);

        /*return [
            { label: 'Service1', value: { id: 1, code: 'Serv1' } },
            { label: 'Serv2', value: { id: 2, code: 'Serv2' } },
            { label: 'Serv3', value: { id: 3, code: 'Serv3' } },
        ];
        */
    }

    public getLogType(): SelectItem[] {
        return [
            { label: "TODOS", value: { id: null, code: null } },
            { label: 'ERROR', value: { id: 1, code: 'ERROR' } },
            { label: 'WARN', value: { id: 2, code: 'WARN' } },
            { label: 'INFO', value: { id: 3, code: 'INFO' } },
            { label: 'DEBUG', value: { id: 4, code: 'DEBUG' } },
        ];
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