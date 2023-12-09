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

    public boolean esPrimeraFase(int indexFase){

    }

    public void añadir(Vehiculo vehiculo){
        fasesRevision[0].asignarVehiculo(vehiculo);
    }

    public void pasarVehiculoDeFase(){
        io.out("pasando vehiculos de fase\n");

        for (int i = NUM_FASES - 1; i >= 0; i--){
            // Si estoy en la ultima fase y hay un vehículo
            // informar del vehiulo que sale
            if (i == NUM_FASES - 1){
                if (fasesRevision[i].tieneVehiculo()){
                    io.out("\n✅ %s terminada revision\n\n", fasesRevision[i].getVehiculo().asString());
                }
            }
            // Si no, mover el vehiculo a la siguiente fase
            else{
                fasesRevision[i+1].asignarVehiculo(fasesRevision[i].getVehiculo());
                fasesRevision[i].desasignarVehiculo();
            }
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
