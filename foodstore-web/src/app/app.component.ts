import { Component } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { PedidoModel } from '../app/model/pedido.model';
import { PedidoService } from '../app/service/pedido.service';
import { SanduicheService } from './service/sanduiche.service';
import { SanduicheModel } from './model/sanduiche.model';
import { HEROES } from './mock/mock-heroes';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  pedido: PedidoModel;
  sanduiche: SanduicheModel;
  heroes = HEROES;

  codigoPedido: number;
  codigoSanduiche: number;

  sanduiches[] = SanduicheModel;

    constructor(private _pedidoService: PedidoService, private _sanduicheService: SanduicheService) {

    }

    ConsultarPedido(): void {
      this._pedidoService.consultarPedido(this.codigoPedido)
          .subscribe((data: PedidoModel) => this.pedido = data,
          error => console.log(error));
    }

    ConsultarSanduiche(codigoSanduiche: number): void {
      this._sanduicheService.consultarSanduiche(this.codigoSanduiche)
          .subscribe((data: SanduicheModel) => this.sanduiche = data,
          error => console.log(error));
    }

    ConsultarSanduiches(): void {
      this._sanduicheService.listarSanduiches()
          .subscribe((data: SanduicheModel[]) => this.sanduiches = data,
          error => console.log(error));
    }
}
