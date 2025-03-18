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

    // Método para obtener los lugares de un usuario específico
    public List<Place> obtenerLugaresPorUsuario(Long userId) {
        return placeRepository.findPlacesByUserId(userId);
    }

    // Método para obtener todos los lugares
    public List<Place> ObtainAllPlaces() {
        return placeRepository.findAll();
    }

    // Método para obtener lugares dentro de un rango de coordenadas
    public List<Place> getPlacesWithinBounds(double ne_lat, double ne_lng, double sw_lat, double sw_lng) {
        // Usar el repositorio para obtener los lugares dentro de los límites
        return placeRepository.findPlacesWithinBounds(sw_lat, ne_lat, sw_lng, ne_lng);
    }

    // Método para guardar un lugar
    public void savePlace(Place place) {
        placeRepository.save(place);  // Guardamos el lugar usando el repositorio
    }
}
