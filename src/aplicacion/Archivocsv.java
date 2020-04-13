package aplicacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidades.Productos;

public class Archivocsv 
{

	public static void main(String[] args) {
	   Locale.setDefault(Locale.US);	
       Scanner sc = new Scanner(System.in);
       
       List<Productos> lista = new ArrayList<>();
       
       System.out.print("Entre Nombre y Ruta del Archivo :");
       String archivoRutaStr = sc.nextLine();
       
       //--Crea una ruta del Archivo de la Vriable de entrada--//
       File archivoRuta = new File(archivoRutaStr);
       
       //--Toma la ruta de la Carpeta--//
       String rutaCarpetaStr = archivoRuta.getParent();
       
       //System.out.println("Ruta y Archivo :"+archivoRuta);
       
       
       //--Crea Varible boolena para crear el sub-Dir--//
       boolean creaDir = new File(rutaCarpetaStr+"/out").mkdir();
       
       //--Crea Archivo final--//
       String destinoArchivoStr = rutaCarpetaStr+"/out/summary.csv";
       
       try(BufferedReader br = new BufferedReader(new FileReader(archivoRuta)))
       {
          String itemCsv = br.readLine();
          while(itemCsv != null)
          {
             String[] campos = itemCsv.split(",");
             
             //--Tomamos el Arreglo--//
             String nombre = campos[0];
             double precio = Double.parseDouble(campos[1]);
             int cantidad = Integer.parseInt(campos[2]);
             
             //--Agregamos a la Lista--//
             lista.add(new Productos(nombre, precio, cantidad));
             
             //--Nos movemos en el Archivo--//
             itemCsv = br.readLine();
          }
          
          //--Creamos el nuevo Archivo con Totales--//
          try(BufferedWriter bw = new BufferedWriter(new FileWriter(destinoArchivoStr)))
          {
             for(Productos xproduc : lista) 
             {
                bw.write(xproduc.getNombre()+","+String.format("%.2f", xproduc.total()));
                bw.newLine();
             }
             
             System.out.println("Archivo summary.csv Creado...");
          }
          catch(IOException e)
          {
             System.out.println("Error de Escritura de Archivo"+e.getMessage());	   
          }
          
       }
       catch(IOException e)
       {
          System.out.println("Error de Lectura de Archivo"+e.getMessage());	   
       }
       finally
       {
    	  sc.close();	   
       }
       
       
	}

}
