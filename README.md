# Tic-Tac-Toe Game

Implementation of Tic-Tac-Toe game rest service in Spring Boot.
The application also contains web-ui on AngularJS.

## Build And Run Application

A. Application runs on port 8080.

B. To run application Java 8 and Maven should be installed.

C. To build web-ui npm should be installed.
If web-ui is not required D and E could be skipped.

D. User also needs to have bower installed globally:

`npm install bower -g`

E. When bower is installed user should install bower components:

`bower install` 

This should be done in project directory.

F. After bower components are installed, the project jar can be build via maven tool:

`mvn clean package`

This and further should be done in project directory.

G. Now tic-tac-toe-1.0-SNAPSHOT.jar is created in target directory of the project.
It can be run with:

`java -jar target/tic-tac-toe-1.0-SNAPSHOT.jar`

## Run Application in a Docker Container

Application already has Dockerfile to create 'docker' image in 'docker' folder.

To create image, type:

`docker build -t tic-tac-toe .`

from 'docker' folder.

To run the image, type:

`docker run -ti -p 8080:8080 -d tic-tac-toe`

Also there is already built jar in 'docker' folder, so one can skip building
application section.

## Rest Service Description

A. As it was already mentioned application runs on port 8080. It has the
only endpoint '/board', which can be accessed via POST. The JSON Board object
should be placed in the request body. Example:

```
{
    "board": [
        "   ",
        " X ",
        "   "
    ],
    "status": "Playing!"
}
``` 

B. 'Content-Type: application/json' header is required in request. 

C. The status field is not required (and is ignored) in request. It is used in 
the server response, to indicate one of three possible statuses:
'X wins!', 'O wins!', 'Draw!'.

D. The response object is the same. If request board has already a winning or draw
position the move will not be made. Only the status will be changed in this case
in response board. In other case the board with the move made will be returned.    

E. Swagger-ui is attached as a REST API Description Service and is available on:

'http://localhost:8080/swagger-ui.html'.

Here and below 'localhost' should be changed to ip address of the instance where application
is running.

## Web-UI Description

Web-UI is available at:

'http://localhost:8080/'.