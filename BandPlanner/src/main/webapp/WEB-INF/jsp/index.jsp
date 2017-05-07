<%-- 
    Document   : index
    Created on : May 7, 2017, 9:46:27 AM
    Author     : marco
--%>

<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html lang="en">
    <head>

        <title>BandPlanner</title>

        <!-- Viewport -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- CDN's for now -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"
                integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>

        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">BandPlanner</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">New
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Artist</a></li>
                            <li><a href="#">Stage</a></li>
                            <li><a href="#">Performance</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container">

            <div class="row">
                <h4>Artists</h4>
                <div class="table-responsive">
                    <table class="table">
                        <theader>
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Actions</th>
                            </tr>
                        </theader>
                        <tbody>
                            <c:forEach var="artist" items="${getAllArtists}">
                                <tr>
                                    <td>${artist.getName()}</td>
                                    <td>${artist.getDescription()}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary">Edit</button>
                                        <button type="button" class="btn btn-danger">Delete</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            
            <div class="row">
                <h4>Performances</h4>
                <div class="table-responsive">
                    <table class="table">
                        <theader>
                            <tr>
                                <th>Time</th>
                                <th>Artist</th>
                                <th>Stage</th>
                                <th>Actions</th>
                            </tr>
                        </theader>
                        <tbody>
                            <c:forEach var="performance" items="${getAllPerformances}">
                                <tr>
                                    <td>Time</td>
                                    <td>${performance.getPodium()}</td>
                                    <td>${performance.getArtist()}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary">Edit</button>
                                        <button type="button" class="btn btn-danger">Delete</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </body>
</html>