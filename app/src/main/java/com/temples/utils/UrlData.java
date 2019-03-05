package com.temples.utils;

public class UrlData {
    public static String SIGNUP = "http://ticketingapp.nhealth.in/TicketBooking.svc/UserSignUp";
    public static String emailPATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static String LOGIN_URL =" http://ticketingapp.nhealth.in/TicketBooking.svc/ValidateUserLogin";

    //public static String TEMPLE_DETAILS ="http://ticketingapp.nhealth.in/TicketBooking.svc/GetVisitingPlaceDetails/1";

    public static String TEMPLE_DETAILS ="http://ticketingapp.nhealth.in/TicketBooking.svc/GetVisitingPlaceDetails/";
    public static String PLACES_LIST= "http://ticketingapp.nhealth.in/TicketBooking.svc/GetVisitingPlacesList";

    public static String PACKAGE_DETAIL= "http://ticketingapp.nhealth.in/TicketBooking.svc/GetVisitingPassDetails/";

    public static String PROCEED_PAYMENT= "http://ticketingapp.nhealth.in/TicketBooking.svc/SaveBookingInformation";

    public static String BOOKING_INFO=" http://ticketingapp.nhealth.in/TicketBooking.svc/GetPassBookingInfo/";
    public static String HISTORY_LIST= "http://ticketingapp.nhealth.in/TicketBooking.svc/GetBookingPassHistory/";
    public static String UPLOAD_IMAGE= "http://ticketingapp.nhealth.in/TicketBooking.svc/UploadBookingPersonPhoto";

}
