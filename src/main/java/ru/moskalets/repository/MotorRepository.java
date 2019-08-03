package ru.moskalets.repository;
import org.springframework.data.repository.CrudRepository;
import ru.moskalets.model.MotorSpring;

public interface MotorRepository extends CrudRepository<MotorSpring, Integer> {
}