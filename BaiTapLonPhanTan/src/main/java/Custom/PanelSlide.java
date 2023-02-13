package Custom;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class PanelSlide extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getAnimate() {
		return animate;
	}

	public void setAnimate(int animate) {
		this.animate = animate;
	}

	public PanelSlide() {
		setLayout(null);
		setBackground(Color.white);
		list = new ArrayList<Component>();
		timer = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				animate();
			}
		});

	}

	private List<Component> list;
	private final Timer timer;
	private Component comExit;
	private Component comShow;
	private int currentShowing = 0;
	private boolean animateRight;
	private int animate = 1;

	public void remove(Component[] com) {
		if (com.length > 0) {
			for (Component c : com) {
				this.remove(c);
				list.remove(c);
			}
		}
	}

	public void init(Component[] com) {
		if (com.length > 0) {
			for (Component c : com) {
				list.add(c);
				c.setSize(this.getWidth(), this.getHeight());
				c.setVisible(false);
				this.add(c);
			}
			list.get(0).setLocation(0, 0);
			list.get(0).setVisible(true);

		}
	}

	public synchronized void show(int index) {
		if (!timer.isRunning()) {
			if (list.size() >= 2 && index < list.size() && index != currentShowing) {
				comShow = list.get(index);
				comExit = list.get(currentShowing);
				animateRight = index < currentShowing;
				currentShowing = index;
				comShow.setVisible(true);
				if (animateRight) {
					comShow.setLocation(-comShow.getWidth(), 0);

				} else {
					comShow.setLocation(comShow.getWidth(), 0);
				}
				timer.start();
			}
		}
	}

	private void animate() {
		if (animateRight) {
			if (comShow.getLocation().x < 0) {
				comShow.setLocation(comShow.getLocation().x + animate, 0);
				comExit.setLocation(comExit.getLocation().x + animate, 0);
			} else {
				// Stop animate
				comShow.setLocation(0, 0);
				timer.stop();
				comExit.setVisible(false);
			}
		} else {
			if (comShow.getLocation().x > 0) {
				comShow.setLocation(comShow.getLocation().x - animate, 0);

				comExit.setLocation(comExit.getLocation().x - animate, 0);
			} else {
				comShow.setLocation(0, 0);
				timer.stop();
				comExit.setVisible(false);
			}
		}
	}

}
