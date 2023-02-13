package Custom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ButtonBorderBottom extends JButton implements FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonBorderBottom() {
	}
	public ButtonBorderBottom(String text) {
		super();
		setText(text);
		setContentAreaFilled(false);
		setForeground(Color.black);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		setText(text);
		setBorder(null);
		setFont(new Font("Serif", Font.BOLD, 21));
		this.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#0eb289")));
		setForeground(Color.decode("#0eb289"));
	}

	@Override
	public void focusLost(FocusEvent e) {
		setBorder(null);
		setForeground(Color.black);
	}

}
