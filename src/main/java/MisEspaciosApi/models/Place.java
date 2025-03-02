package MisEspaciosApi.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "\"places\"")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlace") // Asegura que se use el nombre exacto de la tabla SQL
    private Long idPlace;

    @Column(name = "namePlace", length = 50)
    private String namePlace;

    @Column(name = "descPlace", length = 250)
    private String descPlace;

    @Column(name = "posX", precision = 9, scale = 6)
    private BigDecimal posX;

    @Column(name = "posY", precision = 9, scale = 6)
    private BigDecimal posY;

    @Column(name = "likes")
    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser", nullable = true) // Asegura la FK
    private User user;

    @ManyToOne
    @JoinColumn(name = "idType", referencedColumnName = "idType", nullable = true) // Asegura la FK
    private PlaceTypes placeTypes;

    public Place() {}

    // Getters y Setters
    public Long getIdPlace() { return idPlace; }
    public void setIdPlace(Long idPlace) { this.idPlace = idPlace; }

    public String getNamePlace() { return namePlace; }
    public void setNamePlace(String namePlace) { this.namePlace = namePlace; }

    public String getDescPlace() { return descPlace; }
    public void setDescPlace(String descPlace) { this.descPlace = descPlace; }

    public BigDecimal getPosX() { return posX; }
    public void setPosX(BigDecimal posX) { this.posX = posX; }

    public BigDecimal getPosY() { return posY; }
    public void setPosY(BigDecimal posY) { this.posY = posY; }

    public Integer getLikes() { return likes; }
    public void setLikes(Integer likes) { this.likes = likes; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public PlaceTypes getPlaceType() { return placeTypes; }
    public void setPlaceType(PlaceTypes placeTypes) { this.placeTypes = placeTypes; }
}
