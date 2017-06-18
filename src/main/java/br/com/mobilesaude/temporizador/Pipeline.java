package br.com.mobilesaude.temporizador;

import br.com.mobilesaude.clients.RequisicaoDao;
import br.com.mobilesaude.resource.Service;

public class Pipeline implements Runnable {

	RequisicaoDao cr;
	Service s;
	
	public Pipeline(Service s){
		this.s = s;
		cr = new RequisicaoDao();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("executar pipeline service: "+s.getId());
		cr.newRequest(s);
		
	}

}
