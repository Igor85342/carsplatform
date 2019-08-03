package ru.moskalets.repository;
import org.springframework.data.repository.CrudRepository;
import ru.moskalets.model.BrandSpring;

public interface BrandRepository extends CrudRepository<BrandSpring, Integer> {
}