package br.com.mobilesaude.temporizador;

import br.com.mobilesaude.clients.CRequisicao;
import br.com.mobilesaude.resource.Service;

public class Pipeline implements Runnable {

	CRequisicao cr;
	Service s;
	
	public Pipeline(Service s){
		this.s = s;
		cr = new CRequisicao();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("executar pipeline service: "+s.getId());
		cr.newRequest(s);
		
	}

}
