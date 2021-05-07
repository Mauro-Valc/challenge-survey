# Reto RobinFood

Prueba tecnica para desarrollador Back

## Tecnologias
* Java 11
* Spring
* Gradle
* MySql

### Ejecutar en local

Para correr el programa debemos correr mySql de manera local, montando el squema que se encuentra en db/challenge_script.sql ademas de poblar la DB con el scrip db/base_data.sql
las credenciales de conexion a la DB se pueden actualizar desde src/main/resources/application.yml

```
gradle build //compilamos el proyecto y refrescamos dependencias
gradle run //corremos el proyecto local
```


## Uso
### obtener todas las encuestas disponibles

Request: 
- GET localhost:8080/challenge/survey

Response:

```
  200 OK
  [
  {
    "id": 1,
    "name": "name_survey"
  }
]
```
### obtener el detalle de una encuesta

Request: 
- GET localhost:8080/challenge/survey?:id

Response:

```
  200 OK
{
  "id": 1,
  "name": "name_survey",
  "questions": [
    {
      "id": 1,
      "question": "name_question",
      "options": [
        {
          "id": 1,
          "value": "value_answer"
        }
      ]
    }
  ]
}
```

### enviar una encuesta

--POST localhost:8080/challenge/survey
Request body
```
  {
	"email":"useremail",
	"name":"username",
	"answers":[
        {
          "id": "id_option",
          "value": "value"
        }...
	]
}
```

Response:

```
  200 OK
```

### Test

Para la ejecucion de los test automaticos utilice jUnit 5.
Los test se pueden ejecutar utilizando el comando:

```
gradle test
```

#### Cobertura de codigo

Para validar la cobertura utilice el plugin Java Code Coverage de eclipce

![code_coverage](coverage/coverage.PNG)

### - [Mauricio Valencia Cosme](mauro.valc@gmail.com)