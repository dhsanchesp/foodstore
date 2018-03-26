import { Injectable } from '@angular/core';
import { SanduicheModel } from '../model/sanduiche.model';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';

@Injectable()
export class SanduicheService {
    private _Url = 'http://localhost:8080/foodstore-app/sanduiche/';
    private _Url_Sanduiche_List = 'http://localhost:8080/foodstore-app/sanduiche/sanduiches';

    constructor(private _http: Http) { }

    consultarSanduiche(codigoSanduiche: number): Observable<SanduicheModel> {
        return this._http.get(this._Url + codigoSanduiche)
            .map((response: Response) => <SanduicheModel>response.json())
            .do(data => console.log('All: ' + JSON.stringify(data)))
            .catch(this.handleError);
    }

    listarSanduiches(): Observable<SanduicheModel[]> {
        return this._http.get(this._Url_Sanduiche_List)
            .map((response: Response) => <SanduicheModel[]>response.json())
            .do(data => console.log('All: ' + JSON.stringify(data)))
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
