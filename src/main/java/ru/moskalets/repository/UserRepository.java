package ru.moskalets.repository;
import org.springframework.data.repository.CrudRepository;
import ru.moskalets.model.UserSpring;

public interface UserRepository extends CrudRepository<UserSpring, Integer> {
    UserSpring findByLogin(String login);
}