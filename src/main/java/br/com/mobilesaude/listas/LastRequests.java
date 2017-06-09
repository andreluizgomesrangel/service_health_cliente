package br.com.mobilesaude.listas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import br.com.mobilesaude.resource.LastRequest;

@XmlRootElement(name = "collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class LastRequests {

	@XmlElement(name = "lastRequest")
	private List<LastRequest> lastRequest = new ArrayList<LastRequest>();

	public List<LastRequest> getLastRequest() {
		return lastRequest;
	}

	public void setLastRequest(List<LastRequest> lastRequest) {
		this.lastRequest = lastRequest;
	}

}
