package holiday.repository;

import holiday.domain.BaseEntity;
import holiday.exceptions.ValidatorException;
import holiday.validators.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements InterfaceRepository<ID, T> {
    private Map<ID, T> entities;
    private Validator<T> validator;

    public InMemoryRepository(Validator<T> validator) {
        this.entities = new HashMap<>();
        this.validator = validator;
    }

    @Override
    public Optional<T> findOne(ID id) {
        if(id == null){
            throw new IllegalArgumentException("id must not be null!");
        }
        return Optional.ofNullable(this.entities.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities = this.entities.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());;
        return allEntities;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        if(entity == null){
            throw new IllegalArgumentException("id must not be null!");
        }
        validator.validate(entity);
        return Optional.ofNullable(this.entities.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<T> delete(ID id) {
        if(id == null){
            throw new IllegalArgumentException("id must not be null!");
        }
        return Optional.ofNullable(this.entities.remove(id));
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if(entity == null){
            throw new IllegalArgumentException("entity must not be null!");
        }
        validator.validate(entity);
        return Optional.ofNullable(this.entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
