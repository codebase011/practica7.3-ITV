package itv;

public class Vehiculo {
    private String matricula;
    private String modelo;
    private TipoVehiculo tipoVehiculo;
    public static final String PATRON_MATRICULA = "\\d{4}[A-Z]{3}";

    public Vehiculo(String matricula, String modelo, TipoVehiculo tipoVehiculo){
        this.matricula = matricula;
        this.modelo = modelo;
        this.tipoVehiculo = tipoVehiculo;
    }

    public boolean tieneEsta(String matricula){
        return true;
    }

    public String toString(){
        return "";
    }

    public String getMatricula(){
        return this.matricula;
    }

    public String getModelo(){
        return this.modelo;
    }

    public TipoVehiculo getTipoVehiculo(){
        return this.tipoVehiculo;
    }

    public String asString(){
        return String.format("Vehicle de tipus [%s], model [%s] amb matricula [%s]", tipoVehiculo, modelo, matricula);
    }

    public static TipoVehiculo tipoSegunIndice(int indice){
        return TipoVehiculo.values()[indice];
    }
}
