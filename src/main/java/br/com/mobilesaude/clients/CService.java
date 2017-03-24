package br.com.mobilesaude.clients;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.mobilesaude.listas.Services;
import br.com.mobilesaude.resource.Service;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CService {

	public int insert(String name, String url, String resp) throws JAXBException{
		OkHttpClient client = new OkHttpClient();
	
		int code = 200;
		
		RequestBody formBody = new FormBody.Builder()
		        .add("name", name)
		        .add("url", url)
		        .build();
		Request request = new Request.Builder()
		        .url("http://localhost:8080/Service_Health/ws/servico/service/insert")
		        .post(formBody)
		        .build();
		try {
			Response response = client.newCall(request).execute();
		   
			code = response.code();
			
		    //System.out.println(response.code());
		    
		    String xmlString = new String(response.body().string());
		    JAXBContext jaxbContext = JAXBContext.newInstance(Services.class);
		    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		    StringReader reader = new StringReader( xmlString );
		    Services services = (Services) unmarshaller.unmarshal(reader);
		    
		    return code;
		    
		    // Do something with the response.
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return code;
		
	}
	
	public void update(String id,  String resp) throws JAXBException{
		OkHttpClient client = new OkHttpClient();
	
		RequestBody formBody = new FormBody.Builder()
		        .add("id", id)
		        .add("response", resp)
		        .build();
		Request request = new Request.Builder()
		        .url("http://localhost:8080/Service_Health/ws/servico/service/update")
		        .post(formBody)
		        .build();
	
		try {
		    Response response = client.newCall(request).execute();
		    
		    
		    String xmlString = new String(response.body().string());
		    JAXBContext jaxbContext = JAXBContext.newInstance(Services.class);
		    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		    StringReader reader = new StringReader( xmlString );
		    Services services = (Services) unmarshaller.unmarshal(reader);
		    
		    // Do something with the response.
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public List<Service> getlist() throws JAXBException{
		OkHttpClient client = new OkHttpClient();
	
		RequestBody formBody = new FormBody.Builder()
		        .build();
		Request request = new Request.Builder()
		        .url("http://localhost:8080/Service_Health/ws/servico/service/getlist")
		        .get()
		        .build();
	
		try {
		    Response response = client.newCall(request).execute();
		    
		   // System.out.println(response.code());
		    
		    String xmlString = new String(response.body().string());
		    JAXBContext jaxbContext = JAXBContext.newInstance(Services.class);
		    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		    StringReader reader = new StringReader( xmlString );
		    Services services = (Services) unmarshaller.unmarshal(reader);
		    
		    return services.getServices();
		    
		    // Do something with the response.
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return null;
	}
	
}
