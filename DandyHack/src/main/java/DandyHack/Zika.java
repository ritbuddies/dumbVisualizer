package DandyHack;


//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.text.ParseException;

@Path("/zika")
public class Zika {

    QueryData queryData = new QueryData();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Hello world!";
    }

    @GET
    @Path("/graph1")
    @Produces({MediaType.APPLICATION_JSON})
    public JSONObject getTimeSeriesDataByCount(@Context UriInfo uriInfo) throws ParseException, JSONException {

        String db = uriInfo.getQueryParameters().getFirst("db");
        String type = uriInfo.getQueryParameters().getFirst("type");
        queryData.setTimeSeriesByCount(db);
        System.out.println(queryData.getTimeSeriesByCount());
        System.out.println(db + " " + type);

        JSONObject timeSeriesData = new JSONObject();

        timeSeriesData.put("dataset_ID",1);
        timeSeriesData.put("reportType","zikaTimeVsCountReport");
        JSONArray details = new JSONArray();
        for(DataNode currentDataNode: queryData.getTimeSeriesByCount())
        {
            JSONObject temp = new JSONObject();
            temp.put(currentDataNode.date, currentDataNode.count);
            details.put(temp);
        }

        timeSeriesData.put("data", details);
        return timeSeriesData;
    }

}