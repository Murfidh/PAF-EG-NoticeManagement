<%@page import="model.Notice"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Notice Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/notices.js"></script>

</head>
<body>	
		<div class="container"><div class="row"><div class="col-6">
				
				<h1>Notice Management</h1>
			<form id="formNotice" name="formNotice">
				 Title:
				 <input id="title" name="title" type="text" class="form-control form-control-sm">
				 
				 <br> Description:
				 <input id="description" name="description" type="text" class="form-control form-control-sm">
				 
				 <br> Branch :
				 <input id="branch" name="branch" type="text" class="form-control form-control-sm">
				 
				 <br> Issuing Officer :
				 <input id="issuingOfficer" name="issuingOfficer" type="text" class="form-control form-control-sm">
				 
				 <br>
				 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				 <input type="hidden" id="hidNoticeIDSave" name="hidNoticeIDSave" value="">
			</form>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			
			<div id="divNoticesGrid">
			 <%
			 	Notice noticeObj = new Notice();
			 	out.print(noticeObj.readNotice());
			 %>
</div>
</div> </div> </div> 
			

</body>
</html>