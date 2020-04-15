package user;
import db.DBUser;

public class Professeur extends Utilisateur{
	private Classe[] listeClasses;
	private Matiere[] listeCours;

	public Classe[] getListeClasses(){return this.listeClasses;}
	public Matiere[] getListeCours(){return this.listeCours;}

	public Professeur(){}
	public Professeur(String log){
		this.login = log;
		this.password = DBgetParamUser("password", "Professeur", "login", this.login);
		this.nom = DBgetParamUser("nom", "Professeur", "login", this.login);
		this.prenom = DBgetParamUser("prenom", "Professeur", "login", this.login);
		
		String[] listeNomClassesProfesseur = DBgetListeNomClassesProfesseur(this.login);
		listeClasses = new Classe[listeNomClassesProfesseur.length];
		for(int i = 0; i<listeClasses.length; i++){
			listeClasses[i] = new Classe(listeNomClassesProfesseur[i]);
		}

		String[] listeNomMatieresProfesseur = DBgetListeNomMatieresProfesseur(this.login);
		listeCours = new Matiere[listeNomMatieresProfesseur.length];
		for(int i = 0; i<listeClasses.length; i++){
			listeCours[i] = new Matiere(listeNomMatieresProfesseur[i]);
		}
	}

	public void saisirNote(Note note){
		DBsaisirNote(note);
	}

	public void modifierNote(Note note, double bonus){
		DBmodifierNote(note.getId(), bonus);
	}

	public void supprimerNote(Note note){
		DBsupprimerNote(note.getId());
	}
}