## Frameworks

Spring boot 2.3.5 - Backend (Using Open JDK 11)
Angular 10 

## About the configuration

The frontend app is embeded on backend static files, it will run on any port you set on properties if default (8085) is being used

The sql script creates an user to grant access to the database, if is not necessary you can replace it for the postgres user or any user with all access to the database where you will run the script

## Spring backend

The application is secured with basic sprign security configuration, allowing cors only for the endpoint used to get the transaction details for user with priviligies, in this case the endpoint named 'getGeneralInfo' in the postman collection needs a token of a admin level user, wich is craeted running the script; all the other controllers validate the token to get authenticated.

## Structure

The project structure is simple, divided on folders for security and datasource cofiguration , security filter, controllers invoking service interfaces, the implementation of the services is on service subfolder, those use the JPA repositories for making the queries to the database, defined in the model folders within the entity models and general data transfer models on the object folders 

## Testing

To test the application there are a dew considerations, I set the document, email as unique, so it will validate that for every user creation request.

As the document says, once the user has logged he can see only their donation transactions, only de admin user can fetch all the information without restrictions, with date parameters, the priviligies are validated with the token information directly on the server, it just ask for the token.




