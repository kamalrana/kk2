<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload Confirmation Page</title>
</head>
<body>
	<h3 style="color: green;">File has been uploaded successfully.</h3> <br>
	File Name : ${FORM.name}.
<%-- 	<div id="uploadedData" style="border:thick;width=10px ">
	${FORM.uploadedData} 
	</div> --%>
	<iframe width="100%" id="myFrame" src=${FORM.filePath} scrolling="no" frameborder="1">
An iframe capable browser is
required to view this web site.
</iframe>
</body>
<script type="text/javascript">
function sizeFrame() {
var F = document.getElementById("myFrame");
if(F.contentDocument) {
F.height = F.contentDocument.documentElement.scrollHeight+30; //FF 3.0.11, Opera 9.63, and Chrome
} 
else {
F.height = F.contentWindow.document.body.scrollHeight+30; //IE6, IE7 and Chrome
}

}

window.onload=sizeFrame; 
</script>
</html>