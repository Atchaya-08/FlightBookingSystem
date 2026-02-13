package com.wipro.flight.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get parameters
        String bookingId = request.getParameter("bookingId");
        String passengerName = request.getParameter("passengerName");
        String flightNumber = request.getParameter("flightNumber");
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "your_username",
                    "your_password");

            String sql = "INSERT INTO flight_booking VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bookingId);
            ps.setString(2, passengerName);
            ps.setString(3, flightNumber);
            ps.setString(4, source);
            ps.setString(5, destination);

            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<h2>Booking Added Successfully!</h2>");
                out.println("Booking ID: " + bookingId + "<br>");
                out.println("Passenger Name: " + passengerName + "<br>");
                out.println("Flight Number: " + flightNumber + "<br>");
                out.println("Source: " + source + "<br>");
                out.println("Destination: " + destination + "<br>");
            } else {
                out.println("<h2>Booking Failed!</h2>");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}