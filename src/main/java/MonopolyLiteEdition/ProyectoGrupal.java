package MonopolyLiteEdition;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProyectoGrupal {

    public static void main(String[] args) {
        Gson gson = new Gson();

        try (FileReader fr = new FileReader("src/main/resources/tablero.json")) {
            System.out.println("Leyendo 'src/main/resources/tablero.json'...");

            TableroIntermedio tableroIntermedio = gson.fromJson(fr, TableroIntermedio.class);
            if (tableroIntermedio == null) {
                System.out.println("No se pudo leer el JSON del tablero o está vacío.");
                return;
            }

            Casilla[] casillas = new Casilla[20];

            if (tableroIntermedio.getSalida() != null) {
                for (Salida salida : tableroIntermedio.getSalida()) {
                    if (salida != null && salida.getId() >= 0 && salida.getId() < casillas.length) {
                        casillas[salida.getId()] = salida;
                    }
                }
            }

            if (tableroIntermedio.getPropiedad() != null) {
                for (Propiedad propiedad : tableroIntermedio.getPropiedad()) {
                    if (propiedad != null && propiedad.getId() >= 0 && propiedad.getId() < casillas.length) {
                        casillas[propiedad.getId()] = propiedad;
                    }
                }
            }

            if (tableroIntermedio.getSuerte() != null) {
                for (Suerte suerte : tableroIntermedio.getSuerte()) {
                    if (suerte != null && suerte.getId() >= 0 && suerte.getId() < casillas.length) {
                        casillas[suerte.getId()] = suerte;
                    }
                }
            }

            if (tableroIntermedio.getTransporte() != null) {
                for (Transporte transporte : tableroIntermedio.getTransporte()) {
                    if (transporte != null && transporte.getId() >= 0 && transporte.getId() < casillas.length) {
                        casillas[transporte.getId()] = transporte;
                    }
                }
            }

            if (tableroIntermedio.getCarcel() != null) {
                for (Carcel carcel : tableroIntermedio.getCarcel()) {
                    if (carcel != null && carcel.getId() >= 0 && carcel.getId() < casillas.length) {
                        casillas[carcel.getId()] = carcel;
                    }
                }
            }

            if (tableroIntermedio.getIrACarcel() != null) {
                for (IrACarcel irACarcel : tableroIntermedio.getIrACarcel()) {
                    if (irACarcel != null && irACarcel.getId() >= 0 && irACarcel.getId() < casillas.length) {
                        casillas[irACarcel.getId()] = irACarcel;
                    }
                }
            }

            if (tableroIntermedio.getImpuesto() != null) {
                for (Impuesto impuesto : tableroIntermedio.getImpuesto()) {
                    if (impuesto != null && impuesto.getId() >= 0 && impuesto.getId() < casillas.length) {
                        casillas[impuesto.getId()] = impuesto;
                    }
                }
            }

            Tablero tablero = new Tablero(casillas);
            System.out.println("\nTablero cargado. Listado de casillas:");
            for (Casilla c : tablero.getCasillas()) {
                System.out.println(c);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("JSON inválido: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }



    }
}
