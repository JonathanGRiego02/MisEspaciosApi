package MisEspaciosApi.repository;

import MisEspaciosApi.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("SELECT p FROM Place p WHERE p.user.id_user = :userId")
    List<Place> findPlacesByUserId(Long userId);

    @Query("""
    SELECT p FROM Place p
    WHERE p.pos_x BETWEEN :latMin AND :latMax
      AND p.pos_y BETWEEN :lngMin AND :lngMax
      AND (p.isPrivate = false OR p.user.id_user = :userId)
""")
    List<Place> findVisiblePlacesWithinBounds(@Param("latMin") double latMin,
                                              @Param("latMax") double latMax,
                                              @Param("lngMin") double lngMin,
                                              @Param("lngMax") double lngMax,
                                              @Param("userId") Long userId);

}