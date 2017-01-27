<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Time schedule</title>
</head>
<body>
<h2>${requestScope.message}</h2>
<select>
				<option disabled> select</option>
</select>
</br>
</br>
<table border="0">
	<tr>
		<td><p> Please Select Source & Destination</p></td>
	</tr>
	<tr>
		<td>
			<select>
				<option disabled> select</option>
				<option value ="ahmedabad">ahmedabad</option>
				<option value="surat">surat</option>
				<option value="Baroda">Barora</option>
				<option value="Mumbai">Mumbai</option>
			</select>
		</td>
		<td>
			<select>
				<option disabled> select</option>
				<option value ="ahmedabad">ahmedabad</option>
				<option value="surat">surat</option>
				<option value="Baroda">Barora</option>
				<option value="Mumbai">Mumbai</option>
			</select>
		</td>
		
	</tr>
	<tr>
		<td colspan="2"> <input type="submit" value="submit" onclick="loadDoc()"/></td>
	</tr>
	<p id="demo"></p>
</table>
<script>
			function loadDoc(){
				var x = new XMLHttpRequest();
				x.onreadystatechange = function () {
					if (this.readyState == 4 && this.status == 200){
						document.getElementById("demo").innerHTML = 
						this.responseText;
					}
				};
				
				x.open("GET", "data", true);
				x.send();
			}
		</script>
</body>
</html>