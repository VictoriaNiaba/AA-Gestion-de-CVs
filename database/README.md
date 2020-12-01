## Créer un compte administrateur et une BDD spécifiques au projet

Créer un compte spécifique au projet permet d'éviter les fuites de mots de passe personnels et assure que chaque membre du projet partage la même version du fichier `persistence.xml`.  

La procédure suivante indique comment créer une base de données pour le projet ainsi que son compte administrateur :

1. Se connecter au CLI mysql

   ```bash
   $ sudo mysql
   ```

2. Créer la base de données du projet

   ```mysql
   CREATE DATABASE Gestion-de-CVs;
   ```

3. Créer l'utilisateur `gestion-cv-admin`

   ```mysql
   CREATE USER 'gestion-cv-admin'@'localhost' IDENTIFIED WITH caching_sha2_password BY 'r54^?S6Z7CF=c%Qa';
   ```

   > Il n'est pas nécessaire de modifier le mot de passe `r54^?S6Z7CF=c%Qa`. Ce mot de passe n'est pas sensible, il est utilisé uniquement pour travailler temporairement en environnement de développement. Il peut donc être partagé sur Github sans problème.

3. Attribuer à l'utilisateur tous les privilèges sur la base de données

   ```mysql
   GRANT ALL PRIVILEGES ON `Gestion-de-CVs`.* TO 'gestion-cv-admin'@'localhost';
   ```

4. Appliquer les privilèges pour qu'ils soient fonctionnels immédiatement

   ```mysql
   FLUSH PRIVILEGES;
   ```

## Préparer la base de données de développement

### Créer le schéma des tables

Le code SQL présent dans le fichier `database/databaseSetup.sql` décrit le schéma des tables de la base de données. Il a été généré à l'aide du logiciel JMerise et à partir du Modèle Conceptuel des Données suivant :

<img src="/home/victoria/Documents/cours/cours_M2/Architecture_des_appli/JEE/MCD.png" alt="MCD" style="zoom: 67%;" />

Pour créer les tables en bases de données, se placer dans le dossier `database` et exécuter la commande suivante :

```bash
$ sudo mysql -p Gestion-de-CVs < databaseSetup.sql
```

### Peupler la base de données

L'application à développer doit supporter environ 100 000 personnes (et leurs activités). Afin de vérifier cette contrainte, un générateur de personnes / activités a été développé et a permit de générer le fichier `database/insertQueries-0.sql`. La base de données peut ainsi être peuplée avec la commande suivante :

```bash
$ sudo mysql -p Gestion-de-CVs < insertQueries-0.sql
```

