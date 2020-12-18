package imageviewer.apps.swing;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageDisplay {
    private BufferedImage bitmap;
    private Image image;
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (bitmap == null) return;
        Bounds b = new Bounds();
        g.drawImage(bitmap, b.x(), b.y(), b.width(), b.height(), null);
    }
    
    @Override
    public void display(Image image) {
        this.image=image;
        load();
        repaint();
    }
    
    @Override
    public Image getImage() {
        return image;
    }

    private void load() {
        try {
            this.bitmap= ImageIO.read(new File(image.getName()));
        } catch (Exception e) {
            this.bitmap = null;
        }
    }

    private class Bounds {

        private final int iw;
        private final int ih;
        private final double ir;
        private final int pw;
        private final int ph;
        private final double pr;
        
        public Bounds() {
            iw = bitmap.getWidth();
            ih = bitmap.getHeight();
            ir = (double) iw / ih;
            
            pw = getWidth();
            ph = getHeight();
            pr = (double) pw / ph;
        }
        
        int x() {
            return (pw - width()) / 2;
        }
        
        int y() {
            return (ph - height()) / 2;
        }
        
        int width() {
            return ir < pr ? (int) ((double) iw * ph / ih) : pw;
        }
        
        int height() {
            return ir < pr ? ph : (int) ((double) ih * pw / iw);
        }
    }

}