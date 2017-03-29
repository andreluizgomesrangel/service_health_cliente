package br.com.mobilesaude.resource;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "requisicao")
@XmlAccessorType(XmlAccessType.FIELD)
@ManagedBean
@ViewScoped
public class Requisicao implements Comparable<Requisicao>{

	private long id;
	private long idService;
	private Date time;
	
	private String details;
	private int response;
	
	long requisicao;
	private String timeString;
	private Service service;
	
	String img;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdService() {
		return idService;
	}
	public void setIdService(long idService) {
		this.idService = idService;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getResponse() {
		return response;
	}
	
	public String getImg() {
		
		if( response == 200 ){
			return "status0.gif";
		}
		if( response == 404 ){
			return "status2.gif";
		}
		if( response == 500 ){
			return "status2.gif";
		}
		if( response == 503 ){
			return "status2.gif";
		}
		if( response == 504 ){
			return "status2.gif";
		}
		
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
	public void setResponse(int response) {
		
		if( response == 200 ){
			setDetails("Service is operating normally");
			setImg("status0.gif");
		}
		if( response == 404 ){
			setDetails("Not found!");
			setImg("status2.gif");
		}
		if( response == 500 ){
			setDetails("Internal Server Error");
			setImg("status2.gif");
		}
		if( response == 503 ){
			setDetails("Service Unavailable");
			setImg("status2.gif");
		}
		if( response == 504 ){
			setDetails("Gateway Time-out");
			setImg("status2.gif");
		}

		
		this.response = response;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		service.setLastRequest(time);
		service.setImg(img);
		if( response != 200 ){
			//System.out.println("Erro!");
			service.addErro(requisicao);
		}
		this.service = service;
	}
	public long getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(long requisicao) {
		this.requisicao = requisicao;
	}
	@Override
	public int compareTo(Requisicao o) {
		// TODO Auto-generated method stub
		if( this.id < o.id) return 1;
		if( this.id > o.id ) return -1;
		return 0;
	}
	public String getTimeString() {
		//System.out.println(time.getTime()+"   "+time.toGMTString()+"    "+time.toString() );
		//return time.toString()+"";
		return " ";
	}
	public void setTimeString(String timeString) {
		this.timeString = "hora de morfar";
	}
	
	
	
}
