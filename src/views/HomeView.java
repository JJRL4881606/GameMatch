package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import components.RoundedButton;
import components.RoundedImageOverlayPanel;
import components.RoundedPanel;
import utils.ButtonFactory;
import utils.FormUtils;
import utils.UIColors;
import utils.VisualUtils;

@SuppressWarnings("serial")
public class HomeView extends JPanel{
	
	JTextField txtSearch;
	JLabel lblSearchError;
	RoundedButton btnSearch;

	public HomeView() {
	    this.setBackground(Color.WHITE);
	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    initializeComponents();
	    setVisible(true);
	}
	
	public void initializeComponents() {
		add(createSearchHero());
		
	    add(Box.createRigidArea(new Dimension(0, 60)));
	    add(VisualUtils.createDivider()); 
	    add(Box.createRigidArea(new Dimension(0, 20)));
	}
	
	public JPanel createSearchHero() {

	    JPanel container = new JPanel();
	    container.setLayout(null);
	    container.setPreferredSize(new Dimension(1400, 300));
	    container.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
	    container.setOpaque(false);

	    //BACKGROUND
	    RoundedImageOverlayPanel bg = new RoundedImageOverlayPanel(
	            "/img/img/search-bg.png",
	            1400,
	            300,
	            0,
	            new Color(0, 0, 0, 120) // overlay oscuro
	    );

	    bg.setBounds(0, 0, 1400, 300);

	    // SEARCHBAR
	    JPanel searchBar = createSearchBar();
	    
	    int barWidth = 900;
	    int barHeight = 120;

	    searchBar.setBounds(
	            (1400 - barWidth) / 2, // centrar horizontal
	            (300 - barHeight) / 2, // centrar vertical
	            barWidth,
	            barHeight
	    );

	    // ORDEN DE CAPAS
	    container.add(bg);
	    container.add(searchBar);

	    container.setComponentZOrder(searchBar, 0);
	    container.setComponentZOrder(bg, 1);

	    return container;
	}
	
    public JPanel createSearchBar() {
    	
	    JPanel searchBar = new RoundedPanel(30);
	    searchBar.setLayout(new BoxLayout(searchBar, BoxLayout.X_AXIS));
	    searchBar.setBackground(UIColors.CARD);
	    searchBar.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));
	    searchBar.setAlignmentX(CENTER_ALIGNMENT);
	    searchBar.putClientProperty("FlatLaf.style", "arc:20");
	    searchBar.setPreferredSize(new Dimension(900, 120));
	    searchBar.setMaximumSize(new Dimension(900, 120));
	    
	    searchBar.add(Box.createHorizontalGlue());
	    
	    txtSearch = FormUtils.createTextField();
	    lblSearchError = FormUtils.createErrorLabel();
	    txtSearch.setBackground(new Color(245,245,245));
	    searchBar.add(Box.createRigidArea(new Dimension(15, 0)));
	    searchBar.add(FormUtils.createField("Juego", txtSearch, lblSearchError, "Busca un juego aquí!", 400));
	    
	    btnSearch = ButtonFactory.createBigButton(
	            "Buscar",
	            "/img/btn-icons/button-search-icon.png",
	            "Haz click para buscar"
	    );
	    searchBar.add(Box.createRigidArea(new Dimension(15, 0)));
	    searchBar.add(btnSearch);	
	    searchBar.add(Box.createRigidArea(new Dimension(0, 10)));
	    
	    searchBar.add(Box.createHorizontalGlue());

		return searchBar;
    }
    
	public void clearErrors() {
		clearSearchError();
	}
	
	public void clearSearchError(){
	    FormUtils.clearError(lblSearchError, txtSearch);
	}
	
	public void setSearchError(String msg) {
	    lblSearchError.setText(msg);
	    txtSearch.setBorder(FormUtils.redBorder);
	}

	public String getSearch() {
	    return txtSearch.getText().trim();
	}

	public JTextField getTxtSearch() {
	    return txtSearch;
	}
	
	public JLabel getLblEmailError() {
	    return lblSearchError;
	}	
	
	public RoundedButton getBtnSearch() {
		return btnSearch;
	}
}