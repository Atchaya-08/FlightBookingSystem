<html>
<head>
<title>View Flight Booking</title>
</head>
<body>

<h2>View Flight Booking</h2>

<form action="MainServlet" method="post">

<input type="hidden" name="operation" value="viewRecord">

Passenger Name:
<input type="text" name="passengerName"><br><br>

Travel Date:
<input type="date" name="travelDate"><br><br>

<input type="submit" value="View Booking">

</form>

</body>
</html>