<%@ page import="com.wipro.flight.bean.FlightBookingBean" %>

<html>
<body>

<h2>Flight Booking Details</h2>

<%
FlightBookingBean bean = (FlightBookingBean) request.getAttribute("bean");

if(bean != null){
%>

Record ID: <%= bean.getRecordId() %> <br>
Passenger Name: <%= bean.getPassengerName() %> <br>
Flight Number: <%= bean.getFlightNumber() %> <br>
Travel Date: <%= bean.getTravelDate() %> <br>
Seat No: <%= bean.getSeatNo() %> <br>
Ticket No: <%= bean.getTicketNo() %> <br>
Remarks: <%= bean.getRemarks() %> <br>

<%
} else {
%>

<%= request.getAttribute("message") %>

<%
}
%>

<br><br>
<a href="menu.html">Go Back</a>

</body>
</html>