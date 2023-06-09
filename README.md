# Akbank & Patika.dev Java Spring Bootcamp Final Case

## About Project

It is desired to write a weather application. This application will provide weather forecasts using a RESTful web service. Users will be able to query weather forecasts for a city. Forecasts will be provided in 3-hour intervals covering a period of 5 days. The application will get weather forecasts using the OpenWeatherMap API.

## Project Requirements

- Restful web service: It will receive city and other parameters and provide weather forecast.

- Database: A database system will be used to store the cities and forecasts that users search for.

- Daily forecasts: Weather forecasts will be provided at 3-hour intervals covering a 5-day period.

- User registration: Users can register their cities by creating an account and view weather forecasts for the registered cities.

- API usage: The OpenWeatherMap API will be used to provide weather forecasts.

- Testing: Automated tests will be written to ensure code quality and performance.

- Documentation: Documentation of the RESTful service will be provided using tools like Swagger or OpenApi.

- Logs: A logging mechanism will be set up to help debug the application.

## Technologies
- Java Spring Boot
- API: https://openweathermap.org/api
- PostgreSQL
- Docker 
- Kafka

## Test


![test](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/d13ea586-3500-44f1-a0cd-1cbbc6ca31df)


## Swagger Screenshots

### User Registration Controller

#### Register


![register](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/05bd5bb3-cc0d-470f-93cf-c50ef80eba01)

#### Authenticate

![auth](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/a1f83c01-ad4b-48ca-8ec9-dbcb34258ad2)


### Weather Controller
![Screenshot_3](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/98692c72-2eab-44b8-94fe-125296d34f2f)
![Screenshot_4](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/1834953b-c58f-4ab3-825b-2dc61b5e7ab2)


### User Controller

#### Find all users

![getuser](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/557ec747-ae6e-437a-9c48-d76ec5056cd6)

#### Find by id

![findbyid](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/4ec01002-fa80-4fda-9339-21b93a0db13c)


#### Find by username

![username](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/e0b17264-dd11-4c8c-a0cd-091a5b6c89cf)


#### Saved Cities
- If user has no saved cities
![savedcities](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/ff5b0af0-7917-4773-9e32-ba9487661d69)


- If user has saved cities
![savedcities2](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/fab37064-5de3-40c7-b3dc-eeb6642692cb)


#### Save City

![Screenshot_2](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/61a0dc51-364c-4b16-9581-7438dc2d9a22)



#### Delete City

![deletecity](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/27e8c33d-c83c-41c4-88e6-83bb674feedd)


#### Update user
![Screenshot_14](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/678a387d-ff5e-4df5-8a22-91a31a347622)


#### Delete User

![Screenshot_15](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/e46fc823-43fb-4cb3-b742-bd2918ce26bb)

## Logs
Some examples logs are shown below.
### Error Logs
- ![Screenshot_3](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/94e98cfa-ac4c-4fdc-995e-50bd436dbd06)
- ![Screenshot_4](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/cba6efa5-c1a0-4d01-8800-a559cd007aac)
- ![Screenshot_5](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/bc67a92f-773e-4864-885d-49e0a08748b3)
- ![ss](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/aa4e77f2-0448-4f21-974e-ecf9ec8f76e9)
- ![Screenshot_5](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/03aed418-bfab-4794-97bd-2d63241e5a7f)


### Info Logs
- ![Screenshot_6](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/332cade7-f130-4b9c-a5a8-7677b517690f)
- ![Screenshot_1](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/d7fabf11-b138-4d60-b072-1779215cea40)


## Database

![database](https://github.com/oznurkandakoglu/bootcamp-finalcase/assets/73194842/7cd2a790-0236-4ee8-a588-8c458365b9f1)

