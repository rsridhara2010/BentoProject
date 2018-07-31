package com.test.util;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.model.BaseResponse;

import java.net.URL;
import java.util.List;

import java.io.IOException;

	public class App {
		int linkCounter = 0;
		
	    public BaseResponse findPhiliosophy(String url, int hopDepth) throws IOException {
	      BaseResponse result = new BaseResponse();
	      List<String> list = new java.util.ArrayList<String>();
	      String request = "", article= "";

	      boolean foundPhilosophy = false;
	      request = url;
          while(!foundPhilosophy)
          {
        	  if (url.indexOf("/wiki/") == -1)
        		  break;
        	  if (url.equalsIgnoreCase("http://en.wikipedia.org/wiki/philosophy")) {
        	        
        	        linkCounter = 0;
        	        
        	        foundPhilosophy = true;
        	        
        	        break;
        	    
        	    }
        	  
              URL url1 = new URL(url);            				
              Document doc = Jsoup.parse(url1, 5000);       
       	      Elements links = doc.select("p > a");
       	    
	        
	        String nextLink = "";
	        for (int i = 0; i < links.size(); i++) {        
	            if (isFirstLink(links.get(i).toString())) {
	                nextLink = links.get(i).toString();
	                
	                linkCounter++;
	                break;
	            }
	        }
	        article = nextLink.substring(9, nextLink.indexOf("\"", 10));
	        list.add(article);
	        url= "http://en.wikipedia.org" +article ;
	        
	        if (url.equalsIgnoreCase("http://en.wikipedia.org/wiki/philosophy")) {
	            int total = linkCounter;
	            
	            result.setResult( "Found Philosophy article in " + total + " links.\n");
	            foundPhilosophy = true;

	        } else if (linkCounter > hopDepth) {
	            //linkCounter = 0;
	            result.setResult( "Reached "+hopDepth+" hops, aborting...");
	            break;
	        } 	 
          }
          if (!foundPhilosophy)
          {
        	  result.setResult(result.getResult()+":Not a valid link or can't find Philosophy");
        	  result.setCode(-1);
          }
          else
          {
        	  result.setResult("Found Philosophy article in " + linkCounter + " links.\n");        	  
        	  result.setCode(200);
          }
          result.setHops(list);
          int index = request.indexOf("wiki/");
          result.setRequest(request.substring(index+5));
          
          return result;
	    }
	    public static boolean isFirstLink(String link) {
	        return (link.contains("wiki") && !link.contains("Greek") && !link.contains("Latin") && !link.contains("wiktionary"));
	    }
}

	
	