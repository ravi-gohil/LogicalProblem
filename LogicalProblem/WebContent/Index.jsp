<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>File Upload</title>
	</head>
	<body>
		<h3>File Upload:</h3>
		Select a file to upload: </br>
		<form action="UploadServlet" method="post" enctype="multipart/form-data">
			<input type="file" name="file" placeholder="select Excel file"/>
			</br>
			<input type="submit" value="upload File"/>	
		</form>
	</body>
</html>