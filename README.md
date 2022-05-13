# Advanced Software Engineering: Bartlin

Software Project for the course "Advanced Software Engineering" at the DHBW Karlsruhe. Content of the course is to learn
principles about software programming e.g. DDD, SOLID and Clean Architecture.

## Installation

### Run the application

```
docker-compose up --build
```

or

```
gradle run
```

### URLs

http://localhost:8080

### Run All Tests

```
gradle test
```

## Useful Links

- Domain Driven Design
    - [Value Objects](./3-domain/src/main/kotlin/com/bartlin/domain/types)
    - [Aggregate](./2-application/src/main/kotlin/com/bartlin/application/order/BillOutput.kt)
    - [Entity](./3-domain/src/main/kotlin/com/bartlin/domain/drink/Drink.kt)
    - [Repository](./3-domain/src/main/kotlin/com/bartlin/domain/drink/DrinkRepository.kt)
    - [Service](./2-application/src/main/kotlin/com/bartlin/application/order/OrderService.kt)
- Patterns
    - [Strategy Pattern](./3-domain/src/main/kotlin/com/bartlin/domain/price)
- Tests
    - [Unit-Tests + Mocks](./2-application/src/test/kotlin/com/bartlin/application/drink/DrinkServiceTest.kt)
