package user;
import db.*;
import ecole.Classe;
import ecole.Note;

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
		this.classe = new Classe(nomClasse);
		
		int[] listeIdNotes = DBUser.DBgetListeIdNotesEtudiant(this.login);
		this.listeNotes = new Note[listeIdNotes.length];
		for(int i = 0; i < listeIdNotes.length; i++){
			listeNotes[i] = new Note(listeIdNotes[i]);
		}
	}

	public Classe getClasse(){return this.classe;}
	public Note[] getListeNotes(){return this.listeNotes;}

	public void faireReclamation(String reclamation, Note note){
		DBUser.DBfaireReclamation(reclamation, note.getId());
	}

	/*public void consulterNotes(){
		//a faire avec l'interface graphique

	}*/
	
}