package MisEspaciosApi.services;

import MisEspaciosApi.dto.PlaceTypeDTO;
import MisEspaciosApi.models.PlaceType;
import MisEspaciosApi.repository.PlaceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceTypeService {

  @Autowired
  private PlaceTypeRepository placeTypeRepository;

  public List<PlaceTypeDTO> getAllPlaceTypes() {
    List<PlaceType> placeTypes = placeTypeRepository.findAll();
    return placeTypes.stream()
            .map(pt -> new PlaceTypeDTO(
                    pt.getId_type(),
                    pt.getName_type(),
                    pt.getIcon(),
                    pt.getUser().getid_user() != null ? pt.getUser().getid_user() : null
            ))
            .collect(Collectors.toList());
  }

  public Optional<PlaceType> findById(Long id) {
    return placeTypeRepository.findById(id);
  }

  // Get all place types for a specific user
  public List<PlaceTypeDTO> getPlaceTypesByUserNickname(String nickname) {
    List<PlaceType> placeTypes = placeTypeRepository.findByUser_Nickname(nickname);
    return placeTypes.stream()
            .map(pt -> new PlaceTypeDTO(
                    pt.getId_type(),
                    pt.getName_type(),
                    pt.getIcon(),
                    pt.getUser() != null ? pt.getUser().getid_user() : null
            ))
            .collect(Collectors.toList());
  }

  // Save a new place type
  public void savePlaceType(PlaceType placeType) {
    placeTypeRepository.save(placeType);
  }

}
