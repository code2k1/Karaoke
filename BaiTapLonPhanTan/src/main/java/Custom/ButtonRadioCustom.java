package Custom;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;

import java.awt.*;

public class ButtonRadioCustom extends JRadioButton implements ActionListener, ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon emtyIcon;
	private ImageIcon selectIcon;

	public ButtonRadioCustom(ImageIcon emtyIcon, ImageIcon selectIcon) {
		this.emtyIcon = emtyIcon;
		this.selectIcon = selectIcon;
		setOpaque(false);
		setSelected(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		if (isSelected())
			setIcon((Icon) this.selectIcon);
		else
			setIcon((Icon) this.emtyIcon);
//		addActionListener(this);
		addChangeListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			if (isSelected())
				setIcon((Icon) this.selectIcon);
			else
			setIcon((Icon) this.emtyIcon);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (isSelected())
			setIcon((Icon) this.selectIcon);
		else
		setIcon((Icon) this.emtyIcon);
	}

	

}
