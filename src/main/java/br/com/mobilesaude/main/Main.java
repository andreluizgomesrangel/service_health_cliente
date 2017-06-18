package br.com.mobilesaude.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBException;

import br.com.mobilesaude.bean.RequisicaoJSFBean;
import br.com.mobilesaude.clients.RequisicaoDao;
import br.com.mobilesaude.clients.CService;
import br.com.mobilesaude.resource.LastRequest;
import br.com.mobilesaude.resource.Requisicao;
import br.com.mobilesaude.resource.Service;
import br.com.mobilesaude.resource.Status_History;
import br.com.mobilesaude.temporizador.PipeTeste;
import br.com.mobilesaude.temporizador.Pipeline;

public class Main {
// service health client
	public static void main(String[] args) throws JAXBException {
	
		for(int i=10; i>0; i--){
			Thread t = new Thread( new PipeTeste(i) );
			t.start();
		}
	}
	
}
