package project.LOGIC;

import java.util.ArrayList;
import project.DB.DBBookingNumberGenerator;
import project.DB.DBException;

public class Revenue {

    ArrayList<String> months;
    ArrayList<Double> revenuePerMonth;

    public Revenue(String year) throws DBException {
        this.months = months();
        this.revenuePerMonth = calculateRevenuePerMonth(year);
    }

    //Method to initialize the months
    public static ArrayList<String> months() {
        ArrayList<String> m = new ArrayList<>();
        m.add("January");
        m.add("February");
        m.add("March"); 
        m.add("April");
        m.add("May");
        m.add("June");
        m.add("July");
        m.add("August");
        m.add("September");
        m.add("October");
        m.add("November");
        m.add("December");

        return m;

    }

    //Method to calculate the revenue of a month: tested V
    public static ArrayList<Double> calculateRevenuePerMonth(String year) throws DBException {
        ArrayList<Double> revenuePerMonth = new ArrayList<>();
        ArrayList<BookingNumberGenerator> allBookings = DBBookingNumberGenerator.getBookings();
        Double revJan = 0.0;
        Double revFeb = 0.0;
        Double revMar = 0.0;
        Double revApr = 0.0;
        Double revMay = 0.0;
        Double revJun = 0.0;
        Double revJul = 0.0;
        Double revAug = 0.0;
        Double revSep = 0.0;
        Double revOkt = 0.0;
        Double revNov = 0.0;
        Double revDec = 0.0;
        for (BookingNumberGenerator booking : allBookings) {
            String jaar = booking.getBookingDate().substring(6);
            if (jaar.equals(year)) {
                switch (booking.getBookingDate().substring(3, 5)) {
                    case "01":
                        revJan = revJan + booking.getServiceFee();
                        break;
                    case "02":
                        revJan = revFeb + booking.getServiceFee();
                        break;
                    case "03":
                        revJan = revMar + booking.getServiceFee();
                        break;
                    case "04":
                        revJan = revApr + booking.getServiceFee();
                        break;
                    case "05":
                        revJan = revMay + booking.getServiceFee();
                        break;
                    case "06":
                        revJan = revJun + booking.getServiceFee();
                        break;
                    case "07":
                        revJan = revJul + booking.getServiceFee();
                        break;
                    case "08":
                        revJan = revAug + booking.getServiceFee();
                        break;
                    case "09":
                        revJan = revSep + booking.getServiceFee();
                        break;
                    case "10":
                        revJan = revOkt + booking.getServiceFee();
                        break;
                    case "11":
                        revJan = revNov + booking.getServiceFee();
                        break;
                    case "12":
                        revJan = revDec + booking.getServiceFee();
                        break;
                }
            }
        }
        revenuePerMonth.add(0, revJan);
        revenuePerMonth.add(1, revFeb);
        revenuePerMonth.add(2, revMar);
        revenuePerMonth.add(3, revApr);
        revenuePerMonth.add(4, revMay);
        revenuePerMonth.add(5, revJun);
        revenuePerMonth.add(6, revJul);
        revenuePerMonth.add(7, revAug);
        revenuePerMonth.add(8, revSep);
        revenuePerMonth.add(9, revOkt);
        revenuePerMonth.add(10, revNov);
        revenuePerMonth.add(11, revDec);
        return revenuePerMonth;
    }

    public ArrayList<String> getMonths() {
        return months;
    }

    public ArrayList<Double> getRevenuePerMonth() {
        return revenuePerMonth;
    }
    
}
