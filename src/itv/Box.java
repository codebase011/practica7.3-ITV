package itv;

import itv.util.GestorIO;

public class Box {
    private static String[] TITULOS_FASES = {
        "1. Revisió inicial d'elements de seguretat",
        "2. Revisió del sistema elèctric",
        "3. Revisió d'emissió de fums",
        "4. Revisió de frens i direcció",
    };
    private static int NUM_FASES = 4;
    private GestorIO io = new GestorIO();
    private int id;
    private FaseRevision[] fasesRevision;

    /**
     * Inicializa un box con un id.
     * Inicializa cada fase con su titulo.
     * @param id
     */
    public Box(int id){
        this.id = id;

        FaseRevision[] fr = new FaseRevision[NUM_FASES];

        for (int i = 0; i < NUM_FASES; i++){
            fr[i] = new FaseRevision(TITULOS_FASES[i]);
        }

        fasesRevision = fr;
    }

    /**
     * Comprueba si hay algún vehículo en la primera fase de revisión
     * @return true si no hay ningún vehículo
     */
    public boolean estaLibre() {
        if (!this.fasesRevision[0].tieneVehiculo())
            return true;
        
        return false;
    }

    // No usada
/*     public boolean esPrimeraFase(int indexFase){

    } */

    public void añadir(Vehiculo vehiculo){
        fasesRevision[0].asignarVehiculo(vehiculo);
    }

    /**
     * Pasa los vehiculos de una fase a la siguiente.
     * Si la ultima fase (fase 4 (elemento 3 de array))
     * tiene un vehiculo, se informa de que ha terminado
     * la revisión. 
     */
    public void pasarVehiculoDeFase(){
        io.out("✅ Pasant vehicles de fase\n");

        // Si la última fase tiene vehículo asignado, informar
        // de que ha terminado la revisión
        if (fasesRevision[NUM_FASES-1].tieneVehiculo()){
            io.out("\n✅ %s terminada revision\n\n", fasesRevision[NUM_FASES-1].getVehiculo().asString());
        }    

        // Mover el resto de los vehiculos de fase
        for (int i = NUM_FASES - 2; i >= 0; i--){
            fasesRevision[i+1].asignarVehiculo(fasesRevision[i].getVehiculo());
            fasesRevision[i].desasignarVehiculo();
        }
    }

    public void mostrar() {
        io.out("┌─────────────────────────────────────────┐\n");
        io.out( "│               🏭 Box [%d]                │\n", this.id + 1);
        io.out("└─────────────────────────────────────────┘\n");
        
        for (int i = 0; i < NUM_FASES; i++){
            //io.out("Fase %d. %s\n", i + 1, TITULOS_FASES[i]);
            fasesRevision[i].mostrar();     
        }
    }

    public int getId() {
        return this.id;
    }
}
