package holiday.validators;

import holiday.domain.Hotel;
import holiday.exceptions.ValidatorException;

public class HotelValidator implements Validator<Hotel>{
    @Override
    public void validate(Hotel hotel) throws ValidatorException {
        if(hotel.getName().isEmpty()){
            throw new ValidatorException("Hotel name must not be empty!");
        }

        if(hotel.getLocation().isEmpty()){
            throw new ValidatorException("Location name must not be empty!");
        }

        if(hotel.getStars() <= 0){
            throw new ValidatorException("Hotel stars must be a positive integer!");
        }

        if(hotel.getStars() > 5){
            throw new ValidatorException("Hotel start must be at most 5!");
        }

        if(hotel.getCapacity() <= 0){
            throw new ValidatorException("Hotel capacity must be a positive integer!");
        }

    }
}
