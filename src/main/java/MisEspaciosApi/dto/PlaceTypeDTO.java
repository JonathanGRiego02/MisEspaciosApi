package MisEspaciosApi.dto;

public class PlaceTypeDTO {
  private Long id_type;
  private String name_type;
  private String icon;
  private Long id_user;

  // Constructor
  public PlaceTypeDTO(Long id_type, String name_type, String icon, Long id_user) {
    this.id_type = id_type;
    this.name_type = name_type;
    this.icon = icon;
    this.id_user = id_user;
  }

  // Getters y Setters
  public Long getId_type() { return id_type; }
  public void setId_type(Long id_type) { this.id_type = id_type; }

  public String getName_type() { return name_type; }
  public void setName_type(String name_type) { this.name_type = name_type; }

  public String getIcon() { return icon; }
  public void setIcon(String icon) { this.icon = icon; }

  public Long getId_user() { return id_user; }
  public void setId_user(Long id_user) { this.id_user = id_user; }
}
