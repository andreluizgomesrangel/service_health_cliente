package br.com.mobilesaude.clients;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.mobilesaude.listas.Requisicoes;
import br.com.mobilesaude.listas.Services;
import br.com.mobilesaude.resource.Requisicao;
import br.com.mobilesaude.resource.Service;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CRequisicao {

	public int insert( String idService, String resp, String req ) throws JAXBException{
		OkHttpClient client = new OkHttpClient();
	
		int code = 0;
		
		RequestBody formBody = new FormBody.Builder()
		        .add("idService", idService)
		        .add("response", resp)
		        .add("request", req)
		        .build();
		Request request = new Request.Builder()
		        .url("http://localhost:8080/Service_Health/ws/servico/requisicao/insert")
		        .post(formBody)
		        .build();
		try {
		    Response response = client.newCall(request).execute();
		    
		    code = response.code();
		    
		    String xmlString = new String(response.body().string());
		    JAXBContext jaxbContext = JAXBContext.newInstance(Requisicoes.class);
		    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		    StringReader reader = new StringReader( xmlString );
		    
		    Requisicoes historics = (Requisicoes) unmarshaller.unmarshal(reader);
		    
		    
		    return code;
		    
		    // Do something with the response.
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return 0;
	}
	
	public List<Requisicao> getList() throws JAXBException{
		OkHttpClient client = new OkHttpClient();
	
		int code = 0;
		
		RequestBody formBody = new FormBody.Builder()
		        .build();
		Request request = new Request.Builder()
		        .url("http://localhost:8080/Service_Health/ws/servico/requisicao/getList")
		        .get()
		        .build();
		Requisicoes historics = null;
		try {
		    Response response = client.newCall(request).execute();
		    
		    code = response.code();
		    
		    String xmlString = new String(response.body().string());
		    
		   // System.out.println(xmlString);
		    JAXBContext jaxbContext = JAXBContext.newInstance(Requisicoes.class);
		    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		    StringReader reader = new StringReader( xmlString );
		    
		   
		    
		    historics = (Requisicoes) unmarshaller.unmarshal(reader);
		    
		    //System.out.println(historics.getHitorics().size());
		    
		    return historics.getHitorics();
		    
		    // Do something with the response.
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return historics.getHitorics();
	}
	
	public int postRequest( Service s ){
		
		int code = 0;
		OkHttpClient client = new OkHttpClient().newBuilder()
				.connectTimeout( 30, TimeUnit.SECONDS )
				.readTimeout( 30, TimeUnit.SECONDS)
				.build();
		
		
		HashMap<String, String> param = getParam( s.getParam() );
		
		FormBody.Builder builder = new FormBody.Builder();	
		
		for ( Map.Entry<String, String> entry : param.entrySet() ) {
		    builder.add( entry.getKey(), entry.getValue() );
		}
		
		RequestBody formBody = builder.build();
		
		//System.out.print( "  waiting..." );
		Request request = new Request.Builder()
		        .url( s.getUrl() )
		        .post(formBody)
		        .build();
		
		try {
			
			Response response = client.newCall(request).execute();
			code = response.code();
			return code;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return code;
	}
	
	
	
public int getRequest( Service s ){
		
		int code = 0;
		OkHttpClient client = new OkHttpClient().newBuilder()
				.connectTimeout( 3*60, TimeUnit.SECONDS )
				.readTimeout( 3*60, TimeUnit.SECONDS)
				.build();
		
		FormBody.Builder builder = new FormBody.Builder();	
		
		//System.out.print( "  waiting..." );
		Request request = new Request.Builder()
		        .url( s.getUrl() )
		        .get()
		        .build();
		
		try {
			
			Response response = client.newCall(request).execute();
			code = response.code();
			return code;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return code;
	}
	
	
	public HashMap<String, String> getParam(String query){
		
		HashMap<String, String> p = new HashMap<>();
		
		String[] param = query.split("&");
		for( String a : param ){
			String[] x = a.split("=");
			if( x.length != 0 ){
				p.put( x[0], x[1] );
			}
		}
		return p;
	}
	
	public int request( Service s ){
		
		if( s.getRequestType().equals("post") ){
			return postRequest( s );
		}
		if( s.getRequestType().equals("get") ){
			return getRequest( s );
		}
		//997412385 Laryssa
		
	return 0;
	
	}
	
	public Requisicao newHistoric( Service s, long l ){
		
		int response = request( s );
		
		//System.out.println( " "+response );
		
		Requisicao h = new Requisicao();
		h.setResponse(response);
		h.setIdService( s.getId() );
		
		try {
			insert( s.getId()+"", response+"", l+"");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return h;
	}
	
	public void allRequests( List<Service> services){
		
		List<Requisicao> list = null;
		try {
			list = getList();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long lastRequest = list.get( list.size()-1 ).getRequisicao();
		if(list==null || list.size()==0){
			lastRequest = 0;
		}
		
		for( Service s : services ){
			newHistoric( s, lastRequest+1 );
		}
	}
	
}
