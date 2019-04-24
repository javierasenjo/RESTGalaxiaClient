/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import Pojo.Galaxia;
import Pojo.Planeta;
import RestServices.ServiciosGalaxia;
import RestServices.ServiciosValidador;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author javie
 */
public class Main {

    String inputString, respuesta, respond;
    static String nombrePlaneta;
    static int edadPlaneta;
    static double radioPlaneta;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, java.io.IOException {
        boolean salir = false;

        while (!salir) {
            int inputNum = 0;
            String inputString, respuesta, respond;
            System.out.println("---------------------------");
            System.out.println("1. Crear galaxia");
            System.out.println("2. Obtener galaxia");
            System.out.println("3. Añadir planeta");
            System.out.println("4. Obtener planeta");
            System.out.println("5. Borrar planeta");
            System.out.println("6. Modificar planeta");
            System.out.println("7. Mostrar galaxia");
            System.out.println("8. Devolver planetas");
            System.out.println("9. Validar XSD");
            System.out.println("10. Salir");
            System.out.println("---------------------------");

            Scanner sc = new Scanner(System.in);
            respuesta = sc.nextLine();

            ServiciosGalaxia serviciosGalaxia = new ServiciosGalaxia();
            ServiciosValidador serviciosValidador = new ServiciosValidador();
            try {
                inputNum = Integer.parseInt(respuesta);
            } catch (Exception ex) {
                System.out.println("Escribeme un número anda");
            }

            switch (inputNum) {
                case 1:
                    String nombreGalaxia = null;

                    System.out.println("Escribe el nombre de la galaxia");
                    nombreGalaxia = sc.nextLine();
                    Galaxia galaxia = new Galaxia();
                    galaxia.setNombre(nombreGalaxia);
                    respuesta = serviciosGalaxia.postGalaxia(galaxia);
                    System.out.println(respuesta);
                    break;
                case 2:
                    Galaxia galaxia2 = serviciosGalaxia.getGalaxia(Galaxia.class);
                    if (galaxia2 == null) {
                        System.out.println("No hay ninguna galaxia");
                    } else {
                        System.out.println("Te han devuelto la galaxia: " + galaxia2.getNombre());
                    }
                    break;
                case 3:
                    Galaxia galaxia3 = serviciosGalaxia.getGalaxia(Galaxia.class);
                    if (galaxia3 == null) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    Planeta planeta3 = crearPlaneta();
                    respond = serviciosGalaxia.postPlaneta(planeta3);
                    System.out.println(respond);
                    break;
                case 4:
                    Galaxia galaxia4 = serviciosGalaxia.getGalaxia(Galaxia.class);
                    if (galaxia4 == null) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    List listaPlanetas4 = galaxia4.getPlanetas();
                    if (listaPlanetas4.isEmpty()) {
                        System.out.println("No hay planetas en la galaxia");
                        break;
                    }
                    System.out.println("Estos son los planetas que tienes");
                    respond = serviciosGalaxia.getPlanetasTexto();
                    System.out.println("Escribe el número del planeta");
                    System.out.println(respond);
                    respuesta = sc.nextLine();
                    try {
                        inputNum = Integer.parseInt(respuesta);
                    } catch (Exception ex) {
                        System.out.println("Escribeme un número anda");
                        break;
                    }
                    try {
                        Planeta planeta2 = serviciosGalaxia.getPlaneta(Planeta.class, respuesta);
                        System.out.println("Te han devuelto el planeta " + planeta2.getNombre());
                    } catch (Exception ex) {
                        System.out.println("Planeta no encontrado");
                        break;
                    }
                    break;
                case 5:
                    Galaxia galaxia5 = serviciosGalaxia.getGalaxia(Galaxia.class);
                    if (galaxia5 == null) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    List listaPlanetas5 = galaxia5.getPlanetas();
                    if (listaPlanetas5.isEmpty()) {
                        System.out.println("No hay planetas en la galaxia");
                        break;
                    }
                    System.out.println("Estos son los planetas que tienes");
                    respond = serviciosGalaxia.getPlanetasTexto();
                    System.out.println("Escribe el número del planeta");
                    System.out.println(respond);
                    respuesta = sc.nextLine();
                    try {
                        inputNum = Integer.parseInt(respuesta);
                    } catch (Exception ex) {
                        System.out.println("Escribeme un número anda");
                        break;
                    }
                    try {
                        String respuestaBorrar = serviciosGalaxia.deletePlaneta(respuesta);
                        System.out.println(respuestaBorrar);
                    } catch (Exception ex) {
                        System.out.println("Planeta no encontrado");
                        break;
                    }
                    break;
                case 6:
                    Galaxia galaxia6 = serviciosGalaxia.getGalaxia(Galaxia.class);

                    if (galaxia6 == null) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    List listaPlanetas6 = galaxia6.getPlanetas();
                    if (listaPlanetas6.isEmpty()) {
                        System.out.println("No hay planetas en la galaxia");
                        break;
                    }
                    System.out.println("Estos son los planetas que tienes");
                    respond = serviciosGalaxia.getPlanetasTexto();
                    System.out.println("Escribe el número del planeta que quieres cambiar");
                    System.out.println(respond);
                    respuesta = sc.nextLine();
                    try {
                        inputNum = Integer.parseInt(respuesta);
                    } catch (Exception ex) {
                        System.out.println("Escribeme un número anda");
                        break;
                    }

                    try {
                        Planeta planeta = crearPlaneta();
                        respond = serviciosGalaxia.putPlaneta(planeta, respuesta);
                        System.out.println(respond);
                    } catch (Exception ex) {
                        System.out.println("Planeta no encontrado");
                        break;
                    }
                    break;

                case 7:
                    Galaxia galaxia7 = serviciosGalaxia.getGalaxia(Galaxia.class);
                    if (galaxia7 == null) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    respond = serviciosGalaxia.getPlanetasTexto();
                    System.out.println(respond);

                    break;
                case 8:
                    List<Planeta> listaPlanetas8 = serviciosGalaxia.getPlanetas(List.class);
                    System.out.println(listaPlanetas8.toString());
                    break;
                case 9:
                    System.out.println("¿Qué fichero quieres comprobar? (sin .xml)");
                    System.out.println("Un ejemplo preparado es galaxiaXSD o planetaXSD");
                    System.out.println("También puedes probar un ejemplo no válido llamado galaxiaMal");
                    String ficheroXml = sc.nextLine();
                    try {
                        BufferedReader reader;
                        reader = new BufferedReader(new FileReader("xml/" + ficheroXml + ".xml"));
                        String line = reader.readLine();
                        String contenidoFichero = "";
                        while (line != null) {
                            contenidoFichero += line;
                            // read next line
                            line = reader.readLine();
                        }
                        reader.close();
                        System.out.println(serviciosValidador.postValidarXSD(contenidoFichero));
                    } catch (Exception ex) {
                        System.out.println("No existe ese fichero");
                    }
                    break;
                case 10:
                    salir = true;
                    break;
            }

        }

    }

    public static Planeta crearPlaneta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cómo se llama el planeta?");
        nombrePlaneta = sc.nextLine();
        System.out.println("¿Cuál es la edad del planeta?");
        try {
            edadPlaneta = Integer.parseInt(sc.nextLine());
        } catch (Exception ex) {
            System.out.println("Escribe un número");
        }
        System.out.println("¿Cuál es el radio del planeta?");
        try {
            radioPlaneta = Double.parseDouble(sc.nextLine());
        } catch (Exception ex) {
            System.out.println("Escribe un número");
        }
        Planeta planeta = new Planeta();
        planeta.setNombre(nombrePlaneta);
        planeta.setEdad(edadPlaneta);
        planeta.setRadio(radioPlaneta);
        return planeta;
    }

}
