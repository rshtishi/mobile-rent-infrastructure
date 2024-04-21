email: bt-test-x@proton.me
password: 123456

78



# Mobile Rent Infrastructure

## Description

The app allows user to view all the phones, and book a phones that are available. The app will send a notification to the
user that the phone is booked successfully together with a link to return the phone he booked.

## Setup

1. Clone the repository 
```shell
git@github.com:rshtishi/mobile-rent-infrastructure.git
```
2. Go to the project directory
```shell
cd mobile-rent-infrastructure
```
3. Run the following docker compose command
```shell
docker-compose up
```
4. While waiting the docker compose to finish, go to the browser and  paste the following url: https://mailtrap.io/signin, and login with the following credentials:
```shell
account mailtrap.io: bt-test-x@proton.me
password: 12345678
```
5. Go to the `Inboxes->My Inbox` and you will see the email that was sent by the app.
6. Create a new tab and paste the following url: http://localhost:4200/home
7. You will see the home page of the app. You can view the phones and book a phone that is available.

## Technologies

- Angular for developing the web app
- Spring Boot for developing the mobile rent service and the notification service
- Docker for containerizing the app
- Mysql for the database
- RabbitMQ for the messaging service between services

## Architecture

The app is divided into three services:
- Mobile Rent Service: This service is responsible for managing the phones and the bookings. It exposes a REST API that is used by the web app to view the phones and book a phone.
- Notification Service: This service is responsible for sending the email to the user that the phone is booked successfully. It listens to the RabbitMQ queue and sends the email.
- RabbitMQ: This is the messaging service that is used by the Mobile Rent Service to send a message to the Notification Service that the phone is booked successfully.
- Mysql: This is the database that is used by the Mobile Rent Service to store the phones and the bookings.
- Web App: This is the web app that is used by the user to view the phones and book a phone.


![Architecture](images/architecture.jpg)

