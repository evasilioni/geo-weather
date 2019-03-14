# Geo-Weather
A Minimum Viable Product (MVP) with a simple REST API endpoint providing the current weather
conditions of the available weather stations of a country using the provided web services of
geonames.org.

**Prerequisites**
mvn clean compile package : to build the application

**Packaging**
The application was build by using a package-by-feature approach.

**Installing**
Nothing needs to be installed. Even the database used, is h2 in memory database.

**Running the tests**
mvn test :  in order to run the tests of the application
Two tests fail when the whole suite runs however when this tests run isolated, are successful.
However I did'nt had the time to find out why.

**Break down into end to end tests**
Tests are Integration and Unit test for every feature, service

**Built With**
Maven - Dependency Management

**Contributing**
Please read README.md for details on our code of conduct.

**endpoints**
1. http://localhost:8085/login
--> body example : {
                   	"username":"ferryscanner",
                   	"password":"P@ssword"
                   }
2.http://localhost:8085/country/isoAlphaCode?countryCode={countryCode}  
--> Authorisation token in headers                 

3.http://localhost:8085/weatherObservation?isoAlphaCountyCode={isoAlphaCode}
--> Authorisation token in headers

**Security**
JWT security has been used for the authentication process.

**Authors**
Silioni Evangelia