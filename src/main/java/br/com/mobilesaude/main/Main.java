package br.com.mobilesaude.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBException;

import br.com.mobilesaude.bean.RequisicaoJSFBean;
import br.com.mobilesaude.clients.CRequisicao;
import br.com.mobilesaude.clients.CService;
import br.com.mobilesaude.resource.LastRequest;
import br.com.mobilesaude.resource.Requisicao;
import br.com.mobilesaude.resource.Service;
import br.com.mobilesaude.resource.Status_History;

public class Main {
// service health client
	public static void main(String[] args) throws JAXBException {
	
		/*Status_History h = new Status_History(7,10);
		System.out.println(h.getService().getName());
		System.out.println(h.dataToString( h.getToday() ));
		
		for(int i=0; i<10; i++){
			System.out.print(" "+h.getDia()[i]+h.getImg()[i]+" ");
		}*/ 
		
		
		RequisicaoJSFBean r = new RequisicaoJSFBean();
		for( int i=0; i<r.getQtdDias(); i++){
			System.out.print(" "+r.getDias()[i]+" ");
		}
		
		
	}
}
