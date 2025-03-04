package MisEspaciosApi;

import MisEspaciosApi.models.Place;
import MisEspaciosApi.models.PlaceTypes;
import MisEspaciosApi.models.User;
import MisEspaciosApi.repository.PlaceRepository;
import MisEspaciosApi.repository.PlaceTypeRepository;
import MisEspaciosApi.repository.UserRepository;
import MisEspaciosApi.models.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;



import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:*")
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
        System.out.println("Getting users");
        return userRepository.findAll();
    }

    // GET: Obtener todos los tipos de lugares
    @GetMapping("/placeTypes")
    public List<PlaceTypes> getAllPlaceTypes() {
        System.out.println("Getting place types");
        return placeTypeRepository.findAll();
    }

    // GET: Obtener todos los lugares
    @GetMapping("/places")
    public List<Place> getAllPlaces() {
        System.out.println("Getting places");
        return placeRepository.findAll();
    }

    // POST: Validar login
    @CrossOrigin(origins = "http://127.0.0.1:3000", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET})
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByNickname(loginRequest.getNickname());
        System.out.println("Solicitud recibida: " + loginRequest.getNickname());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (BCrypt.checkpw(loginRequest.getPasswd(), user.getPasswd())) {
                System.out.println("Login exitoso");
                return ResponseEntity.ok().build(); // Solo un 200 OK sin cuerpo
            }
        }

        return ResponseEntity.status(401).build(); // Solo un 401 sin cuerpo (para error de autenticación)
    }

}
