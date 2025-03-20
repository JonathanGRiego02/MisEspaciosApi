package MisEspaciosApi;

import MisEspaciosApi.models.Place;
import MisEspaciosApi.models.PlaceTypes;
import MisEspaciosApi.models.User;
import MisEspaciosApi.repository.UserRepository;
import MisEspaciosApi.repository.PlaceTypeRepository;
import MisEspaciosApi.models.LoginRequest;
import MisEspaciosApi.services.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:*")
public class ApiController {

    private final UserRepository userRepository;
    private final PlaceTypeRepository placeTypeRepository;
    private final PlaceService placeService;

    // Inyección de los repositorios y servicios
    public ApiController(UserRepository userRepository, PlaceTypeRepository placeTypeRepository, PlaceService placeService) {
        this.userRepository = userRepository;
        this.placeTypeRepository = placeTypeRepository;
        this.placeService = placeService;
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


    // Get: Getting the places within the bounds dinamyically as the user moves the map
    @GetMapping("/places")
    public List<Place> getPlacesInBounds(@RequestParam double ne_lat, @RequestParam double ne_lng,
                                         @RequestParam double sw_lat, @RequestParam double sw_lng) {
        System.out.println("Getting places within bounds");

        // Usar el servicio para obtener lugares dentro de los límites especificados
        return placeService.getPlacesWithinBounds(ne_lat, ne_lng, sw_lat, sw_lng);
    }

    // POST: Crear un nuevo lugar
    @PostMapping("/places")
    public ResponseEntity<String> createPlace(@RequestParam String namePlace,
                                              @RequestParam String descPlace,
                                              @RequestParam Long placeTypeId,
                                              @RequestParam Long userId) {
        // Validar si el tipo de lugar existe
        Optional<PlaceTypes> placeTypeOptional = placeTypeRepository.findById(placeTypeId);
        if (!placeTypeOptional.isPresent()) {
            return ResponseEntity.status(400).body("Tipo de lugar no encontrado.");
        }

        // Validar si el usuario existe
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(400).body("Usuario no encontrado.");
        }

        // Crear un nuevo lugar
        Place newPlace = new Place();
        newPlace.setNamePlace(namePlace);
        newPlace.setDescPlace(descPlace);
        newPlace.setPlaceType(placeTypeOptional.get());
        newPlace.setUser(userOptional.get());

        // Guardar el lugar en la base de datos utilizando el servicio
        placeService.savePlace(newPlace);

        // Devolver una respuesta exitosa
        return ResponseEntity.status(201).body("Lugar creado con éxito.");
    }


    // POST: Validar login
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByNickname(loginRequest.getNickname());
        System.out.println("Solicitud recibida: " + loginRequest.getNickname());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (BCrypt.checkpw(loginRequest.getPasswd(), user.getPasswd())) {
                System.out.println("Login exitoso");
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.status(401).build();
    }

    // Endpoint para obtener los lugares de un usuario específico
    @GetMapping("/places/user/{userId}")
    public ResponseEntity<List<Place>> getPlacesByUserId(@PathVariable Long userId) {
        // Usamos el servicio para obtener los lugares del usuario
        List<Place> places = placeService.obtenerLugaresPorUsuario(userId);

        // Si no hay lugares, devolver un estado no content
        if (places.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Si hay lugares, devolver los resultados con un estado OK
        return ResponseEntity.ok(places);
    }
}
