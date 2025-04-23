package MisEspaciosApi.dto;

import MisEspaciosApi.models.PlaceType;

import java.math.BigDecimal;

public class PlaceDTO {
    private Long idPlace;
    private String username;
    private String namePlace;
    private String descPlace;
    private BigDecimal pos_x;
    private BigDecimal pos_y;
    private Integer likes;
    private String image;
    private boolean isPrivate;
    private PlaceType placeType;

    public PlaceDTO(Long idPlace, String namePlace, String descPlace,
                    BigDecimal pos_x, BigDecimal pos_y, Integer likes, String image,
                    boolean isPrivate, PlaceType placeType, String username) {
        this.idPlace = idPlace;
        this.namePlace = namePlace;
        this.descPlace = descPlace;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.likes = likes;
        this.image = image;
        this.isPrivate = isPrivate;
        this.placeType = placeType;
        this.username = username;
    }

    // Getters and setters...


    public Long getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Long idPlace) {
        this.idPlace = idPlace;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }

    public String getDescPlace() {
        return descPlace;
    }

    public void setDescPlace(String descPlace) {
        this.descPlace = descPlace;
    }

    public BigDecimal getPos_x() {
        return pos_x;
    }

    public void setPos_x(BigDecimal pos_x) {
        this.pos_x = pos_x;
    }

    public BigDecimal getPos_y() {
        return pos_y;
    }

    public void setPos_y(BigDecimal pos_y) {
        this.pos_y = pos_y;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }
}
