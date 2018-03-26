import { Injectable } from '@angular/core';
import { PedidoModel } from '../model/pedido.model';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';

@Injectable()
export class PedidoService {
    private _Url = 'http://127.0.0.1:8080/foodstore-app';

    constructor(private _http: Http) { }

    consultarPedido(codigoPedido: number): Observable<PedidoModel> {
        const url = '${this._Url}/pedido/${codigoPedido}';
        return this._http.get(url)
            .map((response: Response) => <PedidoModel>response.json())
            .do(data => console.log('All: ' + JSON.stringify(data)))
            .catch(this.handleError);
    }

    consultarPedidoPorCodigo(codigoPedido: number): Observable<PedidoModel> {
        return this._http.get(this._Url)
            .map((response: Response) => <PedidoModel>response.json())
            .do(data => console.log('All: ' + JSON.stringify(data)))
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}
