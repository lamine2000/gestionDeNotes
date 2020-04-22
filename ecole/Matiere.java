package ecole;

import db.DBEcole;
import ecole.Module;

public class Matiere {
	private String nom;
	private int coefficient;
	private Module module;

	public Matiere(){}
	public Matiere(String nomMatiere){
		this.nom = nomMatiere;
		String chaine = DBEcole.DBgetParam("coefficient", "Matiere", "nom", this.nom);

		if(chaine != "")
			this.coefficient = Integer.parseInt(chaine);
	}

	public void constructModule(){
		String nomModule = DBEcole.DBgetParam("nomModule", "Matiere", "nom", this.nom);
		this.module = new Module(nomModule);
	}

	public void setNom(String vnom){this.nom = vnom;}
	public void setCoefficient(int vcoefficient){this.coefficient = vcoefficient;}
	public void setModule(Module vmodule){this.module = vmodule;}

	public String getNom(){return this.nom;}
	public int getCoefficient(){return this.coefficient;}
	public Module getModule(){return this.module;}
}