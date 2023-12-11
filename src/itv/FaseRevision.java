package itv;

import itv.util.GestorIO;

public class FaseRevision {
    private GestorIO io = new GestorIO();
    private String nombre;
    private Vehiculo vehiculo;

    public FaseRevision(String nombre) {
        this.nombre = nombre;
        //this.vehiculo = vehiculo;
    }

    /**
     * Comprueba si esta fase tiene algún vehículo asignado
     * @return true si tiene vehículo asignado (!null) /
     * false en caso contrario
     */
    public boolean tieneVehiculo(){
        if (vehiculo != null)
            return true;

        return false;
    }

    public void asignarVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }

    public void asignarVehiculoA(FaseRevision faseRevision){
        //this = faseRevision;
    }

    public void desasignarVehiculo(){
        vehiculo = null;
    }

    public void mostrar(){
        io.out(this.nombre + "\n");

        if (this.tieneVehiculo())
            io.out("🔴 Vehicle de tipus [%s] model [%s] amb matricula [%s].\n",
                vehiculo.getTipoVehiculo(),
                vehiculo.getModelo(),
                vehiculo.getMatricula());
        else
            io.out("🔵 Ningú vehicle\n");
    }

    /**
     * Devuelve el vehiculo actual en esta fase de revisión (A.)
     * @return
     */
    public Vehiculo getVehiculo(){
        return vehiculo;
    }
}
