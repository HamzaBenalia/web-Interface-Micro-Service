# Web Interface
Web Interface service is a user interface that shows the functions of the whole system. It shows you patient crud, note crud and the report.
<br> **Web interface is served on url : `localhost:9092`**.

## Technical Stack
Microservice is built with the followings technologies :
- Java 17 and Spring Framework 3.0.1
- HTML with Bootstrap and little of Js for UI elements
- Thymeleaf as a template engine
- Maven

The web interface communicates with each of the micro-services to show you all necessary data.


## WebInterface test-jacoco


![test webInterface](https://github.com/HamzaBenalia/web-Interface-Micro-Service/assets/99022185/35a592a9-12d7-4c64-8a0d-3ec6fe093cfa)



# MediLabo Solutions (we care for you)
![medilabo solution ](https://github.com/HamzaBenalia/web-Interface-Micro-Service/assets/99022185/cd733338-b503-4d2d-bfab-ead007a29391)


## Architecture
L'application est basée sur une architecture micro-services comprenant les services suivants:

Patients - Écoute sur le port 8080.
Notes - Écoute sur le port 9090.
Reports - Écoute sur le port 9091.
User Interface - Écoute sur le port 9092. C'est par ce service que les utilisateurs interagissent avec le système (UI).


## Prérequis
Docker installé sur votre machine.
Git installé sur votre machine.

### Étapes d'installation
Cloner le dépôt:

![clone repo](https://github.com/HamzaBenalia/web-Interface-Micro-Service/assets/99022185/24a0c881-c0e0-45c1-8e8a-295f205a1d43)

### Naviguer vers le dossier du projet:

![naviguer vers le dossier ](https://github.com/HamzaBenalia/web-Interface-Micro-Service/assets/99022185/ec678bab-9e25-4433-ad58-0eea622d14eb)



### Construire et lancer les conteneurs Docker:


![construire le conteneur docker ](https://github.com/HamzaBenalia/web-Interface-Micro-Service/assets/99022185/6663e2da-8366-4385-b542-19bbf530db82)



2. When every images are pulled, you have to create the `patients` table in the MySQL service
    ```
    ```
    ```shell
    > docker compose exec mysql /bin/bash/
    ```
    then

    ```shell
    > mysql -u root -proot
    > use abernathyclinic;
    ```

    **Now you should be connected to the MySQL service**. Paste the following SQL file into your terminal : 
    ```sql
     DROP TABLE IF EXISTS `Patients`;
    CREATE TABLE `Patients` (
    Id int NOT NULL AUTO_INCREMENT,
    nom VARCHAR(125),
    prenom VARCHAR(125),
    date_de_naissance VARCHAR(125),
    genre VARCHAR(125),
    addresse_postale VARCHAR(125),
    numero_de_telephone VARCHAR(125),
  PRIMARY KEY (Id)
);
 ```
 ```

 Check if the table has been created using : 

    > SHOW TABLES;


    If the creation is successful, you should see the `patients` table in the resulting output.
    The application should be running on
     **[http://localhost:9092](http://localhost:9092)**.



###Support

Pour tout problème, question ou suggestion, veuillez créer une issue sur ce dépôt GitHub ou contactez-moi à [hamzabenalia93@gmail.com].

    
