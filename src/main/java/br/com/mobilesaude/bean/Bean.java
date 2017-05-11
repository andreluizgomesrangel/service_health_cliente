package br.com.mobilesaude.bean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBException;
import br.com.mobilesaude.clients.CRequisicao;
import br.com.mobilesaude.clients.CService;
import br.com.mobilesaude.resource.LastRequest;
import br.com.mobilesaude.resource.Requisicao;
import br.com.mobilesaude.resource.Service;
import br.com.mobilesaude.resource.Status_History;

@ManagedBean
@ViewScoped
public class Bean {

	List<LastRequest> lastRequests = new ArrayList<LastRequest>();
	CRequisicao cr = new CRequisicao();
	
	
	public Bean(){
		
		
		try {
			lastRequests = cr.getLastOnes();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public List<LastRequest> getLastRequests() {
		return lastRequests;
	}


	public void setLastRequests(List<LastRequest> lastRequests) {
		this.lastRequests = lastRequests;
	}

	
	
	
}
