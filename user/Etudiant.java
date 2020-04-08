package user;
import db.DBUser;

public class Etudiant extends Utilisateur{
	private Classe classe;
	private Note[] listeNotes;

	public Etudiant(){};

	public Etudiant(String log){

		this.login = log;
		this.password = DBUser.DBgetParam("password", "Etudiant", "login", this.login);
		this.nom = DBUser.DBgetParam("nom", "Etudiant", "login", this.login);
		this.prenom = DBUser.DBgetParam("prenom", "Etudiant", "login", this.login);
		String nomClasse = DBUser.DBgetParam("classe", "Etudiant", "login", this.login);
		this.classe = DBUser.DBgenererClasse(nomClasse);
		int[] listeIdNotes = DBUser.DBgetListeIdNotesEtudiant(this.login);
		this.listeNotes = new Note[listeIdNotes.length];
		this.listeNotes = DBUser.getListeNotesEtudiant(listeIdNotes);
	}

	public Classe getClasse(){return this.classe;}
	public Note[] getListeNotes(){return this.listeNotes;}

	public void faireReclamation(String reclamation, Note note){
		DBEcole.DBfaireReclamation(reclamation, note);
	}

	/*public void consulterNotes(){
		//a faire avec l'interface graphique

	}*/
}