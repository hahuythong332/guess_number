package inmobi.guess_number.repository;

import inmobi.guess_number.entity.User;
import inmobi.guess_number.entity.UserScoreNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserScoreNumberRepository extends JpaRepository<UserScoreNumber,String> {
    boolean existsByUserId(String userId);
    UserScoreNumber findByUserId(String userId);
}
