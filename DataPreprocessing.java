import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class DataPreprocessing {
	
	public static void main(String[] args)throws IOException, ParseException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String csvFile = br.readLine();
		br = new BufferedReader( new FileReader(csvFile));
		String line = null;
		ArrayList<String> column1 = new ArrayList<String>();
//		line = br.readLine();
		while((line = br.readLine()) != null){
			String[] curr = line.split(",");
			String currDate = curr[0];
			String[] date = null;
			
			int count = 0;
			currDate = currDate.replaceAll("_", "-");
			currDate = currDate.replaceAll(" ", "-");
			date = currDate.split("-");
//				if( curr[0].contains("-")){
//						date = curr[0].split("-");
//				}
//				for( int i = 0; i < date.length; i++){
//					System.out.print(date[i]+" ");
//				}
//				System.out.println();
				if(date == null || date.length < 3){
					for( int i = 0; i < date.length; i++){
						
						System.out.print(date[i]+" ");
					}
					System.out.println();
				}
			}
			
		br.close();
	}
	public String cleanDate(String date){
		
		
		return date;
		
	}

}

///Users/Nish/Downloads/cdc_zika.csv