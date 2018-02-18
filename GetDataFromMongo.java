import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

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
public class GetDataFromMongo {
	static MongoClient mongoClient;
	static MongoDatabase database;
	static MongoCollection<Document> collection;
	
	
	public GetDataFromMongo(){
		mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		database = mongoClient.getDatabase("zikaDB");
//		(database).drop();  
		collection = database.getCollection("rawData");
//		(collection).drop();
	}
	   public static void main( String[] args )
	    {
	    	GetDataFromMongo con = new GetDataFromMongo();
	    	MongoCursor<Document> cursor = collection.find().iterator();
//	    	con.getMultipleDoc();
//	    	con.getDate(cursor);
//	    	con.getLocation(cursor);
	    	con.getLocType(cursor, "Brazil");
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
	    public JSONObject getDate(MongoCursor<Document> cursor){
	    	
	    	HashMap<String, Integer> map = new HashMap<String, Integer>();
	    	try {
	    	    while (cursor.hasNext()) {
	    	    	Document doc = cursor.next();
	    	    	String date = (String)doc.get("report_date");
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
	    	JSONObject obj = new JSONObject(map);
	    	System.out.println(obj.toString());
	    	return obj;
	    }
	    
	    public JSONObject getLocation(MongoCursor<Document> cursor){
	   
	    	HashMap<String, Integer> map = new HashMap<String, Integer>();
	    	try {
	    	    while (cursor.hasNext()) {
	    	    	
	    	    	Document doc = cursor.next();
	    	    	String location = (String)doc.get("location");
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
	    	JSONObject obj = new JSONObject(map);
	    	System.out.println(obj.toString());
	    	return obj;
	    }
	    public JSONObject getLocType(MongoCursor<Document> cursor, String country){
	    	
	    	cursor = collection.find().iterator();
	    	HashMap<String, Integer> map = new HashMap<String, Integer>();
	    	try {
	    	    while (cursor.hasNext()) {
	    	    	
	    	    	Document doc = cursor.next();
	    	    	String location = (String)doc.get("location");
	    	    	if( location.contains(country)){
	    	    		String type = (String)doc.get("location_type");
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
	    	JSONObject obj = new JSONObject(map);
	    	System.out.println(obj.toString());
	    	return obj;	
	    }
}
