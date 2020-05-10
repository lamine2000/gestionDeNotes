package user;

import db.DBUser;
import ecole.Classe;
import ecole.Matiere;
import ecole.Note;

public class Professeur extends Utilisateur{
	private Classe[] listeClasses;
	private Matiere[] listeCours;

	public Professeur(){}
	public Professeur(String log){
		this.login = log;
		this.password = DBUser.DBgetParam("password", "Professeur", "login", this.login);
		this.nom = DBUser.DBgetParam("nom", "Professeur", "login", this.login);
		this.prenom = DBUser.DBgetParam("prenom", "Professeur", "login", this.login);
	
	}

	public void constructListeClasses(){
		String[] listeNomClassesProfesseur = DBUser.DBgetListeNomClassesProfesseur(this.login);
		
		int nbClasses = listeNomClassesProfesseur.length;
		for(int i = listeNomClassesProfesseur.length - 1; i >= 0; i--){
			if(listeNomClassesProfesseur[i] == null)
				nbClasses--;
		}
		
		listeClasses = new Classe[nbClasses];
		for(int i = 0; i<listeClasses.length; i++)
			listeClasses[i] = new Classe(listeNomClassesProfesseur[i]);
	}

	public void constructListeCours(){
		String[] listeNomMatieresProfesseur = DBUser.DBgetListeNomMatieresProfesseur(this.login);
		
		int nbMat = listeNomMatieresProfesseur.length;
		for(int i = listeNomMatieresProfesseur.length - 1; i >= 0; i--){
			if(listeNomMatieresProfesseur[i] == null)
				nbMat--;
		}

		listeCours = new Matiere[nbMat];
		for(int i = 0; i<listeCours.length; i++)
			listeCours[i] = new Matiere(listeNomMatieresProfesseur[i]);
	}

	public void setListeClasses(Classe[] vlisteClasses){this.listeClasses = vlisteClasses;}
	public void setListeCours(Matiere[] vlisteCours){this.listeCours = vlisteCours;}

	public Classe[] getListeClasses(){return this.listeClasses;}
	public Matiere[] getListeCours(){return this.listeCours;}

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