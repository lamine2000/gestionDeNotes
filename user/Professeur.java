package user;
import db.*;
import ecole.Matiere;
import ecole.Classe;
import ecole.Note;

public class Professeur extends Utilisateur{
	private Classe[] listeClasses;
	private Matiere[] listeCours;

	public Classe[] getListeClasses(){return this.listeClasses;}
	public Matiere[] getListeCours(){return this.listeCours;}

	public Professeur(){}
	public Professeur(String log){
		this.login = log;
		this.password = DBUser.DBgetParam("password", "Professeur", "login", this.login);
		this.nom = DBUser.DBgetParam("nom", "Professeur", "login", this.login);
		this.prenom = DBUser.DBgetParam("prenom", "Professeur", "login", this.login);
		
		String[] listeNomClassesProfesseur = DBUser.DBgetListeNomClassesProfesseur(this.login);
		listeClasses = new Classe[listeNomClassesProfesseur.length];
		for(int i = 0; i<listeClasses.length; i++){
			listeClasses[i] = new Classe(listeNomClassesProfesseur[i]);
		}

		String[] listeNomMatieresProfesseur = DBUser.DBgetListeNomMatieresProfesseur(this.login);
		listeCours = new Matiere[listeNomMatieresProfesseur.length];
		for(int i = 0; i<listeClasses.length; i++){
			listeCours[i] = new Matiere(listeNomMatieresProfesseur[i]);
		}
	}

	public void saisirNote(Note note){
		DBUser.DBsaisirNote(note);
	}

	public void modifierNote(Note note, double bonus){
		DBUser.DBmodifierNote(note.getId(), bonus);
	}

	public void supprimerNote(Note note){
		DBUser.DBsupprimerNote(note.getId());
	}
}