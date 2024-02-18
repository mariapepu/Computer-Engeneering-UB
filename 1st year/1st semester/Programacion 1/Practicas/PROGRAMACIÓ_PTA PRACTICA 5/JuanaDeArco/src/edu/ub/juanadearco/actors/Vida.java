/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.juanadearco.actors;

import edu.ub.juanadearco.Actor;
import edu.ub.juanadearco.Colisio;
import edu.ub.juanadearco.Constants;
import edu.ub.juanadearco.Context;
import edu.ub.juanadearco.Util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * La vida proporciona un increment en el nivell de vida de l'actor que 
 * col·lisiona amb ella.
 * 
 * @author Prog1
 */
public class Vida extends AbstractActor {

    private final Image image;
    private final int amplada;
    private final int alcada;

    
    /**
     * Crea un nou objecte del tipus vida amb una subimage del fitxer
     * <code>objectes.png</code> 
     * 
     * @param imgPosX posició x de la subimage
     * @param imgPosY posició y de la subimage
     * @param imgAmplada amplada de la subimage
     * @param imgAlcada alçada de la subimage
     */
    public Vida(int imgPosX, int imgPosY, int imgAmplada, int imgAlcada) {
        this.image = Util.carregarImatge("objectes.png", imgPosX, imgPosY, imgAmplada, imgAlcada);
        this.amplada = imgAmplada;
        this.alcada = imgAlcada;
        setEstat(Constants.ESTAT_ACTIU);
    }
    @Override
    public void actualitzar(Context context) {
        // no fem res, es estàtic
    }

    @Override
    public Rectangle getLimits() {
        return new Rectangle(getX(), getY(), amplada, alcada);
    }

    @Override
    public void tractarColisio(Colisio colisio) {
        Actor actor = colisio.getActor();
        if (actor instanceof Heroina) {
	        actor.setVida(Math.min(100.0f, actor.getVida()));
	        setEstat(Constants.ESTAT_INACTIU);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, getX(), getY(), observer);
        g.setColor(Color.GREEN);
        Font f = new Font("Dialog", Font.BOLD, 10);
        g.setFont(f);
        String nom = null;
        g.drawString(nom, getX(), getY() - 3);
    }
           
}