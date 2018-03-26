package com.foodstore.app.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.foodstore.app.endpoint.IngredienteEndpoint;
import com.foodstore.app.endpoint.PedidoEndpoint;
import com.foodstore.app.endpoint.SanduicheEndpoint;

@Component
@ApplicationPath("/foodstore-app")
public class FoodstoreJerseyConfig extends ResourceConfig {

	public FoodstoreJerseyConfig() {
		this.register(IngredienteEndpoint.class);
		this.register(SanduicheEndpoint.class);
		this.register(PedidoEndpoint.class);
	}

}
