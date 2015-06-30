package org.sistcoop.cooperativa.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.PathParam;

import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaHistorialesResource;
import org.sistcoop.cooperativa.admin.client.resource.CajaBovedaResource;
import org.sistcoop.cooperativa.models.BovedaCajaModel;
import org.sistcoop.cooperativa.models.BovedaCajaProvider;
import org.sistcoop.cooperativa.models.utils.ModelToRepresentation;
import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;

@Stateless
public class CajaBovedaResourceImpl implements CajaBovedaResource {

	@PathParam("bovedaCaja")
	private String bovedaCaja;	
	
	@Inject
	private BovedaCajaProvider bovedaCajaProvider;
	
	@Inject
	private CajaBovedaHistorialesResource cajaBovedaHistorialesResource;
	
	private BovedaCajaModel getBovedaCajaModel(){
		return bovedaCajaProvider.getBovedaCajaById(bovedaCaja);
	}

	@Override
	public BovedaCajaRepresentation boveda() {
		return ModelToRepresentation.toRepresentation(getBovedaCajaModel());
	}

	@Override
	public void update(BovedaCajaRepresentation bovedaCajaRepresentation) {
		throw new BadRequestException();
	}

	@Override
	public void enable() {
		throw new BadRequestException();
	}

	@Override
	public void disable() {
		throw new BadRequestException();
	}

	@Override
	public void remove() {
		bovedaCajaProvider.removeBovedaCaja(getBovedaCajaModel());
	}
	
	@Override
	public CajaBovedaHistorialesResource historiales() {
		return cajaBovedaHistorialesResource;
	}

}
