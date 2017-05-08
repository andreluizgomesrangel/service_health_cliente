package br.com.mobilesaude.resource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class Requisicao{

	private long id;
	private long idService;
	private Date time;
	
	private String details;
	private int response;
	
	long requisicao;
	private String timeString;
	
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
			return "status3.gif";
		}
		if( response == 500 ){
			return "status3.gif";
		}
		if( response == 503 ){
			return "status3.gif";
		}
		if( response == 504 ){
			return "status3.gif";
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
			setImg("status3.gif");
		}
		if( response == 500 ){
			setDetails("Internal Server Error");
			setImg("status3.gif");
		}
		if( response == 503 ){
			setDetails("Service Unavailable");
			setImg("status3.gif");
		}
		if( response == 504 ){
			setDetails("Gateway Time-out");
			setImg("status3.gif");
		}

		
		this.response = response;
	}
	
	public long getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(long requisicao) {
		this.requisicao = requisicao;
	}
	
	public String getTimeString() {
		this.timeString = dataToString( time );
		return timeString;
	}
	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	
	public String dataToString(Date d){
		DateFormat df = new SimpleDateFormat("hh:mm:ss");
		String reportDate = df.format(d.getTime());
		return reportDate;
	}
	
}
