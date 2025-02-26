package misespacios.MisEspaciosApi.db.models;

public class Lugar {
    private int idLugar;
    private String nameLugar;
    private String descLugar;
    private int idUsuario;
    private int idType;
    private double posX;
    private double posY;

    public Lugar() {}

    public Lugar(int idLugar, String nameLugar, String descLugar, int idUsuario, int idType, double posX, double posY) {
        this.idLugar = idLugar;
        this.nameLugar = nameLugar;
        this.descLugar = descLugar;
        this.idUsuario = idUsuario;
        this.idType = idType;
        this.posX = posX;
        this.posY = posY;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getNameLugar() {
        return nameLugar;
    }

    public void setNameLugar(String nameLugar) {
        this.nameLugar = nameLugar;
    }

    public String getDescLugar() {
        return descLugar;
    }

    public void setDescLugar(String descLugar) {
        this.descLugar = descLugar;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
}
