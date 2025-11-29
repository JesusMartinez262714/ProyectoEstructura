package Estudiantes.Modelo;

public class Direccion {
    private String calle;
    private String numero;
    private String colonia;
    private String ciudad;

    public Direccion(String calle, String numero, String colonia, String ciudad) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getColonia() {
        return colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Override
    public String toString() {
        return calle + " " + numero + ", " + colonia + ", " + ciudad;
    }

}
