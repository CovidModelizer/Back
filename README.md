[![Build Status](https://travis-ci.com/CovidModelizer/Back.svg?branch=main)](https://travis-ci.com/CovidModelizer/Back)

# CovidModelizer - Back

## Accès

* [API](https://api.covid-modelizer.fr/swagger-ui/index.html?configUrl=/api-docs/swagger-config)
* [Application](https://covid-modelizer.fr)

## Fonctionnalités

* Batch quotidien de récupération
  des [données gouvernementales](https://www.data.gouv.fr/fr/datasets/r/d2671c6c-c0eb-4e12-b69a-8e8f87fc224c)
* Batch de calcul quotidien des modèles de prédictions
* API de données réelles et modélisées

## Technologies

* Spring Batch
* Spring Boot
* Swagger
* [Docker](https://hub.docker.com/r/covidmodelizer/back)

## Exécution

* Télécharger le certificat SSL de [opendata.gouv](https://www.data.gouv.fr/fr/)
* Importer le certificat dans votre JRE :

```
keytool -import -file <certificat> -alias <donner un nom au certificat> -keystore <path vers le fichier cacerts>
```

* Lancer le projet :
    * Dans [covid-modelizer-app/](https://github.com/CovidModelizer/Back/tree/main/covid-modelizer-app)

```
./mvnw spring-boot:run
```

* Accéder à l'API :
    * [localhost:8080](http://localhost:8080)
