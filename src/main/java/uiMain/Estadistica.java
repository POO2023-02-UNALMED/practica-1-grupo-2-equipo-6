package uiMain;

import java.util.Scanner;

import gestorAplicacion.entidad.producto.Producto;
import uiMain.utilidades.Validaciones;

public class Estadistica extends Validaciones {
	private static Scanner scanner;

	public static void interfaz(Scanner scanner) {
		Estadistica.scanner = scanner;

		generales:do {
			System.out.println(getOpciones());
			int opcion;
			try {
				opcion = validarOpcionMenu(scanner.nextLine(), 1, 4);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}

			switch (opcion) {
			case 1:
				menuEstadisticasGenerales();
				continue;
			case 2:
				
			case 3:
				break generales;		
			default:
				System.out.println("Ha elegido una opción invalida.");
			}
		} while (true);

	}

	private static void menuEstadisticasGenerales() {
		estadisticas:do {
			System.out.println("Bienvenido a las estadisticas generales del E-commerce, ingrese el numero de la estadistica que desea visualizar");
			System.out.println(opcionesEstadistica());
			int opcion;
			try {
				opcion = validarOpcionMenu(scanner.nextLine(), 1, 5);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				continue;
			}

			switch (opcion) {
			case 1:
				totalVentas();
				
				break estadisticas;
			case 2:
				System.out.println("estadisticas");
			case 5:
				break estadisticas;
			default:
				System.out.println("Ha elegido una opción invalida.");
			}
		} while (true);

	}
	private static void totalVentas() {
		float ventas=Producto.ventasTotales();
		System.out.println("El e-commerce genero %s".formatted(ventas));
		
	}

	private static String opcionesEstadistica() {
		// TODO Auto-generated method stub
		return 
		"1. Total ventas e-commerce\n"+
		"2. Total devoluciones";
	}

	// TODO Auto-generated method stub

	private static void productoMasVendido() {
		// TODO Auto-generated method stub
		Producto producto = Producto.productoMasVendido();
		System.out.println("El producto mas vendido es %s que pertenece a la categoria %s "
				.formatted(producto.getNombre(), producto.getCategoria()));
	}

	private static String getOpciones() {
		// TODO Auto-generated method stub
		return "1. Estadisticas generales";
	}
}