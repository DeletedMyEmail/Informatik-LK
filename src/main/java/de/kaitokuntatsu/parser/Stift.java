package de.kaitokuntatsu.parser;

import java.awt.*;

public class Stift
{
    int winkel;
    Position startPosition, zielPosition;

    Color farbe = Color.RED;
    Graphics g;

    public Stift(Graphics g)
    {
        this.winkel = 90;
        this.startPosition = new Position(0,0);
        this.zielPosition = new Position(0,0);
        this.g = g;
    }

    public Stift(Graphics g, double x, double y)
    {
        this.winkel = 90;
        this.startPosition = new Position(x,y);
        this.zielPosition = new Position(x,y);
        this.g = g;
    }


    // Öffentliche Methoden:

    public void dreheRechts(int winkel)
    {
        aendereWinkel((-1)*winkel);
    }

    public void dreheLinks(int winkel)
    {
        aendereWinkel(winkel);
    }

    public void zeichneVorwaerts(int laenge)
    {
        berechneZielVorwaerts(laenge);
        g.setColor(farbe);
        g.drawLine((int)startPosition.getX(),(int)startPosition.getY(),(int)zielPosition.getX(),(int)zielPosition.getY());
        startPosition = zielPosition;
    }







    // Private Hilfsmethoden:

    private void berechneZielVorwaerts(int laenge)
    {
        double xNeu = startPosition.getX() + Math.sin((winkel*Math.PI/180))*laenge;
        double yNeu = startPosition.getY() + Math.cos((winkel*Math.PI/180))*laenge;
        zielPosition = new Position(xNeu, yNeu);
    }

    private void aendereWinkel(int pWinkel)
    {
        winkel = winkel + pWinkel % 360;
    }

    private class Position
    {
        double x;
        double y;

        public Position(double x, double y)
        {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }
}
