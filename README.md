# digitalbanking Transfer Microservice
Digital Banking Training

We are in the process of dockerizing this.

mvn clean install
Run App.java from com.capg.cardservices package

This is a drop wizard application
It will run embedded jetty container.

To run
java –jar card.jar server configuration.yml

To test, start postman app in chrome
http://localhost:90/cardservices/556677/cards

You have to create db_cards database in postgres.
Use sql scripts to create the tables and insert the data.

## Running from Eclipse/STS
Right click on App.java -> Run As -> Run Configurations -> In right panel -> Arguments -> type server configuration.yml
Click on Run button.

