package br.com.mobilesaude.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBException;

import br.com.mobilesaude.clients.CRequisicao;
import br.com.mobilesaude.clients.CService;
import br.com.mobilesaude.resource.EstatisticasServicoDia;
import br.com.mobilesaude.resource.Requisicao;
import br.com.mobilesaude.resource.Service;
import br.com.mobilesaude.resource.Status_History;
import br.com.mobilesaude.resource.TipoDeResposta;

@ManagedBean
@ViewScoped
public class ServiceDayResponseBean extends RequisicaoJSFBean {

	private long id;
	private String date = new String();

	private String Estatistica = new String();

	List<Status_History> status = new ArrayList<Status_History>();
	List<Requisicao> allHistorics = new ArrayList<Requisicao>();
	List<Service> services = new ArrayList<Service>();
	EstatisticasServicoDia estatisticas = new EstatisticasServicoDia();
	private int response;

	public ServiceDayResponseBean() {
		setLists();
		setUrlParameter();
		setServiceHistoric(id, date);
	}

	public void setLists() {

		CRequisicao ch = new CRequisicao();
		CService cs = new CService();

		try {
			allHistorics = ch.getList();
			services = cs.getlistSortById();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < services.size(); i++) {
			lastHistorics.add(allHistorics.get(i));
		}
	}

	public void setUrlParameter() {

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String projectId = paramMap.get("id");
		id = Integer.parseInt(projectId);
		String projectResponse = paramMap.get("response");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> response url"+projectResponse);
		response = Integer.parseInt(projectId);
		date = paramMap.get("date");
		CRequisicao ch = new CRequisicao();
		try {
			estatisticas = ch.getDayResponse(projectId, date, projectResponse);
			setarEstatisticas(estatisticas);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setarEstatisticas(EstatisticasServicoDia estatistica) {
		StringBuilder string = new StringBuilder();
		List<TipoDeResposta> respostas = estatistica.getRespostas();
		string.append("| quantidade de consultas - " + estatisticas.getRequisicoes().size() + " | ");
		for (int i = 0; i < respostas.size(); i++) {
			string.append(
					" Codigo " + respostas.get(i).getResponse() + " - " + respostas.get(i).getPorcentagem() + " |");
		}
		Estatistica = string.toString();
	}

	public List<Requisicao> getAllHistorics() {
		return allHistorics;
	}

	public void setAllHistorics(List<Requisicao> allHistorics) {
		this.allHistorics = allHistorics;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEstatistica() {
		return Estatistica;
	}

	public void setEstatistica(String estatistica) {
		Estatistica = estatistica;
	}

	public List<Status_History> getStatus() {
		return status;
	}

	public void setStatus(List<Status_History> status) {
		this.status = status;
	}

	public EstatisticasServicoDia getEstatisticas() {
		return estatisticas;
	}

	public void setEstatisticas(EstatisticasServicoDia estatisticas) {
		this.estatisticas = estatisticas;
	}

	public int getResponse() {
		return response;
	}

	public void setResponse(int response) {
		this.response = response;
	}

}