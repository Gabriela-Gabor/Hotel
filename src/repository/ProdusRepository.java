package repository;

import domain.Produs;
import domain.validator.Validator;

import java.util.List;

public class ProdusRepository extends AbstractFileRepository<String, Produs> {


    public ProdusRepository(String fileName, Validator<Produs> validator) {
        super(fileName, validator);
    }

    @Override
    public Produs extractEntity(List<String> attributes) {
        Produs p = new Produs(attributes.get(0), Integer.parseInt(attributes.get(1)));
        p.setId(attributes.get(0));
        return p;
    }

    @Override
    protected String createEntityAsString(Produs entity) {
        return entity.getDenumire() + ";" + entity.getPret();
    }
}
