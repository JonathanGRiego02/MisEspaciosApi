package MisEspaciosApi.repository;

import MisEspaciosApi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickname(String nickname);

    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(f) FROM Follower f WHERE f.followed.id_user = :userId")
    int countFollowersByUserId(@Param("userId") int userId);
}