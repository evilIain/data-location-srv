## User location


### Introduction

Start application

```
mvn clean install -DskipTests
```

For database changes run
```
mvn clean compile liquibase:clearCheckSums liquibase:dropAll liquibase:update -D"liquibase.contexts"=gen liquibase:diff 
```

Run Tests:

1. Install and start Docker (any version, was tested on 4.15.0)
2. Run tests

To start application:

1. Compose docker image from docker-compose.yml.
    Go to directory src/test/resources and run command ```docker-compose up -d```
2. Start application with "local" profile active.
   ```-Dspring.profiles.active=local``` or simply point local in latest Intellij IDEA versions in Spring Boot application start.

---

### How to use

Method ```createUser POST "/api/v1.0/user"```:

Input:

```
{
    "email": "johndoe@gmail.com",
    "firstName": "John",
    "secondName": "Doe"
}
```

Output:

```
{
    "userId": "b3a826f8-fc5e-41b2-943c-03c2a1477b8f",
    "email": "johndoe@gmail.com",
    "firstName": "John",
    "secondName": "Doe"
}
```

---

Method ```updateUser POST "/api/v1.0/user/{userId}"```:

All validation constraints are checked and will throw exception if violated.

Input:

```
{
    "email": "johndoe@gmail.com",
    "firstName": "John",
    "secondName": "Peterson"
}
```

Output:

```
{
    "userId": "b3a826f8-fc5e-41b2-943c-03c2a1477b8f",
    "email": "johndoe@gmail.com",
    "firstName": "John",
    "secondName": "Peterson"
}
```

---

Method ```getLocations POST "/api/v1.0/user/{userId}/locations"```:

Input: dateFrom field is obligatory, dateTo is optional (if not specified current date will be considered)

Input:

```
{
    "dateFrom": "2023-01-29T09:44:57.155832Z"
}
```

Output:

```
{
    "userId": "b3a826f8-fc5e-41b2-943c-03c2a1477b8f",
    "locations": [
        {
            "createdOn": "2023-01-30T14:01:34.230041Z",
            "location": {
                "latitude": 14.5,
                "longitude": 14.5
            }
        },
        {
            "createdOn": "2023-01-30T14:02:34.230041Z",
            "location": {
                "latitude": 14.6,
                "longitude": 14.6
            }
        }
    ]
}
```

---

---

Method ```getLocation GET "/api/v1.0/user/{userId}/location"```

Prints latest location provided for user if present, else prints only user information.

Output:

```
{
    "userId": "b3a826f8-fc5e-41b2-943c-03c2a1477b8f",
    "email": "johndoe@gmail.com",
    "firstName": "John",
    "secondName": "Peterson",
    "location": {
        "latitude": 14.6,
        "longitude": 14.6
    }
}
```

---

Method ```registerLocation POST "/api/v1.0/user/location"```

Input: createdOn field is obligatory.

```
{
    "userId": "b3a826f8-fc5e-41b2-943c-03c2a1477b8f",
    "createdOn": "2023-01-30T14:02:34.230041300Z",
    "location": {
        "latitude": "14.6",
        "longitude": "14.6"
    }
}
```

Output:

```
{
    "locationId": "1e523283-43f6-4df4-bdd7-48326b41ca9c",
    "latitude": 14.6,
    "longitude": 14.6,
    "createdOn": "2023-01-30T14:02:34.230041300Z"
}
```

### Tests

There are positive and negative test scenarios, including validation violation.
To run tests Testcontainers were used.
