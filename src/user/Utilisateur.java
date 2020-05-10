package user;

import db.DBUser;

public class Utilisateur{
	//attrubuts
	protected String login, password, nom, prenom;

	//constructeurs
	public Utilisateur(){}

	public Utilisateur(String vlogin, String vpassword, String vnom, String vprenom){
		this.login = vlogin;
		this.password = vpassword;
		this.nom = vnom;
		this.prenom = vprenom;
	}

	//setters
	public void setLogin(String vlogin){this.login = vlogin;}
	public void setPassword(String vpassword){this.password = vpassword;}
	public void setNom(String vnom){this.nom = vnom;}
	public void setPrenom(String vprenom){this.prenom = vprenom;}

	//getters
	public String getLogin(){return this.login;}
	public String getPassword(){return this.password;}
	public String getNom(){return this.nom;}
	public String getPrenom(){return this.prenom;}

	//methodes
	public static int connecterUser(String log, String pass){
		//verifier dans les tables Professeur et Etutiant s'il existe un enregistrement
		// avec les memes login et password que l'objet courant
		//retourne 1 si trouvé dans la table Etudiant, 2 pour Professeur et 0 si non existant dans la db

		return DBUser.DBrechercherUser(log, pass);
	}

	/*public boolean deconnecter(){
		//avec l interface grafique, renvoyer l'utilisateur sur la page d'authentification
	}*/
}