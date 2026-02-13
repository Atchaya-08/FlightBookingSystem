<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Flight Booking</title>
</head>
<body>

<h2>Add Flight Booking</h2>

<form action="MainServlet" method="post">

Booking ID:
<input type="text" name="bookingId" required><br><br>

Passenger Name:
<input type="text" name="passengerName" required><br><br>

Flight Number:
<input type="text" name="flightNumber" required><br><br>

Source:
<input type="text" name="source" required><br><br>

Destination:
<input type="text" name="destination" required><br><br>

<input type="submit" value="Add Booking">

</form>

</body>
</html>