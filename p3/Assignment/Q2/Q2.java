import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class Q2
{
	public static Statement statement;
    public static int sqlCode=0;      // Variable to hold SQLCODE
    public static String sqlState="00000";  // Variable to hold SQLSTATE
    
    public static void query1(String virus, String symptom)
	{
        // Querying a table
		try 
		{
            String querySQL = "SELECT sub1.vName AS \"Virus\", sub1.sName AS \"Symptom\", (sub1.Count * 1. / sub2.Count * 1.) AS \"P (Virus | Symptom)\" FROM (  SELECT I.vName, E.sName, Count(*) AS Count  FROM Infect I JOIN Exhibit E ON I.Nationality = E.Nationality AND I.National_ID = E.National_ID  GROUP BY I.vName, E.sName  )  sub1 JOIN (  SELECT E.sName, Count(*) AS Count  FROM Infect I JOIN Exhibit E ON I.Nationality = E.Nationality AND I.National_ID = E.National_ID  GROUP BY E.sName ) sub2 ON sub1.sName = sub2.sName WHERE sub1.vName = " + "\'" + virus + "\'" + " AND sub1.sName = " + "\'" + symptom + "\'";
			System.out.println (querySQL) ;
			java.sql.ResultSet rs = statement.executeQuery ( querySQL ) ;
			while ( rs.next ( ) ) 
			{
				float rate = rs.getFloat("P (Virus | Symptom)");
				System.out.println("P (" + virus + " | " + symptom + ")");
				System.out.println(rate);
			}
			System.out.println ("DONE");
		} 
		catch (SQLException e)
		{
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE
					
			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		}
	}

    public static void query2(String virus)
	{
        // Querying a table
		try 
		{
            String querySQL = "SELECT V.iName AS \"Research Institute\", V.rName AS \"Country\", R.address AS \"Address\" FROM Vaccination AS V JOIN Research_Institute AS R ON V.rName = R.rName AND V.iName = R.iName WHERE V.vName = \'" + virus + "\'";
			System.out.println(querySQL) ;
			java.sql.ResultSet rs = statement.executeQuery(querySQL) ;
			while (rs.next( )) 
			{
                String institute = rs.getString("Research Institute");
                String country = rs.getString("Country");
                String address = rs.getString("Address");
                System.out.println("Research Institute : " + institute);
                System.out.println("Country : " + country);
                System.out.println("Address : " + address);
                System.out.println("----------------------------------------------");
			}
			System.out.println ("DONE");
		} 
		catch (SQLException e)
		{
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE
					
			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		}
	}

    public static void insert1(String symptom, String description)
	{
		// Inserting Data into the table
		try 
		{
			String insertSQL = "INSERT INTO Symptom(sname, description) VALUES ( \'" + symptom + "\' , \'" + description + "\' ) ";
			System.out.println ( insertSQL ) ;
			statement.executeUpdate ( insertSQL ) ;
			System.out.println ( "DONE" ) ;
		} 
		catch (SQLException e)
		{
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE
			
			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		}
    }
    
    public static void modify1(String region, double percentage)
    {
        //Updating a table
        try 
        {
            String updateSQL = "UPDATE vaccination SET price = price * " + percentage + " where rname = \'" + region + "\'";
            System.out.println(updateSQL);
            statement.executeUpdate(updateSQL);
            System.out.println("DONE");
        } 
        catch (SQLException e)
        {
            sqlCode = e.getErrorCode(); // Get SQLCODE
            sqlState = e.getSQLState(); // Get SQLSTATE
                    
            // Your code to handle errors comes here;
            // something more meaningful than a print would be good
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
    }
    
	public static void modify2(String region, String today)
    {
        //Updating a table
        try 
        {
            String updateSQL = "UPDATE closed_border SET End_Time = \'" + today + "\' where r1rname = \'" + region + "\'";
            System.out.println(updateSQL);
            statement.executeUpdate(updateSQL);
            System.out.println("DONE");
        } 
        catch (SQLException e)
        {
            sqlCode = e.getErrorCode(); // Get SQLCODE
            sqlState = e.getSQLState(); // Get SQLSTATE
                    
            // Your code to handle errors comes here;
            // something more meaningful than a print would be good
            System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
        }
    }
    public static void main ( String[ ] args ) throws SQLException
    {
		// Register the driver.  You must register the driver before you can use it.
		try 
		{
			DriverManager.registerDriver ( new org.postgresql.Driver() ) ;
		} 
		catch (Exception cnfe)
		{
			System.out.println("Class not found");
		}

		// This is the url you must use for Postgresql.
		//Note: This url may not valid now !
		String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
		String usernamestring = "cs421g18";
		String passwordstring = "AlbertLearMimiVincent";
		Connection con = DriverManager.getConnection (url,usernamestring, passwordstring) ;
		try
		{
			statement = con.createStatement();
			int option = -1;
			do
			{
				Helpers.displayMainMenu();
				option = Helpers.numSanitiser(Helpers.mainMin, Helpers.mainMax);
				
				if (option != 0)
					System.out.println("------------------ Start Request ------------------");

				if (option == 1)
					Helpers.option1();
				else if (option == 2)
					Helpers.option2();
				else if (option == 3)
					Helpers.option3();
				else if (option == 4)
					Helpers.option4();
				else if (option == 5)
					Helpers.option5();

				if (option != 0)
					System.out.println("------------------ End Request ------------------");

			} while (option != 0);

			
			System.out.println("------------------ Bye! ------------------");
		}
		finally
		{
			// Finally but importantly close the statement and connection
			if (statement != null)
				statement.close ( ) ;
			if (con != null)
				con.close ( ) ;
		}
	}
}