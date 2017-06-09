package br.com.mobilesaude.listas;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.mobilesaude.resource.Requisicao;
import br.com.mobilesaude.resource.Service;

@XmlRootElement(name = "collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class Requisicoes {

	@XmlElement(name = "requisicao")
	List<Requisicao> hitorics = new ArrayList<Requisicao>();

	public List<Requisicao> getHitorics() {
		return hitorics;
	}

	public void setHitorics(List<Requisicao> hitorics) {
		this.hitorics = hitorics;
	}

}
