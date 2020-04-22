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
		this.semestres = DBEcole.DBgetSemestresModule(nomModule);
	}

	public void constructFiliere(){
		String nomFiliere = DBEcole.DBgetParam("nomFiliere", "Module", "nom", this.nom);
		this.filiere = new Filiere(nomFiliere);
	}
	public void constructListeMatieres(){
		String[] listeNomMatieres = DBEcole.DBgetListeNomMatieresModule(this.nom);
		listeMatieres = new Matiere[listeNomMatieres.length];
		for(int i = 0; i < listeNomMatieres.length; i++)
			listeMatieres[i] = new Matiere(listeNomMatieres[i]);
	}

	public void setNom(String vnom){this.nom = vnom;}
	public void setFiliere(Filiere vfiliere){this.filiere = vfiliere;}
	public void setSemestres(Boolean[] vsemestres){this.semestres = vsemestres;}
	public void setListeMatieres(Matiere[] vlisteMatieres){this.listeMatieres = vlisteMatieres;}

	public String getNom(){return this.nom;}
	public Filiere getFiliere(){return this.filiere;}
	public Boolean[] getSemestres(){return this.semestres;}
	public Matiere[] getListeMatieres(){return this.listeMatieres;}

	public static void ajouterMatiere(Matiere matiere){
		DBEcole.DBajouterMatiere(matiere);
	}

	public static void retirerMatiere(String nomMatiere){
		DBEcole.DBretirerMatiere(nomMatiere);
	}
}