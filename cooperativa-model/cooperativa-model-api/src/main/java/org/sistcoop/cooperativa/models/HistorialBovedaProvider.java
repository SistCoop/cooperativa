package org.sistcoop.cooperativa.models;

import javax.ejb.Local;

import org.sistcoop.cooperativa.provider.Provider;

@Local
public interface HistorialBovedaProvider extends Provider {

	HistorialBovedaModel addHistorialBoveda(BovedaModel bovedaModel);

}