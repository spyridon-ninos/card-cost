# Card Cost API
Exercise as part of the XXXXXX company hiring process

## Prerequisites
You need to have installed:
- jdk 14
- docker v19.03.0+ (if an older docker server is available, change the "version" from 3.8 to whatever suits you, in the docker-compose.yml file. Look out for compatibility issues)

## Running the system
You need to follow the steps below:
1. clone the code from https://github.com/spyridon-ninos/card-cost.git
1. cd /path/to/source/code/directory
1. run: `mvn clean install`
1. run: `docker-compose up --build`
1. open a browser window/tab
1. go to: `localhost:4000`

At this point you are ready to use the system

## General description
This system implements the requirements document provided during the hiring process from the XXXXXX group (see src/main/resources)

Initially the system is protected by a login page. There are two accounts that can be used:
1. Account with credentials: **user**/**userpass**
1. Account with credentials: **admin**/**adminpass**

- The user account is meant to be used only with the request that maps a credit card number with a clearing cost. As such, if the user account tries to use an endpoint that should only be used by the admin account, an access denied error (403) will be returned.

- The admin account is meant to be used with all the requests, along with all the CRUD endpoints provided by the system in order to manage the clearing costs stored in the system's database. As such, the admin account can use all the endpoints.

- Once the credentials are provided to the login page, the user is redirected to the api usage page (swagger-ui.html). From that point on, the user can start testing the endpoints.

## Security Considerations
1. The API is protected by basic authentication
1. In a zero-trust network/defence in depth implementation a different authZ/authN combination would be used (service-to-service friendlier)
1. HTTPS should also be used but it was deemed unnecessary for this implementation
1. The system implements authentication and authorization at the perimeter by protecting the endpoints
1. The system also implements authorization by protecting the main API calls in the business layer
1. The docker image runs as an ordinary (non-root) user. All non-essential java programs were removed from the docker image (mostly in order to avoid memory dumps)
1. The code inside the same java service is considered trusted

## Architectural description
* **Code design**: as suggested in best practices - most important is the functional style (wherever obviously required) used
* **Code organization**: The code is generally divided to business logic and code infrastructure. This organization is mostly obvious in the business layer.
* **Code architecture**: the hexagonal/binds-and-ports architecture is used to logically divide the code, however due to the fact that it's conceptually easier we refer to the different sections of the code as layers. The architecture is explained in great detail in the Microsoft Application Architecture Guide (see https://docs.microsoft.com/en-us/previous-versions/msp-n-p/ff650706(v=pandp.10)). Java packages were used to implement the architecture's layers:
  1. _Service layer_ (com.ninos.service): this is the layer that includes all the java services/endpoints ("rest controllers") to other (micro-)services
  1. _Core layer_ (com.ninos.core): this is the layer that includes all the business and infrastructure related java classes
  1. _Integration layer_ (com.ninos.integration): this is the layer that includes all the code required for the service to communicate with peers (databases, other services etc). This is most commonly known as "data layer"
* **System architecture**: a two-tier system architecture (cost-card-api service and the db) has been implemented due to the nature of this exercise

## Deployment Model
* The service is tailored to be deployed as a docker container
* The service configuration can be used as it is, in order to be deployed to a kubernetes cluster (using Helm charts)
* If a standalone deployment is required (or a non-kubernetes/helm compatible) then the application configuration file should be adapted

## Availability Model
* The service can be adjusted to be used in a high availability deployment model. Because it's a stateful service (it monitors users' sessions), we need to modify the cache used to be a distributed one. Aside from that, no other change is required.
