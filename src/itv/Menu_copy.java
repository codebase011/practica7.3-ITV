package itv;

import java.util.Scanner;

import itv.util.*;

public class Menu_copy {
    private static final String[] TITULOS = {
            "Alta i recepció de vehicles",
            "Reclamar vehicle per a entrar al box",
            "Moure tots els vehicles de fase dins d’un box",
            "Informació de l'estat d'un box concret",
            "Informació general de tots els boxes",
            "Eixir del programa"
    };

    private static final Interval OPCIONES = new Interval(1, TITULOS.length);
    private static final Interval OPCIONES_TIPO_VEHICULO = new Interval(1, TipoVehiculo.values().length);

    private static GestorIO gestorIO = new GestorIO();

    // Lo he hecho estatico por que no veo la necesidad de crear un objeto "menu"
    // No se si es lo más correcto.
    public static void mostrar() {
        // System.out.println();
        System.out.println("┌──────────────────────┐");
        System.out.println("│ 📑 MENÚ PRINCIPAL    │");
        // System.out.println("│ Selecciona una opció │");
        System.out.println("└──────────────────────┘");

        for (int i = 0; i < TITULOS.length; i++) {
            System.out.printf(" %d. %s\n", i + 1, TITULOS[i]);
        }
    }

    public static void mostrarTiposVehiculo() {
        System.out.println();
        System.out.println("Tipus de vehícle");
        System.out.println("────────────────");

        for (int i = 0; i < TipoVehiculo.values().length; i++) {
            System.out.printf("%d. %s\n", i + 1, TipoVehiculo.values()[i].toString());
        }
    }

    /**
     * Lee una opción introducida por teclado y la válida
     * La opción tiene que estar en el rango de opciones disponibles (TITULOS)
     * 
     * @return
     */
    public static int leerOpcion() {
        Scanner teclado = new Scanner(System.in);
        int opcionEscogida;

        System.out.print("\n▶ Introdueix una opció: ");

        while (true) {
            if (!teclado.hasNextInt()) {
                teclado.nextLine();
                System.out.println("❌ Opció no vàlida");
                System.out.print("▶  Introdueix una opció: ");
            } else {
                opcionEscogida = teclado.nextInt();
                teclado.nextLine();

                if (!OPCIONES.inclou(opcionEscogida)) {
                    // if (opcionEscogida < 1 || opcionEscogida > TITULOS.length)
                    System.out.println("❌ Opció no vàlida");
                    System.out.print("▶  Introdueix una opció: ");
                } else
                    return opcionEscogida;
            }
        }
    }

    /**
     * Lee una opción introducida por teclado y la válida
     * La opción tiene que estar en el rango de opciones disponibles (TITULOS)
     * 
     * @return
     */
    public static int leerOpcionTipoVehiculo() {
        Scanner teclado = new Scanner(System.in);
        int opcionEscogida;

        System.out.print("▶ Introdueix una opció: ");

        while (true) {
            if (!teclado.hasNextInt()) {
                teclado.nextLine();
                System.out.println("❌ Opció no vàlida");
                System.out.print("▶  Introdueix una opció: ");
            } else {
                opcionEscogida = teclado.nextInt();
                teclado.nextLine();

                if (!OPCIONES_TIPO_VEHICULO.inclou(opcionEscogida)) {
                    // if (opcionEscogida < 1 || opcionEscogida > TITULOS.length)
                    System.out.println("❌ Opció no vàlida");
                    System.out.print("▶  Introdueix una opció: ");
                } else
                    return opcionEscogida;
            }
        }
    }

    public static void esperar(){
        gestorIO.out("Pulsa INTRO per a continuar...");
        gestorIO.inString();
    }
}
