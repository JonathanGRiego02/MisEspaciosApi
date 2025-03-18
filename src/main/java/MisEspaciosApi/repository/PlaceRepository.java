package MisEspaciosApi.repository;

import MisEspaciosApi.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("SELECT p FROM Place p WHERE p.user.id_user = :userId")
    List<Place> findPlacesByUserId(Long userId);
}