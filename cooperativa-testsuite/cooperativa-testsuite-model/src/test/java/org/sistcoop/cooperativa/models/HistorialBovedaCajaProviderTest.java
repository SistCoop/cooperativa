package org.sistcoop.cooperativa.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sistcoop.cooperativa.models.enums.OrigenTransaccionBovedaCaja;
import org.sistcoop.cooperativa.models.jpa.BovedaAdapter;
import org.sistcoop.cooperativa.models.jpa.BovedaCajaAdapter;
import org.sistcoop.cooperativa.models.jpa.CajaAdapter;
import org.sistcoop.cooperativa.models.jpa.HistorialBovedaCajaAdapter;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaBovedaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.JpaHistorialBovedaCajaProvider;
import org.sistcoop.cooperativa.models.jpa.entities.HistorialBovedaCajaEntity;
import org.sistcoop.cooperativa.provider.Provider;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public class HistorialBovedaCajaProviderTest {

	@Inject
	private BovedaProvider bovedaProvider;
	
	@Inject
	private CajaProvider cajaProvider;
	
	@Inject
	private BovedaCajaProvider bovedaCajaProvider;
	
	@Inject
	private HistorialBovedaCajaProvider historialBovedaCajaProvider;	
	
	@Deployment
	public static WebArchive createDeployment() {
		File[] dependencies = Maven.resolver()
				.resolve("org.slf4j:slf4j-simple:1.7.10")
				.withoutTransitivity().asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
				/**persona-model-api**/
				.addClass(Provider.class)										
				.addClass(BovedaProvider.class)
				.addClass(CajaProvider.class)
				.addClass(BovedaCajaProvider.class)
				.addClass(HistorialBovedaCajaProvider.class)
				
				.addPackage(HistorialBovedaCajaModel.class.getPackage())				
				
				.addPackage(OrigenTransaccionBovedaCaja.class.getPackage())
				
				/**persona-model-jpa**/
				.addClass(JpaBovedaProvider.class)
				.addClass(BovedaAdapter.class)
				
				.addClass(JpaCajaProvider.class)
				.addClass(CajaAdapter.class)
				
				.addClass(JpaBovedaCajaProvider.class)
				.addClass(BovedaCajaAdapter.class)
				
				.addClass(JpaHistorialBovedaCajaProvider.class)
				.addClass(HistorialBovedaCajaAdapter.class)
				
				.addPackage(HistorialBovedaCajaEntity.class.getPackage())
				
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")			
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");

		war.addAsLibraries(dependencies);

		return war;
	}			
	   
	@Test
	public void addBovedaCaja() {
		BovedaModel bovedaModel = bovedaProvider.addBoveda("01", "PEN", "Boveda nuevos soles");
		CajaModel cajaModel = cajaProvider.addCaja("01", "Caja-01");		
		BovedaCajaModel bovedaCajaModel = bovedaCajaProvider.addBovedaCaja(bovedaModel, cajaModel);
			
		HistorialBovedaCajaModel model = historialBovedaCajaProvider.addHistorialBovedaCajaModel(bovedaCajaModel);
		
		assertThat(model, is(notNullValue()));
		assertThat(model.getId(), is(notNullValue()));
		assertThat(model.getFechaApertura(), is(notNullValue()));
		assertThat(model.getHoraApertura(), is(notNullValue()));
		assertThat(model.getEstado(), is(true));	
	}	
	
}
