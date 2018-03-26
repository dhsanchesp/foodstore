import { PedidoItem } from './pedido.item.model';

export class PedidoModel {

    constructor(
        public codigoPedido: number,
        public nomeCliente: string,
        private cpf: string,
        public endereco: string,
        public telefone: string,
        public valorPedidoSemDesconto: number,
        public valorPedidoComDesconto: number,
        public valorPedidoDesconto: number,
        public sanduiches: Array<PedidoItem>
    ) {}

}
