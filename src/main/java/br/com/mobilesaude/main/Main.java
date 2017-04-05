package br.com.mobilesaude.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBException;

import br.com.mobilesaude.clients.CRequisicao;
import br.com.mobilesaude.clients.CService;
import br.com.mobilesaude.resource.Requisicao;
import br.com.mobilesaude.resource.Service;
import br.com.mobilesaude.resource.Status_History;

public class Main {
// service health client
	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub
		
		/*
		 * Reproduzir todo o Main do Service_Health aqui
		 */
		
		CService cs = new CService();
		List<Service> services = cs.getlist();

		int qtdServices = services.size();
		int qtdDias = 11;
		
		Calendar d = Calendar.getInstance();
		
		Status_History []status = new Status_History[qtdServices];

		//pegar os status
		for( int i=0; i<qtdServices; i++ ){
			status[i] = new Status_History( services.get(i).getId() , qtdDias );
		}
		
		//pegar os dias
		System.out.print("            ");
		for( int i=0; i<qtdDias; i++  ){
			
			d.add(Calendar.DATE, -1);
			System.out.print( "  | "+status[0].dataToStringBR(d));
			
		}
		System.out.println();
		
		for( int j=0; j<qtdServices; j++ ){
			int []dia = status[j].getDia();
			System.out.print(" "+services.get(j).getName());
			for(int i=0; i<dia.length; i++){
				System.out.print( "       "+dia[i]+" " );
			}
			System.out.println();
		}

	}

}
