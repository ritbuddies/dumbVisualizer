
/* 
 * Client.java 
 * 
 * 
 */

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This Client class generates a graph with correlation between
 * 
 * @author Vinay Vasant More
 *
 */
public class RestAPIClient {

	RestAPIClient(){
		try {
			URL url = new URL("http://192.168.43.140:8080/hello");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
		//	conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new RestAPIClient();
		
		View view = new View();
		view.build(Color.white);
	
		getZikaWorldReport();
	}
	
	public void getZikaYearCountReport(){
		
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("zikaYearCountReport.html"));
			//Reference: Used Google Chart API below https://developers.google.com/chart/
			bw.write("");

			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void getZikaWorldReport(){
		
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/vin/Desktop/zikaWorldReport.html"));
			//Reference: Used Google Chart API below https://developers.google.com/chart/
			bw.write("<html>\n<head>"+
    "\n<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"+
    "\n<script type=\"text/javascript\">"+
     "\ngoogle.charts.load('current', {"+
       "\n'packages':['geochart'],"+
        "\n'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'"+
      "\n});"+
      "\ngoogle.charts.setOnLoadCallback(drawRegionsMap);"+
      "\nfunction drawRegionsMap() {"+
        "\nvar data = google.visualization.arrayToDataTable(["+
         "\n['Country', 'Zica_cases'],"+
         "\n['Germany', 200],"+
         "\n['United States', 300],"+
         "\n['Brazil', 400],"+
         "\n['Canada', 500],"+
         "\n['France', 600],"+
         "\n['RU', 700]"+
        "\n]);"+
        "\nvar options = {title: 'ZIKA Infections - World Report'};"+
        "\nvar chart = new google.visualization.GeoChart(document.getElementById('regions_div'));"+
        "\nchart.draw(data, options);"+
      "\n}"+
    "\n</script>"+
  "\n</head>"+
  "\n<body>"+
   "\n<div id=\"regions_div\" style=\"width: 900px; height: 500px;\"></div>"+
  "\n</body>"+
"\n</html>");

			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
