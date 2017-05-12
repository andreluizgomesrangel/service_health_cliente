package br.com.mobilesaude.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import br.com.mobilesaude.clients.CRequisicao;
import br.com.mobilesaude.clients.CService;
import br.com.mobilesaude.resource.Requisicao;
import br.com.mobilesaude.resource.Service;
import br.com.mobilesaude.resource.Status_History;

@ManagedBean
@ViewScoped
public class RequisicaoJSFBean {
	
	List<Requisicao> newHistorics = new ArrayList<Requisicao>();
	List<Requisicao> lastHistorics = new ArrayList<Requisicao>();
	List<Requisicao> allHistorics = new ArrayList<Requisicao>();
	List<Service> services = new ArrayList<Service>();
	List<Service> problems = new ArrayList<Service>();
	List<Requisicao> lastHour = new ArrayList<Requisicao>();
	List<Status_History> status = new ArrayList<Status_History>();
	List<Requisicao> serviceHistoric = new ArrayList<Requisicao>();
	long serviceId = 7;
	int qtdDias = 12;
	String []dias = new String[qtdDias];
	String []diasUS = new String[qtdDias];
	String [][]urlParam;
	int qtdServices; 
	int parametrourl;
	
	public RequisicaoJSFBean(){
		
		setDias( qtdDias  ); // ( v ) 
		setLists();
		qtdServices = services.size();
		getDays();
		setServicesInRequests();
		setServicesInAlert();
	}
	
	public String[] getDiasUS() {
		return diasUS;
	}

	public void setDiasUS(String[] diasUS) {
		this.diasUS = diasUS;
	}

	public String[][] getUrlParam() {
		return urlParam;
	}

	public void setUrlParam(String[][] urlParam) {
		this.urlParam = urlParam;
	}

	
	public void setUrlParameter(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String projectId = paramMap.get("parametrourl");
		parametrourl = Integer.parseInt( projectId ); 
		System.out.println(">>>>>>>>>> parametrourl  >>>>>>>  "+parametrourl);
		
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
		
		if(services.size()!=0){
			for( int i=0; i<services.size(); i++ ){
				lastHistorics.add( allHistorics.get( i ) );
			}
			
			urlParam = new String[qtdDias][services.size()];
			for(int i=0; i<services.size(); i++){
				for( int j=0; j<qtdDias; j++ ){
					urlParam[j][i] = dataToString(diasUS[j], services.get(i).getId());
					System.out.print(" "+urlParam[j][i]);
				}System.out.println();
			}
		}
		
	}
	
	public void setServicesInAlert(){
		
		for(Service s : services){
			if( s.isAlert()==true ){
				problems.add(s);
			}
			
		}
		
	}
	
	public void setServicesInRequests(){
		
		for( Requisicao h : allHistorics ){ 
			Service s = new Service();
			s = findService( h.getIdService(), services );
		}
		
	}
	
	public void setServiceHistoric(long serviceId, String day){
		
		CRequisicao ch = new CRequisicao();
		try {
			serviceHistoric = ch.getDay(serviceId+"", day );
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*if(serviceHistoric == null || serviceHistoric.size()==0){
			System.out.println(">>>>>NUUUUUULLLLLL");
			serviceHistoric.add(new Requisicao());
		}*/
		
	}
	
	//obter o status de cada service de no periodo de qtdDias
	public void getDays(){
		
		for( int i=0; i<qtdServices; i++ ){
			Status_History s = new Status_History( services.get(i).getId() , qtdDias );
			
			status.add( s );
		}
	}
	
	public void setDias( int qtdDias  ){
		
		Calendar d = Calendar.getInstance();
		for( int i=0; i<qtdDias; i++ ){
			
			dias[i] 	=  dataToStringBR(d);
			diasUS[i]   =  dataToString(d);
			d.add(Calendar.DATE, -1);
			
		}
		
	}
	
	public String dataToString( String c, long id ){
		return "serviceday.xhtml?id="+id+"&date="+c;
	}
	
	public String dataToString( Calendar c, long id ){
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String reportDate = df.format(c.getTime());
		return "serviceday.xhtml?id="+id+"&date="+reportDate;
	}
	
	public String dataToString2(Calendar c){
		DateFormat df = new SimpleDateFormat("hh:mm:ss");
		String reportDate = df.format(c.getTime());
		return reportDate;
	}
	
	public String dataToString(Calendar c){
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String reportDate = df.format(c.getTime());
		return reportDate;
	}

	public String dataToStringBR(Calendar c){
		DateFormat df = new SimpleDateFormat("dd MMM");
		String reportDate = df.format(c.getTime());
		return reportDate;
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

	public List<Requisicao> getLastHour() {
		return lastHour;
	}

	public void setLastHour(List<Requisicao> lastHour) {
		this.lastHour = lastHour;
	}

	public List<Status_History> getStatus() {
		return status;
	}

	public void setStatus(List<Status_History> status) {
		this.status = status;
	}

	public int getQtdDias() {
		return qtdDias;
	}

	public void setQtdDias(int qtdDias) {
		this.qtdDias = qtdDias;
	}

	public String[] getDias() {
		return dias;
	}

	public void setDias(String[] dias) {
		this.dias = dias;
	}

	public int getQtdServices() {
		return qtdServices;
	}

	public void setQtdServices(int qtdServices) {
		this.qtdServices = qtdServices;
	}

	public List<Requisicao> getServiceHistoric() {
		return serviceHistoric;
	}

	public void setServiceHistoric(List<Requisicao> serviceHistoric) {
		this.serviceHistoric = serviceHistoric;
	}

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	public int getParametrourl() {
		return parametrourl;
	}

	public void setParametrourl(int parametrourl) {
		this.parametrourl = parametrourl;
	}
	
}
