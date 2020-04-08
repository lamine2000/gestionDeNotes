package ecole;
import db.DBEcole;

public class Matiere {
	private String nom;
	private int coefficient;
	private Module module;

	public Matiere(){}
	public Matiere(String nomMatiere){
		this.nom = nomMatiere;
		this.coefficient = DBgetParam("coefficient", "Matiere", "nom", nomMatiere);
		this.module = DBgetParam("nomModule", "Matiere", "nom", nomMatiere);
	}

	public String getNom(){return this.nom;}
	public int getCoefficient(){return this.coefficient;}
	public Module getModule(){return this.module;}
}