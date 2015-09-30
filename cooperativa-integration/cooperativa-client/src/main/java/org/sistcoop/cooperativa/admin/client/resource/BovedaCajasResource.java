package org.sistcoop.cooperativa.admin.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.cooperativa.representations.idm.BovedaCajaRepresentation;
import org.sistcoop.cooperativa.representations.idm.search.SearchResultsRepresentation;

/**
 * @author carlosthe19916@gmail.com
 */
@Consumes(MediaType.APPLICATION_JSON)
public interface BovedaCajasResource {

    @Path("/{bovedaCaja}")
    public BovedaCajaResource bovedaCaja(@PathParam("bovedaCaja") String bovedaCaja);

    // @POST
    // public Response create(BovedaCajaRepresentation
    // bovedaCajaRepresentation);

    @POST
    public Response create(BovedaCajaRepresentation[] bovedaCajaRepresentations);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BovedaCajaRepresentation> getAll();

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<BovedaCajaRepresentation> search(@QueryParam("boveda") String boveda,
            @QueryParam("caja") String caja, @QueryParam("estado") Boolean estado);

}
