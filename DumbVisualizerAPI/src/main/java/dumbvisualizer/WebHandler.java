package dumbvisualizer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Set;

@RestController
public class WebHandler {
	
	GetDataFromMongo mongoDbObject = new GetDataFromMongo("zikaDB", "cleanData");
	
	public WebHandler() {}
	
	@RequestMapping("/timeSeries")
    public JSONObject fetchTimeSeriesData(@RequestParam(value="db") String db, @RequestParam(value="type") String type) {
		JSONObject timeSeriesData = new JSONObject();
		try {							
			int dbName = Integer.parseInt(db);
	        int graphType = Integer.parseInt(type);
	        System.out.println(db+ " "+type);
			JSONArray responseObj = mongoDbObject.getDate();
			System.out.println(responseObj.toJSONString());			
	        timeSeriesData.put("dataset_ID",1);
			timeSeriesData.put("reportType",1);
			timeSeriesData.put("data", responseObj);			
		}catch(Exception E) {
			
		}
		return timeSeriesData;
    }
	
	
	
	@RequestMapping("/location")
    public JSONObject fetchLocation(@RequestParam(value="db") String db, @RequestParam(value="type") String type) {
		JSONObject locationData = new JSONObject();
		try {							
			int dbName = Integer.parseInt(db);
	        int graphType = Integer.parseInt(type);
	        System.out.println(db+ " "+type);
			JSONArray responseObj = mongoDbObject.getLocation();
			System.out.println(responseObj.toJSONString());			
			locationData.put("dataset_ID",1);
			locationData.put("reportType",2);
			locationData.put("data", responseObj);			
		}catch(Exception E) {
			
		}
		return locationData;
    }
	
	
	
	@RequestMapping("/location_category")
    public JSONObject locationByCategory(@RequestParam(value="db") String db, @RequestParam(value="type") String type,
    		@RequestParam(value="country") String country) {
		JSONObject locationData = new JSONObject();
		try {		
			
			System.out.println(db+" "+type+" "+country);
			JSONArray responseObj = mongoDbObject.getLocType(country);
			System.out.println(responseObj.toJSONString());			
			locationData.put("dataset_ID",1);
			locationData.put("reportType",3);
			locationData.put("data", responseObj);			
		}catch(Exception E) {
			
		}
		return locationData;
    }
	
	@RequestMapping("/report_category")
    public JSONObject reportByCategory(@RequestParam(value="db") String db, @RequestParam(value="type") String type,
    		@RequestParam(value="country") String country) {
		JSONObject reportData = new JSONObject();
		try {		
			
			System.out.println(db+" "+type+" "+country);
			JSONArray responseObj = mongoDbObject.getCategory(country);
			System.out.println(responseObj.toJSONString());			
			reportData.put("dataset_ID",1);
			reportData.put("reportType",3);
			reportData.put("data", responseObj);			
		}catch(Exception E) {
			
		}
		return reportData;
    }
	public static void main(String[] args){
		WebHandler web = new WebHandler();
	}
}
