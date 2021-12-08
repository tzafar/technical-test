# Branthill Technical Task - Vet appointment service
The project contains the technical task for Branthill Computing which should take no more than 2 hours.

# Getting Started
Included in the project are a few dependencies and starters to store data as well as an embedded web server (Jetty)
Data is stored using H2 and the initial data & schema information can be found in the resources folder.

# Requirements
* Design & implement an RESTful API that allows any web client to add, find, update and delete pets associated with the vet.
* Design & implement an RESTful API that allows any web client to create, read, update and delete appointments for pets.
* Ensure that it builds, zip up the project with your changes and return.

# Restrictions
* Implement a solution that does not make any use of ORM implementations and attempt to demonstrate SQL knowledge.

# Solution
These are the endpoints created
For Animals

 - `POST /animals`
 - `GET /animals/{animalId}`
 - `PUT /animals`
 - `Delete /animals/{animalId}`

For Appointments

 - `POST /appointments`
 - `GET /appointments/{id}`
 - `PUT /appointments`
 - `Delete /appointments/{id}`


Request body for Post and Put animals:

```
{
    "animalName": "L654sadfasdfsadfili",
    "vetId": 1,
    "animalTypeId": 1
}
```

Request body for Post and Put appointments
```
{
    "startTime": "2021-12-08T10:00:00",
    "animalId": 1
}
```
