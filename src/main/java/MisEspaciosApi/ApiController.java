package MisEspaciosApi;

import MisEspaciosApi.models.Place;
import MisEspaciosApi.models.PlaceTypes;
import MisEspaciosApi.models.User;
import MisEspaciosApi.repository.PlaceRepository;
import MisEspaciosApi.repository.PlaceTypeRepository;
import MisEspaciosApi.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserRepository userRepository;
    private final PlaceTypeRepository placeTypeRepository;
    private final PlaceRepository placeRepository;

    public ApiController(UserRepository userRepository, PlaceTypeRepository placeTypeRepository, PlaceRepository placeRepository) {
        this.userRepository = userRepository;
        this.placeTypeRepository = placeTypeRepository;
        this.placeRepository = placeRepository;
    }

    // GET: Obtener todos los usuarios
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET: Obtener todos los tipos de lugares
    @GetMapping("/placeTypes")
    public List<PlaceTypes> getAllPlaceTypes() {
        return placeTypeRepository.findAll();
    }

    // GET: Obtener todos los lugares
    @GetMapping("/places")
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }
}