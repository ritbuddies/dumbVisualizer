import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SetDataInMongo_Clean {
	
	static MongoClient mongoClient;
	static MongoDatabase database;
	static MongoCollection<Document> collection;
	
	
	public SetDataInMongo_Clean(){
		mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		database = mongoClient.getDatabase("zikaDB");
		collection = database.getCollection("cleanData");
//		collection.drop();
	}
	
    public static void main( String[] args )
    {
		SetDataInMongo_Clean con = new SetDataInMongo_Clean();
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

		String[] keys = {"Date","Country","State/City","Location_Type","State"};

		System.out.println(Arrays.toString(keys));
		List<Document> documents = new ArrayList<Document>();
		String[] values;
		while((line = br.readLine()) != null){
			int i = 0;
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
			String state = null;
			if( country_state.length == 2){
				state = country_state[1];
			}
			values[i++] = country;
			values[i++] = state;
			values[i++] = curr[2];
			values[i] = curr[3];

			Document doc = createJson(keys,values);
			documents.add(doc);
		}

		br.close();
//        System.out.println(documents.toString());
		collection.insertMany(documents);

	}
	public Document createJson(String[] keys, String[] values){
		Document doc= new Document();

		for( int i = 0; i < keys.length; i++){
			String key = keys[i].replace("\"","");
			String value = null;
			if( values[i] != null){

				value = values[i].replace("\"","");
			}

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