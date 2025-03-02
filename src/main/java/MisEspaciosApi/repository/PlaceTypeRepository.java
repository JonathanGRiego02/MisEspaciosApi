package MisEspaciosApi.repository;

import MisEspaciosApi.models.PlaceTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceTypeRepository extends JpaRepository<PlaceTypes, Long> {
}