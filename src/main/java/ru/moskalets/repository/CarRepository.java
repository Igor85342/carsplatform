package ru.moskalets.repository;

import org.springframework.data.repository.CrudRepository;
import ru.moskalets.model.BrandSpring;
import ru.moskalets.model.CarSpring;
import java.util.List;

public interface CarRepository extends CrudRepository<CarSpring, Integer> {

    List<CarSpring> findByImageBase64Not(String imageBase64);

    List<CarSpring> findByDateBetween(Long start, Long end);

    List<CarSpring> findByBrand(BrandSpring brand);
}

