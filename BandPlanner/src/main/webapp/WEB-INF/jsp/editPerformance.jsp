<%-- 
    Document   : editPerformance
    Created on : May 11, 2017, 11:42:22 AM
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>

        <title>BandPlanner - Edit Performance</title>

        <!-- Viewport -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- CDN's for now -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"
                integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery.datetimepicker.min.css"/>" />
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
                <h4>Edit Performance</h4>

                <form:form method="POST" action="updatePerformance" modelAttribute="performance">

                    <div class="form-group hidden">
                        <form:label path="PerformanceId">ID</form:label>
                        <form:input value="${thisPerformance.getPerformanceId().toString()}" required="true" class="form-control" path="performanceId"/>
                    </div>

                    <div class="form-group">
                        <form:label path="artist">Artist</form:label>
                        <form:select class="form-control" path="artist" name="dropdown">
                            <c:forEach items="${getAllArtists}" var="artist">
                                <option value="${artist.getArtistId()}" label="${artist.getName()}"/>
                            </c:forEach>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <form:label path="podium">Podium</form:label>
                        <form:select class="form-control" path="podium" name="dropdown">
                            <c:forEach items="${getAllPodia}" var="podium">
                                <option value="${podium.getPodiumId()}" label="${podium.getName()}"/>
                            </c:forEach>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <form:label path="starttime">Start Time</form:label>
                        <form:input value="${thisPerformance.getStartTimeFormat()}" type="text" required="true" class="datetimepicker form-control" path="starttime"/>
                    </div>

                    <div class="form-group">
                        <form:label path="endtime">End Time</form:label>
                        <form:input value="${thisPerformance.getEndTimeFormat()}" type="text" required="true" class="datetimepicker form-control" path="endtime"/>
                    </div>

                    <button type="submit" class="btn btn-default">Submit</button>

                </form:form>

            </div>
        </div>

        <script>
            jQuery('.datetimepicker').datetimepicker({
                format: 'd-m-Y H:i'
            });
        </script>

    </body>
</html>
