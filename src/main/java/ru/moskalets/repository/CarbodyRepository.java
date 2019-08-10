package ru.moskalets.repository;
import org.springframework.data.repository.CrudRepository;
import ru.moskalets.model.CarbodySpring;

public interface CarbodyRepository extends CrudRepository<CarbodySpring, Integer> {
}