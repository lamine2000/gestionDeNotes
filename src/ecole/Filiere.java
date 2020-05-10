package ecole;

import db.DBEcole;

public class Filiere {
	private String nom;
	private Module[] listeModules;

	public Filiere() {
	}

	public Filiere(String nom) {
		this.nom = nom;
	}

	public void constructListeModules() {
		String[] listeNomModulesFiliere = DBEcole.DBgetListeNomModulesFiliere(this.nom);
		listeModules = new Module[listeNomModulesFiliere.length];
		for (int i = 0; i < listeNomModulesFiliere.length; i++)
			listeModules[i] = new Module(listeNomModulesFiliere[i]);
	}

	public void setNom(String vnom) {
		this.nom = vnom;
	}

	public void setlisteModules(Module[] vlisteModules) {
		this.listeModules = vlisteModules;
	}

	public String getNom() {
		return this.nom;
	}

	public Module[] getListeModules() {
		return this.listeModules;
	}

	public static void ajouterModule(Module module) {
		DBEcole.DBajouterModule(module);
	}

	public static void retirerModule(String nomModule) {
		DBEcole.DBretirerModule(nomModule);
	}
}