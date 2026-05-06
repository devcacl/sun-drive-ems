# Sun Drive EMS

Spring Boot inventory management application for products, users, roles and sales.

## Requirements

- Java 17 or newer
- PostgreSQL
- Maven Wrapper included in the project

## Configuration

The application reads database settings from environment variables and keeps local defaults for development.

| Variable | Default |
| --- | --- |
| `DB_URL` | `jdbc:postgresql://localhost:5432/db_inventory` |
| `DB_USERNAME` | `user_java` |
| `DB_PASSWORD` | empty |

## Run

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts on port `8080`.

## Test

```bash
./mvnw test
```

On Windows:

```powershell
.\mvnw.cmd test
```
