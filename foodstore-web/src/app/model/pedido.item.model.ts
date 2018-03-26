import { PedidoItemIngrediente } from './pedido.item.ingrediente.model';

export class PedidoItem {

    constructor(
        public codigoItemPedido: number,
        public codigoSanduiche: number,
        public valorSemDesconto: number,
        public valorComDescont: number,
        public valorDescont: number,
        public promoLight: boolean,
        public promoCarne: boolean,
        public promoQueijo: boolean,
        public ingredientesItemPedido: Array<PedidoItemIngrediente>
    ) {}
}
