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
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = true)
    private User user; // Fixed field name

    // Getters and Setters
    public Long getId_type() { return id_type; }
    public void setId_type(Long id_type) { this.id_type = id_type; }

    public String getName_type() { return name_type; }
    public void setName_type(String name_type) { this.name_type = name_type; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
