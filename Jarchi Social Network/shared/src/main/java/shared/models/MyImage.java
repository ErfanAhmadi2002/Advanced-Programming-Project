package shared.models;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MyImage {
    private final byte[] bytes;
    private int id;

    public MyImage(byte[] bytes, int id) {
        this.bytes = bytes;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public Image convertToImage (){
        ImageIcon imageIcon = new ImageIcon(bytes);
        BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth() , imageIcon.getIconHeight() , BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.createGraphics();
        imageIcon.paintIcon(null , graphics , 0 , 0);
        graphics.dispose();
        return SwingFXUtils.toFXImage(bufferedImage , null);
    }
}
