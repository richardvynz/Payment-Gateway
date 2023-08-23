                      Payment Gateway Application
The Payment Gateway application is a secure solution developed using Spring Boot, allowing users to conduct seamless purchases while ensuring automatic refunds for failed transactions.

Technologies Used
Spring Boot
PostgreSQL
Git
Spring Data JPA
JUnit and Mockito (for testing)
Postman (for API testing)
docker-compose (for containerization)
Features
Seamless Purchases: Users can easily make purchases using this payment gateway, ensuring a smooth and user-friendly experience.

Automatic Refunds: In case of failed transactions, the application handles automatic refunds, enhancing user trust and satisfaction.

Getting Started
Prerequisites
Java Development Kit (JDK) installed
Docker and docker-compose installed (for containerization)
PostgreSQL database (configured in application.properties)
Installation and Setup
Clone the repository: git clone https://github.com/richardvynz/Payment-Gateway.git

Navigate to the project directory: cd Payment-Gateway

Build the application: ./mvnw clean install

Start the application and PostgreSQL database using docker-compose: docker-compose up

Access the application at: http://localhost:8080

Usage
Open Postman or any API testing tool.

Send a POST request to http://localhost:8080/api/payment with payment details in JSON format.

Sample Request:

json
Copy code
{
  "amount": 50.0,
  "cardNumber": "1234567890123456",
  "expiryDate": "12/23",
  "cvv": "123"
}
The application will process the payment. If successful, you'll receive a success response. If not, a refund will be initiated.

Testing
To run the unit tests, use the following command:

bash
Copy code
./mvnw test
Contributors
Vincent Ndubuisi Richard
License
This project is licensed under the MIT License - see the LICENSE file for details.

