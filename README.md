# airlines_rest_web_app
airlines_app

This is a Java Spring Web application. 

App needs MongoDB running on port 27017.

After successfully starting the app you can access it on localhost on port 6000.

## First functionality
You have to send get request to localhost:6000/cargo_weight with flightNumber and departureDate params.

here is an example of correct request : 'localhost:6000/cargo_weight?flightNumber=3375&departureDate=2017-11-28T09:51:33 -01:00'



## Second functionality
You have to send get request to localhost:6000/airport_info with airportCode and departureDate params.

here is an example of correct request : 'localhost:6000/airport_info?airportCode=KRK&departureDate=2020-11-29T01:29:00 -01:00'


## Changing test data
To change test data you have to modify cargo.json and flights.json located in src/main/resources folder.

