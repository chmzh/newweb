package com.cmz.web1.ribbon;

import java.net.URI;

import com.netflix.client.ClientFactory;
import com.netflix.client.RetryHandler;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.RestClient;

public class RibbonClient {
    public static void main( String[] args ) throws Exception {  
        ConfigurationManager.loadPropertiesFromResources("sample-client.properties");  
        System.out.println(ConfigurationManager.getConfigInstance().getProperty("sample-client.ribbon.listOfServers"));  
          
        RestClient client = (RestClient)ClientFactory.getNamedClient("sample-client");  
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build();

        client.setRetryHandler(new RetryHandler() {
			
			@Override
			public boolean isRetriableException(Throwable e, boolean sameServer) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isCircuitTrippingException(Throwable e) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public int getMaxRetriesOnSameServer() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getMaxRetriesOnNextServer() {
				// TODO Auto-generated method stub
				return 0;
			}
		});
        
        HttpResponse response = client.executeWithLoadBalancer(request);
        
        System.out.println("Status for URI:" + response.getRequestedURI() + " is :" + response.getStatus());
        
        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();

        System.out.println(lb.getLoadBalancerStats());
        /*
        for(int i = 0; i < 20; i ++) {  
            HttpResponse response = client.executeWithLoadBalancer(request);  
            System.out.println("Status for URI:" + response.getRequestedURI() + " is :" + response.getStatus());  
        }  
          
        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();  
        System.out.println(lb.getLoadBalancerStats());  
          
        ConfigurationManager.getConfigInstance().setProperty("sample-client.ribbon.listOfServers", "www.baidu.com:80,www.linkedin.com:80");  
          
        System.out.println("changing servers ...");  
        Thread.sleep(3000);  
          
        for(int i = 0; i < 20; i ++) {  
            HttpResponse response = client.executeWithLoadBalancer(request);  
            System.out.println("Status for URI:" + response.getRequestedURI() + " is :" + response.getStatus());  
        }  
        System.out.println(lb.getLoadBalancerStats());  
        */
    }  
}
