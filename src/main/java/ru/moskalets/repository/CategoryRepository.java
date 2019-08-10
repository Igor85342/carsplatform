package ru.moskalets.repository;
import org.springframework.data.repository.CrudRepository;
import ru.moskalets.model.CategorySpring;

public interface CategoryRepository extends CrudRepository<CategorySpring, Integer> {
}