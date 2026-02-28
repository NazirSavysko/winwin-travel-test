# Test Assignment: Two Spring Boot Services

Here are two microservices (`auth-api` and `data-api`) on Spring Boot that run in Docker along with Postgres. Everything as requested.

## What's inside

- **auth-api**: Handles user registration/login, proxies requests to `data-api`, and writes logs to the database.
- **data-api**: Just a simple text transformer service. Secured by an internal token.
- **docker-compose.yml**: A file to spin everything up with a single command (database + 2 services).

## Requirements

To run this, you only need **Docker** and **Docker Compose**. Java and Maven are only needed if you want to dig into the code locally.

## Configuration

All settings are in `.env`. Database passwords and secret tokens are there. Feel free to change them if you need to.

## How to run

Everything is done with one command. Open a terminal in the project folder and type:

`docker-compose up -d --build`

After that:
- `auth-api` will be available on port **8080**
- `data-api` will be on port **8081** (Exposed for testing, but requires internal token)

To shut everything down:
`docker-compose down`

## How to test the API (Postman)

I added your collection `win-win.postman_collection.json` to the project root. This is the most convenient way to test.

1.  Open Postman.
2.  Click **Import** and select the file `win-win.postman_collection.json`.
3.  There are 3 ready-made requests:

    *   **register**: Creates a user (example: `nazir@gmail.com`).
    *   **login**: Logs the user in and returns a token.
    *   **process**: Sends text for processing.

**Important:** In the `process` request, the `Authorization` header might contain an old token. Don't forget to copy the new token from the `login` response and paste it there (Bearer ...).

## If you need curl

If you're too lazy to open Postman, you can use the console:

### 1. Register

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d "{\"email\":\"nazir@gmail.com\",\"password\":\"Nazir228733\"}"
```

### 2. Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d "{\"email\":\"nazir@gmail.com\",\"password\":\"Nazir228733\"}"
```
Copy the token from the response!

### 3. Process Text

Replace `<your_token>` with yours.

```bash
curl -X POST http://localhost:8080/api/process \
  -H "Authorization: Bearer <your_token>" \
  -H "Content-Type: application/json" \
  -d "{\"text\":\"privet\"}"
```

**Expected response:**
```json
{
    "id": "...",
    "input": "privet",
    "output": "PRIVET",
    "timestamp": "...",
    "userId": "..."
}
```

## Checklist (all done)

- [x] **Reg & Login**: Works via `auth-api` (port 8080).
- [x] **Protected Process**: `/api/process` requires a JWT token.
- [x] **Service Communication**: `auth-api` calls `data-api` with the `X-Internal-Token` header.
- [x] **Privacy**: `data-api` rejects requests without the internal token (403).
- [x] **Database**: `auth-api` saves users and logs to Postgres.
