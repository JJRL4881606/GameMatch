package views;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SearchView extends JPanel{
	public SearchView() {
	    
	    this.setBackground(Color.WHITE);
	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    setVisible(true);
	}

}
