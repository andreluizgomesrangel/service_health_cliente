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

import br.com.mobilesaude.listas.LastRequests;
import br.com.mobilesaude.listas.Requisicoes;
import br.com.mobilesaude.listas.Services;
import br.com.mobilesaude.resource.LastRequest;
import br.com.mobilesaude.resource.Requisicao;
import br.com.mobilesaude.resource.Service;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CRequisicao {

	public int insert( String idService, String resp ) throws JAXBException{
		OkHttpClient client = new OkHttpClient();
		int code = 0;
		
		RequestBody formBody = new FormBody.Builder()
		        .add("idService", idService)
		        .add("response", resp)
		        .build();
		Request request = new Request.Builder()
		        .url("http://localhost:8080/Service_Health/ws/servico/requisicao/insert")
		        .post(formBody)
		        .build();
		try {
		    Response response = client.newCall(request).execute();
		    
		    code = response.code();
		    
		    String xmlString = new String(response.body().string());
		    //JAXBContext jaxbContext = JAXBContext.newInstance(Requisicoes.class);
		    JAXBContext jaxbContext = JAXBContext.newInstance(Requisicao.class);
		    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		    StringReader reader = new StringReader( xmlString );
		    
		    
		    //Requisicoes historics = (Requisicoes) unmarshaller.unmarshal(reader);
		    Requisicao historics = (Requisicao) unmarshaller.unmarshal(reader);
		    //System.out.println(code);
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
		    JAXBContext jaxbContext = JAXBContext.newInstance(Requisicoes.class);
		    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		    StringReader reader = new StringReader( xmlString );
		    historics = (Requisicoes) unmarshaller.unmarshal(reader);
		    return historics.getHitorics();
		    
		    // Do something with the response.
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return historics.getHitorics();
	}
	
	
	public List<LastRequest> getLastOnes() throws JAXBException{
		OkHttpClient client = new OkHttpClient();
	
		int code = 0;
		
		RequestBody formBody = new FormBody.Builder()
		        .build();
		Request request = new Request.Builder()
		        .url("http://localhost:8080/Service_Health/ws/servico/requisicao/getLastOnes")
		        .get()
		        .build();
		LastRequests lasts = null;
		try {
		    Response response = client.newCall(request).execute();
		    code = response.code();
		    String xmlString = new String(response.body().string());
		    JAXBContext jaxbContext = JAXBContext.newInstance(LastRequests.class);
		    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		    StringReader reader = new StringReader( xmlString );
		    lasts = (LastRequests) unmarshaller.unmarshal(reader);
		    return lasts.getLastRequest();
		    
		    // Do something with the response.
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return lasts.getLastRequest();
	}
	
	
	public List<Requisicao> getDay( String id, String day) throws JAXBException{
		OkHttpClient client = new OkHttpClient();
	
		int code = 0;
		
		RequestBody formBody = new FormBody.Builder()
		        .add("id", id)
		        .add("day", day)
		        .build();
		Request request = new Request.Builder()
		        .url("http://localhost:8080/Service_Health/ws/servico/requisicao/getDay")
		        .post(formBody)
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
		//System.out.print( " post waiting..." );
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
		
		//System.out.print( " get  waiting..." );
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
	
	//Metodo responsavel por fazer uma requisicao
	public int request( Service s ){
		
		if( s.getRequestType().equals("post") ){
			//System.out.println("post");
			return postRequest( s );
		}
		if( s.getRequestType().equals("get") ){
			//System.out.println("get");
			return getRequest( s );
		}
		//997412385 Laryssa
		
	return 0;
	
	}
	
	
	//faz uma requisicao e insere ela no bd
	public Requisicao newRequest( Service s ){
		
		int response = request( s );
		
		//System.out.println( " "+response );
		
		Requisicao h = new Requisicao();
		h.setResponse(response);
		h.setIdService( s.getId() );
		
		try {
			insert( s.getId()+"", response+"");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return h;
	}
	
	public void allRequests( ){
		
		CService cs = new CService();
		
		List<Service> services = null;
		try {
			services = cs.getlistById();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for( Service x : services ){
			newRequest(x);
		}
	}
	
}
