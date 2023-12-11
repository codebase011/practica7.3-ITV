package itv;

import itv.util.GestorIO;

public class ColaInicial {
    private Vehiculo[] vehiculos;
    private GestorIO io = new GestorIO();

    public ColaInicial() {

    }

    /**
     * Añade un Vehiculo a la cola.
     * Si es el primer vehiculo que entra a la cola (vehiculos == null)
     * se inicializa el array.
     * Si ya hay vehiculos en el array, se copia el array a un array temporal con un
     * elemento más y se vuelve a asignar al array "vehiculos".
     * 
     * @param vehiculo
     */
    public void añadir(Vehiculo vehiculo) {
        if (vehiculos == null) {
            vehiculos = new Vehiculo[] { vehiculo };
        } else {
            Vehiculo nuevosVehiculos[] = new Vehiculo[vehiculos.length + 1];

            for (int i = 0; i < vehiculos.length; i++) {
                nuevosVehiculos[i] = vehiculos[i];
            }
            nuevosVehiculos[vehiculos.length] = vehiculo;

            vehiculos = nuevosVehiculos;
        }

        io.out("\n✅ %s afegit en la posició [%d] de la cua.\n\n",
                vehiculo.asString(),
                vehiculos.length);
    }

    /**
     * Saca el primer vehículo de la cola.
     * Se crea un array auxliar del tamaño actual de vehiculos[] - 1
     * y se añaden todos los vehiculos actuales en el array menos el primero
     * Se reasigna al array vehiculos de la cola.
     * 
     * @return
     *         El vehiculo que se saca de la cola.
     */
    public Vehiculo sacar() {
        Vehiculo vehiculoQueSale;

        vehiculoQueSale = vehiculos[0];
        // Comprobar que no es el ultimo vehiculo que va a salir
        if (vehiculos.length > 1) {
            Vehiculo auxVehiculos[] = new Vehiculo[vehiculos.length - 1];

            for (int i = 0; i < vehiculos.length - 1; i++) {
                auxVehiculos[i] = vehiculos[i + 1];
            }

            vehiculos = auxVehiculos;
        // Si no queda ningún vehiculo, se pone el array a null
        } else {
            vehiculos = null;
        }

        return vehiculoQueSale;
    }

    public boolean estaVacia() {
        return vehiculos == null || vehiculos.length == 0;
    }

    public void mostrar() {
        io.out("┌─────────────────────────────────────────┐\n");
        io.out("│              📄 Cúa Actual              │\n");
        io.out("└─────────────────────────────────────────┘\n");

        for (int i = 0; i < vehiculos.length; i++){
            io.out("%d. %s\n", i + 1, vehiculos[i].asString());
        }
    }

    public boolean tieneMatricula(String matricula) {
        if (!estaVacia()) {
            for (Vehiculo v : vehiculos) {
                if (v.getMatricula().equals(matricula))
                    return true;
            }
        }

        return false;
    }

    public Vehiculo[] getVehiculos(){
        return vehiculos;
    }
}
