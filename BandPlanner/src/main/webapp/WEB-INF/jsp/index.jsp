<%-- 
    Document   : index
    Created on : May 7, 2017, 9:46:27 AM
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html lang="en">
    <head>

        <title>BandPlanner</title>

        <!-- Viewport -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- CDN's for now -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    </head>
    <body>

        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                </div>
            </div>
        </nav>

        <div class="container">

            <div class="row">
                <div class="table-responsive">
                    <table class="table">
                        <theader>
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                            </tr>
                        </theader>
                        <tbody>
                            <tr>
                                <td>${artist.name}</td>
                                <td>${artist.description}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>

    </body>
</html>