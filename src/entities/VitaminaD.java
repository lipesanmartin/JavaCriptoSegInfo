package entities;

public class VitaminaD {
    Integer id;
    String ngml;

    public VitaminaD() {
    }

    public VitaminaD(Integer id, String ngml) {
        this.id = id;
        this.ngml = ngml;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNgml() {
        return ngml;
    }

    public void setNgml(String ngml) {
        this.ngml = ngml;
    }
}
