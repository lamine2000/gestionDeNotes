package ecole;
import db.DBEcole;
import ecole.Matiere;
import ecole.Filiere;

public class Module {
	private String nom;
	private Filiere filiere;
	private Boolean[] semestres = new Boolean[2];
	private Matiere[] listeMatieres;

	public Module(){}
	public Module(String nomModule){
		this.nom = nomModule;
		String nomFiliere = DBEcole.DBgetParam("nomFiliere", "Module", "nom", nomModule);
		this.filiere = new Filiere(nomFiliere);
		this.semestres = DBEcole.DBgetSemestresModule(nomModule);
		
		String[] listeNomMatieres = DBEcole.DBgetListeNomMatieresModule(nomModule);
		listeMatieres = new Matiere[listeNomMatieres.length];
		for(int i = 0; i < listeNomMatieres.length; i++){
			listeMatieres[i] = new Matiere(listeNomMatieres[i]);
		}
	}

	public String getNom(){return this.nom;}
	public Filiere getFiliere(){return this.filiere;}
	public Boolean[] getSemestres(){return this.semestres;}
	public Matiere[] getListeMatieres(){return this.listeMatieres;}

	public void ajouterMatiere(Matiere matiere){
		DBEcole.DBajouterMatiere(matiere);
	}

	public void retirerMatiere(String nomMatiere){
		DBEcole.DBretirerMariere(nomMatiere);
	}
}