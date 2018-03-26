package com.foodstore.app.endpoint;

import java.util.List;

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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.foodstore.app.dto.IngredienteDTO;
import com.foodstore.app.service.IIngredienteService;

/**
 * Classe para exposição dos serviços rest de Ingrediente
 *
 * @author dhsanchesp
 */
@Component
@Path("/ingrediente")
public class IngredienteEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngredienteEndpoint.class);

    @Autowired
    private IIngredienteService ingredienteService;

    @GET
    @Path("/ingredientes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaIngredientes() {

        try {
            final List<IngredienteDTO> listaIngredientesDTO = this.ingredienteService.buscarTodosIngredientes();

            LOGGER.debug("Lista de ingredientes obtida com sucesso.");

            return Response.ok(listaIngredientesDTO).build();
        } catch (Exception e) {
            LOGGER.error("Não foi possível obter a lista de ingredientes.");
            return Response.status(Status.NOT_FOUND).build();
        }

    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngredientePorCodigo(@PathParam("codigo") final Integer codigoIngrediente) {

        try {
            final IngredienteDTO listaIngredientesDTO = this.ingredienteService
                            .buscarIngredientePorCodigo(codigoIngrediente);

            LOGGER.debug("Ingrediente obtido com sucesso.");

            return Response.ok(listaIngredientesDTO).build();

        } catch (Exception e) {
            LOGGER.error("Ingrediente não encontrado");
            return Response.status(Status.NOT_FOUND).build();
        }

    }

    @POST
    @Path("/atualizar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizaIngrediente(@RequestBody final IngredienteDTO ingredienteDTO) {

        try {
            final IngredienteDTO listaIngredientesDTO = this.ingredienteService.atualizarIngrediente(ingredienteDTO);

            LOGGER.debug("Ingrediente atualizado com sucesso.");

            return Response.ok(listaIngredientesDTO).build();

        } catch (Exception e) {
            LOGGER.error("Não foi possivel atualizar o ingrediente");
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

    }

}
