package br.com.mobilesaude.listas;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.mobilesaude.resource.Service;

@XmlRootElement(name = "collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class Services {

	@XmlElement(name = "service")
	List<Service> services = new ArrayList<Service>();

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

}
