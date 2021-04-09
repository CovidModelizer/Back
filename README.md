# Covid_Modelizer_back

## Accès
* API : [http://api.covid-modelizer.fr](http://api.covid-modelizer.fr/swagger-ui/index.html?configUrl=/api-docs/swagger-config)
* Application : http://covid-modelizer.fr

## Fonctionnalités
* Batch de récupération quotidient des [données gouvernementales](https://www.data.gouv.fr/fr/datasets/r/d2671c6c-c0eb-4e12-b69a-8e8f87fc224c)
* Batch de calcul quotidient des modélisations
* API d'accès aux données reels et modélisées

## Technologies
* Spring Boot
* Spring Batch
* Swagger

## Docker
* [docker-hub](https://hub.docker.com/r/covidmodelizer/back)

## Launch
* Setup une base de donnée dans [le fichier propriété](covid-modelizer-app/src/main/resources/application.properties)
* Télécharger le certificat SSL de [opendata.gouv](https://www.data.gouv.fr/fr/)
* Importer le certificat dans votre JRE : 
```
keytool -import -file <certifiact> -alias <nom que vous souhaitez> -keystore <path vers le fichier cacerts>
```
* Lancer le projet:
```
./mvn spring-boot:run
```
