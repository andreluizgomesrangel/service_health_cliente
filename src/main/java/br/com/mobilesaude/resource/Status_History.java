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

	Service service = new Service();
	int []dia;
	private Calendar today = Calendar.getInstance();
	private String todayString;
	
	long id;
	
	private String []img;
	
	public Status_History(){
		
	}
	
	
	public Status_History( long id, int n ){
		this.id = id;
		img = new String[n];
		dia = new int[n];
		verifDias( id, n );
		CService cs = new CService();
		
		List<Service> services = new ArrayList<Service>();
		try {
			services = cs.getlistById();
			service = findService( id, services );
			//System.out.println(">>>>>>>>>>> "+service.getName());
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
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
	 * Verificar getOneDay para o servico de id por n dias a partir de ontem
	 */
	
	public void verifDias( long id, int n ){
		
		for(int i=0; i<n;i++){
			today.add(Calendar.DATE, -1);
			String dayString = dataToString(today);
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
			//System.out.println("DIIIIIIAAAAA: "+today.getTime()+" >>>> "+v);
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


}
