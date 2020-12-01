#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Person
#------------------------------------------------------------

CREATE TABLE Person(
        id          Int  Auto_increment  NOT NULL ,
        lastName    Varchar (255) NOT NULL ,
        firstName    Varchar (255) NOT NULL ,
        website     Varchar (255) ,
        dateOfBirth Date NOT NULL ,
        password    Varchar (102) NOT NULL ,
        email       Varchar (255) NOT NULL
	,CONSTRAINT Person_AK UNIQUE (email)
	,CONSTRAINT Person_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Activity
#------------------------------------------------------------

CREATE TABLE Activity(
        id          Int  Auto_increment  NOT NULL ,
        year        Smallint NOT NULL ,
        title       Varchar (255) NOT NULL ,
        description Varchar (10000) ,
        webAddress  Varchar (255) ,
        nature      Enum ("PROFESSIONAL_EXPERIENCE","EDUCATION","PROJECT","OTHER") NOT NULL ,
        ownerId   Int NOT NULL
	,CONSTRAINT Activity_PK PRIMARY KEY (id)

	,CONSTRAINT Activity_Person_FK FOREIGN KEY (ownerId) REFERENCES Person(id)
)ENGINE=InnoDB;

