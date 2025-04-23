package MisEspaciosApi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"place_types\"")
public class PlaceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type")
    private Long id_type;

    @Column(name = "name_type", length = 50)
    private String name_type;

    @Column(name = "icon", length = 50)
    private String icon;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = true) // Asegura la FK
    private User id_user;

    // Getters y Setters
    public Long getid_type() { return id_type; }
    public void setid_type(Long id_type) { this.id_type = id_type; }

    public String getname_type() { return name_type; }
    public void setname_type(String name_type) { this.name_type = name_type; }
}
