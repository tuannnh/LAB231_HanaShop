<%-- 
    Document   : upload
    Created on : Jan 18, 2020, 10:23:13 PM
    Author     : tuannnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="UploadServlet" method="post" enctype="multipart/form-data">
            <input type="text" name="description" />
            <input type="file" name="file" />
            <input type="submit" />
        </form>
    </body>
</html>
