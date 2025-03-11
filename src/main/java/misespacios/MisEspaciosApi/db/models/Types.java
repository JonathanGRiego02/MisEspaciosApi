package misespacios.MisEspaciosApi.db.models;

public class Types {

    private int idType;
    private String nameType;

    public Types() {}
    
    public Types(int idType, String nameType) {
        this.idType = idType;
        this.nameType = nameType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }
}
