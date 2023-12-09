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
                    Menu.esperar();
                    break;
                case 2:              
                    reclamarVehiclePerAEntrarAlBox();
                    Menu.esperar();
                    break;
                case 3:
                    Box box = leerBox();
                    box.pasarVehiculoDeFase();
                    break;
                case 4:
                    Box boxB = leerBox();
                    boxB.mostrar();
                    Menu.esperar();
                    break;
                case 5:
                    mostrarBoxes(false);
                    Menu.esperar();
                    break;
                case 6:
                    io.out(" Bye! 👋");
                    salir = true;
                    break;
                default:
                    break;
            }
        }
    }

    public Vehiculo recogerVehiculo() {
        String matricula = leerMatricula();
        String modelo = leerModelo();

        Menu.mostrar(TipoMenu.TIPO_VEHICULO);
        TipoVehiculo tipoVehiculo = leerTipoVehiculo();

        return new Vehiculo(matricula, modelo, tipoVehiculo);
    }

    public String leerMatricula() {
        //GestorIO gestorIO = new GestorIO();
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

    public String leerModelo() {
        //GestorIO gestorIO = new GestorIO();
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

    public void reclamarVehiclePerAEntrarAlBox() {
        if (colaInicial.estaVacia()) {
            io.out("❌ No hi ningú vehicle a la cola de espera.\n");
        } else {
            io.out("🔹 Próxim vehicle: %s\n", colaInicial.getVehiculos()[0].asString());
            mostrarBoxes(true);
            Box box = leerBox();
            if (box.estaLibre()) {
                Vehiculo vehiculoQueSaleDeLaCola = colaInicial.sacar();
                box.añadir(vehiculoQueSaleDeLaCola);
                io.out("✅ Vehicle %s afegit al box [%d].\n",
                        vehiculoQueSaleDeLaCola.asString(),
                        box.getId() + 1);
                //box.mostrar();
            } else {
                io.out("❌ El box %d está ocupat.\n", box.getId() + 1);
            }
        }
    }

    public Box leerBox() {
        int numBox;
        Interval interval = new Interval(1, NUM_BOXES);

        do {
            io.out(" ▶ Introdueix el id de box [1-%d]: ", NUM_BOXES);
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
                    io.out("Box %d\n", box.getId() + 1);
            } else
                box.mostrar();
        }
    }
}