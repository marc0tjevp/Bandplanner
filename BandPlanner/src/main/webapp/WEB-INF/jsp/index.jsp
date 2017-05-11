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

        <!-- Stylesheets -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="resources/style.css">

        <!-- JavaScript -->
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="resources/init.js"></script>

    </head>
    <body>

        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index">BandPlanner</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index">Home</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">New
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="addArtist">Artist</a></li>
                            <li><a href="addPodium">Podium</a></li>
                            <li><a href="addPerformance">Performance</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container">

            <div class="row">
                <h4>Artists</h4>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead class="thead-inverse">
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="artist" items="${getAllArtists}">
                                <tr class="clickable-row" data-href="viewArtist?id=${artist.getArtistId()}">
                                    <td>${artist.getName()}</td>
                                    <td>${artist.getDescription()}</td>
                                    <td>
                                        <a type="button" href="editArtist?id=${artist.getArtistId()}" class="btn btn-primary">Edit</a>
                                        <a type="button" href="deleteArtist?id=${artist.getArtistId()}" class="btn btn-danger">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row">
                <h4>Podia</h4>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="stage" items="${getAllPodia}">
                                <tr class="clickable-row" data-href="viewPodium?id=${stage.getPodiumId()}">
                                    <td>${stage.getName()}</td>
                                    <td>
                                        <a type="button" href="editPodium?id=${stage.getPodiumId()}" class="btn btn-primary">Edit</a>
                                        <a type="button" href="deletePodium?id=${stage.getPodiumId()}" class="btn btn-danger">Delete</a>
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
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Time</th>
                                <th>Artist</th>
                                <th>Stage</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="performance" items="${getAllPerformances}">
                                <tr class="clickable-row" data-href="viewPerformance?id=${performance.getPerformanceId()}">
                                    <td>${performance.getStartTimeFormat()} - ${performance.getEndTimeFormat()}</td>
                                    <td>${performance.getPodium().getName()}</td>
                                    <td>${performance.getArtist().getName()}</td>
                                    <td>
                                        <a type="button" href="editPerformance?id=${performance.getPerformanceId()}" class="btn btn-primary">Edit</a>
                                        <a type="button" href="deletePerformance?id=${performance.getPerformanceId()}" class="btn btn-danger">Delete</a>
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