package br.com.mobilesaude.resource;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EstatisticasServicoDia")
@XmlAccessorType(XmlAccessType.FIELD)
@ManagedBean
@ViewScoped
public class EstatisticasServicoDia {
	@XmlElement(name = "Service")
	private long idService;
	@XmlElement(name = "Dia")
	private String dia;
	@XmlElementWrapper(name = "TiposDeRespostas")
	@XmlElement(name = "TipoDeResposta")
	private List<TipoDeResposta> Respostas = new ArrayList<TipoDeResposta>();
	@XmlElementWrapper(name = "Requisicoes")
	@XmlElement(name = "Requisicao")
	private List<Requisicao> Requisicoes = new ArrayList<Requisicao>();
	private int qtdRespostas;
	private int qtdRequisicoes;
	
	
	public EstatisticasServicoDia() {

	}

	public EstatisticasServicoDia(long id, String dia) {
		this.idService = id;
		this.dia = dia;
	}

	public List<TipoDeResposta> getRespostas() {
		return Respostas;
	}

	public void setRespostas(List<TipoDeResposta> respostas) {
		Respostas = respostas;
	}

	public long getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public List<Requisicao> getRequisicoes() {
		return Requisicoes;
	}

	public void setRequisicoes(List<Requisicao> requisicoes) {
		Requisicoes = requisicoes;
	}

	public void setIdService(long idService) {
		this.idService = idService;
	}

	public String toString() {
		return "id: " + idService + " dia:" + dia + " qtdRespostas:" + Respostas.size() + " qtdRequisicoes: "
				+ Requisicoes.size();
	}

	public int getQtdRespostas() {
		qtdRespostas = Respostas.size();
		return qtdRespostas;
	}

	public void setQtdRespostas(int qtdRespostas) {
		this.qtdRespostas = qtdRespostas;
	}

	public int getQtdRequisicoes() {
		qtdRequisicoes = Requisicoes.size();
		return qtdRequisicoes;
	}

	public void setQtdRequisicoes(int qtdRequisicoes) {
		this.qtdRequisicoes = qtdRequisicoes;
	}

}
