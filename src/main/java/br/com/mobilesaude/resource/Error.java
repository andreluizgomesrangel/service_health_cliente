package br.com.mobilesaude.resource;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
@ManagedBean
@ViewScoped
public class Error extends Service {
	
	private long timeOut;
	
	
	public Error( List<Service> services ){
		
	}
	
}
