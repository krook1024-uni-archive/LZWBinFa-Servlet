<%--
  Created by IntelliJ IDEA.
  User: b1
  Date: 9/16/19
  Time: 8:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>LZWBinFa</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-fluid navbar-light bg-light mt-0 mb-3">
    <span class="navbar-brand mb-0 h1">LZWBinFa generátor</span>
</nav>
<div class="container">
    <form action="processForm" method="get">
        <div class="form-group">
            <label for="textarea1">Bemenet</label>
            <textarea name="text" class="form-control" id="textarea1" rows="5"></textarea>
        </div>
        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="button1" name="save"/>
            <label class="form-check-label" for="button1">Mentés fájlba?</label>
        </div>
        <label for="filename" aria-hidden="true" class="sr-only">Fájlnév</label>
        <div class="input-group mb-2">
            <input type="text" name="filename" class="form-control" id="filename" placeholder="Fájlnév" disabled/>
            <div class="input-group-append">
                <div class="input-group-text">.txt</div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Elküld</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>

<script>
    $(document).ready(function () {
        $('#button1').click(function () {
            if ($(this).prop("checked") == true) {
                $("#filename").prop("disabled", false);
                $("#filename").prop("required", true);
            } else {
                $("#filename").prop("disabled", true);
                $("#filename").prop("required", false);
            }
        });
    });
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
