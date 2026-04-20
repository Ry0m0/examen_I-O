package pio.daw;

import java.io.*;
import java.util.*;

public static void main (String [] args) {

    List<Radionuclido> lista = new Arraylist<>();
    try (BufferedReader br = new BufferedReader(new FileReader("residuos.csv"))){
    String linea;
    while((linea = br.readLine()) != null) {
        if (linea.isBlank()) continue;
        String []p = linea.split(";");
        String id = parse[0].trim();
        String isotopo = parse[1].trim();
        double masa = Double.parseDouble(parse[2].trim());
        double actividadEspecificaInicial = Double.parseDouble(parse[3].trim);
        LocalDateTime fechaEntrega = LocalDateTime.parseLocalDateTime(parse[4].trim());
        Lista.add(newRadionuclido(id, isotopo, masa, actividadEspecificaInicial));
    }
    }catch (IOException e) {
        e.printStackTrace();
    }

    try(BufferedWriter bw = new BufferedWriter(new FileWriter("factura.txt"))){
        for(Radionuclido r : lista) {
            bw.write(r.toFactura());
            bw.newLine();
        }
    } catch (IOException e) {e.printStackTrace();}

} 


