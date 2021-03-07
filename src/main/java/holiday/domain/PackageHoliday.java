package holiday.domain;

import holiday.exceptions.StringNotConvertibleToDateException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The PackageHoliday class extends the class BaseEntity.
 * This class has the following attributes:
 *      the ids of a client and a hotel      -> idClient, idHotel : Long, Long ( which is a pair stored in BaseEntity )
 *      the start date of the package        -> startDate : Calendar
 *      the end date of the package          -> endDate   : Calendar
 *      the price of the package             -> price     : Integer
 *
 *      This class has:
 *      -> a constructor which receives Calendar type for start & end dates.
 *      -> a constructor which receives String type for start & end dates.
 *      -> a setter with both String and Calendar types received as Parameter for start & end dates.
 *      -> getters and setters for all other fields
 *      -> a toString function
 *      -> an equals function


    Notes: -> used Calendar Type because Date is deprecated.
           -> developed String support for easier development process regarding Calendar instances.
 **/

public class PackageHoliday extends BaseEntity<PairIds<Long, Long>>{
    private Calendar startDate;
    private Calendar endDate;
    private Integer price;
    
    public PackageHoliday(Long clientId, Long hotelId) {
        this.setId(new PairIds<>(clientId, hotelId));
    };
    
    public PackageHoliday(Long clientId, Long hotelId, Calendar startDate, Calendar endDate, Integer price) {
        this.setId(new PairIds<>(clientId, hotelId));
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
    
    public PackageHoliday(Long clientId, Long hotelId, String startDate, String endDate, Integer price) throws StringNotConvertibleToDateException {
        this.setId(new PairIds<>(clientId, hotelId));
        this.startDate = convertStringToCalendar(startDate);
        this.endDate = convertStringToCalendar(endDate);
        this.price = price;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) throws StringNotConvertibleToDateException {
        this.startDate = convertStringToCalendar(startDate);
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(String endDate) throws StringNotConvertibleToDateException {
        this.endDate = convertStringToCalendar(endDate);
    }
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getClientId(){
        return this.getId().getFirstId();
    }

    public void setClientId(Long clientId){
        this.getId().setFirstId(clientId);
    }

    public Long getHotelId(){
        return this.getId().getSecondId();
    }

    public void setHotelId(Long hotelId){
        this.getId().setSecondId(hotelId);
    }

    /**
     * This function converts a date given as String into a Calendar date.
     * @param dateAsString : String
     * @return : the String converted into Calendar date
     * @throws StringNotConvertibleToDateException
     */
    private static Calendar convertStringToCalendar(String dateAsString) throws StringNotConvertibleToDateException {
        String[] result = dateAsString.split("/");
        if (result.length != 3){
            throw new StringNotConvertibleToDateException("Bad number of parameters in string: " + dateAsString);
        }

        int year = Integer.parseInt(result[0]);
        int month = Integer.parseInt(result[1]) - 1;
        int day = Integer.parseInt(result[2]);

        // formula to set a certain date for a Calendar Instance
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || object.getClass() != this.getClass())
            return false;

        PackageHoliday packageHoliday = (PackageHoliday) object;

        if (packageHoliday.getStartDate() != this.getStartDate())
            return false;

        if (packageHoliday.getEndDate() != this.getEndDate())
            return false;

        if(! packageHoliday.getId().equals(this.getId()))
            return false;

        return packageHoliday.price.equals(this.price);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

        return "PackageHoliday{" +
                "startDate=" + formatDate.format(this.startDate.getTime()) + "/" +
                "endDate=" + formatDate.format(this.endDate.getTime()) + "/" +
                "price=" + this.price.toString() + "/" +
                super.toString() + "}";
    }
}
