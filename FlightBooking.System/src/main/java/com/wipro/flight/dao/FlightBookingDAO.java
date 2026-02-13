package com.wipro.flight.dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.wipro.flight.bean.FlightBookingBean;
import com.wipro.flight.util.DBUtil;

public class FlightBookingDAO {
	public boolean recordExists(String passengerName, java.util.Date travelDate) {

	    boolean flag = false;

	    try {
	        Connection con = DBUtil.getDBConnection();
	        PreparedStatement ps = con.prepareStatement(
	                "SELECT * FROM FLIGHTBOOK_TB WHERE PASSENGERNAME=? AND TRAVEL_DATE=?");

	        ps.setString(1, passengerName);
	        ps.setDate(2, new java.sql.Date(travelDate.getTime()));

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            flag = true;
	        }

	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return flag;
	}
	public String generateRecordID(String passengerName, java.util.Date travelDate) {

	    String recordId = "";

	    try {
	        DateFormat format = new SimpleDateFormat("yyyyMMdd");
	        String datePart = format.format(travelDate);

	        String namePart = passengerName.substring(0, 2).toUpperCase();

	        Connection con = DBUtil.getDBConnection();
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery("SELECT FLIGHTBOOK_SEQ.NEXTVAL FROM DUAL");

	        rs.next();
	        String seq = String.format("%02d", rs.getInt(1));

	        recordId = datePart + namePart + seq;

	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return recordId;
	}
	public String createRecord(FlightBookingBean bookingBean) {

	    String result = "FAIL";

	    try {
	        Connection con = DBUtil.getDBConnection();

	        PreparedStatement ps = con.prepareStatement(
	                "INSERT INTO FLIGHTBOOK_TB VALUES (?,?,?,?,?,?,?)");

	        ps.setString(1, bookingBean.getRecordId());
	        ps.setString(2, bookingBean.getPassengerName());
	        ps.setString(3, bookingBean.getFlightNumber());
	        ps.setDate(4, new java.sql.Date(bookingBean.getTravelDate().getTime()));
	        ps.setString(5, bookingBean.getSeatNo());
	        ps.setString(6, bookingBean.getTicketNo());
	        ps.setString(7, bookingBean.getRemarks());

	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            result = bookingBean.getRecordId();
	        }

	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}
	public FlightBookingBean fetchRecord(String passengerName, java.util.Date travelDate) {

	    FlightBookingBean bean = null;

	    try {
	        Connection con = DBUtil.getDBConnection();

	        PreparedStatement ps = con.prepareStatement(
	                "SELECT * FROM FLIGHTBOOK_TB WHERE PASSENGERNAME=? AND TRAVEL_DATE=?");

	        ps.setString(1, passengerName);
	        ps.setDate(2, new java.sql.Date(travelDate.getTime()));

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            bean = new FlightBookingBean();

	            bean.setRecordId(rs.getString("RECORDID"));
	            bean.setPassengerName(rs.getString("PASSENGERNAME"));
	            bean.setFlightNumber(rs.getString("FLIGHTNUMBER"));
	            bean.setTravelDate(rs.getDate("TRAVEL_DATE"));
	            bean.setSeatNo(rs.getString("SEATNO"));
	            bean.setTicketNo(rs.getString("TICKETNO"));
	            bean.setRemarks(rs.getString("REMARKS"));
	        }

	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return bean;
	}
	public List<FlightBookingBean> fetchAllRecords() {

	    List<FlightBookingBean> list = new ArrayList<>();

	    try {
	        Connection con = DBUtil.getDBConnection();
	        Statement st = con.createStatement();

	        ResultSet rs = st.executeQuery("SELECT * FROM FLIGHTBOOK_TB");

	        while (rs.next()) {

	            FlightBookingBean bean = new FlightBookingBean();

	            bean.setRecordId(rs.getString("RECORDID"));
	            bean.setPassengerName(rs.getString("PASSENGERNAME"));
	            bean.setFlightNumber(rs.getString("FLIGHTNUMBER"));
	            bean.setTravelDate(rs.getDate("TRAVEL_DATE"));
	            bean.setSeatNo(rs.getString("SEATNO"));
	            bean.setTicketNo(rs.getString("TICKETNO"));
	            bean.setRemarks(rs.getString("REMARKS"));

	            list.add(bean);
	        }

	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
}