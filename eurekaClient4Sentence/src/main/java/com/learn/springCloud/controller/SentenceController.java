package com.learn.springCloud.controller;

import java.net.URI;
import java.util.List;

import org.assertj.core.api.UriAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author rajes
 * date    Apr 7, 20171:27:33 AM
 *
 */
@RestController
public class SentenceController {
	private static final Logger log = LoggerFactory.getLogger(SentenceController.class);
	

	
	@Autowired
	private DiscoveryClient client;

	 @RequestMapping("/sentence")
	  public @ResponseBody String getSentence() {
		return getWordsFromAllClients("eurekaClient1Subject") + " " +getWordsFromAllClients("eurekaClient2Verb") 
		+" "+  getWordsFromAllClients("eurekaClient3Adjective");
		
	    
	  }
	 
	 //to get the information about the other clients using discvery client
	 public String getWordsFromAllClients(String appName)
	 {
		 List<ServiceInstance> app=client.getInstances(appName);
		 System.out.println(app);
	     if (app !=null && app.size() >0 )
	     {
	    	URI uri= app.get(0).getUri();
	    	 System.out.println(app.get(0).getUri());
	    	 
	    	 if(uri !=null)
	    	 {
	    		 RestTemplate rest=new RestTemplate();
	    		return rest.getForObject(uri, String.class); //Parsing the Uri content
	    	 }
	     }
		return null;
	 }
}
