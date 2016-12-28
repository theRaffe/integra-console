import { Http, BaseRequestOptions, Response, ResponseOptions, RequestMethod } from '@angular/http';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { app_context } from '../shared/global';
 
export let fakeBackendProvider = {
    // use fake backend in place of Http service for backend-less development
    provide: Http,
    useFactory: (backend, options) => {
        // configure fake backend
        backend.connections.subscribe((connection: MockConnection) => {
            let testUser = { username: 'test', password: 'test', firstName: 'Test', lastName: 'User' };
 
            // wrap in timeout to simulate server api call
            setTimeout(() => {
 
                // fake authenticate api end point
                if (connection.request.url.endsWith(`${app_context}/auth`) && connection.request.method === RequestMethod.Post) {
                    // get parameters from post request
                    let params = JSON.parse(connection.request.getBody());
 
                    // check user credentials and return fake jwt token if valid
                    if (params.username === testUser.username && params.password === testUser.password) {
                        connection.mockRespond(new Response(
                            new ResponseOptions({ status: 200, body: { token: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwLXJicmlvbmVzIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNDgxNTcwMzIyMTg1LCJleHAiOjE0ODIxNzUxMjJ9.iODa2i4Nl_Ik5CV3ElRsJo9qIXiSesSF3uLak_FN9STymz5WCIVpU7RG6BqTwzyiG5Q07pPbm7OK3qbZfAqnTA' } })
                        ));
                    } else {
                        connection.mockRespond(new Response(
                            new ResponseOptions({ status: 200 })
                        ));
                    }
                }
 
                // fake users api end point
                if (connection.request.url.endsWith(`${app_context}/api/users`) && connection.request.method === RequestMethod.Get) {
                    // check for fake auth token in header and return test users if valid, this security is implemented server side
                    // in a real application
                    if (connection.request.headers.get('Authorization') === 'Bearer fake-jwt-token') {
                        connection.mockRespond(new Response(
                            new ResponseOptions({ status: 200, body: [testUser] })
                        ));
                    } else {
                        // return 401 not authorised if token is null or invalid
                        connection.mockRespond(new Response(
                            new ResponseOptions({ status: 401 })
                        ));
                    }
                }

                // fake service getUserInformation
                if (connection.request.url.endsWith(`${app_context}/getUserInformation`) && connection.request.method === RequestMethod.Get) {
                    connection.mockRespond(new Response(
                            new ResponseOptions({ status: 200, body: {
                                success: true,
                                message: '',
                                profileName: 'ROLE_ADMIN',
                                menuItems: [
                                     {
                                        "label": "pantalla-1",
                                        "items": [
                                            {
                                                "label": "pantalla-1-1",
                                                "routerLink": ["home/test"]
                                            }
                                        ]
                                    },
                                    {
                                        "label": "pantalla-2",
                                        "items": [
                                            {
                                                "label": "pantalla-2-1",
                                                "routerLink": "action 2-1"
                                            },
                                            {
                                                "label": "pantalla-2-2",
                                                "items": [
                                                    {
                                                        "label": "pantalla-2-2-1",
                                                        "routerLink": "action-2-2-1"
                                                    }
                                                ]
                                            }
                                        ]
                                    }
                                ]

                            } })

                        ));
                }
 
            }, 500);
 
        });
 
        return new Http(backend, options);
    },
    deps: [MockBackend, BaseRequestOptions]
};