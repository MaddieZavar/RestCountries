# RestCountries Project

This project is a Spring Boot application that interacts with the [REST Countries API](https://restcountries.com) to fetch and process information about European countries.

## Features
- Fetches a list of all countries based on a region.
- Returns selected attributes:
    - Name
    - Currency
- Supports sorting by name or currency in ascending or descending order.
- Fetch list of all currencies in the world, with information in which countries each currency
  is used.
- Uses `@FeignClient` for third-party API integration.

## Requirements
- Java 17
- Maven (Maven Wrapper included)

## Setup

### Clone the Repository
Clone the project to your local machine:
```bash
git clone <repository-url>
cd RestCountries
```

### Run the Application
Use the Maven Wrapper to start the application:
```bash
./mvnw spring-boot:run
```

### Access the API
Once the application is running, you can access the API at:
```text
localhost:8080/countries/{region}
```

#### Parameters:
- `sortBy` (optional): Sorting attribute (`name` or `currency`). Defaults to `name`.
- `order` (optional): Sorting order (`asc` or `desc`). Defaults to `asc`.

Example:
```text
http://localhost:8080/api/countries/europe?sortBy=currency&order=desc
http://localhost:8080/api/currencies

```

### Key Components
1. **Controller**: Handles API requests and returns responses.
2. **Service**: Contains the business logic for processing country data.
3. **Feign Client**: Interacts with the REST Countries API to fetch data.
4. **DTO Classes**: Models the structure of data received from the API.

## Dependencies
The project uses the following dependencies:
- Spring Boot Starter Web
- Spring Cloud Starter OpenFeign
- Lombok
- Maven Wrapper

## Testing
Run tests using:
```bash
./mvnw test
```

## Additional Notes
- Ensure you have an active internet connection to fetch data from the REST Countries API.
- The project includes Maven Wrapper for consistent build environments.
- Java 21 is required for compatibility.

