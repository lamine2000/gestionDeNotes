package ecole;
import db.DBEcole;
import user.*;
import ecole.Filiere;

public class Classe{
	private String nom;
	private Filiere filiere;
	private Etudiant[] listeEtudiants;
	private Professeur[] listeProfesseurs;

	public Classe(){}
	public Classe(String nomClasse){
		
		this.nom = nomClasse;
		
		String nomFiliere = DBEcole.DBgetParam("nomFiliere", "Classe", "nom", nomClasse);
		Filiere filiere = new Filiere(nomFilere);
		
		String[] listeLoginEtudiants = DBEcole.DBgetListeLoginEtudiantsClasse(nomClasse);
		listeEtudiant = new Etudiant[listeLoginEtudiants.length];
		for(int i = 0; i<listeEtudiants.length; i++){
			listeEtudiants[i] = new Etudiant(listeLoginEtudiants[i]);
		}

		String[] listeLoginProfesseurs = DBEcole.DBgetListeLoginProfesseursClasse(nomClasse);
		listeProfesseurs = new Professeur[listeLoginProfesseurs.length];
		for(int i = 0; i<listeProfesseurs.length; i++){
			listeProfesseurs[i] = new Professeur(listeLoginProfesseurs[i]);
		}
	}

	public String getNom(){return this.nom;}
	public Filiere getFiliere(){return this.filiere;}
	public Etudiant[] getListeEtudiants(){return this.listeEtudiants;}
	public Professeur[] getListeProfesseurs(){return this.listeProfesseurs;}

	public void ajouterEtudiant(Etudiant etu){
		DBEcole.DBajouterEtudiant(etu);
	}

	public void retirerEtudiant(Etudiant etu){
		DBEcole.DBretirerEtudiant(etu.getLogin());
	}
}