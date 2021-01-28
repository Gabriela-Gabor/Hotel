package repository;

import domain.Comanda;
import domain.validator.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ComandaRepository extends AbstractFileRepository<Integer, Comanda>{

    public ComandaRepository(String fileName, Validator<Comanda> validator) {
        super(fileName, validator);
    }

    @Override
    public Comanda extractEntity(List<String> attributes) {
        Comanda c=new Comanda(Integer.parseInt(attributes.get(1)),attributes.get(2),Integer.parseInt(attributes.get(3)), LocalDateTime.parse(attributes.get(4)));
        c.setId(Integer.parseInt(attributes.get(0)));
        return c;
    }

    @Override
    protected String createEntityAsString(Comanda entity) {
        return entity.getId()+";"+entity.getNrCamera()+";"+entity.getProdus()+";"+entity.getPret()+";"+entity.getData();
    }
}
