bookstoreApp:

The bookstoreApp projects allows the registration and log in of new users, so they can 
retrieve the existing books and perform orders. 
Currently there is only a subproject called bookstore. It consists of a Spring Boot 
application that expose 4 APIs:

_ An API for users registration:

POST -> http://localhost:8080/register
Body: 
{
    "firstName": String,
    "lastName": String,
    "email": String,
    "password": String 
}

Rules to compose the password:

-- Have at least eight characters or more.
-- Include at least one capital letter.
-- Contain at least one lowercase letter.
-- Have at least one digit (0-9).
-- Include at least one special symbol of the next ones @#$%^&+=!*?.
-- And importantly, it shouldn’t contain spaces or tabs.

_ API for login of registered users: You will get the token needed for the other next APIs

POST -> http://localhost:8080/authenticate
Body:
{
    "email": String,
    "password": String
}

_ API for retrieving a page containing the existing books sorted by title (ASC).

POST -> http://localhost:8080/books/list
Body:
{
    "pageNumber": int
    "pageSize": int 
}
You need to use the token received by the login API.

_ API for checkout of an order.

POST -> http://localhost:8080/checkout
Body:
{
        "orderPrice": int
        "cartItemInputList": [
                                {
                                    "quantity": int,
                                    "bookId": String
                                }
                            ]
}

You need to use the token received by the login API.

REQUIREMENTS:

• JDK 21 • Maven

HOW TO RUN THE APPLICATION:

_ Clone the project bookstoreApp in your machine. 
_ With the command line move to bookstoreApp/bookstore and execute: mvn spring-boot:run
_ After that the 4 endpoints described above will be ready to be used for example by Postman.

Note: The project also contains a ui directory that is still in development.
Anyway, you can execute the login, signin and retrieving the books using this React App (all the APIs but the checkout).
To execute it:
_ With the command line move to bookstoreApp/ui and execute the next commands:
npm install
npm start
Then the application will be available in http://localhost:3000/
