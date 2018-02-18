import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.json.JSONObject;

import java.util.Arrays;
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
public class SetDataInMongo {
	
	static MongoClient mongoClient;
	static MongoDatabase database;
	static MongoCollection<Document> collection;
	
	
	public SetDataInMongo(){
		mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		database = mongoClient.getDatabase("zikaDB");
		collection = database.getCollection("rawData");
	}
	
    public static void main( String[] args )
    {
    	SetDataInMongo con = new SetDataInMongo();
//    	con.insertSingleDoc();
    	try {
			con.insertFileInMongo();
		} catch (IOException e) {
			e.printStackTrace();
		}
//    	con.getMultipleDoc();
    	 mongoClient.close();
    }
    
    public void insertFileInMongo() throws IOException {
		
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String csvFile = br.readLine();
		
		br = new BufferedReader( new FileReader(csvFile));
		String line = null;
		line = br.readLine();
		
		String[] keys = line.split(",");
		
		System.out.println(Arrays.toString(keys));
		JSONObject obj;
		List<Document> documents = new ArrayList<Document>();
		String[] values;
		while((line = br.readLine()) != null){
			int i = 0;
			obj = new JSONObject();
			String[] curr = line.split(",");
			values = new String[5];
			String cleanedDate = null;
			if( (cleanedDate = cleanDate(curr[0]))== null){
				continue;
			}
			values[i] = cleanedDate;
			i++;
			String[] country_state = curr[1].split("-");
			String country = country_state[0];
			String state = country_state[1];
			values[i++] = country;
			values[i++] = state;
			values[i++] = curr[2];
			values[i] = curr[3];
		}
		
		br.close();
		
		collection.insertMany(documents);
		
	}

	public Document createJson(String[] keys, String[] values){
		Document doc= new Document();
		
		for( int i = 0; i < keys.length; i++){
			String key = keys[i].replace("\"","");
			String value = values[i].replace("\"","");
			doc.append(key, value);
		}
		
   	 	return doc;
    }
    
    public void insertSingleDoc(){
    	Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
   	 	collection.insertOne(doc);
    }
	public static String cleanDate(String date){
		String result = null;
		date = date.replaceAll("_", "-");
		date = date.replaceAll(" ", "-");
		String[] dateArray = date.split("-");
		if( dateArray == null || dateArray.length < 3)
			return result;
		else 
			return date;
		
	}
}
///Users/Nish/Downloads/cdc_zika.csv