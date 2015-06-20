package org.sistcoop.cooperativa.admin.client.resource;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.HistorialBovedaRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Consumes(MediaType.APPLICATION_JSON)
public interface BovedaHistorialesResource {

	@Path("/{historial}")
	public BovedaHistorialResource historial(@PathParam("historial") String historial);

	@POST	
	public Response create(HistorialBovedaRepresentation historialBovedaRepresentation);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<HistorialBovedaRepresentation> search(
			@QueryParam("estado") @DefaultValue(value = "true") boolean estado);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<HistorialBovedaRepresentation> search(
			@QueryParam("desde") Date desde,
			@QueryParam("hasta") Date hasta,
			@QueryParam("firstResult") Integer firstResult,
			@QueryParam("maxResults") Integer maxResults);

}
