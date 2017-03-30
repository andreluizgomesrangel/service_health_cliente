package br.com.mobilesaude.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "service")
@XmlAccessorType(XmlAccessType.FIELD)
@ManagedBean
@ViewScoped
public class Service  implements Comparable<Service> {

	private long id;
	private String name;
	
	private String url;
	private String requestType;
	private String param;
	
	boolean alert = false;
	int timeOut = 0;
	
	String img = new String();
	private Date lastRequest = new Date();
	
	private List<Integer> errors = new ArrayList<Integer>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public List<Integer> getErrors() {
		return errors;
	}
	public void setErrors(List<Integer> errors) {
		this.errors = errors;
	}
	public void addErro( long requisicao ){
		if( errors.size()==0 ){
			errors.add( (int) requisicao );
		}
		else if( errors.size()>0 ){
			
			int lastone = errors.get( errors.size()-1 );
			//System.out.println( " ultima requisicao: "+lastone+" requisicao: "+requisicao );
			if( lastone+1 == (int) requisicao ){
				//System.out.println(" Sao requisicoes seguidas! ");
				errors.add( (int) requisicao );
				if(errors.size()>=3){
					timeOut = errors.size();
					setAlert(true);
				}
			}
			if( lastone+1 != (int) requisicao ){
				errors.clear();
				errors = new ArrayList<Integer>();
			}
		}
	}
	public boolean isAlert() {
		return alert;
	}
	public void setAlert(boolean alert) {
		this.alert = alert;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	@Override
	public int compareTo(Service o) {
		// TODO Auto-generated method stub
		if( this.id < o.id) return 1;
		if( this.id > o.id ) return -1;
		return 0;
	}
	public Date getLastRequest() {
		return lastRequest;
	}
	public void setLastRequest(Date lastRequest) {
		this.lastRequest = lastRequest;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}

