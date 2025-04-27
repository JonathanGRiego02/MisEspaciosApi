package MisEspaciosApi.repository;

import MisEspaciosApi.dto.PlaceDTO;
import MisEspaciosApi.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("SELECT p FROM Place p WHERE p.user.nickname = :username")
    List<Place> findPlacesByUsername(@Param("username") String username);

    @Query("""
            SELECT p FROM Place p
            WHERE p.pos_x BETWEEN :latMin AND :latMax
              AND p.pos_y BETWEEN :lngMin AND :lngMax
              AND (p.isPrivate = false OR p.user.nickname = :username)
            """)
    List<Place> findVisiblePlacesWithinBounds(@Param("latMin") double latMin,
                                              @Param("latMax") double latMax,
                                              @Param("lngMin") double lngMin,
                                              @Param("lngMax") double lngMax,
                                              @Param("username") String username);

    @Query("""
            SELECT new MisEspaciosApi.dto.PlaceDTO(
                p.idPlace, p.namePlace, p.descPlace, 
                p.pos_x, p.pos_y, p.likes, 
                p.image, p.isPrivate, p.placeType, p.user.nickname
            )
            FROM Place p
            WHERE p.user.nickname = :nickname AND p.isPrivate = false
            """)
    List<PlaceDTO> findPublicPlacesByUserNickname(@Param("nickname") String nickname);
}