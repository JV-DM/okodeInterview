# okodeInterview
 
This application runs a RESTFUL api server that searches films by title in themoviedb

### Running the server
In order to run the server is as easy as getting into the project folder and run the next command in a console prompt:
```
java -jar app.jar
```

### Request examples using CURL
The default URL for the application is: 
```
http://localhost:8080/movies
```
The application will ask for a title in order to search for the movies that have similar titles.

A typical curl query to search would be:
```
curl -X GET -G http://localhost:8080/movies -d title=star%20wars
```
Note that if the title has any spaces we should replace them with "%20" in the console, if you are querying in the browser, the browser will do that replacement for you
