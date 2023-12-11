package itv;

import java.util.Scanner;

import itv.util.*;

public class Menu {
    enum TipoMenu {
        PRINCIPAL,
        TIPO_VEHICULO
    }

    private static TipoMenu menuActual;

    private static final String[] TITULOS_MENU_PRINCIPAL = {
            "Alta i recepció de vehicles",
            "Reclamar vehicle per a entrar al box",
            "Moure tots els vehicles de fase dins d’un box",
            "Informació de l'estat d'un box concret",
            "Informació general de tots els boxes",
            "Eixir del programa",
    };

    private static final Interval OPCIONES_MENU_PRINCIPAL = new Interval(1, TITULOS_MENU_PRINCIPAL.length);
    private static final Interval OPCIONES_TIPO_VEHICULO = new Interval(1, TipoVehiculo.values().length);
    private static final Interval[] OPCIONES = { 
        OPCIONES_MENU_PRINCIPAL, 
        OPCIONES_TIPO_VEHICULO 
    };

    private static GestorIO gestorIO = new GestorIO();

    // Lo he hecho estatico por que no veo la necesidad de crear un objeto "menu"
    // No se si es lo más correcto.
    public static void mostrar(TipoMenu tipoMenu) {
        // Cuando se muestra un menu, se guarda el tipo de menú que se está mostrando
        // Para luego saber el criterio a seguir al leer la opción introducida
        menuActual = tipoMenu;

        switch (tipoMenu) {
            case PRINCIPAL:
                System.out.println("┌─────────────────────────────────────────┐");
                System.out.println("│            📄 MENÚ PRINCIPAL            │");
                System.out.println("└─────────────────────────────────────────┘");

                for (int i = 0; i < TITULOS_MENU_PRINCIPAL.length; i++) {
                    System.out.printf(" %d. %s\n", i + 1, TITULOS_MENU_PRINCIPAL[i]);
                }
                break;
            case TIPO_VEHICULO:
                System.out.println();
                System.out.println("Tipus de vehícle");
                System.out.println("────────────────");

                for (int i = 0; i < TipoVehiculo.values().length; i++) {
                    System.out.printf(" %d. %s\n", i + 1, TipoVehiculo.values()[i].toString());
                }
            default:
                break;
        }
    }

    /**
     * Lee una opción introducida por teclado y la válida
     * La opción tiene que estar en el rango de opciones disponibles (TITULOS_MENU_PRINCIPAL)
     * 
     * @return
     */
    public static int leerOpcion() {
        Scanner teclado = new Scanner(System.in);
        int opcionEscogida;

        while (true) {
            System.out.print("\n ▶ Introdueix una opció: ");
            if (!teclado.hasNextInt()) {
                teclado.nextLine();
                System.out.println("❌ Valor no vàlid");
            } else {
                opcionEscogida = teclado.nextInt();
                teclado.nextLine();

                if (!OPCIONES[menuActual.ordinal()].inclou(opcionEscogida)) {
                    System.out.println("❌ Opció no vàlida");
                } else
                    return opcionEscogida;
            }
        }
    }

    /**
     * Obliga a pulsar la tecla [INTRO] para poder continuar
     */
    public static void esperar() {
        gestorIO.out("\nPulsa INTRO per a continuar...");
        gestorIO.inString();
        gestorIO.out("\n");
    }
}
