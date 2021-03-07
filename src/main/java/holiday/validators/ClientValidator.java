package holiday.validators;

import holiday.domain.Client;
import holiday.exceptions.ValidatorException;

public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client client) throws ValidatorException {
        if(client.getName().isEmpty()){
            throw new ValidatorException("Client name must not be empty!");
        }

        if(client.getAge() < 18){
            throw new ValidatorException("Client must be at least 18 years old!");
        }

    }
}
