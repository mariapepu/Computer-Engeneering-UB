
package edu.ub.juanadearco;

import edu.ub.juanadearco.actors.Clau;
import edu.ub.juanadearco.actors.Heroina;
import edu.ub.juanadearco.actors.Menjar;
import edu.ub.juanadearco.actors.Vida;

/**
 * Demo.
 * 
 * @author Mpepu
 */
public class Practica{

    private final Joc joc;
    private final Castell castell;
    private final Heroina heroina;

    private static final int MAX_MENJAR_PER_HABITACIO = 2;
    
    private Practica() {
        castell = new Castell(3, 3);
        castell.addHabitacio(0, 0, crearHabitacio0Planta0());
        castell.addHabitacio(0, 1, crearHabitacio1Planta0());
        castell.addHabitacio(0, 2, crearHabitacio2Planta0());                            
        castell.addHabitacio(1, 0, crearHabitacio0Planta1());
        castell.addHabitacio(1, 1, crearHabitacio1Planta1());
        castell.addHabitacio(1, 2, crearHabitacio2Planta1());   
        castell.addHabitacio(2, 0, crearHabitacio0Planta2());
        castell.addHabitacio(2, 1, crearHabitacio1Planta2());
        castell.addHabitacio(2, 2, crearHabitacio2Planta2());   
       
        heroina = new Heroina();
        Habitacio h = castell.getHabitacio(0, 0);
        int[] p = h.getPosicioCela(10, 10);
        heroina.setPosicioInicial(p[0], p[1]);
        
        joc = new Joc(castell, heroina);                            
        distribuirMenjar();
        
        GuiJoc gui = new GuiJoc(joc);      
    }
    
        
    private Habitacio crearHabitacio0Planta0() {
        Habitacio h = Util.carregarHabitacio("h0_0.txt");
        Porta porta = h.getPorta(14, 24);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1, 1));

        porta = h.getPorta(0, 10);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(1);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(14, 10));        

        return h;
    }
    
    private Habitacio crearHabitacio1Planta0() {
        Habitacio h = Util.carregarHabitacio("h0_1.txt");  
        Porta porta = h.getPorta(1, 0);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(14, 23));
       
        porta = h.getPorta(0, 10);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(2);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(14, 10));     
        return h;
    }

    private Habitacio crearHabitacio2Planta0() {
        Habitacio h = Util.carregarHabitacio("h0_2.txt");
        Porta porta = h.getPorta(16, 10);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(1);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1, 10));
        
        porta = h.getPorta(0, 10);
        porta.setNumPlantaDesti(1);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(14, 10));
        
        return h;
    }
    
    private Habitacio crearHabitacio0Planta1() {
        Habitacio h = Util.carregarHabitacio("h1_0.txt");
        Porta porta = h.getPorta(16, 10);
        porta.setNumPlantaDesti(0);
        porta.setNumHabitacioDesti(2);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1, 10));
        
        porta = h.getPorta(0, 10);
        porta.setNumPlantaDesti(1);
        porta.setNumHabitacioDesti(1);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(14, 10));
        
        return h;
    }
        
    private Habitacio crearHabitacio1Planta1() {
        Habitacio h = Util.carregarHabitacio("h1_1.txt");
        Porta porta = h.getPorta(16, 10);
        porta.setNumPlantaDesti(1);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1, 10));
        
        porta = h.getPorta(0, 10);
        porta.setNumPlantaDesti(1);
        porta.setNumHabitacioDesti(2);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(14, 10));
        
        return h;
    }
        private Habitacio crearHabitacio2Planta1() {
        Habitacio h = Util.carregarHabitacio("h1_2.txt");
        Porta porta = h.getPorta(16, 10);
        porta.setNumPlantaDesti(1);
        porta.setNumHabitacioDesti(1);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1, 10));
        
        porta = h.getPorta(0, 10);
        porta.setNumPlantaDesti(2);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(14, 10));
        
        return h;
    }
        private Habitacio crearHabitacio0Planta2() {
        Habitacio h = Util.carregarHabitacio("h2_0.txt");
        Porta porta = h.getPorta(16, 10);
        porta.setNumPlantaDesti(1);
        porta.setNumHabitacioDesti(2);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1, 10));
        
        porta = h.getPorta(0, 10);
        porta.setNumPlantaDesti(2);
        porta.setNumHabitacioDesti(1);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(14, 10));
        
        return h;
    }
        private Habitacio crearHabitacio1Planta2() {
        Habitacio h = Util.carregarHabitacio("h2_1.txt");
        Porta porta = h.getPorta(16, 10);
        porta.setNumPlantaDesti(2);
        porta.setNumHabitacioDesti(0);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1, 10));
        
        porta = h.getPorta(0, 10);
        porta.setNumPlantaDesti(2);
        porta.setNumHabitacioDesti(2);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(14, 10));
        
        return h;
    }
        private Habitacio crearHabitacio2Planta2() {
        Habitacio h = Util.carregarHabitacio("h2_2.txt");
        Porta porta = h.getPorta(16, 10);
        porta.setNumPlantaDesti(2);
        porta.setNumHabitacioDesti(1);
        porta.setPosicioHabitacioDesti(h.getPosicioCela(1, 10));
        
        return h;
    }
    
    private void distribuirMenjar() {
        String[] menjars = {  "Pizza", "Pollastre", "Síndria" } ;
        // { calories,x,y,width,height } de cada imatge
        int[][] dades = {
            { 25, 540, 14, 30, 22 },
            { 50, 439, 14, 27, 23 },
            { 50, 97, 100, 30, 20 }
        };
        
        for (int i = 0; i < castell.getNumPlantes(); i++) {
            
            for (int j = 0; j < castell.getNumHabitacions(i); j++) {
                Habitacio h = castell.getHabitacio(i, j);
                int numMenjars = (int)(Math.random() * (MAX_MENJAR_PER_HABITACIO + 1));
                
                for (int k = 0; k < numMenjars; k++) {
                    int[] cela = obtenirCelaLliure(h);
                    int imenjar = (int)(Math.random() * menjars.length);
                    Menjar m = new Menjar(menjars[imenjar], 
                            dades[imenjar][0], dades[imenjar][1], dades[imenjar][2], 
                            dades[imenjar][3], dades[imenjar][4]);
                    
                    int[] posicio = h.getPosicioCela(cela[0], cela[1]);
                    m.setPosicioInicial(posicio[0], posicio[1]);
                    h.addActor(m);                     
                }
            }
            
        }
    }

    private void distribuirVida() {
        // {x,y,width,height } de cada imatge
        int numPlanta =  castell.getNumPlantes()-1;
        int numHabitacio = castell.getNumHabitacions(numPlanta)-1;
        Habitacio h = castell.getHabitacio(numPlanta, numHabitacio);
        int[] cela = obtenirCelaLliure(h);
        Vida a = new Vida(174, 12, 26, 24 );  

        int[] posicio = h.getPosicioCela(cela[0], cela[1]);
        a.setPosicioInicial(posicio[0], posicio[1]);
        h.addActor(a);
            
    }
    
    private void distribuirVida2() {
        // {x,y,width,height } de cada imatge
        int numPlanta =  castell.getNumPlantes()-2;
        int numHabitacio = castell.getNumHabitacions(numPlanta)-2;
        Habitacio h = castell.getHabitacio(numPlanta, numHabitacio);
        int[] cela = obtenirCelaLliure(h);
        Vida b = new Vida(174, 12, 26, 24 );  

        int[] posicio = h.getPosicioCela(cela[0], cela[1]);
        b.setPosicioInicial(posicio[0], posicio[1]);
        h.addActor(b);
            
    }    
    private int[] obtenirCelaLliure(Habitacio h) {
        int fila = 0;
        int col = 0;
        int cela[];
        boolean trobada = false;
        boolean terra;

        while (!trobada) {            
            terra = false;
            while (!terra) {
                fila = (int)(Math.random() * Constants.NUM_CELES_VERTICALS);
                col = (int)(Math.random() * Constants.NUM_CELES_HORIZONTALS);
                terra = h.getValor(fila, col) == Constants.SIMBOL_TERRA;
            }
            
            // comprovar que cap actor està dins la cela
            Actor[] actors = h.getActorsAsArray();
            int i = 0;
            boolean lliure = true;
            while (i < actors.length && lliure) {
                cela = h.getCela(actors[i].getPosicioInicial()[0], 
                        actors[i].getPosicioInicial()[1]);
                lliure = fila != cela[0] || col != cela[1];            
                i++;
            }         
            trobada = lliure;
        }
        return new int[] { fila, col };
    }
    private void distribuirClaus() {
        String[] claus = {  "Cobre", "Bronze", "Llauto", "Plata" } ;
        // { x,y,width,height } de cada imatge
        int[][] dades = {
            {  675, 50, 20, 30 },
            {  675, 50, 20, 30 },           
            {  675, 50, 20, 30 }           
        };
        
                for (int i = 0; i < castell.getNumPlantes(); i++) {
            
            for (int j = 0; j < castell.getNumHabitacions(i); j++) {
                Habitacio h = castell.getHabitacio(i, j);
                int numClaus = 5;
                
                for (int k = 0; k < numClaus; k++) {
                    int[] cela = obtenirCelaLliure(h);
                    int iclaus = (int)(Math.random() * claus.length);
                    Clau c = new Clau (claus[0], dades[iclaus][0], dades[iclaus][1], dades[iclaus][2], dades[iclaus][3]);
                    
                    int[] posicio = h.getPosicioCela(cela[0], cela[1]);
                    c.setPosicioInicial(posicio[0], posicio[1]);
                    h.addActor(c);                     
                }
            }
            
        }
    }
}