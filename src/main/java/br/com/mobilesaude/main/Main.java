package br.com.mobilesaude.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBException;

import br.com.mobilesaude.clients.CRequisicao;
import br.com.mobilesaude.clients.CService;
import br.com.mobilesaude.resource.Service;

public class Main {
// service health client
	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
		Calendar cal = Calendar.getInstance();  
		System.out.println(dateFormat.format(cal.getTime()));

	}

}
