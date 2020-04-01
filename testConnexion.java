import java.sql.*;

public class testConnexion {

	public static void main(String[] args) {
	  try {
		Class.forName("com.mysql.jdbc.Driver");
		   
		String url = "jdbc:mysql://localhost:3306/gestionDeNotes?characterEncoding=utf8";
		String user = "root";
		String passwd = "qsdfjklm";
		   
		Connection conn = DriverManager.getConnection(url, user, passwd);
		
		//Création d'un objet Statement
		Statement state = conn.createStatement();
		//L'objet ResultSet contient le résultat de la requête SQL
		ResultSet result = state.executeQuery("SELECT * FROM Matiere");
		//On récupère les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();
		   
		System.out.println("\n**********************************************************************************");
		//On affiche le nom des colonnes
		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		  System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
		   
		System.out.println("\n**********************************************************************************");
		   
		while(result.next()){         
		  for(int i = 1; i <= resultMeta.getColumnCount(); i++)
			System.out.print("\t" + result.getObject(i).toString() + "\t |");
			  
		  System.out.println("\n--------------------------------------------------------------------------");
  
		}
  
		result.close();
		state.close();
		   
	  } catch (Exception e) {
		e.printStackTrace();
	  }      
	}
  }