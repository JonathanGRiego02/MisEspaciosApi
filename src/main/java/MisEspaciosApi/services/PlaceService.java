package MisEspaciosApi.services;

import MisEspaciosApi.dto.PlaceDTO;
import MisEspaciosApi.models.Place;
import MisEspaciosApi.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    // Get places by username
    public List<PlaceDTO> GetPlacesByUsername(String username) {
        List<Place> places = placeRepository.findPlacesByUsername(username);
        return places.stream()
                .map(place -> new PlaceDTO(
                        place.getIdPlace(),
                        place.getNamePlace(),
                        place.getDescPlace(),
                        place.getpos_x(),
                        place.getpos_y(),
                        place.getLikes(),
                        place.getImage(),
                        place.isPrivate(),
                        place.getPlaceType(),
                        place.getUser().getNickname()
                ))
                .toList();
    }
    // Method to get all places
    public List<Place> ObtainAllPlaces() {
        return placeRepository.findAll();
    }

    public List<PlaceDTO> getPlacesWithinBounds(double ne_lat, double ne_lng,
                                                double sw_lat, double sw_lng,
                                                String username) {
        List<Place> places = placeRepository.findVisiblePlacesWithinBounds(
                sw_lat, ne_lat, sw_lng, ne_lng, username
        );

        return places.stream()
                .map(place -> new PlaceDTO(
                        place.getIdPlace(),
                        place.getNamePlace(),
                        place.getDescPlace(),
                        place.getpos_x(),
                        place.getpos_y(),
                        place.getLikes(),
                        place.getImage(),
                        place.isPrivate(),
                        place.getPlaceType(),
                        place.getUser().getNickname()
                ))
                .toList();
    }

    // Method to save a place
    public void savePlace(Place place) {
        placeRepository.save(place);  // Guardamos el lugar usando el repositorio
    }
}
