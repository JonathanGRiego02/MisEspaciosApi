package MisEspaciosApi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"place_types\"")
public class PlaceTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idType") // Asegura que el nombre de la columna sea exacto
    private Long idType;

    @Column(name = "nameType", length = 50) // Se agrega para respetar el nombre exacto
    private String nameType;

    public PlaceTypes() {}

    // Getters y Setters
    public Long getIdType() { return idType; }
    public void setIdType(Long idType) { this.idType = idType; }

    public String getNameType() { return nameType; }
    public void setNameType(String nameType) { this.nameType = nameType; }
}
