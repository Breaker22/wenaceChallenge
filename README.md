# Wenace Challenge
> Challenge de Wenace

## Servicio price

> Obtiene el precio del bitcoin de acuerdo a una fecha y el porcentaje del valor

### Request

`GET /price?time=2021-10-31%2017:20:22`

```
curl -i -H 'Accept: application/json' http://localhost:8080/price?time=2021-10-31%2017:20:22
```

### Response
```
HttpStatus: 200 OK
Content-Type: application/json

price: 61434.5 prc: 61434.5
```

## Servicio prices

> Obtiene el precio del bitcoin de acuerdo a dos fechas y el porcentaje del valor

### Request

`GET /prices?time=2021-10-31%2017:20:22&time=2021-10-31%2017:21:22`

```
curl -i -H 'Accept: application/json' http://localhost:8080/prices?time=2021-10-31%2017:20:22&time=2021-10-31%2017:21:22
```

### Response
```
HttpStatus: 200 OK
Content-Type: application/json

price: 61434.5 prc: 61434.5
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