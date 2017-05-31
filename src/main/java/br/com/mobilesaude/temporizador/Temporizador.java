package br.com.mobilesaude.temporizador;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
//import br.com.ythalo.session.AutomaticTimer;
import javax.xml.bind.JAXBException;

import br.com.mobilesaude.clients.CRequisicao;
import br.com.mobilesaude.clients.CService;
import br.com.mobilesaude.resource.Service;

@Stateless
public class Temporizador{

	@Schedule(second = "*/60", minute = "*", hour = "*")
	public void doWork() {

		/*CService cs = new CService();
		List<Service> list = new ArrayList<Service>();
		try {
			list = cs.getlistSortById();
			for(Service x : list){
				Thread t = new Thread(new Pipeline(x));
		        t.start();
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();*/
		//}
	
	}
	
}
