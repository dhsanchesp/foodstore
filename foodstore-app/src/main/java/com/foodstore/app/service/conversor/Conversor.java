package com.foodstore.app.service.conversor;

import java.util.ArrayList;
import java.util.List;

import com.foodstore.app.dto.IngredienteDTO;
import com.foodstore.app.dto.IngredienteSanduichePedidoDTO;
import com.foodstore.app.dto.PedidoDTO;
import com.foodstore.app.dto.SanduichePedidoDTO;
import com.foodstore.app.entity.IngredienteEntity;
import com.foodstore.app.entity.IngredienteSanduichePedidoEntity;
import com.foodstore.app.entity.PedidoEntity;
import com.foodstore.app.entity.SanduichePedidoEntity;

/**
 * Classe de Conversores de Entity para DTO e vice versa. Utilizar esta classe quandoo DozerHelper não converte
 * corretamente o objeto.
 */
public class Conversor {

    /**
     * Converte pedido entity em DTO.
     *
     * @param pedidoEntity o pedido entity
     * @return o pedido DTO
     */
    public static PedidoDTO convertePedidoEntityEmDTO(final PedidoEntity pedidoEntity) {

        final PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setCodigoPedido(pedidoEntity.getCodigoPedido());
        pedidoDTO.setCpf(pedidoEntity.getCpf());
        pedidoDTO.setNomeCliente(pedidoEntity.getNomeCliente());
        pedidoDTO.setEndereco(pedidoEntity.getEndereco());
        pedidoDTO.setTelefone(pedidoEntity.getTelefone());

        pedidoDTO.setValorPedidoComDesconto(pedidoEntity.getValorPedidoComDesconto());
        pedidoDTO.setValorPedidoDesconto(pedidoEntity.getValorPedidoDesconto());
        pedidoDTO.setValorPedidoSemDesconto(pedidoEntity.getValorPedidoSemDesconto());

        final List<SanduichePedidoDTO> listaSanduichesDTO = new ArrayList<>();
        SanduichePedidoDTO sanduicheDTO = new SanduichePedidoDTO();
        for (final SanduichePedidoEntity sanduicheEntity : pedidoEntity.getSanduiches()) {
            sanduicheDTO = new SanduichePedidoDTO();

            sanduicheDTO.setCodigoItemPedido(sanduicheEntity.getCodigoItemPedido());
            sanduicheDTO.setCodigoPedido(sanduicheEntity.getPedidoEntity().getCodigoPedido());
            sanduicheDTO.setCodigoSanduiche(sanduicheEntity.getCodigoSanduiche());
            sanduicheDTO.setNomeSanduiche(sanduicheEntity.getNomeSanduiche());
            sanduicheDTO.setDescricaoSanduiche(sanduicheEntity.getDescricaoSanduiche());

            sanduicheDTO.setValorDescontoPromoCarne(sanduicheEntity.getValorDescontoPromoCarne());
            sanduicheDTO.setValorDescontoPromoLight(sanduicheEntity.getValorDescontoPromoLight());
            sanduicheDTO.setValorDescontoPromoQueijo(sanduicheEntity.getValorDescontoPromoQueijo());

            sanduicheDTO.setValorDescontoTotal(sanduicheEntity.getValorDescontoTotal());
            sanduicheDTO.setValorSanduicheComDesconto(sanduicheEntity.getValorSanduicheComDesconto());
            sanduicheDTO.setValorSanduicheSemDesconto(sanduicheEntity.getValorSanduicheSemDesconto());

            sanduicheDTO.setPromoCarne(sanduicheEntity.getPromoCarne());
            sanduicheDTO.setPromoLight(sanduicheEntity.getPromoLight());
            sanduicheDTO.setPromoQueijo(sanduicheEntity.getPromoQueijo());

            final List<IngredienteSanduichePedidoDTO> listaIngredientesDTO = new ArrayList<>();
            IngredienteSanduichePedidoDTO ingredienteDTO = new IngredienteSanduichePedidoDTO();

            for (final IngredienteSanduichePedidoEntity ingredienteEntity : sanduicheEntity.getIngredientes()) {
                ingredienteDTO = new IngredienteSanduichePedidoDTO();

                ingredienteDTO.setCodigoIngrediente(ingredienteEntity.getCodigoIngrediente());
                ingredienteDTO.setNomeIngrediente(ingredienteEntity.getNomeIngrediente());
                ingredienteDTO.setQuantidadeIngrediente(ingredienteEntity.getQuantidadeIngrediente());
                ingredienteDTO.setValorIngrediente(ingredienteEntity.getValorIngrediente());

                listaIngredientesDTO.add(ingredienteDTO);
            }

            sanduicheDTO.setIngredientes(listaIngredientesDTO);

            listaSanduichesDTO.add(sanduicheDTO);
        }
        pedidoDTO.setSanduiches(listaSanduichesDTO);

        return pedidoDTO;
    }

    /**
     * Converte um pedido DTO em um pedido entity.
     *
     * @param pedidoDTO the pedido DTO
     * @param pedidoEntity the pedido entity
     */
    public static PedidoEntity convertePedidoDTOEmPedidoEntity(final PedidoDTO pedidoDTO) {

        final PedidoEntity pedidoEntity = new PedidoEntity();

        pedidoEntity.setCpf(pedidoDTO.getCpf());
        pedidoEntity.setNomeCliente(pedidoDTO.getNomeCliente());
        pedidoEntity.setTelefone(pedidoDTO.getTelefone());
        pedidoEntity.setEndereco(pedidoDTO.getEndereco());

        pedidoEntity.setValorPedidoSemDesconto(pedidoDTO.getValorPedidoSemDesconto());
        pedidoEntity.setValorPedidoComDesconto(pedidoDTO.getValorPedidoComDesconto());
        pedidoEntity.setValorPedidoDesconto(pedidoDTO.getValorPedidoDesconto());

        final List<SanduichePedidoEntity> listaSanduiches = new ArrayList<>();
        SanduichePedidoEntity sanduichePedidoEntity;
        for (final SanduichePedidoDTO sanduicheDTO : pedidoDTO.getSanduiches()) {
            sanduichePedidoEntity = new SanduichePedidoEntity();
            sanduichePedidoEntity.setCodigoSanduiche(sanduicheDTO.getCodigoSanduiche());
            sanduichePedidoEntity.setNomeSanduiche(sanduicheDTO.getNomeSanduiche());
            sanduichePedidoEntity.setDescricaoSanduiche(sanduicheDTO.getDescricaoSanduiche());
            sanduichePedidoEntity.setValorDescontoPromoCarne(sanduicheDTO.getValorDescontoPromoCarne());
            sanduichePedidoEntity.setValorDescontoPromoLight(sanduicheDTO.getValorDescontoPromoLight());
            sanduichePedidoEntity.setValorDescontoPromoQueijo(sanduicheDTO.getValorDescontoPromoQueijo());
            sanduichePedidoEntity.setValorDescontoTotal(sanduicheDTO.getValorDescontoTotal());
            sanduichePedidoEntity.setValorSanduicheComDesconto(sanduicheDTO.getValorSanduicheComDesconto());
            sanduichePedidoEntity.setValorSanduicheSemDesconto(sanduicheDTO.getValorSanduicheSemDesconto());

            sanduichePedidoEntity.setPromoCarne(sanduicheDTO.getPromoCarne());
            sanduichePedidoEntity.setPromoLight(sanduicheDTO.getPromoLight());
            sanduichePedidoEntity.setPromoQueijo(sanduicheDTO.getPromoQueijo());

            final List<IngredienteSanduichePedidoEntity> ingredientesEntity = new ArrayList<>();
            IngredienteSanduichePedidoEntity ingredienteEntity = new IngredienteSanduichePedidoEntity();

            for (final IngredienteSanduichePedidoDTO ingrediente : sanduicheDTO.getIngredientes()) {
                ingredienteEntity = new IngredienteSanduichePedidoEntity();
                ingredienteEntity.setCodigoIngrediente(ingrediente.getCodigoIngrediente());
                ingredienteEntity.setNomeIngrediente(ingrediente.getNomeIngrediente());
                ingredienteEntity.setQuantidadeIngrediente(ingrediente.getQuantidadeIngrediente());
                ingredienteEntity.setValorIngrediente(ingrediente.getValorIngrediente());
                ingredienteEntity.setSanduiche(sanduichePedidoEntity);

                ingredientesEntity.add(ingredienteEntity);
            }
            sanduichePedidoEntity.setIngredientes(ingredientesEntity);

            sanduichePedidoEntity.setPedidoEntity(pedidoEntity);
            listaSanduiches.add(sanduichePedidoEntity);
        }

        pedidoEntity.setSanduiches(listaSanduiches);

        return pedidoEntity;
    }

    /**
     * Converte uma lista de pedido entity em uma lista de pedido DTO.
     *
     * @param listaPedidosEntity the lista pedidos entity
     * @return uma lista de pedidos dto
     */
    public static List<PedidoDTO> converteListaPedidoEntityEmDTO(final List<PedidoEntity> listaPedidosEntity) {

        final List<PedidoDTO> listaPedidosDTO = new ArrayList<>();

        for (final PedidoEntity pedidoEntity : listaPedidosEntity) {
            listaPedidosDTO.add(convertePedidoEntityEmDTO(pedidoEntity));
        }

        return listaPedidosDTO;
    }

    /**
     * Converte um ingrediente entity em um ingrediente DTO.
     *
     * @param ingredienteEntity the ingrediente entity
     * @return the ingrediente convertido
     */
    public static IngredienteDTO converteIngredienteEntityEmDTO(final IngredienteEntity ingredienteEntity) {

        IngredienteDTO ingredienteDTO = new IngredienteDTO();

        ingredienteDTO.setCodigoIngrediente(ingredienteEntity.getCodigoIngrediente());
        ingredienteDTO.setNomeIngrediente(ingredienteEntity.getNomeIngrediente());
        ingredienteDTO.setValorIngrediente(ingredienteEntity.getValorIngrediente());

        return ingredienteDTO;
    }

    /**
     * Converte um ingrediente dto em um ingrediente entity.
     *
     * @param ingredienteDTO o ingrediente DTO
     * @return o ingrediente convertido
     */
    public static IngredienteEntity converteIngredienteDTOEmEntity(final IngredienteDTO ingredienteDTO) {

        IngredienteEntity ingredienteEntity = new IngredienteEntity();

        ingredienteEntity.setCodigoIngrediente(ingredienteDTO.getCodigoIngrediente());
        ingredienteEntity.setNomeIngrediente(ingredienteDTO.getNomeIngrediente());
        ingredienteEntity.setValorIngrediente(ingredienteDTO.getValorIngrediente());

        return ingredienteEntity;
    }

}
