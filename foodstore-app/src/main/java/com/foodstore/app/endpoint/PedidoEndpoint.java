package com.foodstore.app.endpoint;

import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.foodstore.app.dto.PedidoDTO;
import com.foodstore.app.dto.SanduichePedidoDTO;
import com.foodstore.app.service.IPedidoService;

/**
 * Classe para exposição dos serviços rest de Sanduiche
 *
 * @author dhsanchesp
 */
@Component
@Path("/pedido")
public class PedidoEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoEndpoint.class);

    @Autowired
    private IPedidoService pedidoService;

    @GET
    @Path("/pedidos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaPedidos() {

        try {
            final List<PedidoDTO> listaSanduiches = this.pedidoService.consultarTodosPedidos();

            LOGGER.debug("Lista de Pedidos obtida com sucesso");

            return Response.ok(listaSanduiches).build();

        } catch (Exception e) {
            LOGGER.error("Não foi possível obter a lista de pedidos.");
            return Response.status(Status.NOT_FOUND).build();
        }

    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidoPorCodigo(@PathParam("codigo") final Integer codigo) {

        try {
            final PedidoDTO pedidoDTO = this.pedidoService.consultarPedidoPorCodigo(codigo.longValue());

            LOGGER.debug("Pedido obtido com sucesso");

            return Response.ok(pedidoDTO).build();
        } catch (final NoResultException e) {
            LOGGER.error("Pedido com código: " + codigo + " não encontrado.", e);
            return Response.status(Status.NOT_FOUND).build();
        } catch (final EmptyResultDataAccessException empty) {
            LOGGER.error("Pedido com código: " + codigo + " não encontrado.", empty);
            return Response.status(Status.NOT_FOUND).build();
        }

    }

    @POST
    @Path("/incluirpedido")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incluirPedido(@RequestBody final PedidoDTO pedidoDTO) {

        try {
            final PedidoDTO pedidoCadastrado = this.pedidoService.incluirPedido(pedidoDTO);

            LOGGER.debug("Pedido Incluido com sucesso");

            return Response.ok(pedidoCadastrado).build();

        } catch (final Exception e) {
            LOGGER.error("Erro ao incluir pedido.", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(pedidoDTO).build();
        }

    }

    @POST
    @Path("/calculaValorItem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response calculaValorItem(@RequestBody final SanduichePedidoDTO sanduichePedido) {

        try {
            final SanduichePedidoDTO sanduicheCalculado = this.pedidoService
                            .calculaValorSanduichePedido(sanduichePedido);

            LOGGER.debug("Valor do ItemPedido calculado com sucesso.");

            return Response.ok(sanduicheCalculado).build();

        } catch (final Exception e) {
            LOGGER.error("Erro ao calcular valor do sanduiche.", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(sanduichePedido).build();
        }

    }

}
