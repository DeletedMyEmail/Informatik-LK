package de.kaitokuntatsu.random;

import de.joshua.DataStructures.BinaryTree;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 *
 * Beschreibung
 *
 * @version 1.0a vom 04.03.2012
 * @Andreas Hamerla
 * @Edit by Florian P.
 */

public class BaumZeichner<ContentType> extends Frame {

    BinaryTree<ContentType> _baum;
    BufferedImage img;
    Graphics g;
    int _dy;              //  Abstand der Baumebenen
    int _tiefe = 0;       //  Tiefe des Baumes

    public BaumZeichner(int frameWidth, int frameHeight, BinaryTree<ContentType> pBaum) {
        super("Baumzeichner");
        _baum = pBaum;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) { dispose(); }
        });

        addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
                zeichne();
            }
        });

        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);

        setResizable(true);
        setVisible(true);

    }

    public BaumZeichner(BinaryTree<ContentType> pBaum)
    {
        new BaumZeichner(800, 600, pBaum);
    }

    public void paint( Graphics g )   {
        super.paint(g);
        g.drawImage(img, 0,25, this);
    }

    // Ende Methoden

    private void zeichne()
    {  if (_baum == null)  return;

        Dimension x = getSize();
        img = new BufferedImage( x.width, x.height-25, BufferedImage.TYPE_INT_RGB );
        g = img.getGraphics();
        g.setColor(new Color(200,200,200));
        loesche(g, x.width, x.height);
        _dy = (x.height-25) / (tiefe(_baum)+1);
        zeichneInorder(0,x.width,50, _baum);
        this.repaint();
    }

    public void loesche(Graphics g, int x, int y)
    {   g.setColor(new Color(220,220,230));
        g.fillRect(0,0, x, y);
        g.setColor(new Color(0,0,250));
        g.setFont(new Font("Arial", Font.BOLD,12));
    }

    private void zeichneInorder(int links, int rechts, int y, BinaryTree<ContentType> baum)
    {  if (baum.isEmpty()) return;
        int mitte = (links + rechts) / 2;
        zeichneInorder(links, mitte,y+_dy,baum.getLeftTree());
        zeigeKnoten(links, rechts, y, baum.getContent().toString());
        zeichneInorder(mitte, rechts,y+_dy,baum.getRightTree());
    }

    private void zeigeKnoten(int links, int rechts, int y, String inhalt)
    {  int mitte = (links + rechts) / 2;
        double abstand = Math.sqrt((mitte-links)*(mitte-links)+_dy*_dy);
        int ddx = (int) ((mitte-links)/abstand*10);
        int ddy = (int) (_dy/abstand*10);
        g.setColor(new Color(0,0,0));
        g.drawLine(mitte-ddx,y+ddy, (links+mitte) / 2 + ddx, y + _dy-ddy);
        g.drawLine(mitte+ddx,y+ddy, (mitte+rechts) /2 - ddx, y + _dy-ddy);
        g.setColor(new Color(255,255,0));
        g.fillArc(mitte-10,y-10,20,20,0,360);
        g.setColor(new Color(0,0,0));
        g.drawArc(mitte-10,y-10,20,20,0,360);
        g.setColor(new Color(250,0,50));
        g.drawString(inhalt,mitte-3,y+5);
    }

    private void tief( BinaryTree<ContentType> baum, int t)
    {   if (baum.isEmpty()) return;

        if (t > _tiefe) _tiefe = t;
        tief(baum.getLeftTree(), t+1);
        tief(baum.getRightTree(),t+1);
    }

    public int tiefe(BinaryTree<ContentType> baum)
    {  _tiefe = 0;
        tief(baum, 1);
        return _tiefe ;
    }

    public void warte(int dur)
    {  try { Thread.sleep(dur);
    } catch(Exception e) {
    } finally {  }
    }

}
