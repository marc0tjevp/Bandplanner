<%-- 
    Document   : viewPerformance
    Created on : May 11, 2017, 9:41:56 AM
    Author     : maxim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>

        <title>BandPlanner</title>

        <!-- Viewport -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Stylesheets -->
        <link rel="stylesheet" href="resources/bootstrap.css">
        <link rel="stylesheet" href="resources/style.css">

        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

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
                <h3>${thisPerformance.getArtist().getName()}</h3>

                <hr>

                <p>
                    <i class="fa fa-music" aria-hidden="true"></i>
                    ${thisPerformance.getPodium().getName()}
                </p>

                <p>
                    <i class="fa fa-calendar"></i>
                    ${thisPerformance.getStartTimeFormat()} - ${thisPerformance.getEndTimeFormat()}
                </p>

                <div style="padding-top: 20px">
                    <a type="button" href="getPreviousPerformance?id=${thisPerformance.getPerformanceId()}" class="btn btn-primary">Previous</a>
                    <a type="button" href="getNextPerformance?id=${thisPerformance.getPerformanceId()}" class="btn btn-primary pull-right">Next</a>
                </div>

            </div>
        </div>

    </body>
</html>
