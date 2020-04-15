package ecole;
import db.DBEcole;

public class Filiere {
	private String nom;
	private Module[] listeModules;

	public Filiere(){}
	public Filiere(String nom){
		this.nom = nom;
		
		String[] listeNomModulesFiliere = DBgetListeNomModulesFiliere(nom);
		listeModules = new Module[listeNomModulesFiliere.length];
		for(int i = 0; i < listeNomModulesFiliere.length; i++){
			listeModules[i] = new Module(listeNomModulesFiliere[i]);
		}
	}

	public String getNom(){return this.nom;}
	public Module[] getListeModules(){return this.listeModules;}

	public void ajouterModule(Module module){
		DBajouterModule(module);
	}

	public void retirerModule(String nomModule){
		DBretirerModule(nomModule);
	}
}