<html>
<head>

</head>
<body>
<?php
/**
 * Created by PhpStorm.
 * User: slyubimov
 * Date: 09.02.2011
 * Time: 19:03:40
 * To change this template use File | Settings | File Templates.
 */
if(isset($_POST["date"])){
    echo strtotime($_POST["date"]);
}
?>
<br>
<form method="post" action="#">
    <label>
        Date
        <input type="text" name="date">
    </label>
    <input type="submit">
</form>


</body>
</html>
