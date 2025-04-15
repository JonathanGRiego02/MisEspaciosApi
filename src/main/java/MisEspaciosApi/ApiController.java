package MisEspaciosApi;

import MisEspaciosApi.models.Place;
import MisEspaciosApi.models.PlaceTypes;
import MisEspaciosApi.models.User;
import MisEspaciosApi.models.LoginRequest;
import MisEspaciosApi.repository.PlaceTypeRepository;
import MisEspaciosApi.services.PlaceService;
import MisEspaciosApi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:*")
public class ApiController {

    private final UserService userService;
    private final PlaceTypeRepository placeTypeRepository;
    private final PlaceService placeService;

    // Inyección de los servicios
    public ApiController(UserService userService, PlaceTypeRepository placeTypeRepository, PlaceService placeService) {
        this.userService = userService;
        this.placeTypeRepository = placeTypeRepository;
        this.placeService = placeService;
    }

    // GET: all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        System.out.println("Getting users");
        return userService.getAllUsers();
    }

    // GET: user by nickname
    @GetMapping("/users/{nickname}")
    public ResponseEntity<User> getUserByNickname(@PathVariable String nickname) {
        Optional<User> userOptional = userService.getUserByNickname(nickname);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // GET: all places
    @GetMapping("/placeTypes")
    public List<PlaceTypes> getAllPlaceTypes() {
        System.out.println("Getting place types");
        return placeTypeRepository.findAll();
    }

    // Get: Getting the places within the bounds dynamically as the user moves the map
    @GetMapping("/places")
    public List<Place> getPlacesInBounds(@RequestParam double ne_lat,
                                         @RequestParam double ne_lng,
                                         @RequestParam double sw_lat,
                                         @RequestParam double sw_lng,
                                         @RequestParam Long userId) {
        System.out.println("Getting places within bounds for user: " + userId);
        return placeService.getPlacesWithinBounds(ne_lat, ne_lng, sw_lat, sw_lng, userId);
    }

    // POST: new place
    @PostMapping("/places")
    public ResponseEntity<String> createPlace(@RequestParam String namePlace,
                                              @RequestParam String descPlace,
                                              @RequestParam Long placeTypeId,
                                              @RequestParam String userNickname) {
        // place_type exist
        Optional<PlaceTypes> placeTypeOptional = placeTypeRepository.findById(placeTypeId);
        if (!placeTypeOptional.isPresent()) {
            return ResponseEntity.status(400).body("Tipo de lugar no encontrado.");
        }

        // user exist
        Optional<User> userOptional = userService.getUserByNickname(userNickname);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(400).body("Usuario no encontrado.");
        }

        // Create new place
        Place newPlace = new Place();
        newPlace.setNamePlace(namePlace);
        newPlace.setDescPlace(descPlace);
        newPlace.setPlaceType(placeTypeOptional.get());
        newPlace.setUser(userOptional.get());

        // Save on the db
        placeService.savePlace(newPlace);

        // Return success
        return ResponseEntity.status(201).body("Lugar creado con éxito.");
    }

    // POST: validate login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.getUserByNickname(loginRequest.getNickname());
        System.out.println("Solicitud recibida: " + loginRequest.getNickname());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (BCrypt.checkpw(loginRequest.getPasswd(), user.getPasswd())) {
                System.out.println("Login exitoso");

                Map<String, Object> response = new HashMap<>();
                response.put("id_user", user.getid_user());
                response.put("nickname", user.getNickname());

                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(401).build();
    }


    // GET: places by user
    @GetMapping("/places/user/{userId}")
    public ResponseEntity<List<Place>> getPlacesByUserId(@PathVariable Long userId) {
        List<Place> places = placeService.obtenerLugaresPorUsuario(userId);

        if (places.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(places);
    }
}
