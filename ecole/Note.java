package ecole;
import db.DBEcole;
import ecole.EnumTypeNote;

public class Note{
	private int id;
    private double valeur;
    private EnumTypeNote type;
    private Matiere matiere;
    private String loginEtudiant;
	private String reclamation;
    
    public Note() {}
    public Note(int identifiant/*double vvaleur, EnumTypeNote vtype, Matiere vmatiere, String vloginEtudiant*/) {
		this.id = identifiant;
		this.valeur = DBgetParam("valeur", "Note", "id", identifiant);
		this.type = DBgetParam("typeNote", "Note", "id" ,identifiant);
		
		String nomMatiere = DBgetParam("matiere", "Note", "id" ,identifiant);
		this.matiere = new Matiere(nomMatiere);
		
		this.loginEtudiant = DBgetParam("loginEtudiant", "Note", "id" ,identifiant);
		this.reclamation = DBgetParam("reclamation", "Note", "id" ,identifiant);
	}
	
	public int getId(){return this.id;}
	public double getValeur(){return this.valeur;}
	public EnumTypeNote geType(){return this.type;}
	public Matiere getMatiere(){return this.matiere;}
	public String getLoginEtudiant(){return this.loginEtudiant;}
	public String getReclamation(){return this.reclamation;}

}