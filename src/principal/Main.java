/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import Pojo.Galaxia;
import Pojo.ListaPlanetas;
import Pojo.Planeta;
import Pojo.Usuario;
import RestServices.ServiciosGalaxia;
import RestServices.ServiciosLogin;
import RestServices.ServiciosValidador;
import RestServices.ServiciosRegistrarse;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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
            System.out.println("---------------------------------------------------");
            System.out.println("1. Registrarse      --------  2. Iniciar Sesión");
            System.out.println("3. Crear galaxia    --------  8. Modificar planeta");
            System.out.println("4. Obtener galaxia  --------  9. Mostrar galaxia");
            System.out.println("5. Añadir planeta   --------  10. Devolver planetas");
            System.out.println("6. Obtener planeta  --------  11. Validar XSD");
            System.out.println("7. Borrar planeta   --------  12. Salir");
            System.out.println("---------------------------------------------------");

            Scanner sc = new Scanner(System.in);
            respuesta = sc.nextLine();

            ServiciosGalaxia serviciosGalaxia = new ServiciosGalaxia();
            ServiciosValidador serviciosValidador = new ServiciosValidador();
            ServiciosRegistrarse serviciosRegistrarse = new ServiciosRegistrarse();
            ServiciosLogin serviciosLogin = new ServiciosLogin();
            try {
                inputNum = Integer.parseInt(respuesta);
            } catch (Exception ex) {
                System.out.println("Escribeme un número anda");
            }

            switch (inputNum) {
                case 1:
                    System.out.println("Escribe el nombre de usuario");
                    String nombre = sc.nextLine();
                    System.out.println("Escribe el la contraseña");
                    String contraseña = sc.nextLine();
                    Usuario usuario = new Usuario();
                    usuario.setNombre(nombre);
                    usuario.setPassword(contraseña);
                    respond = serviciosRegistrarse.signup(usuario);
                    System.out.println(respond);
                    break;
                case 2:
                    System.out.println("Escribe el nombre de usuario");
                    String nombre2 = sc.nextLine();
                    System.out.println("Escribe el la contraseña");
                    String contraseña2 = sc.nextLine();
                    Usuario usuario2 = new Usuario();
                    usuario2.setNombre(nombre2);
                    usuario2.setPassword(contraseña2);
                    respond = serviciosLogin.loginear(usuario2);
                    System.out.println("Tu token es " + respond);
                    break;
                case 3:
                    String nombreGalaxia = null;

                    System.out.println("Escribe el nombre de la galaxia");
                    nombreGalaxia = sc.nextLine();
                    Galaxia galaxia = new Galaxia();
                    Galaxia galaxiaNueva = new Galaxia();
                    galaxia.setNombre(nombreGalaxia);
                    galaxiaNueva = serviciosGalaxia.postGalaxia(galaxia, Galaxia.class);
                    System.out.println(respuesta);
                    break;
                case 4:
                    Galaxia galaxia4 = serviciosGalaxia.getGalaxia(Galaxia.class);
                    if (galaxia4 == null) {
                        System.out.println("No hay ninguna galaxia");
                    } else {
                        System.out.println("Te han devuelto la galaxia: " + galaxia4.getNombre());
                    }
                    break;
                case 5:
                    Galaxia galaxia5 = serviciosGalaxia.getGalaxia(Galaxia.class);
                    if (galaxia5 == null) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    Planeta planeta5 = crearPlaneta();
                    Planeta planetaNuevo = serviciosGalaxia.postPlaneta(planeta5, Planeta.class);
                    System.out.println(planetaNuevo);
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
                case 7:
                    Galaxia galaxia7 = serviciosGalaxia.getGalaxia(Galaxia.class);
                    if (galaxia7 == null) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    ArrayList<Planeta> listaPlanetas7 = (ArrayList<Planeta>) galaxia7.getPlanetas();
                    if (listaPlanetas7.isEmpty()) {
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
                       Galaxia GalaxiaNueva7 = serviciosGalaxia.deletePlaneta(Galaxia.class,respuesta);
                        System.out.println(GalaxiaNueva7);
                    } catch (Exception ex) {
                        System.out.println("Planeta no encontrado");
                        break;
                    }
                    break;
                case 8:
                    Galaxia galaxia8 = serviciosGalaxia.getGalaxia(Galaxia.class);

                    if (galaxia8 == null) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    List listaPlanetas8 = galaxia8.getPlanetas();
                    if (listaPlanetas8.isEmpty()) {
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
                        Planeta planetaNuevo8 = serviciosGalaxia.putPlaneta(planeta,Planeta.class, respuesta);
                        System.out.println(respond);
                    } catch (Exception ex) {
                        System.out.println("Planeta no encontrado");
                        break;
                    }
                    break;

                case 9:
                    Galaxia galaxia9 = serviciosGalaxia.getGalaxia(Galaxia.class);
                    if (galaxia9 == null) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    respond = serviciosGalaxia.getPlanetasTexto();
                    System.out.println(respond);

                    break;
                case 10:
                    List<Planeta> listaPlanetas10 = serviciosGalaxia.getPlanetas(ListaPlanetas.class).getPlanetas();
                    System.out.println(listaPlanetas10.toString());
                    break;
                case 11:
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
                case 12:
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
