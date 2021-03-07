package holiday.validators;

import holiday.domain.PackageHoliday;
import holiday.exceptions.ValidatorException;

import java.util.Calendar;

public class PackageHolidayValidator implements Validator<PackageHoliday>{
    @Override
    public void validate(PackageHoliday packageHoliday) throws ValidatorException {
        if (packageHoliday.getPrice() <= 0) {
            throw new ValidatorException("The price for package holiday must be a positive integer!");
        }

    }
}
