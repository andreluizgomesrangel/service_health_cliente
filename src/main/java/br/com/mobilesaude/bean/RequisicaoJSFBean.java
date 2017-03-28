package br.com.mobilesaude.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBException;

import br.com.mobilesaude.clients.CRequisicao;
import br.com.mobilesaude.clients.CService;
import br.com.mobilesaude.resource.Requisicao;
import br.com.mobilesaude.resource.Service;

@ManagedBean
@ViewScoped
public class RequisicaoJSFBean {

	
	
	List<Requisicao> newHistorics = new ArrayList<Requisicao>();
	List<Requisicao> lastHistorics = new ArrayList<Requisicao>();
	
	List<Requisicao> allHistorics = new ArrayList<Requisicao>();
	List<Service> services = new ArrayList<Service>();
	
	List<Service> problems = new ArrayList<Service>();
	
	
	public RequisicaoJSFBean() throws JAXBException{
		CRequisicao ch = new CRequisicao();
		CService cs = new CService();
		
		allHistorics = ch.getList();
		services = cs.getlist();
		
		
		
		
		for( Requisicao h : allHistorics ){
			h.setService(  findService( h.getIdService(), services )  );
			//System.out.println("OLHAAAA A HOOOOOOOORAAAAAA: "+h.getTime() );
			
		}
		
		
		//allHistorics.get(0).get
		
		FacesContext context = FacesContext.getCurrentInstance();
		Iterator<String> clients = context.getClientIdsWithMessages();

		for(Service s : services){
			if( s.isAlert()==true ){
				problems.add(s);
				//System.out.print("service: "+s.getId()+" | chamadas: ");
				//for( Integer i : s.getErrors() ){
					//System.out.print( "  > "+i+" " );
				//}
				//System.out.println(" ");
			}
			
		}
		
		allHistorics.sort(null);
	}

	public Service findService( long id, List<Service> list ){
		for( Service s : list ){
			if( id == s.getId() ) return s;
		}
		return null;
	}
	
	public void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		
		
		//context.getClientIdsWithMessages()
		
		
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();
	}
	
	public List<Requisicao> getNewHistorics() {
		return newHistorics;
	}

	public void setNewHistorics(List<Requisicao> newHistorics) {
		this.newHistorics = newHistorics;
	}

	public List<Requisicao> getLastHistorics() {
		return lastHistorics;
	}

	public void setLastHistorics(List<Requisicao> lastHistorics) {
		this.lastHistorics = lastHistorics;
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

	public List<Service> getProblems() {
		return problems;
	}

	public void setProblems(List<Service> problems) {
		this.problems = problems;
	}
	
}
