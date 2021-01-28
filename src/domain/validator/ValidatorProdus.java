package domain.validator;

import domain.Produs;

public class ValidatorProdus implements Validator<Produs> {
    @Override
    public void validate(Produs entity) throws ValidationException {
        String mesaj="";
        if(entity.getPret()<=0)
            mesaj+="Pret invalid";

        if(!mesaj.isEmpty())
            throw new ValidationException(mesaj);

    }
}
