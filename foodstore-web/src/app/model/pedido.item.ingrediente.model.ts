import { IngredienteModel } from './ingrediente.model';

export class PedidoItemIngrediente {

    constructor(
        public codigoItemPedidoIngrediente: number,
        public codigoIngrediente: number,
        public ingrediente: IngredienteModel,
        public quantidadeIngrediente: number
    ) {}
}
