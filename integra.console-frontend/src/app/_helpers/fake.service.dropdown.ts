import { Response, ResponseOptions } from '@angular/http';

export class FakeServiceDropdown {

    getServiceDropdown():Response {
        let result = [
            { processId: "UPDATE-PWO-VERSION-ATM-ROUTE", description: "Actualiza la versión de payworks del ATM.", application: "ATM", category: "SRV", sequence: "2001"},
            { processId: "GET-PAYMENTS-BY-ACCOUNT-ROUTE", description: "Consulta de pagos.", application: "BRM", category: "SRV", sequence: "2012"},
        ]
        
        return new Response(
            new ResponseOptions({ status: 200, body: result })
        )
    }

}