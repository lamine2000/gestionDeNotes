package ecole;

import db.DBEcole;
import user.Etudiant;
import user.Professeur;

public class Classe {
	private String nom;
	private Filiere filiere;
	private Etudiant[] listeEtudiants;
	private Professeur[] listeProfesseurs;

	public Classe() {
	}

	public Classe(String nomClasse) {
		this.nom = nomClasse;
	}

	public void contructFiliere() {
		String nomFiliere = DBEcole.DBgetParam("nomFiliere", "Classe", "nom", this.nom);
		filiere = new Filiere(nomFiliere);
	}

	public void constructListeEtudiants() {
		String[] listeLoginEtudiants = DBEcole.DBgetListeLoginEtudiantsClasse(this.nom);
		listeEtudiants = new Etudiant[listeLoginEtudiants.length];
		for (int i = 0; i < listeEtudiants.length; i++)
			listeEtudiants[i] = new Etudiant(listeLoginEtudiants[i]);
	}

	public void constructListeProfesseurs() {
		String[] listeLoginProfesseurs = DBEcole.DBgetListeLoginProfesseursClasse(this.nom);
		listeProfesseurs = new Professeur[listeLoginProfesseurs.length];
		for (int i = 0; i < listeProfesseurs.length; i++)
			listeProfesseurs[i] = new Professeur(listeLoginProfesseurs[i]);
	}

	public void setNom(String vnom) {
		this.nom = vnom;
	}

	public void setFiliere(Filiere vfiliere) {
		this.filiere = vfiliere;
	}

	public void setListeEtudiants(Etudiant[] vlisteEtudiants) {
		this.listeEtudiants = vlisteEtudiants;
	}

	public void setListeProfesseurs(Professeur[] vlisteProfesseurs) {
		this.listeProfesseurs = vlisteProfesseurs;
	}

	public String getNom() {
		return this.nom;
	}

	public Filiere getFiliere() {
		return this.filiere;
	}

	public Etudiant[] getListeEtudiants() {
		return this.listeEtudiants;
	}

	public Professeur[] getListeProfesseurs() {
		return this.listeProfesseurs;
	}

	public static void ajouterEtudiant(Etudiant etu) {
		DBEcole.DBajouterEtudiant(etu);
	}

	public static void retirerEtudiant(Etudiant etu) {
		DBEcole.DBretirerEtudiant(etu.getLogin());
	}
}