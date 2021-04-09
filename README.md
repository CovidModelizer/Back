[![Build Status](https://travis-ci.com/CovidModelizer/Back.svg?branch=main)](https://travis-ci.com/CovidModelizer/Back)
# Covid_Modelizer_back

## Accès
* API : [http://api.covid-modelizer.fr](http://api.covid-modelizer.fr/swagger-ui/index.html?configUrl=/api-docs/swagger-config)
* Application : http://covid-modelizer.fr

## Fonctionnalités
* Batch de récupération quotidien des [données gouvernementales](https://www.data.gouv.fr/fr/datasets/r/d2671c6c-c0eb-4e12-b69a-8e8f87fc224c)
* Batch de calcul quotidien des modélisations
* API d'accès aux données reels et modélisées

## Technologies
* Spring Boot
* Spring Batch
* Swagger

## Docker
* [docker-hub](https://hub.docker.com/r/covidmodelizer/back)

## Launch
* Setup les variables d'environnement suivante :
    * DB_USERNAME
    * DB_PASSWORD
    * DB_IP
    * DB_PORT
* Télécharger le certificat SSL de [opendata.gouv](https://www.data.gouv.fr/fr/)
* Importer le certificat dans votre JRE : 
```
keytool -import -file <certifiact> -alias <nom que vous souhaitez> -keystore <path vers le fichier cacerts>
```
* Lancer le projet:
```
./mvn spring-boot:run
```
