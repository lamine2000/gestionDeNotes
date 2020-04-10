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

	public void saisirNote(Etudiant etu, Note note){
		DBsaisirNote(etu, note);
	}

	public void modifierNote(Etudiant etu, Note note, double bonus){
		DBmodifierNote(etu, note, bonus);
	}

	public void supprimerNote(Etudiant etu, Note note){
		DBsupprimerNote(etu, note);
	}
}