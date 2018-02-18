package dumbvisualizer;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Arrays;
import java.util.HashMap;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class GetDataFromMongo {
	static MongoClient mongoClient;
	static MongoDatabase database;
	static MongoCollection<Document> collection;
	static MongoCursor<Document> cursor;
	
	GetDataFromMongo(){}
	
	public GetDataFromMongo(String dbName, String collectionName){
		mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		database = mongoClient.getDatabase(dbName);
//		(database).drop();  
		collection = database.getCollection(collectionName);
		cursor = collection.find().iterator();
//		(collection).drop();
	}
	   public static void main( String[] args )
	    {
	    	GetDataFromMongo con =  new GetDataFromMongo("zikaDB", "cleanData");
	    	
//	    	con.getMultipleDoc();
	    	System.out.println(con.getDate().toJSONString());
//	    	con.getLocation(cursor);
//	    	con.getLocType("Brazil");
	    }
	   
	    public void getMultipleDoc(){
	    	MongoCursor<Document> cursor = collection.find().iterator();
	    	try {
	    	    while (cursor.hasNext()) {
	    	        System.out.println(cursor.next().toJson());
	    	    }
	    	} finally {
	    	    cursor.close();
	    	}
	    }
	    
	    public JSONArray getDate(){
	    	
	    	HashMap<String, Integer> map = new HashMap<String, Integer>();
	    	try {
	    	    while (cursor.hasNext()) {
	    	    	Document doc = cursor.next();
	    	    	String date = (String)doc.get("Date");
	    	    	if( map.containsKey(date)){
	    	    		map.put(date,map.get(date)+1);
	    	    	}
	    	    	else{
	    	    		map.put(date,1);
	    	    	}
	    	    }
	    	} finally {
	    	    cursor.close();
	    	}
	    	JSONArray jarray = new JSONArray();
            Set<String> keys = map.keySet();
            for(String key : keys){
                JSONObject obj = new JSONObject();
                obj.put("date", key);
                obj.put("count", map.get(key));
				jarray.add(obj);
            }
            return jarray;
	    }
	    
	    public JSONArray getLocation(){
	   
	    	HashMap<String, Integer> map = new HashMap<String, Integer>();
	    	try {
	    	    while (cursor.hasNext()) {
	    	    	
	    	    	Document doc = cursor.next();
	    	    	String location = (String)doc.get("Country");
	    	    	if( map.containsKey(location)){
	    	    		map.put(location,map.get(location)+1);
	    	    	}
	    	    	else{
	    	    		map.put(location,1);
	    	    	}
	    	    }
	    	} finally {
	    	    cursor.close();
	    	}
	    	JSONArray jarray = new JSONArray();
            Set<String> keys = map.keySet();
            for(String key : keys){
                JSONObject obj = new JSONObject();
                obj.put("country", key);
                obj.put("count", map.get(key));
				jarray.add(obj);
            }
            return jarray;
	    }
	    
	    
	    public JSONArray getLocType(String country){
	    	
	    	cursor = collection.find().iterator();
	    	HashMap<String, Integer> map = new HashMap<String, Integer>();
	    	try {
	    	    while (cursor.hasNext()) {
	    	    	
	    	    	Document doc = cursor.next();
	    	    	String location = (String)doc.get("Country");
	    	    	if( location.contains(country)){
	    	    		String type = (String)doc.get("Location_Type");
	    	    		if( map.containsKey(type)){
		    	    		map.put(type,map.get(type)+1);
		    	    	}
		    	    	else{
		    	    		map.put(type,1);
		    	    	}
	    	    	}
	    	    	
	    	    }
	    	} finally {
	    	    cursor.close();
	    	}
	    	JSONArray jarray = new JSONArray();
            Set<String> keys = map.keySet();
            for(String key : keys){
                JSONObject obj = new JSONObject();
                obj.put("category", key);
                obj.put("count", map.get(key));
				jarray.add(obj);
            }
            return jarray;
	    }
}
