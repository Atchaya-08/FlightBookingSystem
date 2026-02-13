package com.wipro.flight.service;

import java.util.Date;
import java.util.List;

import com.wipro.flight.bean.FlightBookingBean;
import com.wipro.flight.dao.FlightBookingDAO;
import com.wipro.flight.util.InvalidInputException;
public class Administrator {

    FlightBookingDAO dao = new FlightBookingDAO();
    public String addRecord(FlightBookingBean bookingBean) {

        String result = "";

        try {

            // 1️⃣ Null validation
            if (bookingBean == null || 
                bookingBean.getPassengerName() == null || 
                bookingBean.getTravelDate() == null) {

                throw new InvalidInputException();
            }

            // 2️⃣ Passenger name length check
            if (bookingBean.getPassengerName().length() < 2) {
                return "INVALID PASSENGER NAME";
            }

            // 3️⃣ Check if record already exists
            if (dao.recordExists(bookingBean.getPassengerName(),
                                 bookingBean.getTravelDate())) {

                return "ALREADY EXISTS";
            }

            // 4️⃣ Generate Record ID
            String recordId = dao.generateRecordID(
                    bookingBean.getPassengerName(),
                    bookingBean.getTravelDate());

            bookingBean.setRecordId(recordId);

            // 5️⃣ Insert record
            result = dao.createRecord(bookingBean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }

        return result;
    }
    public FlightBookingBean viewRecord(String passengerName, Date travelDate) {

        return dao.fetchRecord(passengerName, travelDate);
    }
    public List<FlightBookingBean> viewAllRecords() {

        return dao.fetchAllRecords();
    }

}