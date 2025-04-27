package MisEspaciosApi.repository;

import MisEspaciosApi.dto.PlaceTypeDTO;
import MisEspaciosApi.models.Place;
import MisEspaciosApi.models.PlaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceTypeRepository extends JpaRepository<PlaceType, Long> {

  // Find all place types created by a user by their nickname
  List<PlaceType> findByUser_Nickname(String nickname);
}