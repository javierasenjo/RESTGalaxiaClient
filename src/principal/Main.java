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
import RestServices.ServiciosLogout;
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
    static String token = "";
    static boolean tokenValidado = false;
    static boolean galaxiaExiste = false;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, java.io.IOException {
        boolean salir = false;

        while (!salir) {
            int inputNum = 0;
            String inputString, respuesta, respond;

            System.out.println("-----------------------------------------------------------");
            System.out.println("1. Registrarse              --------  7.  Borrar planeta");
            System.out.println("2. Iniciar Sesión           --------  8.  Modificar planeta");
            System.out.println("3. Crear galaxia            --------  9.  Mostrar galaxia");
            System.out.println("4. Obtener nombre galaxia   --------  10. Devolver planetas");
            System.out.println("5. Añadir planeta           --------  11. Validar XSD");
            System.out.println("6. Obtener planeta          --------  12. Cerrar sesión");
            System.out.println("------------------------ 13. Salir ------------------------");
            System.out.println("-----------------------------------------------------------");

            Scanner sc = new Scanner(System.in);
            respuesta = sc.nextLine();

            ServiciosGalaxia serviciosGalaxia = new ServiciosGalaxia();
            ServiciosValidador serviciosValidador = new ServiciosValidador();
            ServiciosRegistrarse serviciosRegistrarse = new ServiciosRegistrarse();
            ServiciosLogin serviciosLogin = new ServiciosLogin();
            ServiciosLogout serviciosLogout = new ServiciosLogout();
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
                    if (respond.equals("")) {
                        System.out.println("Ha habido un problema al iniciar sesión, por favor inténtelo de nuevo");
                    } else {
                        System.out.println("Tu token es " + respond);
                        token = respond;
                    }
                    break;
                case 3:
                    if (!comprobarToken()) {
                        System.out.println("No has obtenido un token, por favor inicia sesión");
                        break;
                    }
                    Galaxia galaxia3 = serviciosGalaxia.getGalaxia(Galaxia.class, token);
//                    if (galaxia3 != null) {
//                        System.out.println("Ya yienes una galaxia creada");
//                        break;
//                    }
                    String nombreGalaxia = null;

                    System.out.println("Escribe el nombre de la galaxia");
                    nombreGalaxia = sc.nextLine();
                    Galaxia galaxia = new Galaxia();
                    Galaxia galaxiaNueva = new Galaxia();
                    galaxia.setNombre(nombreGalaxia);
                    galaxiaNueva = serviciosGalaxia.postGalaxia(galaxia, Galaxia.class, token);
                    System.out.println(galaxiaNueva.toString());
                    break;
                case 4:
                    if (!comprobarToken()) {
                        System.out.println("No has obtenido un token, por favor inicia sesión");
                        break;
                    }
                    Galaxia galaxia4 = serviciosGalaxia.getGalaxia(Galaxia.class, token);
                    if (comprobarGalaxia(galaxia4) == false) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    } else {
                        System.out.println("Te han devuelto la galaxia: " + galaxia4.getNombre());
                    }
                    break;
                case 5:
                    if (!comprobarToken()) {
                        System.out.println("No has obtenido un token, por favor inicia sesión");
                        break;
                    }
                    Galaxia galaxia5 = serviciosGalaxia.getGalaxia(Galaxia.class, token);
                    if (comprobarGalaxia(galaxia5) == false) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    try {
                        Planeta planeta5 = crearPlaneta();
                        Planeta planetaNuevo = serviciosGalaxia.postPlaneta(planeta5, Planeta.class, token);
                        System.out.println(planetaNuevo);
                    } catch (Exception ex) {
                        System.out.println("Los datos del planeta no tienen sentido");
                    }

                    break;
                case 6:
                    if (!comprobarToken()) {
                        System.out.println("No has obtenido un token, por favor inicia sesión");
                        break;
                    }
                    Galaxia galaxia6 = serviciosGalaxia.getGalaxia(Galaxia.class, token);
                    if (comprobarGalaxia(galaxia6) == false) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    List listaPlanetas6 = galaxia6.getPlanetas();
                    if (listaPlanetas6.isEmpty()) {
                        System.out.println("No hay planetas en la galaxia");
                        break;
                    }
                    System.out.println("Estos son los planetas que tienes");
                    ListaPlanetas listPlanetas6 = (ListaPlanetas) serviciosGalaxia.getPlanetas(ListaPlanetas.class, token);
                    System.out.println(listPlanetas6.toString());
                    System.out.println("Escribe el número del planeta");
                    respuesta = sc.nextLine();
                    try {
                        inputNum = Integer.parseInt(respuesta);
                    } catch (Exception ex) {
                        System.out.println("Escribeme un número anda");
                        break;
                    }
                    try {
                        Planeta planeta2 = serviciosGalaxia.getPlaneta(Planeta.class, respuesta, token);
                        System.out.println("Te han devuelto el planeta " + planeta2.getNombre());
                    } catch (Exception ex) {
                        System.out.println("Planeta no encontrado");
                        break;
                    }
                    break;
                case 7:
                    if (!comprobarToken()) {
                        System.out.println("No has obtenido un token, por favor inicia sesión");
                        break;
                    }
                    Galaxia galaxia7 = serviciosGalaxia.getGalaxia(Galaxia.class, token);
                    if (comprobarGalaxia(galaxia7) == false) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    ArrayList<Planeta> listaPlanetas7 = (ArrayList<Planeta>) galaxia7.getPlanetas();
                    if (listaPlanetas7.isEmpty()) {
                        System.out.println("No hay planetas en la galaxia");
                        break;
                    }
                    System.out.println("Estos son los planetas que tienes");
                    ListaPlanetas listPlanetas7 = (ListaPlanetas) serviciosGalaxia.getPlanetas(ListaPlanetas.class, token);
                    System.out.println(listPlanetas7.toString());
                    System.out.println("Escribe el número del planeta");
                    respuesta = sc.nextLine();
                    try {
                        inputNum = Integer.parseInt(respuesta);
                    } catch (Exception ex) {
                        System.out.println("Escribeme un número anda");
                        break;
                    }
                    try {
                        Galaxia GalaxiaNueva7 = serviciosGalaxia.deletePlaneta(Galaxia.class, respuesta, token);
                        System.out.println(GalaxiaNueva7);
                    } catch (Exception ex) {
                        System.out.println("Planeta no encontrado");
                        break;
                    }
                    break;
                case 8:
                    if (!comprobarToken()) {
                        System.out.println("No has obtenido un token, por favor inicia sesión");
                        break;
                    }
                    Galaxia galaxia8 = serviciosGalaxia.getGalaxia(Galaxia.class, token);

                    if (comprobarGalaxia(galaxia8) == false) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    List listaPlanetas8 = galaxia8.getPlanetas();
                    if (listaPlanetas8.isEmpty()) {
                        System.out.println("No hay planetas en la galaxia");
                        break;
                    }
                    System.out.println("Estos son los planetas que tienes");
                    ListaPlanetas listPlanetas8 = (ListaPlanetas) serviciosGalaxia.getPlanetas(ListaPlanetas.class, token);
                    System.out.println(listPlanetas8.toString());
                    System.out.println("Escribe el número del planeta que quieres cambiar");
                    respuesta = sc.nextLine();
                    try {
                        inputNum = Integer.parseInt(respuesta);
                    } catch (Exception ex) {
                        System.out.println("Escribeme un número anda");
                        break;
                    }

                    try {
                        Planeta planeta = crearPlaneta();
                        Planeta planetaNuevo8 = serviciosGalaxia.putPlaneta(planeta, Planeta.class, respuesta, token);
                        System.out.println("Planeta " + planetaNuevo8.getNombre() + " modificado con éxito");
                    } catch (Exception ex) {
                        System.out.println("Planeta no encontrado");
                        break;
                    }
                    break;

                case 9:
                    if (comprobarToken() == false) {
                        System.out.println("No has obtenido un token, por favor inicia sesión");
                        break;
                    }
                    Galaxia galaxia9 = serviciosGalaxia.getGalaxia(Galaxia.class, token);
                    if (comprobarGalaxia(galaxia9) == false) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    respond = serviciosGalaxia.getPlanetasTexto(token);
                    System.out.println(respond);

                    break;
                case 10:
                    if (!comprobarToken()) {
                        System.out.println("No has obtenido un token, por favor inicia sesión");
                        break;
                    }
                    Galaxia galaxia10 = serviciosGalaxia.getGalaxia(Galaxia.class, token);
                    if (comprobarGalaxia(galaxia10) == false) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    ListaPlanetas listaPlanetas10 = (ListaPlanetas) serviciosGalaxia.getPlanetas(ListaPlanetas.class, token);
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
                    if (!comprobarToken()) {
                        System.out.println("No has obtenido un token, por favor inicia sesión");
                        break;
                    }
                    Galaxia galaxia12 = serviciosGalaxia.getGalaxia(Galaxia.class, token);
                    if (comprobarGalaxia(galaxia12) == false) {
                        System.out.println("No hay una galaxia creada");
                        break;
                    }
                    respuesta = serviciosLogout.cerrarSesion(token);
                    token = "";
                    System.out.println(respuesta);
                    break;
                case 13:
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
        if (nombrePlaneta == "" || edadPlaneta == 0 || radioPlaneta == 0) {
            return null;
        }
        Planeta planeta = new Planeta();
        planeta.setNombre(nombrePlaneta);
        planeta.setEdad(edadPlaneta);
        planeta.setRadio(radioPlaneta);
        return planeta;
    }

    private static boolean comprobarToken() {
        if (token.toString().equals("")) {
            tokenValidado = false;
        } else {
            tokenValidado = true;
        }
        return tokenValidado;
    }

    private static boolean comprobarGalaxia(Galaxia galaxia) {
        if (galaxia.getNombre().equals("")) {
            galaxiaExiste = false;
        } else {
            galaxiaExiste = true;
        }
        return galaxiaExiste;
    }
}
