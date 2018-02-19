# dumbVisualizer
Most of the times users cannot interpret data from raw datasets and it is a tedious task to form meaningful reports. We wanted some utility which can fetch insightful reports, charts based on user selection.

## What it does?
1. Our dumb visualizer fetches insightful reports from a given dataset based on user selection. Our services try to provide the user with the best possible data visualization for the people who do not know how to find insights from data.
2. We have made it easily accessible from any View (Desktop/web/mobile application) since it is based on RESTful API to generate html reports.
3. User can see previously created reports on existing datasets, they can choose to create new reports or even upload new datasets.
Sharing of the charts/reports becomes easy in organizations since dumbVisualizer does data retrieval and chart preparation.

## Built With
Java, Spring MVC, Gradle, MongoDB, Java-Swings, jersey-framework

## Installations
### Database : MongoDB
Requirement : mongo-java-driver-3.6.3.jar, install mongo
MongoDB : https://stackoverflow.com/questions/18452023/installing-and-running-mongodb-on-osx
Jar File : http://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/  

## How we built it?
We have a client-server model. We have hosted our RESTful API on the server and we are providing clients with RESTful client utility. Based on user selection it fetches required report details from the rest API and plots different charts or graphs using google charts API. We are using MongoDB to store the data and mongo-connectors for data extraction using java.

## What's next for dumbVisualizer?
Create machine learning models to produce better insights from data. Create interactive dashboards and add other other health datasets.

## For more information about the project, please follow the below link:
https://devpost.com/software/dumbvisualizer

