/**
 * Autor: Manuel Alejandro Sánchez Garcia
 * Descripcioón: Taller ITV modular
 * 1º DAW Semipresencial
 */

package itv;

import itv.Menu.TipoMenu;
import itv.util.*;

public class Taller {
    private ColaInicial colaInicial;
    private static int NUM_BOXES = 6;
    private Box[] boxes;
    private GestorIO io = new GestorIO();

    public static void main(String[] args) {
        Taller taller = new Taller();
        taller.inicio();
    }

    public Taller() {
        Box[] auxBoxes = new Box[NUM_BOXES];

        io.out("\n⏳ Inicializant Taller...\n");

        for (int i = 0; i < NUM_BOXES; i++) {
            auxBoxes[i] = new Box(i);
        }

        boxes = auxBoxes;
        System.out.printf("✅ Taller initzializat amb [%d] boxes\n\n", NUM_BOXES);
    }

    /**
     * Muestra el menú principal y se encarga de actuar en base
     * a la opción seleccionada
     */
    public void inicio() {
        // Menu menu = new Menu();
        colaInicial = new ColaInicial();
        int opcionEscogida;
        boolean salir = false;

        while (!salir) {
            // menu.mostrar();
            Menu.mostrar(TipoMenu.PRINCIPAL);

            // opcionEscogida = menu.leerOpcion();
            opcionEscogida = Menu.leerOpcion();

            switch (opcionEscogida) {
                case 1:
                    colaInicial.añadir(recogerVehiculo());
                    colaInicial.mostrar();
                    break;
                case 2:              
                    reclamarVehiclePerAEntrarAlBox();
                    break;
                case 3:
                    Box box = leerBox();
                    box.pasarVehiculoDeFase();
                    break;
                case 4:
                    Box boxB = leerBox();
                    boxB.mostrar();
                    break;
                case 5:
                    mostrarBoxes(false);
                    break;
                case 6:
                    io.out(" Bye! 👋");
                    salir = true;
                    break;
                default:
                    break;
            }

            // Si no se quiere salir del programa,
            // Hacer una "pausa" antes de mostrar el menú de nuevo
            if (!salir) Menu.esperar();
        }
    }

    public Vehiculo recogerVehiculo() {
        String matricula = leerMatricula();
        String modelo = leerModelo();

        Menu.mostrar(TipoMenu.TIPO_VEHICULO);
        TipoVehiculo tipoVehiculo = leerTipoVehiculo();

        return new Vehiculo(matricula, modelo, tipoVehiculo);
    }

    /**
     * Pide la matricula y comprueba que sea del formato
     * correcto y no esté repetida
     * @return
     */
    public String leerMatricula() {
        //GestorIO io = new GestorIO();
        String matricula;

        do {
            System.out.print(" ▶ Introdueix la matricula: ");
            matricula = io.inString();

            if (!matricula.matches(Vehiculo.PATRON_MATRICULA)){
                io.out("❌ Format de matrícula no vàlid.\n");
            }
            else{
                if (colaInicial.tieneMatricula(matricula))
                    io.out("❌ Matricula repetida.\n");
                else
                    return matricula;
            }
        } while (true);
    }

    /**
     * Pide que se introduzca un modelo por teclado
     * @return El modelo introducido
     */
    public String leerModelo() {
        //GestorIO io = new GestorIO();
        String modelo;

        do {
            System.out.print(" ▶ Introdueix el model: ");
            modelo = io.inString();

            if (modelo.isBlank())
                io.out("❌ Has d'introduir un model.\n");
            else
                return modelo;
        } while (true);
    }

    public TipoVehiculo leerTipoVehiculo() {
        return TipoVehiculo.values()[Menu.leerOpcion() - 1];
    }

    /**
     * Saca un vehículo de la cola y lo introduce en el Box
     * seleccionado
     */
    public void reclamarVehiclePerAEntrarAlBox() {
        if (colaInicial.estaVacia()) {
            io.out("❌ No hi ningú vehicle a la cola de espera.\n");
        } else {
            io.out("🔹 Próxim vehicle: %s\n", colaInicial.getVehiculos()[0].asString());
            io.out("\n Boxs LLiures\n");
            io.out(" ────────────\n");
            mostrarBoxes(true); 
            Box box = leerBox();
            if (box.estaLibre()) {
                Vehiculo vehiculoQueSaleDeLaCola = colaInicial.sacar();
                box.añadir(vehiculoQueSaleDeLaCola);
                io.out("\n✅ Vehicle %s afegit al box [%d].\n",
                        vehiculoQueSaleDeLaCola.asString(),
                        box.getId() + 1);
                //box.mostrar();
            } else {
                io.out("❌ El box %d está ocupat.\n", box.getId() + 1);
            }
        }
    }

    /**
     * Pide el id de un box y devuelve el objecto Box en particular
     * @return Box seleccionado
     */
    public Box leerBox() {
        int numBox;
        Interval interval = new Interval(1, NUM_BOXES);

        do {
            //io.out(" ▶ Introdueix el id de box [1-%d]: ", NUM_BOXES);
            io.out(" ▶ Introdueix el id de box: ");
            if (!interval.inclou(numBox = io.inInt())) {
                io.out("❌ Box incorrecte\n");
            } else
                return boxes[numBox - 1];
        } while (true);
    }

    /**
     * Muestra los boxes
     * 
     * @param mostrarSoloBoxesLibres
     * {@code true} solo muestra los que están libres.
     * {@code false} los muestra todos
     */
    public void mostrarBoxes(boolean mostrarSoloBoxesLibres) {
        for (Box box : boxes) {
            if (mostrarSoloBoxesLibres) {
                if (box.estaLibre())
                    io.out(" Box %d\n", box.getId() + 1);
            } else
                box.mostrar();
        }
    }

    /**
     * Comprueba si hay algun box libre o si están todos ocupados
     * @return
     */
    public boolean hayBoxesLibres(){
        for (Box b : boxes) {
            if (b.estaLibre()) return true;
        }

        return false;
    }
}