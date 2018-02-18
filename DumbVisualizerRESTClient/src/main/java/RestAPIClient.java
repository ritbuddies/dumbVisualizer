
/* 
 * RestAPIClient.java 
 * 
 * 
 */

import java.awt.Color;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This Client class generates a graph with correlation between
 * 
 * @author Vinay Vasant More
 *
 */
public class RestAPIClient {

	RestAPIClient() {
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// new RestAPIClient();


		View view1 = new View();
		view1.buildDashBoard(Color.white);
	
		View view2 = new View();
		//view2.build(Color.white);
		
		
	}

	public static void getInternalCountryDistribution(String db, String graph_type, String country) {
		JSONObject obj = null;
		try {
			URL url = new URL(ClientConfig.serverURLString + ClientConfig.LOCATION_CATEGORY + "db=" + db + "&type="
					+ graph_type + "&country=" + country);
			System.out.println("URL:" + url.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			try {
				JSONParser parser = new JSONParser();
				obj = (JSONObject) parser.parse(new InputStreamReader(conn.getInputStream()));
			} catch (ParseException ex) {
				throw new IOException("JSON parser error, " + ex.getMessage(), ex);
			}

			String output;
			System.out.println("Output from Server ....\n");
			System.out.println(obj.toJSONString());
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONArray dataArray = (JSONArray) obj.get("data");
		String buildString = "";
		for (int i = 0; i < dataArray.size(); i++) {
			JSONObject dataField = (JSONObject) dataArray.get(i);

			buildString = buildString + "['" + dataField.get("category").toString() + "',"
					+ dataField.get("count").toString() + "]" + ",";
		}

		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(ClientConfig.OUTPUT_PATH + "zikaInternalCountryDistribution.html"));
			// Reference: Used Google Chart API below
			// https://developers.google.com/chart/
			bw.write("<html>" + "\n<head>" + "\n<title>ZIKA Infections - Distribution across country</title>"
					+ "\n<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
					+ "\n<script type=\"text/javascript\">"
					+ "\n  google.charts.load(\"current\", {packages:[\"corechart\"]});"
					+ "\n  google.charts.setOnLoadCallback(drawChart);" + "\n    function drawChart() {"
					+ "\n      var data = google.visualization.arrayToDataTable(["
					+ "\n        ['CountryPart', 'Infection_Count'],"
					+ buildString.substring(0, buildString.length() - 1) +
					"\n      ]);" +
					"\n     var options = {" + "\n    title: 'Infection distribution across "+country+"',"
					+ "\n       is3D: true," + "\n     };" +
					"\n    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));"
					+ "\n   chart.draw(data, options);" + "\n }" + "\n </script>" + "\n </head>" + "\n <body>"
					+ "\n <div id=\"piechart_3d\" style=\"width: 900px; height: 600px;\"></div>" + "\n </body>"
					+ "\n </html>");

			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Desktop.getDesktop().browse(new URI(ClientConfig.OUTPUT_PATH + "zikaInternalCountryDistribution.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	
	public static void getCountrySpecificCategoryData(String db, String graph_type, String country) {
		JSONObject obj = null;
		try {
			URL url = new URL(ClientConfig.serverURLString + ClientConfig.REPORT_CATEGORY + "db=" + db + "&type="
					+ graph_type + "&country=" + country);
			System.out.println("URL:" + url.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			try {
				JSONParser parser = new JSONParser();
				obj = (JSONObject) parser.parse(new InputStreamReader(conn.getInputStream()));
			} catch (ParseException ex) {
				throw new IOException("JSON parser error, " + ex.getMessage(), ex);
			}

			String output;
			System.out.println("Output from Server ....\n");
			System.out.println(obj.toJSONString());
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONArray dataArray = (JSONArray) obj.get("data");
		String buildString = "";
		for (int i = 0; i < dataArray.size(); i++) {
			JSONObject dataField = (JSONObject) dataArray.get(i);

			buildString = buildString + "['" + dataField.get("State").toString() + "',"
					+ dataField.get("Count").toString() + "]" + ",";
		}

		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(ClientConfig.OUTPUT_PATH + "CountrySpecificCategoryData.html"));
			// Reference: Used Google Chart API below
			// https://developers.google.com/chart/
			bw.write("<html>" + "\n<head>" + "\n<title>ZIKA Infections - Distribution across country</title>"
					+ "\n<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
					+ "\n<script type=\"text/javascript\">"
					+ "\n  google.charts.load(\"current\", {packages:[\"corechart\"]});"
					+ "\n  google.charts.setOnLoadCallback(drawChart);" + "\n    function drawChart() {"
					+ "\n      var data = google.visualization.arrayToDataTable(["
					+ "\n        ['Status', 'Count'],"
					+ buildString.substring(0, buildString.length() - 1) +
					"\n      ]);" +
					"\n     var options = {" + "\n    title: 'Country Specific ZICA Cases',"
					+ "\n       is3D: true," + "\n     };" +
					"\n    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));"
					+ "\n   chart.draw(data, options);" + "\n }" + "\n </script>" + "\n </head>" + "\n <body>"
					+ "\n <div id=\"piechart_3d\" style=\"width: 900px; height: 600px;\"></div>" + "\n </body>"
					+ "\n </html>");

			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Desktop.getDesktop().browse(new URI(ClientConfig.OUTPUT_PATH + "CountrySpecificCategoryData.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	
	public static void getZikaYearCountReport(String db, String graph_type) {
		JSONObject obj = null;
		try {
			URL url = new URL(
					ClientConfig.serverURLString + ClientConfig.TIME_SERIES + "db=" + db + "&type=" + graph_type);
			System.out.println("URL:" + url.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			try {
				JSONParser parser = new JSONParser();
				obj = (JSONObject) parser.parse(new InputStreamReader(conn.getInputStream()));
			} catch (ParseException ex) {
				throw new IOException("JSON parser error, " + ex.getMessage(), ex);
			}

			String output;
			System.out.println("Output from Server .... \n");
			System.out.println(obj.toJSONString());
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONArray dataArray = (JSONArray) obj.get("data");
		String buildString = "";
		for (int i = 0; i < dataArray.size(); i++) {
			JSONObject dataField = (JSONObject) dataArray.get(i);
			String dateString = dataField.get("date").toString();
			buildString = buildString + "[new Date(" + dateString.substring(0, 4) + "," + dateString.substring(5, 7)
					+ "," + dateString.substring(8, 10) + "), " + dataField.get("count") + "],";
		}

		System.out.println("buildString:" + buildString);

		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(ClientConfig.OUTPUT_PATH + "zikaYearCountReport.html"));
			// Reference: Used Google Chart API below
			// https://developers.google.com/chart/
			bw.write("<html>" + "\n<head>" + "\n<title>ZIKA Infections Time-series Graph</title>"
					+ "\n<p><font size=\"5\" color=\"black\">ZIKA Infections Time-series Graph</font></p>"
					+ "\n<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
					+ "\n<script type=\"text/javascript\">"
					+ "\ngoogle.charts.load('current', {packages: ['corechart', 'line']});"
					+ "\ngoogle.charts.setOnLoadCallback(drawBackgroundColor);" + "\nfunction drawBackgroundColor() {"
					+ "\n   var data = new google.visualization.DataTable();" + "\n      data.addColumn('date', 'X');"
					+ "\n      data.addColumn('number', 'Infections');" + "\n      data.addRows([" + "\n "
					+ buildString.substring(0, buildString.length() - 1) + "\n      ]);" + "\n      var options = {"
					+ "\n        hAxis: {" + "\n          title: 'Time'" + "\n        }," + "\n        vAxis: {"
					+ "\n          title: 'ZIKA_Infections'" + "\n        }," + "\n        backgroundColor: '#f1f8e9'"
					+ "\n      };" +
					"\n      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));"
					+ "\n      chart.draw(data, options);" + "\n    }" + "\n	</script>" + "\n </head>" + "\n<body>"
					+ "\n<div id=\"chart_div\" style=\"width: 900px; height: 500px;\"></div>" + "\n</body>"
					+ "\n</html>");

			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Desktop.getDesktop().browse(new URI(ClientConfig.OUTPUT_PATH + "zikaYearCountReport.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getZikaWorldReport(String db, String graph_type) {
		JSONObject obj = null;
		try {
			URL url = new URL(
					ClientConfig.serverURLString + ClientConfig.LOCATION + "db=" + db + "&type=" + graph_type);
			System.out.println("URL:" + url.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			try {
				JSONParser parser = new JSONParser();
				obj = (JSONObject) parser.parse(new InputStreamReader(conn.getInputStream()));
			} catch (ParseException ex) {
				throw new IOException("JSON parser error, " + ex.getMessage(), ex);
			}

			String output;
			System.out.println("Output from Server .... \n");
			System.out.println(obj.toJSONString());
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONArray dataArray = (JSONArray) obj.get("data");
		String buildString = "";
		for (int i = 0; i < dataArray.size(); i++) {
			JSONObject dataField = (JSONObject) dataArray.get(i);
			String countryString = dataField.get("country").toString();
			buildString = buildString + "['" + countryString + "', " + dataField.get("count") + "],";
		}

		System.out.println("buildString:" + buildString);

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(ClientConfig.OUTPUT_PATH + "zikaWorldReport.html"));
			// Reference: Used Google Chart API below
			// https://developers.google.com/chart/
			bw.write("<html>\n<head>" + "\n<title>ZIKA Infections-World Report</title>"
					+ "\n<p><font size=\"5\" color=\"black\">ZIKA Infections-World Report</font></p>"
					+ "\n<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
					+ "\n<script type=\"text/javascript\">" + "\ngoogle.charts.load('current', {"
					+ "\n'packages':['geochart']," + "\n'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'"
					+ "\n});" + "\ngoogle.charts.setOnLoadCallback(drawRegionsMap);" + "\nfunction drawRegionsMap() {"
					+ "\nvar data = google.visualization.arrayToDataTable([" +
					"\n['Country', 'Zica_cases']," + buildString.substring(0, buildString.length() - 1) +
					"\n]);" + "\nvar options = {colorAxis: {colors: ['#e7711c', '#4374e0']}};"
					+ "\nvar chart = new google.visualization.GeoChart(document.getElementById('regions_div'));"
					+ "\nchart.draw(data, options);" + "\n}" + "\n</script>" + "\n</head>" + "\n<body>"
					+ "\n<div id=\"regions_div\" style=\"width: 900px; height: 500px;\"></div>" + "\n</body>"
					+ "\n</html>");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Desktop.getDesktop().browse(new URI(ClientConfig.OUTPUT_PATH + "zikaWorldReport.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
