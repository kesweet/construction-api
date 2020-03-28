# construction-api
SIMPLE demo of API using Spring. This is a work in progress.

# To run

1. Create a mysql database and user
1. Update values in /construction-api/src/main/resources/application.properties
   * You cand remove the ${APP_DB_NAME} tokens and replace them with values for now, but eventually you need to [use env variables for db settings](https://education.launchcode.org/gis-devops/configurations/02-environment-variables-intellij/index.html).
1. Run bootRun gradle task
1. Go to http://localhost:8080/api/items
   * You will get an empty `[]` if nothing is in the db

# Use REST tool to send requests

These examples are using [Postman request tool](https://www.postman.com/)

## POST Item (send new item to controller to be saved into db)
![POST Item Exmaple](https://raw.githubusercontent.com/welzie/construction-api/master/images/post-item.png "POST Item")

## GET Items (retrieve all items out of the db and return them as JSON)
![GET Items Exmaple](https://raw.githubusercontent.com/welzie/construction-api/master/images/get-items.png "GET Items")


# Example JSON returned
```
[
    {
        "id": 1,
        "name": "Elbert",
        "price": 99.99,
        "newItem": false,
        "description": "The cat that wont't stop annoying me"
    },
    {
        "id": 2,
        "name": "Nails",
        "price": 0.99,
        "newItem": false,
        "description": "The little pointy metal things."
    }
]
```
