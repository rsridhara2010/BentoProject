package com.test.app;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.test.model.BaseResponse;
import com.test.model.WikiPediaRequest;
import com.test.util.App;

@RestController
@RequestMapping("/wiki")
public class WikiPediaController {
    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public String echo() {
    	return "Hello BentoRestApp-Ravi";
    }	
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public BaseResponse findResult(@RequestParam int depth, @RequestBody WikiPediaRequest request) {
        
        BaseResponse response = new BaseResponse();
        
        {
            App app = new App();
            String url = "";
            try {
            	url = request.getRequestURL();
            	response= app.findPhiliosophy(url,depth);
            	if (response.getCode() == 200)
            		UpsertPathToPhilosophy(response.getRequest(),response.getHops());
            }catch(Exception e)
            {
            	StackTraceElement [] elems = e.getStackTrace();
            	StringBuffer errorStr = new StringBuffer();
            	for( int i = 0 ; i < elems.length; i ++ )
            	{
            		errorStr.append(elems[i].toString() +"\n");
            		
            	}
            	response.setResult("url:"+url+",depth:"+depth+" "+e.getMessage()+":"+errorStr.toString());
            }
            
        }
        
        return response;
    }
    @SuppressWarnings("deprecation")
	public static void UpsertPathToPhilosophy(String url, List<String> list) throws Exception{
    	MongoClientURI uri = new MongoClientURI(
    		    "mongodb+srv://bento1:bento1@cluster0-kkyvv.mongodb.net/test?retryWrites=true");
    	MongoClient mongoClient = new MongoClient(uri);
		
        try {
        	
    		DB database = mongoClient.getDB("test");
        	DBObject philosophy = new BasicDBObject("_id",url).append("hops", list);
        	
        	DBCollection collection = database.getCollection("WikiPathToPhilosphy");
        	collection.update(new BasicDBObject("_id",url), philosophy, true,false);
             
        } catch (Exception ex) {
            throw ex;
        }
        finally {
        	mongoClient.close();
        }
         
    }
}