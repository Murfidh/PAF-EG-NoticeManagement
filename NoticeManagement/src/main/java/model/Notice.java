package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Notice {
	
	//A common method to connect to the DB
	private Connection connect(){
		
		 Connection con = null;
		 try{
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName,username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbitems", "root", "");
		 }
		 catch (Exception e){
			 e.printStackTrace();
		 	}
		 	
		 	return con;
		 }
		
	//Method to insert notices to the system
	public String insertNotice(String title, String description, String branch, String issuingOfficer){
		
		 String output = "";
		 
		 try{
			 
			 Connection con = connect();
		
			 if (con == null){
				 return "Error while connecting to the database for inserting."; 
				}
		 
			 // create a prepared statement
			 String query = "insert into notices(`ID`,`Title`,`Description`,`Branch`,`issuingOfficer`)" + " values (?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, title);
			 preparedStmt.setString(3, description);
			 preparedStmt.setString(4, branch);
			 preparedStmt.setString(5, issuingOfficer);
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			  
			 String newNotice = readNotice() ;
			 output = "{\"status\":\"success\", \"data\": \"" + newNotice + "\"}"; 
		 }
		 	catch (Exception e){	
		 		output = "{\"status\":\"error\", \"data\": \"Error while inserting the notice.\"}";
		 				 System.err.println(e.getMessage()); 
		 				 e.printStackTrace();
		 		}
		 	
		 return output;
	}
		

	//---------------method to view all the notices------------------------
	public String readNotice(){
		
		 String output = "";
		
		 try{
			 Connection con = connect(); 
			 if (con == null){
				 return "Error while connecting to the database for reading."; }
		
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Title</th><th>Description</th>" +
					 "<th>Branch</th>" +
					 "<th>Issuing Officer</th>" +
					 "<th>Update</th><th>Remove</th></tr>";

			 String query = "select * from notices";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
			 while (rs.next()){			 
				 String ID = Integer.toString(rs.getInt("ID"));
				 String title = rs.getString("Title");
				 String description = rs.getString("Description");
				 String branch = rs.getString("Branch");
				 String issuingOfficer = rs.getString("issuingOfficer");
				 	
				 // Add into the html table
				 output += "<tr><td>" + title + "</td>";
				 output += "<td>" + description + "</td>";
				 output += "<td>" + branch + "</td>";
				 output += "<td>" + issuingOfficer + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-noticeid='" + ID + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-noticeid='" + ID + "'></td></tr>"; 
			 }
			 	
			 con.close();
			 // Complete the html table
			 output += "</table>";
		 }
		 	catch (Exception e){
		 		output = "Error while reading the notices.";
		 		System.err.println(e.getMessage());
		 	}
		 
		 	return output;
	}	
	//-------------------------method to update notice informations by id---------------------
	public String updateNotice(String ID, String title, String description, String branch, String issuingOfficer){
		
			 String output = "";
			
			 try{
				  Connection con = connect();
				  if (con == null){
					  return "Error while connecting to the database for updating."; }
			
				  // create a prepared statement
				  String query = "UPDATE notices SET Title=?,Description=?,Branch=?,issuingOfficer=? WHERE ID=?";
			 	  PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 	  // binding values
			 	  preparedStmt.setString(1, title);
			 	  preparedStmt.setString(2, description);
			 	  preparedStmt.setString(3, branch);
			 	  preparedStmt.setString(4, issuingOfficer);
			 	  preparedStmt.setInt(5, Integer.parseInt(ID));
			 
			 	  // execute the statement
			 	  preparedStmt.execute();
			 	  con.close();
			 	  String newNotice = readNotice();
				 	 output = "{\"status\":\"success\", \"data\": \"" + newNotice + "\"}";
				 }
				 	catch (Exception e){
				 		output = "{\"status\":\"error\", \"data\":\"Error while updating the notice.\"}";
				 				 System.err.println(e.getMessage());
				 	}
			 return output;
	}
			

	//--------------------method to delete by id ----------------------------------
	public String deleteNotice(String ID){
		
			 String output = "";
			 
			 try{	 
				 Connection con = connect();
				  if (con == null){
					  return "Error while connecting to the database for deleting."; }
			 
				  // create a prepared statement
				  String query = "delete from notices where ID=?";
				  PreparedStatement preparedStmt = con.prepareStatement(query);
			 
				  // binding values
				  preparedStmt.setInt(1, Integer.parseInt(ID));
			 
				  // execute the statement
				  preparedStmt.execute();
				  con.close();
				  
				  String newNotice = readNotice();
				  output = "{\"status\":\"success\", \"data\": \"" + newNotice + "\"}"; 
			 }
			 	catch (Exception e){
			 		output = "{\"status\":\"error\", \"data\": \"Error while deleting the notice.\"}";
			 				 System.err.println(e.getMessage());
			 	  }
			 return output;
	  } 
	//-----------------------------------------------------------------------------------
}

