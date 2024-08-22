# API Documentation

## Endpoints

### POST http://localhost/api/reservations
**Request Body:**
```json
{
  "id": 123,
  "user": {
    "id": 1,
    "name": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  },
  "space": {
    "id": 456,
    "name": "Playground",
    "floor": 1,
    "description": "Children's playground",
    "capacity": 50,
    "available": true
  },
  "startTime": "2024-08-16T10:00:00Z",
  "endTime": "2024-08-16T12:00:00Z",
  "status": "PENDING"
}
```
### GET http://localhost/api/reservations

#### Response (200 OK):
```json
{
  "id": 123,
  "user": {
    "id": 1,
    "name": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com"
  },
  "space": {
    "id": 456,
    "name": "Playground",
    "floor": 1,
    "description": "Children's playground",
    "capacity": 50,
    "available": true
  },
  "startTime": "2024-08-16T10:00:00Z",
  "endTime": "2024-08-16T12:00:00Z",
  "status": "PENDING"
}

```
### GET http://localhost/api/reservations/user/{userId}
#### Response (200 OK):
```json
[
  {
    "id": 123,
    "user": {
      "id": 1,
      "name": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com"
    },
    "space": {
      "id": 456,
      "name": "Playground",
      "floor": 1,
      "description": "Children's playground",
      "capacity": 50,
      "available": true
    },
    "startTime": "2024-08-16T10:00:00Z",
    "endTime": "2024-08-16T12:00:00Z",
    "status": "PENDING"
  }
]
```
### GET http://localhost/api/reservations/space/{spaceId}

#### Response (200 OK):
```json
[
  {
    "id": 123,
    "user": {
      "id": 1,
      "name": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com"
    },
    "space": {
      "id": 456,
      "name": "Playground",
      "floor": 1,
      "description": "Children's playground",
      "capacity": 50,
      "available": true
    },
    "startTime": "2024-08-16T10:00:00Z",
    "endTime": "2024-08-16T12:00:00Z",
    "status": "PENDING"
  }
]
```
### GET http://localhost/api/reservations/space/{spaceId}
#### Response (200 OK):
```json
[
  {
    "id": 123,
    "user": {
      "id": 1,
      "name": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com"
    },
    "space": {
      "id": 456,
      "name": "Playground",
      "floor": 1,
      "description": "Children's playground",
      "capacity": 50,
      "available": true
    },
    "startTime": "2024-08-16T10:00:00Z",
    "endTime": "2024-08-16T12:00:00Z",
    "status": "PENDING"
  }
]
```
### GET http://localhost/api/spaces
#### Response (200 OK):
```json
{
  "content": [
    {
      "id": 456,
      "name": "Playground",
      "floor": 1,
      "description": "Children's playground",
      "capacity": 50,
      "available": true,
      "typeSpace": "Play Area",
      "codeUuid": "e2d89a56-98ee-4e6f-a914-ef7a6f78e6c7"
    }
  ],
  "page": {
    "size": 1,
    "number": 0,
    "totalElements": 1,
    "totalPages": 1
  }
}

``` 
