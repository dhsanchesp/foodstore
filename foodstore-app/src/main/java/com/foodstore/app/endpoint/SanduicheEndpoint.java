package com.foodstore.app.endpoint;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foodstore.app.dto.SanduicheDTO;
import com.foodstore.app.service.ISanduicheService;

/**
 * Classe para exposição dos serviços rest de Sanduiche
 *
 * @author dhsanchesp
 */
@Component
@Path("/sanduiche")
public class SanduicheEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(SanduicheEndpoint.class);

    @Autowired
    private ISanduicheService sanduicheService;

    private final Mapper mapper = new DozerBeanMapper();

    @GET
    @Path("/sanduiches")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaSanduiches() {

        try {
            final List<SanduicheDTO> listaSanduiches = this.sanduicheService.buscarSanduiches();

            LOGGER.debug("Lista de Sanduiches obtida com sucesso");

            return Response.ok(listaSanduiches).build();

        } catch (Exception e) {
            LOGGER.error("Não foi possível obter a lista de sanduiches.");
            return Response.status(Status.NOT_FOUND).build();
        }

    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSanduichePorCodigo(@PathParam("codigo") final Integer codigo) {

        try {
            final SanduicheDTO sanduiche = this.sanduicheService.buscarSanduichePorCodigo(codigo);

            LOGGER.debug("Sanduiche obtido com sucesso");

            return Response.ok(sanduiche).build();
        } catch (Exception e) {
            LOGGER.error("Sanduiche não encontrado");
            return Response.status(Status.NOT_FOUND).build();
        }

    }

}
