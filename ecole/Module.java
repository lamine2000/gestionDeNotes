package ecole;
import db.DBEcole;

public class Module {
	private String nom;
	private Filiere filiere;
	private Boolean[] semestres = new Boolean[2];
	private Matiere[] listeMatieres;

	public Module(){}
	public Module(String nomModule){
		this.nom = nomModule;
		this.filiere = new Filiere(filiere);
		this.semestres = DBgetSemestresModule(nomModule);
		
		String[] listeNomMatieres = DBgetListeNomMatieresModule(nomModule);
		listeMatieres = new Matiere[listeNomMatieres.length];
		for(int i = 0; i < listeNomMatieres.length; i++){
			listeMatieres[i] = new Matiere(listeNomMatieres[i]);
		}
	}

	public String getNom(){return this.nom;}
	public Filiere getFiliere(){return this.filiere;}
	public Boolean[] getsemestres(){return this.semestres;}
	public Matiere[] listeMatieres(){return this.listeMatieres;}

	public void ajouterMatiere(Matiere matiere){
		DBajouterMatiere(matiere);
	}

	public void retirerMatiere(Matiere matiere){
		dBretirerMariere(matiere);
	}
}