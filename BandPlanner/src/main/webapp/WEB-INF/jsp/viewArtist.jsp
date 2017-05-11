<%-- 
    Document   : viewArtist
    Created on : May 11, 2017, 12:00:51 AM
    Author     : maxim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>

        <title>BandPlanner - ${thisArtist.getName()}</title>

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
                <h4>${thisArtist.getName()}</h4>
                <p>${thisArtist.getDescription()}</p>
            </div>

            <div class="row">
                <div class="well">
                    <h4>Upcoming performances</h4>
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Time</th>
                                    <th>Stage</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="performance" items="${performancesByArtist}">
                                    <tr class="clickable-row" data-href="viewPerformance?id=${performance.getPerformanceId()}">
                                        <td>${performance.getStartTimeFormat()} - ${performance.getEndTimeFormat()}</td>
                                        <td>${performance.getPodium().getName()}</td>
                                        <td>
                                            <a type="button" class="btn btn-primary">View Performance</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>

    </body>
</html>
