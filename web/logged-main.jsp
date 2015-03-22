<%-- 
    Document   : logged-main.jsp
    Created on : 22-mar-2015, 15:19:05
    Author     : odin.delrio
--%>

<%@page import="User.Domain.Model.User.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Main page for logged users</title>

        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }

        </style>

    </head>
    <body>

        <jsp:include page="partial/navbar.jsp" />
        
        <div class="container">
            
            <h1>Hello <%= ((User)session.getAttribute("user")).name() %></h1>
          
            <ul>
                <li><a href="private/page-1.jsp">Page 1</a></li>
                <li><a href="private/page-2.jsp">Page 2</a></li>
                <li><a href="private/page-3.jsp">Page 3</a></li>
            </ul>
        </div> <!-- /container -->

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>
