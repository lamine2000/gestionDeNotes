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
		this.password = DBUser.DBgetParamUser("password", "Professeur", "login", this.login);
		this.nom = DBUser.DBgetParamUser("nom", "Professeur", "login", this.login);
		this.prenom = DBUser.DBgetParamUser("prenom", "Professeur", "login", this.login);
		
		String[] listeNomClassesProfesseur = DBUser.DBgetListeNomClassesProfesseur(this.login);
		listeClasses = new Classe[listeNomClassesProfesseur.length];
		for(int i = 0; i<listeClasses.length; i++){
			listeClasses[i] = DBUser.DBgenererClasse(listeNomClassesProfesseur[i]);
		}

		String[] listeNomMatieresProfesseur = DBUser.DBgetListeNomMatieresProfesseur(this.login);
		listeCours = new Matiere[listeNomMatieresProfesseur.length];
		for(int i = 0; i<listeClasses.length; i++){
			listeCours[i] = DBUser.DBgenererMatiere(listeNomMatieresProfesseur[i]);
		}
	}

	public void saisirNote(Etudiant etu, Note note){
		DBUser.DBsaisirNote(etu, note);
	}

	public void modifierNote(Etudiant etu, Note note, double bonus){
		DBUser.DBmodifierNote(etu, note, bonus);
	}

	public void supprimerNote(Etudiant etu, Note note){
		DBUser.DBsupprimerNote(etu, note);
	}
}