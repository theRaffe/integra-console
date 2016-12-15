import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs';

import { AuthHttp, JwtHelper } from 'angular2-jwt';

import { AuthenticationService } from '../_services/index';
import { User, UserAdditionalInfo } from '../_models/index';
import { app_context } from '../shared/global';

@Injectable()
export class UserService {
    private token: string;
 
    constructor(
        private http: Http,
        private authenticationService: AuthenticationService,
        private authHttp: AuthHttp) {
    }
 
    getUsers(): Observable<User[]> {
        // add authorization header with jwt token
        let headers = new Headers({ 'Authorization': 'Bearer ' + this.authenticationService.token });
        let options = new RequestOptions({ headers: headers });
 
        // get users from api
        return this.http.get('/api/users', options)
            .map((response: Response) => response.json());
    }

    getUserInformation(): Observable<UserAdditionalInfo>{
        let jwtHelper: JwtHelper = new JwtHelper();
        var token = this.authenticationService.token || null;
        if (token){
            console.log('decodeToken=' + JSON.stringify(jwtHelper.decodeToken(token)));
        } else {
            console.log('token is null!!');
        }

        return this.authHttp.get(`${app_context}/getUserInformation`)
            .map((response: Response) => response.json())
            .catch(this.handleError);
    }

    private handleError (error: Response | any) {
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