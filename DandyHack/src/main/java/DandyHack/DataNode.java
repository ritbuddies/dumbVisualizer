package DandyHack;

class DataNode{

    String date, country, category;
    int count;
    DataNode(){
        date ="";
        country = "";
        category = "";
        count = 0;
    }

    DataNode(String date, int count){
        this.date = date;
        this.count = count;
    }

}