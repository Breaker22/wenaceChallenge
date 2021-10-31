# Wenace Challenge
> Challenge de Wenace

## Servicio price

> Obtiene el precio del bitcoin de acuerdo a una fecha

### Request

`GET /price?time=2021-10-31%2017:20:22`

```
curl -i -H 'Accept: application/json' http://localhost:8080/
```

### Response
```
HttpStatus: 200 OK
Content-Type: application/json

602432.2
```

## Dependencias
* Spring 2.1.5
* H2
* JUnit

## Ejecucion de proyecto
```
mvn clean install
java -jar wenaceChallenge.jar
```