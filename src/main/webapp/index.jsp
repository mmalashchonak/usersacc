<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h2>Bank App Home Page</h2>
<form action="GetRichestUserServlet" method="GET">
    <input type="text" value='<%=request.getAttribute("richestUser")%>'/>
    <input type="submit" value="Get richest user"/>
</form>
<form action="SumAccountsServlet" method="GET">
    <input type="text" value='<%=request.getAttribute("sum")%>'/>
    <input type="submit" value="Get accounts sum"/>
</form>
<form action="UserServlet">
    <button type="submit">View users table</button>
</form>
<form action="AccountServlet">
    <button type="submit">View accounts table</button>
</form>
</body>
</html>
