import { Injectable } from '@angular/core';
import { IngredienteModel } from '../model/ingrediente.model';
import { SanduicheModel } from '../model/sanduiche.model';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';

@Injectable()
export class AppFoodService {
    private _Url_Sanduiches = 'http://127.0.0.1:8080/foodstore-app/sanduiche/sanduiches';

    private _Url_Ingredintes = 'http://127.0.0.1:8080/foodstore-app/ingrediente/ingredientes';

    constructor(private _http: Http) { }

    getSanduiches(cep: string): Observable<SanduicheModel> {
        return this._http.get(this._Url_Sanduiches)
            .map((response: Response) => <SanduicheModel>response.json())
            .do(data => console.log('All: ' + JSON.stringify(data)))
            .catch(this.handleError);
    }

    getIngredientes(cep: string): Observable<IngredienteModel> {
        return this._http.get(this._Url_Ingredintes)
            .map((response: Response) => <IngredienteModel>response.json())
            .do(data => console.log('All: ' + JSON.stringify(data)))
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
