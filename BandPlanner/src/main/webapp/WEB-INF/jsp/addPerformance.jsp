<%-- 
    Document   : addPerformance
    Created on : May 10, 2017, 9:56:06 PM
    Author     : maxim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>

        <title>BandPlanner - Add Performance</title>

        <!-- Viewport -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Stylesheets -->
        <link rel="stylesheet" href="resources/bootstrap.css">
        <link rel="stylesheet" href="resources/style.css">
        <link rel="stylesheet" href="resources/jquery.datetimepicker.min.css"/>

        <!-- JavaScript -->
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/jquery.datetimepicker.full.min.js"/>"></script>

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
                <h4>Add Performance</h4>

                <form:form method="POST" action="addPerformance" modelAttribute="performance">

                    <div class="form-group">
                        <form:select class="form-control" path="artist" name="dropdown">
                            <option value="">Select Artist</option>
                            <c:forEach items="${getAllArtists}" var="artist">
                                <option value="${artist.getArtistId()}"/>${artist.getName()}</option>
                            </c:forEach>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <form:select class="form-control" path="podium" name="dropdown">
                            <option value="">Select Podium</option>
                            <c:forEach items="${getAllPodia}" var="podium">
                                <option value="${podium.getPodiumId()}">${podium.getName()}</option>
                            </c:forEach>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <form:label path="starttime">Start Time</form:label>
                        <form:input type="text" required="true" class="datetimepicker form-control" path="starttime"/>
                    </div>

                    <div class="form-group">
                        <form:label path="endtime">End Time</form:label>
                        <form:input type="text" required="true" class="datetimepicker form-control" path="endtime"/>
                    </div>

                    <button type="submit" class="btn btn-default">Submit</button>

                </form:form>

            </div>
        </div>

        <script>
            jQuery('.datetimepicker').datetimepicker({
                format:'d-m-Y H:i'
            });
        </script>

    </body>
</html>

