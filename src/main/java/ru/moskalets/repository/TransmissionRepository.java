package ru.moskalets.repository;
import org.springframework.data.repository.CrudRepository;
import ru.moskalets.model.TransmissionSpring;

public interface TransmissionRepository extends CrudRepository<TransmissionSpring, Integer> {
}