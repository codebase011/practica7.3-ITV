package itv;

public enum TipoVehiculo {
    COCHE("🚗 Cotxe"),
    FURGONETA("🚐 Furgoneta"),
    MICROBUS("🚎 Microbus"),
    CAMION("🚛 Camió"),
    ALTRE(".. Altre");

    private String nombre;

    private TipoVehiculo(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){
        return this.nombre;
    }

    public static boolean existe(String tipo){
        for (TipoVehiculo tv : TipoVehiculo.values()){
            if (tv.name().equals(tipo)) return true;
        }

        return false;
    }

    public static void mostrarTiposVehiculo() {
/*         for (TipoVehiculo tipo : TipoVehiculo.values()){
            System.out.println(tipo);
        } */

  /*       for (int i = 0; i < TipoVehiculo.values().length; i++){
            System.out.printf("%d. %s\n", i + 1, TipoVehiculo.values()[i]);  
        } */
    }
}
