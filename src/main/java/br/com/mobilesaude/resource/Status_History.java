package br.com.mobilesaude.resource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.mobilesaude.clients.CRequisicao;
import br.com.mobilesaude.clients.CService;


@XmlRootElement(name = "status_history")
@XmlAccessorType(XmlAccessType.FIELD)
@ManagedBean
@ViewScoped
public class Status_History {

	//status de 1 servico
	Service service = new Service();
	int []dia;
	private Calendar today = Calendar.getInstance();
	private String todayString;
	
	long id;
	
	private String []img;
	
	private String hoje = dataToString(today);
	private Calendar diaInicial = today;
	
	private String url = new String();
	
	public Status_History(){
		
	}
	
	
	public Status_History( long id, int n, Calendar primeiroDia ){
		
		//this.diaInicial = primeiroDia;
		System.out.println(">>>>>>>>>>>> primeiroDia  >>>>>>>>>>"+dataToString(primeiroDia));
		System.out.println(">>>>>>>>>>>> dia inicial  >>>>>>>>>>"+dataToString(diaInicial));
		
		this.id = id;
		img = new String[n];
		dia = new int[n];
		verifDias( id, n );
		CService cs = new CService();
		
		List<Service> services = new ArrayList<Service>();
		try {
			services = cs.getlistById();
			service = findService( id, services );
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
		
		url = new String("services.xhtml?data="+hoje);
	}
	
	Service findService( long id, List<Service> list ){
		for(Service s : list){
			if( s.getId() == id ){
				return s;
			}
		}
		return service;
	}
	
	/*
	 * Verificar getOneDay para o servico de id por n dias a partir do diaInicial
	 */
	public void verifDias( long id, int n ){
		
		System.out.println(">>>>>>>>>>>> dia inicial 2 >>>>>>>>>>"+dataToString(diaInicial));
		Calendar diax = diaInicial;
		for(int i=0; i<n;i++){
			
			String dayString = dataToString(diax);
			System.out.println(">>>>>>>>>>>> dia x >>>>>>>>>>"+dataToString(diax));
			
			int v = getOneDay( id, dayString );
			dia[i] = v;
			img[i] = new String();
			if( v==1 ){
				img[i]="status0.gif";
			}
			if( v==-1 ){
				img[i]="status2.gif";
			}
			if( v==0 ){
				img[i]="info.gif";
			}
			diaInicial.add(Calendar.DATE, -1);
		}
	}
	

	public String dataToString(Calendar c){
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		String reportDate = df.format(c.getTime());
		return reportDate;
	}
	
	public String dataToStringBR(Calendar c){
		DateFormat df = new SimpleDateFormat("MMM dd");
		String reportDate = df.format(c.getTime());
		return reportDate;
	}
	
	//obter verificacao (-1/1/0) para requisicoes (erro/sem erro/sem requisicao) de um dia
	public int getOneDay( long id, String day ){
		
		List<Requisicao> reqDia = new ArrayList<Requisicao>();
		CRequisicao cr = new CRequisicao();
		try {
			reqDia = cr.getDay( id+"" , day );
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(reqDia.size()>0){
			for( Requisicao r : reqDia ){
				if( r.getResponse()!=200 ){
					return -1;
				}
			}
			return 1;
		}
		
		return 0;
	}	

	public int[] getDia() {
		return dia;
	}

	public void setDia(int[] dia) {
		this.dia = dia;
	}

	public String getTodayString() {
		return todayString;
	}

	public void setTodayString(String todayString) {
		this.todayString = todayString;
	}


	public Calendar getToday() {
		return today;
	}


	public void setToday(Calendar today) {
		this.today = today;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String[] getImg() {
		return img;
	}


	public void setImg(String[] img) {
		this.img = img;
	}


	public Service getService() {
		return service;
	}


	public void setService(Service service) {
		this.service = service;
	}


	public String getHoje() {
		return hoje;
	}


	public void setHoje(String hoje) {
		this.hoje = hoje;
	}


	public Calendar getDiaInicial() {
		return diaInicial;
	}


	public void setDiaInicial(Calendar diaInicial) {
		this.diaInicial = diaInicial;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


}
