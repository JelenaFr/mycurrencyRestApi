#Currency
This app is a very basic currency API. 
##Description
This service is reading public data from European Central Bank
Note: The reference rates are usually updated around 16:00 CET on every working day.
API make query in European Central Bank to get the rates in EUR.
All these rates are stored in the DB.

API can provide: 
Overview of currency rates; 
• Update currency; 
• Add new currency; 
• Delete currency;


Backend: Java
Frontend:  VueJs
Database: Relational (MySQL)

Please provide URL for the schema to connect to MySQL data base and username and password in application.properties

http://localhost:8080/

###Installation
git clone https://github.com/JelenaFr/mycurrencyRestApi

cd mycurrencyRestApi

mvn install

java -jar mycurrencyRestApi.jar