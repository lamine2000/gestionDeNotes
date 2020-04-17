package db;

import db.DBConnect;
import user.*;
import ecole.*;
import java.sql.*;

//ici sont definies l'ensemble des fonctions qui permettent l'instanciation d'objets relatif à l'ecole, en se basant sur les données enregistrées dans la db

public class DBEcole {

	public static String[] DBgetListeLoginEtudiantsClasse(String nomClasse){
		
		String[] liste = null;
		int taille = 0;
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();

		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			result = state.executeQuery("SELECT login FROM Etudiant WHERE classe = '"+nomClasse+"'");

			while(result.next())
				taille++;
			
			liste = new String[taille];
			result.relative(-1*taille);

			taille = 0;
			while(result.next()){
				liste[taille] = result.getObject(1).toString();
				taille++;
			}
			result.close();
			state.close();
		}catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}finally{
			return liste;
		}
	}


/**************************************************************************************************************** */


	public static String[] DBgetListeLoginProfesseursClasse(String nomClasse){
		String[] liste = null;
		int taille = 0;
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();

		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			result = state.executeQuery("SELECT login FROM Professeur WHERE classe1 = '"+nomClasse+"' or classe2 = '"+nomClasse+"' or classe3 = '"+nomClasse+"' or classe4 = '"+nomClasse+"' or classe5 = '"+nomClasse+"'");

			while(result.next())
				taille++;
			
			liste = new String[taille];
			result.relative(-1*taille);

			taille = 0;
			while(result.next()){
				liste[taille] = result.getObject(1).toString();
				taille++;
			}
			result.close();
			state.close();
		}catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}finally{
			return liste;
		}
	}


/**************************************************************************************************************** */


	public static void DBajouterEtudiant(Etudiant etu){
		Statement state = null;

		Connection conn = DBConect.getInstance().getConn();

		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			state.executeUpdate("INSERT INTO Etudiant VALUES ('"+etu.getLogin()+"', '"+etu.getPassword()+"', '"+etu.getNom()+"', '"+etu.getPrenom()+"', '"+etu.getClasse().getNom()+"')");
			state.close();
		}catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}
	}


/**************************************************************************************************************** */


	public static void DBretirerEtudiant(String log){
		Statement state = null;

		Connection conn = DBConect.getInstance().getConn();

		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			state.executeUpdate("DELETE FROM Etudiant WHERE login = '"+log+"'");
			state.close();
		}catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}
	}


/**************************************************************************************************************** */


	public static void DBajouterModule(ecole.Module mod){
		Statement state = null;

		Connection conn = DBConect.getInstance().getConn();

		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			state.executeUpdate("INSERT INTO Module VALUES ('"+mod.getNom()+"', '"+mod.getFiliere().getNom()+"', "+mod.getSemestres()[0]+", "+mod.getSemestres()[1]+")");
			state.close();
		}catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}

	}


/**************************************************************************************************************** */


	public static void DBretirerModule(String nomModule){
		Statement state = null;

		Connection conn = DBConect.getInstance().getConn();

		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			state.executeUpdate("DELETE FROM Module WHERE nom = '"+nomModule+"'");
			state.close();
		}catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}

	}


/**************************************************************************************************************** */


	public static void DBajouterMatiere(Matiere matiere){
		Statement state = null;

		Connection conn = DBConect.getInstance().getConn();

		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			state.executeUpdate("INSERT INTO Matiere VALUES ('"+matiere.getNom()+"', "+matiere.getCoefficient()+", '"+matiere.getModule().getNom()+"')");
			state.close();
		}catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}

	}


/**************************************************************************************************************** */


	public static void DBretirerMatiere(String nomMatiere){
		Statement state = null;

		Connection conn = DBConect.getInstance().getConn();

		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			state.executeUpdate("DELETE FROM Matiere WHERE nom = '"+nomMatiere+"'");
			state.close();
		}catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}

	}


/**************************************************************************************************************** */


	public static String[] DBgetListeNomModulesFiliere(String nomFiliere){
		String[] liste = null;
		int taille = 0;
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();

		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			result = state.executeQuery("SELECT nom FROM Module WHERE nomFiliere = '"+nomFiliere+"'");

			while(result.next())
				taille++;

			liste = new String[taille];
			result.relative(-1*taille);
			taille = 0;
			
			while(result.next()){
				liste[taille] = result.getObject(1).toString();
				taille++;
			}
			result.close();
			state.close();
		} catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}
		finally{
			return liste;
		}
	}


/**************************************************************************************************************** */


	public static Boolean[] DBgetSemestresModule(String nomModule){
		Boolean[] liste = new Boolean[2];
		Statement state1 = null, state2 = null;
		ResultSet result1 = null, result2 = null;

		Connection conn = DBConnect.getInstance().getConn();

		try{
			state1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			state2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			result1 = state.executeQuery("SELECT semestre1 FROM Module WHERE nom = '"+nomModule+"'");
			result2 = state.executeQuery("SELECT semestre2 FROM Module WHERE nom = '"+nomModule+"'");
			
			if(result1.next())
				liste[0] = Boolean.valueOf(result.getObject(1));
			if(result2.next())
				liste[1] = Boolean.valueOf(result.getObject(1));
			
			result1.close();
			result2.close();
			state1.close();
			state2.close();

		} catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}

		return liste;
	}


/**************************************************************************************************************** */


	public static String[] DBgetListeNomMatieresModule(String nomModule){
		String[] liste = null;
		int taille = 0;
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();

		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}

		try{
			result = state.executeQuery("SELECT DISTINCT nom FROM Matiere WHERE nomModule = '"+nomModule+"'");

			while(result.next())
				taille++;

			liste = new String[taille];
			result.relative(-1*taille);
			taille = 0;
			
			while(result.next()){
				liste[taille] = result.getObject(1).toString();
				taille++;
			}
			result.close();
			state.close();
		} catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}
		finally{
			return liste;
		}
	}


/**************************************************************************************************************** */


	public static String DBgetParam(String nomParam, String nomTable, String nomIdentifiant, String valeurIdentifiant){
		String param = "";
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();
		
		try{
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch(Exception e){
			System.out.println("Erreur de creation du Statement");
		}
		
		try{
			result = state.executeQuery("SELECT "+nomParam+" FROM "+nomTable+" where "+nomIdentifiant+"='"+valeurIdentifiant+"'");

			if(result.next())
				param = result.getObject(1).toString();

			state.close();
			result.close();

		} catch(Exception e){
			System.out.println("Echec de communication avec la base de donnees");
		}
		
		return param;
	}


/**************************************************************************************************************** */


public static String DBgetParam2(String nomParam, String nomTable, String nomIdentifiant, int valeurIdentifiant){
	String param = "";
	Statement state = null;
	ResultSet result = null;

	Connection conn = DBConnect.getInstance().getConn();
	
	try{
		state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	} catch(Exception e){
		System.out.println("Erreur de creation du Statement");
	}
	
	try{
		result = state.executeQuery("SELECT "+nomParam+" FROM "+nomTable+" where "+nomIdentifiant+"="+valeurIdentifiant+"");

		if(result.next())
			param = result.getObject(1).toString();

		state.close();
		result.close();

	} catch(Exception e){
		System.out.println("Echec de communication avec la base de donnees");
	}
	
	return param;
}
}