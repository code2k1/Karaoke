package Custom;

import javax.swing.ImageIcon;

public class DataSearch {
	private ImageIcon imageIcon;
	private String text;
	public ImageIcon getImageIcon() {
		return imageIcon;
	}
	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public DataSearch() {
		// TODO Auto-generated constructor stub
	}
	public DataSearch(ImageIcon imageIcon, String text) {
		super();
		this.imageIcon = imageIcon;
		this.text = text;
	}
	
}
