 $(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
 
// Form validation-------------------
var status = validateNoticeForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidNoticeIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
	{
		 url : "NoticeAPI",
		 type : type,
		 data : $("#formNotice").serialize(),
 		 dataType : "text",
		 complete : function(response, status)
 	{
 	onNoticeSaveComplete(response.responseText, status);
  }
 });
});

function onNoticeSaveComplete(response, status){
	
		 if (status == "success"){
		 var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success"){
		
 			$("#alertSuccess").text("Successfully saved.");
 			$("#alertSuccess").show();
 			$("#divNoticesGrid").html(resultSet.data);
 	}else if (resultSet.status.trim() == "error"){
	
			 $("#alertError").text(resultSet.data);
 			 $("#alertError").show();
   }
 }else if (status == "error"){
	
		 $("#alertError").text("Error while saving.");
 		 $("#alertError").show();
 	}else{
	
 		 $("#alertError").text("Unknown error while saving..");
 		 $("#alertError").show();
 		 
  }  	 
 		$("#hidNoticeIDSave").val("");
 		$("#formNotice")[0].reset();
}


function onNoticeDeleteComplete(response, status){
	
if (status == "success"){
	
	 var resultSet = JSON.parse(response);
	 if (resultSet.status.trim() == "success"){
		
 		$("#alertSuccess").text("Successfully deleted.");
 		$("#alertSuccess").show();
 		$("#divNoticesGrid").html(resultSet.data);
 	}else if (resultSet.status.trim() == "error"){
	
 		$("#alertError").text(resultSet.data);
 		$("#alertError").show();
   }
 }else if (status == "error"){
	
    	$("#alertError").text("Error while deleting.");
 		$("#alertError").show();
 }else{
 		$("#alertError").text("Unknown error while deleting..");
 		$("#alertError").show();
 	}
}

$(document).on("click", ".btnRemove", function(event){
	 $.ajax(
		 {
			 url : "NoticeAPI",
 			 type : "DELETE",
   			 data : "ID=" + $(this).data("noticeid"),
 			 dataType : "text",
 			 complete : function(response, status)
 		{
 
 	onNoticeDeleteComplete(response.responseText, status);
  }
 });
});


$(document).on("click", ".btnUpdate", function(event){
	
		$("#hidNoticeIDSave").val($(this).data("noticeid"));
 		$("#title").val($(this).closest("tr").find('td:eq(0)').text());
 		$("#description").val($(this).closest("tr").find('td:eq(1)').text());
 		$("#branch").val($(this).closest("tr").find('td:eq(2)').text());
 		$("#issuingOfficer").val($(this).closest("tr").find('td:eq(3)').text());
});


function validateNoticeForm(){

	if ($("#title").val().trim() == ""){
 		return "Insert Item Title.";
 	}

	if ($("#description").val().trim() == ""){
		return "Insert Item Description.";
 	} 

	if ($("#branch").val().trim() == ""){
 		return "Insert Item Branch.";
 	}

	if ($("#issuingOfficer").val().trim() == ""){	
	    return "Insert Item Issuing Officer.";
 	}
	
	return true;
 }