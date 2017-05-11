<%-- 
    Document   : updateArtist
    Created on : May 11, 2017, 10:54:01 AM
    Author     : maxim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>

        <title>BandPlanner - Edit Artist</title>

        <!-- Viewport -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Stylesheets -->
        <link rel="stylesheet" href="resources/bootstrap.css">
        <link rel="stylesheet" href="resources/style.css">

        <!-- JavaScript -->
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    </head>
    <body>

        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index">BandPlanner</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class=""><a href="index">Home</a></li>
                </ul>
            </div>
        </nav>

        <div class="container">

            <div class="row">
                <h4>Edit Artist</h4>

                <form:form method="POST" action="updateArtist" modelAttribute="artist">
                    <div class="form-group hidden">
                        <form:label path="artistId">ID</form:label>
                        <form:input value="${thisArtist.getArtistId().toString()}" required="true" class="form-control" path="artistId"/>
                    </div>
                    <div class="form-group">
                        <form:label path="name">Name</form:label>
                        <form:input value="${thisArtist.getName()}" required="true" class="form-control" path="name"/>
                    </div>
                    <div class="form-group">
                        <form:label path="description">Description</form:label>
                        <form:input value="${thisArtist.getDescription()}" required="true" class="form-control" path="description"/>
                    </div>

                    <button type="submit" class="btn btn-default">Submit</button>

                </form:form>

            </div>
        </div>

    </body>
</html>
