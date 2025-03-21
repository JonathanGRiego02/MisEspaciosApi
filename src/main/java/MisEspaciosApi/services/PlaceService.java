package MisEspaciosApi.services;

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

    // Method to get places by user
    public List<Place> obtenerLugaresPorUsuario(Long userId) {
        return placeRepository.findPlacesByUserId(userId);
    }

    // Method to get all places
    public List<Place> ObtainAllPlaces() {
        return placeRepository.findAll();
    }

    // Method to get places by coordenates
    public List<Place> getPlacesWithinBounds(double ne_lat, double ne_lng, double sw_lat, double sw_lng) {
        // Usar el repositorio para obtener los lugares dentro de los límites
        return placeRepository.findPlacesWithinBounds(sw_lat, ne_lat, sw_lng, ne_lng);
    }

    // Method to save a place
    public void savePlace(Place place) {
        placeRepository.save(place);  // Guardamos el lugar usando el repositorio
    }
}
