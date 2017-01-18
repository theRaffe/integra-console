import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthHttp, JwtHelper } from 'angular2-jwt';

import { AuthenticationService } from '../_services/index';

import { DropDownResult } from '../_models/index';

@Injectable()
export class DropDownService {

    constructor(private authenticationService: AuthenticationService,
        private authHttp: AuthHttp) { }


    public getServices(): DropDownResult[] {

        return [
            { label: 'Serv1', value: { id: 1, code: 'Serv1' } },
            { label: 'Serv2', value: { id: 2, code: 'Serv2' } },
            { label: 'Serv3', value: { id: 3, code: 'Serv3' } },
        ];
    }

    public getLogType(): DropDownResult[] {
        return [
            { label: 'ERROR', value: 'ERROR' },
            { label: 'WARN', value: 'WARN' },
            { label: 'INFO', value: 'INFO' },
            { label: 'DEBUG', value: 'DEBUG' },
        ];
    }
}