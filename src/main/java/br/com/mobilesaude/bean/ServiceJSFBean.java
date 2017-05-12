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
import br.com.mobilesaude.resource.Requisicao;
import br.com.mobilesaude.resource.Service;
import br.com.mobilesaude.resource.Status_History;

@ManagedBean
@ViewScoped
public class ServiceJSFBean extends RequisicaoJSFBean {

	long id;
	String date = new String();
	
	List<Status_History> status = new ArrayList<Status_History>();
	List<Requisicao> allHistorics = new ArrayList<Requisicao>();
	List<Service> services = new ArrayList<Service>();
	
	public ServiceJSFBean(){
		
		setLists();
		setDias( 12  );
		setUrlParameter();
		setServiceHistoric( id , date );
		
		System.out.println(id);
		
	}
	
	public void setLists(){
		
		CRequisicao ch = new CRequisicao();
		CService cs = new CService();
		
		try {
			allHistorics = ch.getList();
			services = cs.getlistById();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for( int i=0; i<services.size(); i++ ){
			lastHistorics.add( allHistorics.get( i ) );
		}
	}
	
	public void setUrlParameter(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String projectId = paramMap.get("id");
		id = Integer.parseInt( projectId );
		date = paramMap.get("date");
		
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
	
	
	
}
