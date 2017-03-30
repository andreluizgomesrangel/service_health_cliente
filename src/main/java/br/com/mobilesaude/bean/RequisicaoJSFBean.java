package br.com.mobilesaude.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

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
	
	List<Requisicao> lastHour = new ArrayList<Requisicao>();
	
	public RequisicaoJSFBean(){
		CRequisicao ch = new CRequisicao();
		CService cs = new CService();
		
		try {
			allHistorics = ch.getList();
			services = cs.getlist();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for( Requisicao h : allHistorics ){
			Service s = new Service();
			s = findService( h.getIdService(), services );
			h.setService(  s  );
			System.out.println( "  GEEEEEEEEETTT  "+h.getImg() +"  "+h.getTime());
		}
		
		for(Service s : services){
			if( s.isAlert()==true ){
				problems.add(s);
			}
			
		}
		
		allHistorics.sort(null);
	}

	public boolean validate(Date now, Date start, Date end) {
		  if(now == null || start == null || end == null)
		    return false;
		  return now.after(start) && now.before(end);
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
