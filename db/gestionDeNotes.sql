drop database IF EXISTS gestionDeNotes;
drop user IF EXISTS 'gdn'; 
create database gestionDeNotes;
create user 'gdn' identified by 'passer';
grant all privileges on gestionDeNotes.* to 'gdn'@'%';
FLUSH privileges;
use gestionDeNotes;

create table Filiere
(
	nom VARCHAR(255) PRIMARY KEY
);

DESC Filiere;

create table Module
(
	nom VARCHAR(255) NOT NULL,
	nomFiliere VARCHAR(255) NOT NULL,
	semestre1 boolean NOT NULL DEFAULT false,
	semestre2 boolean DEFAULT false,
	PRIMARY KEY (nom),
	FOREIGN KEY (nomFiliere) REFERENCES Filiere(nom)
);

DESC Module;

create table Classe
(
	nom VARCHAR(255) PRIMARY KEY,
	nomFiliere VARCHAR(255),
	FOREIGN KEY (nomFiliere) REFERENCES Filiere(nom)
);

DESC Classe;

create table Etudiant 
(
	login VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL UNIQUE,
	nom VARCHAR(255) NOT NULL,
	prenom VARCHAR(255) NOT NULL,
	classe VARCHAR(255) NOT NULL,
	PRIMARY KEY (login, password),
	FOREIGN KEY (classe) REFERENCES Classe(nom)
);

DESC Etudiant;

create table Matiere
(
	nom VARCHAR(255) PRIMARY KEY,
	coefficient INT(2),
	nomModule VARCHAR(255),
	FOREIGN KEY (nomModule) REFERENCES Module(nom)
);

DESC Matiere;


create table Professeur
(
	login VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL UNIQUE,
	nom VARCHAR(255) NOT NULL,
	prenom VARCHAR(255) NOT NULL,
	cours1 VARCHAR(255) NOT NULL,
	cours2 VARCHAR(255),
	classe1 VARCHAR(255) NOT NULL,
	classe2 VARCHAR(255),
	classe3 VARCHAR(255),
	classe4 VARCHAR(255),
	classe5 Varchar(255),
	PRIMARY KEY(login, password),
	FOREIGN KEY (cours1) REFERENCES Matiere(nom),
	FOREIGN KEY (cours2) REFERENCES Matiere(nom),
	FOREIGN KEY (classe1) REFERENCES Classe(nom),
	FOREIGN KEY (classe2) REFERENCES Classe(nom),
	FOREIGN KEY (classe3) REFERENCES Classe(nom),
	FOREIGN KEY (classe4) REFERENCES Classe(nom),
	FOREIGN KEY (classe5) REFERENCES Classe(nom)
);

DESC Professeur;

create table TypeNote
(
	type VARCHAR(255) PRIMARY KEY
);

DESC TypeNote;

create table Note
(
	id int AUTO_INCREMENT PRIMARY KEY,
	valeur FLOAT NOT NULL,
	typeNote VARCHAR(255) NOT NULL,
	matiere VARCHAR(255) NOT NULL,
	loginEtudiant VARCHAR(255) NOT NULL,
	reclamation VARCHAR(255),
	FOREIGN KEY (matiere) REFERENCES Matiere(nom),
	FOREIGN KEY (loginEtudiant) REFERENCES Etudiant(login),
	FOREIGN KEY (typeNote) REFERENCES TypeNote(type),
	CHECK (valeur <= 20 AND valeur >= 0)
);

DESC Note;


INSERT INTO Filiere
VALUES
("DST2"),
("DUT2");

select * from Filiere;

INSERT INTO Module
VALUES
("informatique","DUT2", true, true),
("general", "DUT2", true, false),
("mathematiques", "DST2", true, false);

select * from Module;

INSERT INTO Classe
VALUES
("DSTI2-A","DST2"),
("DSTI2-B","DST2"),
("DUT2","DUT2");

select * from Classe;

INSERT INTO Etudiant
VALUES
("lemzo","qsdfjklm","gueye","mouhamadou lamine","DSTI2-B"),
("kmd","sokhna","diop","khady mbacke","DSTI2-B"),
("djiby","c++","sow","djibril","DSTI2-B"),
("spectateur","javascript","diallo","alpha","DSTI2-B"),
("ngone1010","oumou","ndiaye","ndeye ngone","DSTI2-A"),
("maoumy","ngone","wade","sokhna oumou","DSTI2-A"),
("inconnu","inconnu","inconnu","inconnu","DUT2");

select * from Etudiant;

INSERT INTO Matiere
VALUES
("utilisation des se",3,"informatique"),
("poo", 3, "informatique"),
("programmation web", 3, "informatique"),
("administration des reseaux", 2, "informatique"),
("modelisation des systemes d'information", 4, "informatique"),
("statistiques",2,"mathematiques"),
("recherche operationnelle",2,"mathematiques"),
("gestion de projets",2,"mathematiques"),
("gestion d'entreprise",2,"general"),
("anglais",2,"general"),
("culture et societes",2,"general");

select * from Matiere;

INSERT INTO Professeur(login, password, nom, prenom, cours1, cours2, classe1, classe2, classe3)
VALUES
("waid","javamsi","diaw","samba","poo","modelisation des systemes d'information","DSTI2-A","DSTI2-B",NULL),
("avantminuit","linux","ba","mandicou","utilisation des se",NULL,"DSTI2-A","DSTI2-B","DUT2"),
("tftp","voiture","ouya","samuel","administration des reseaux",NULL,"DSTI2-A","DSTI2-B","DUT2");

select * from Professeur;

INSERT INTO TypeNote
VALUES
("cc"),
("ds"),
("tp");

select * from TypeNote;

INSERT INTO Note (valeur, typeNote, matiere, loginEtudiant)
VALUES
(17,"cc","administration des reseaux","kmd"),
(15.5,"ds","anglais","kmd"),
(20,"tp","programmation web","ngone1010"),
(13,"cc","gestion de projets","maoumy"),
(16,"ds","gestion de projets","maoumy");

select * from Note;