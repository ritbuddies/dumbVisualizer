package DandyHack;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by JINESH on 2/17/2018.
 */
public class QueryData {


    List<DataNode> timeSeriesByCount;

    QueryData(){
        timeSeriesByCount = new ArrayList<DataNode>();
    }

    public List<DataNode> getTimeSeriesByCount() {
        return timeSeriesByCount;
    }

    public void setTimeSeriesByCount(String db) throws ParseException {

        timeSeriesByCount.add(new DataNode("11-11-2016", 5));
        timeSeriesByCount.add(new DataNode("11-12-2016", 15));
        timeSeriesByCount.add(new DataNode("11-5-2016", 25));

    }





}
