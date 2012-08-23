<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head>
    <meta name="description" content="Spring Conversio portal" />
	<meta name="author" content="Daniil Ivanov" />
	<meta http-equiv="content-type" content="text/html;charset=UTF-8" /> 
    <title>Conversion portal</title>
    <style type="text/css">
        body {
        	font-family: courier }
	    div {
	    	width: 75%;
	        margin-left: auto;
  	    	margin-right: auto;
  	    	text-align: center } 
		table {
		    font-size: 200%;
			border-width: 0px;
			border-collapse: collapse;
	        margin-left: auto;
  	    	margin-right: auto }
		td {
		    width: 10%;
		    text-align: center;
			border-width: 1px;
		    border-style: solid }
	</style>
  </head>
  <body>
  	<div>
    <h1>Decimal to Roman numeral converter</h1>
    <form:form method="POST" commandName="inputNumeral">
      Decimal number: <form:input path="numeral"/>
      <input type="submit" value="Convert">
	</form:form>
    <p>Previous conversions:</p>
    <table>
	<tr>
	  <c:forEach var="conversion" items="${history}">
        <tr>
 		  <td><c:out value="${conversion.key}"/></td>
	      <td><c:out value="${conversion.value}"/></td>
        </tr>
      </c:forEach>
	</tr>
	</table> 
  	</div>
  </body>
</html>