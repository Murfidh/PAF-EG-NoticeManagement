package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Notice;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Notice")
public class NoticeServices {
	
	
	//notice object 
	Notice noticeObj = new Notice(); 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)   //output as html 
	public String readNotices(){
	 return noticeObj.readNotice();
	 
  }

//******************************************************************************************************//	
		
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)   //@Consumes specify the input type; here as form data
	@Produces(MediaType.TEXT_PLAIN)					   //@Produces status message; here output as plain text	
	public String insertNotice(@FormParam("title") String Title,
			@FormParam("description") String Description,
			@FormParam("branch") String Branch,
			@FormParam("issuingOfficer") String issuingOfficer)
	{
		String output = noticeObj.insertNotice(Title, Description, Branch,issuingOfficer);
		return output;
  }


//*******************************************************************************************************//
//----used json to update operation-----------------	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateNotice(String noticeData){
		
		//Convert the input string to a JSON object
		JsonObject noticeObject = new JsonParser().parse(noticeData).getAsJsonObject();
		
		//Read the values from the JSON object
		String ID = noticeObject.get("ID").getAsString();
		String Title = noticeObject.get("title").getAsString();
		String Description = noticeObject.get("description").getAsString();
		String Branch = noticeObject.get("branch").getAsString();
		String issuingOfficer = noticeObject.get("issuingOfficer").getAsString();
		String output = noticeObj.updateNotice(ID, Title, Description, Branch,issuingOfficer) ;
	
		return output;
  }


//*********************************************************************************************************//
//----------------used xml to delete operation--------------------	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteNotice(String noticeData){
		
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(noticeData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String ID = doc.select("ID").text();
	 String output = noticeObj.deleteNotice(ID);
	
	 return output;
  }

}




