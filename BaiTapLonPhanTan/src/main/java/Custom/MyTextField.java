package Custom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
public class MyTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }
    private Icon prefixIcon;

    public MyTextField() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        paintIcon(g);
        if (!isFocusOwner()) {
            g.setColor(new Color(255, 255, 255,150));
            g.fillRect(0, getHeight()-2, getWidth() - 1, 2);
        } else {
        	 g.setColor(new Color(255, 255, 255,255));
             g.fillRect(0, getHeight()-2, getWidth() - 1, 2);
        }
    }

    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 3, y, this);
        }
    }

    private void initBorder() {
        int left = 5;
        int right = 5;
        //  5 is default
        if (prefixIcon != null) {
            //  prefix is left
            left = prefixIcon.getIconWidth() + 10;
        }
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, left, 0, right));
    }
    
}