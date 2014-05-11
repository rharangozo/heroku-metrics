<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head></head>
<body>

<form action="/main" method="post">
    Token : <input name="token" type="text"/>
    
    Owner : <input name="owner" type="text"/>
    Repository : <input name="repository" type="text"/>

    <input type="submit"/>
</form>

<c:forEach items="${result}" var="entry">
    Day = ${entry.key}, cyclic time = ${entry.value}<br>
</c:forEach>

</body>