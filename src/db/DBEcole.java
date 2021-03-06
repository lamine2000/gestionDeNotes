package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ecole.Matiere;
import user.Etudiant;

//ici sont definies l'ensemble des fonctions qui permettent l'instanciation d'objets relatif à l'ecole, en se basant sur les données enregistrées dans la db

public class DBEcole {

	@SuppressWarnings("finally")
	public static String[] DBgetListeLoginEtudiantsClasse(String nomClasse) {

		String[] liste = null;
		int taille = 0;
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			result = state.executeQuery("SELECT login FROM Etudiant WHERE classe = '" + nomClasse + "'");

			while (result.next())
				taille++;

			liste = new String[taille];
			result.relative(-1 * taille - 1);

			taille = 0;
			while (result.next()) {
				liste[taille] = result.getObject(1).toString();
				taille++;
			}
			result.close();
			state.close();
		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		} finally {
			return liste;
		}
	}

	/**************************************************************************************************************** */

	@SuppressWarnings("finally")
	public static String[] DBgetListeLoginProfesseursClasse(String nomClasse) {
		String[] liste = null;
		int taille = 0;
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			result = state.executeQuery("SELECT login FROM Professeur WHERE classe1 = '" + nomClasse
					+ "' or classe2 = '" + nomClasse + "' or classe3 = '" + nomClasse + "' or classe4 = '" + nomClasse
					+ "' or classe5 = '" + nomClasse + "'");

			while (result.next())
				taille++;

			liste = new String[taille];
			result.relative(-1 * taille - 1);

			taille = 0;
			while (result.next()) {
				liste[taille] = result.getObject(1).toString();
				taille++;
			}
			result.close();
			state.close();
		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		} finally {
			return liste;
		}
	}

	/**************************************************************************************************************** */

	public static void DBajouterEtudiant(Etudiant etu) {
		Statement state = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			state.executeUpdate("INSERT INTO Etudiant VALUES ('" + etu.getLogin() + "', '" + etu.getPassword() + "', '"
					+ etu.getNom() + "', '" + etu.getPrenom() + "', '" + etu.getClasse().getNom() + "')");
			state.close();
		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		}
	}

	/**************************************************************************************************************** */

	public static void DBretirerEtudiant(String log) {
		Statement state = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			state.executeUpdate("DELETE FROM Etudiant WHERE login = '" + log + "'");
			state.close();
		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		}
	}

	/**************************************************************************************************************** */

	public static void DBajouterModule(ecole.Module mod) {
		Statement state = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			state.executeUpdate("INSERT INTO Module VALUES ('" + mod.getNom() + "', '" + mod.getFiliere().getNom()
					+ "', " + mod.getSemestres()[0] + ", " + mod.getSemestres()[1] + ")");
			state.close();
		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		}

	}

	/**************************************************************************************************************** */

	public static void DBretirerModule(String nomModule) {
		Statement state = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			state.executeUpdate("DELETE FROM Module WHERE nom = '" + nomModule + "'");
			state.close();
		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		}

	}

	/**************************************************************************************************************** */

	public static void DBajouterMatiere(Matiere matiere) {
		Statement state = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			state.executeUpdate("INSERT INTO Matiere VALUES ('" + matiere.getNom() + "', " + matiere.getCoefficient()
					+ ", '" + matiere.getModule().getNom() + "')");
			state.close();
		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		}

	}

	/**************************************************************************************************************** */

	public static void DBretirerMatiere(String nomMatiere) {
		Statement state = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			state.executeUpdate("DELETE FROM Matiere WHERE nom = '" + nomMatiere + "'");
			state.close();
		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		}

	}

	/**************************************************************************************************************** */

	@SuppressWarnings("finally")
	public static String[] DBgetListeNomModulesFiliere(String nomFiliere) {
		String[] liste = null;
		int taille = 0;
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			result = state.executeQuery("SELECT nom FROM Module WHERE nomFiliere = '" + nomFiliere + "'");

			while (result.next())
				taille++;

			liste = new String[taille];
			result.relative(-1 * taille - 1);
			taille = 0;

			while (result.next()) {
				liste[taille] = result.getObject(1).toString();
				taille++;
			}
			result.close();
			state.close();
		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		} finally {
			return liste;
		}
	}

	/**************************************************************************************************************** */

	public static Boolean[] DBgetSemestresModule(String nomModule) {
		Boolean[] liste = new Boolean[2];
		Statement state1 = null, state2 = null;
		ResultSet result1 = null, result2 = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			state2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			result1 = state1.executeQuery("SELECT semestre1 FROM Module WHERE nom = '" + nomModule + "'");
			result2 = state2.executeQuery("SELECT semestre2 FROM Module WHERE nom = '" + nomModule + "'");

			if (result1.next())
				liste[0] = Boolean.valueOf(result1.getObject(1).toString());
			if (result2.next())
				liste[1] = Boolean.valueOf(result2.getObject(1).toString());

			result1.close();
			result2.close();
			state1.close();
			state2.close();

		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		}

		return liste;
	}

	/**************************************************************************************************************** */

	@SuppressWarnings("finally")
	public static String[] DBgetListeNomMatieresModule(String nomModule) {
		String[] liste = null;
		int taille = 0;
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			result = state.executeQuery("SELECT DISTINCT nom FROM Matiere WHERE nomModule = '" + nomModule + "'");

			while (result.next())
				taille++;

			liste = new String[taille];
			result.relative(-1 * taille - 1);
			taille = 0;

			while (result.next()) {
				liste[taille] = result.getObject(1).toString();
				taille++;
			}
			result.close();
			state.close();
		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		} finally {
			return liste;
		}
	}

	/**************************************************************************************************************** */

	public static String DBgetParam(String nomParam, String nomTable, String nomIdentifiant, String valeurIdentifiant) {
		String param = "";
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			result = state.executeQuery("SELECT " + nomParam + " FROM " + nomTable + " where " + nomIdentifiant + "='"
					+ valeurIdentifiant + "'");

			if (result.next() && result.getObject(1) != null)
				param = result.getObject(1).toString();

			state.close();
			result.close();
		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		}

		return param;
	}

	/**************************************************************************************************************** */

	public static String DBgetParam2(String nomParam, String nomTable, String nomIdentifiant, int valeurIdentifiant) {
		String param = "";
		Statement state = null;
		ResultSet result = null;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			result = state.executeQuery("SELECT " + nomParam + " FROM " + nomTable + " WHERE " + nomIdentifiant + " = "
					+ valeurIdentifiant);

			if (result.next() && result.getObject(1) != null)
				param = result.getObject(1).toString();

			state.close();
			result.close();

		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
		}

		return param;
	}

	/**
	 * @throws SQLException
	 * **************************************************************************************************************
	 */

	public static void DBSaveNote(String nom, String prenom, String valeur, String type, String matiere)
			throws SQLException {
		Statement state1 = null, state2 = null, state = null;
		ResultSet result = null, result1 = null;
		String login = new String();
		int nb = 0;

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			state1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			state2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		result1 = state1.executeQuery("select count(*) from Note where typeNote = '" + type + "' and matiere = '"
				+ matiere + "' and loginEtudiant = (select login from Etudiant where nom = '" + nom + "' and prenom = '"
				+ prenom + "')");

		if (result1.next())
			nb = (int) result1.getInt(1);

		result = state
				.executeQuery("select login from Etudiant where nom = '" + nom + "' and prenom = '" + prenom + "'");

		if (result.next())
			login = (String) result.getObject(1);

		if (nb == 0) {
			try {
				state2.executeUpdate("insert into Note(valeur, typeNote, matiere, loginEtudiant) values('" + valeur
						+ "', '" + type + "', '" + matiere + "', '" + login + "')");

			} catch (Exception e) {
				System.out.println("Echec de communication avec la base de donnees");
				// e.printStackTrace();
			}
		}

		else {
			try {
				state2.executeUpdate("update Note set valeur = " + Double.parseDouble(valeur) + " where typeNote = '"
						+ type + "' and matiere = '" + matiere
						+ "' and loginEtudiant = (select login from Etudiant where nom = '" + nom + "' and prenom = '"
						+ prenom + "')");

			} catch (Exception e) {
				System.out.println("Echec de communication avec la base de donnees");
				// e.printStackTrace();
			}
		}
		state.close();
		state1.close();
		state2.close();
		result.close();
		result1.close();
	}

	/**************************************************************************************************************** */

	public static String[] DBgetNotes(String login, String matiere) {
		String[] notes = new String[3];
		Statement state1 = null, state2 = null, state3 = null;
		ResultSet result1 = null, result2 = null, result3 = null;

		for (@SuppressWarnings("unused")
		String elt : notes)
			elt = "";

		Connection conn = DBConnect.getInstance().getConn();

		try {
			state1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			state2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			state3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			System.out.println("Erreur de creation du Statement");
		}

		try {
			result1 = state1.executeQuery("select valeur from Note where loginEtudiant = '" + login
					+ "' and matiere = '" + matiere + "' and typeNote = 'tp'");
			result2 = state2.executeQuery("select valeur from Note where loginEtudiant = '" + login
					+ "' and matiere = '" + matiere + "' and typeNote = 'cc'");
			result3 = state3.executeQuery("select valeur from Note where loginEtudiant = '" + login
					+ "' and matiere = '" + matiere + "' and typeNote = 'ds'");

			if (result1.next() && result1.getObject(1) != null)
				notes[0] = result1.getObject(1).toString();

			if (result2.next() && result2.getObject(1) != null)
				notes[1] = result2.getObject(1).toString();

			if (result3.next() && result3.getObject(1) != null)
				notes[2] = result3.getObject(1).toString();

			state1.close();
			state2.close();
			state3.close();
			result1.close();
			result2.close();
			result3.close();

		} catch (Exception e) {
			System.out.println("Echec de communication avec la base de donnees");
			e.printStackTrace();
		}

		return notes;
	}
}