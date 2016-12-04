<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<html>
    <body>
    <form:form method="post" action="/newweb/th/form1" modelAttribute="personForm">
        
            <table>
                <tr>
                    <td>Name:</td>
                    <td><form:input path="name" /></td>
                    <th><form:errors path="name" cssClass="error" /> </th>
                    
                </tr>
                <tr>
                    <td>Age:</td>
                    <td><form:input path="age" /></td>
                    <th><form:errors path="age" cssClass="error" /> </th>
                </tr>
                <tr>
                    <td><button type="submit">Submit</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
