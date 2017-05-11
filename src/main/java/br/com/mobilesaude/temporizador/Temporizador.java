package br.com.mobilesaude.temporizador;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
//import br.com.ythalo.session.AutomaticTimer;
import javax.xml.bind.JAXBException;

import br.com.mobilesaude.clients.CRequisicao;
import br.com.mobilesaude.clients.CService;

@Stateless
public class Temporizador{

	@Schedule(second = "*/60", minute = "*", hour = "*")
	public void doWork() {

	
	/*try {
		//System.out.println();
		//cr.allRequests(cs.getlistById());
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	
	}
	
}
