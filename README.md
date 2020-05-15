# Never Late

## Build
/!\ For now, remove the MySql dependency, it is not used.

`mvn clean package`

## Run

`java --jar target/never-late-0.0.1-SNAPSHOT.jar`

## Endpoints
### Get next departures

`http://localhost:8080/departures/{UICcode}?line={line}`

> Example :
    `http://localhost:8080/departures/87381137?line=L`