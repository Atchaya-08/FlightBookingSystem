<%@ page import="java.util.*,com.wipro.flight.bean.FlightBookingBean" %>

<html>
<body>

<h2>All Flight Bookings</h2>

<%
List<FlightBookingBean> list =
(List<FlightBookingBean>) request.getAttribute("list");

if(list != null && !list.isEmpty()){
%>

<table border="1">
<tr>
<th>Record ID</th>
<th>Name</th>
<th>Flight</th>
<th>Date</th>
</tr>

<%
for(FlightBookingBean bean : list){
%>

<tr>
<td><%= bean.getRecordId() %></td>
<td><%= bean.getPassengerName() %></td>
<td><%= bean.getFlightNumber() %></td>
<td><%= bean.getTravelDate() %></td>
</tr>

<%
}
%>

</table>

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