package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import components.GameCard;
import components.RoundedButton;
import components.RoundedImageOverlayPanel;
import components.RoundedPanel;
import models.Game;
import utils.AppFont;
import utils.ButtonFactory;
import utils.FormUtils;
import utils.UIColors;
import utils.VisualUtils;

@SuppressWarnings("serial")
public class HomeView extends JPanel{
	
	private JTextField txtSearch;
	private JLabel lblSearchError;
	private RoundedButton btnSearch;
	private JPanel gamesContainer;

	public HomeView() {
	    this.setBackground(Color.WHITE);
	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    initializeComponents();
	    setVisible(true);
	}
	
	public void initializeComponents() {
		add(createSearchSection());
		
	    add(Box.createRigidArea(new Dimension(0, 60)));
	    add(VisualUtils.createDivider()); 
	    add(Box.createRigidArea(new Dimension(0, 20)));
	    
	    add(wrapSection(createFeaturedGamesSection()));
	}
	
	public JPanel createSearchSection() {
	    JPanel container = new JPanel(new BorderLayout());
	    container.setPreferredSize(new Dimension(0, 300));
	    container.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

	    RoundedImageOverlayPanel bg = new RoundedImageOverlayPanel(
	            "/assets/img/search/search-bg.png",
	            0,
	            new Color(0, 0, 0, 120)
	    );

	    bg.setLayout(new BorderLayout());

	    JPanel searchBar = createSearchBar();
	    searchBar.setPreferredSize(new Dimension(900, 120));

	    JPanel centerWrapper = new JPanel(new GridBagLayout());
	    centerWrapper.setOpaque(false);
	    centerWrapper.add(searchBar);

	    bg.add(centerWrapper, BorderLayout.CENTER);
	    container.add(bg, BorderLayout.CENTER);

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
	    searchBar.add(FormUtils.createField(null, txtSearch, lblSearchError, "Busca un juego aquí!", 400, 60));
	    
	    btnSearch = ButtonFactory.createButton(
	    	    "Buscar",
	    	    "/assets/img/btn-icons/button-search-icon.png",
	    	    "Haz click para buscar",
	    	    220,
	    	    55
	    	);
	    
	    searchBar.add(Box.createRigidArea(new Dimension(15, 0)));
	    searchBar.add(btnSearch);	
	    searchBar.add(Box.createRigidArea(new Dimension(0, 10)));
	    searchBar.add(Box.createHorizontalGlue());

		return searchBar;
    }
    
    public JPanel createFeaturedGamesSection() {
        JPanel gamesPanel = new JPanel();
        gamesPanel.setLayout(new BoxLayout(gamesPanel, BoxLayout.Y_AXIS));
        gamesPanel.setOpaque(false);
        
        // HEADER SECTION
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Juegos destacados");
        titleLabel.setFont(AppFont.title());
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        
        // contenedor horizontal de habitaciones
        gamesContainer = new JPanel(new GridLayout(0, 4, 20, 20));
        gamesContainer.setOpaque(false);
        
        gamesPanel.add(headerPanel);
        gamesPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        gamesPanel.add(gamesContainer);
        gamesPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		return gamesPanel;
    }
    
    public void setGames(List<Game> games) {
        gamesContainer.removeAll();

        for (Game game : games) {
        	gamesContainer.add(new GameCard(game));
        }

        gamesContainer.revalidate();
        gamesContainer.repaint();
        
        gamesContainer.setPreferredSize(gamesContainer.getLayout().preferredLayoutSize(gamesContainer));
        
        this.revalidate();
        this.repaint();
    }
    
    private JPanel wrapSection(JPanel section) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 30));
        wrapper.setOpaque(false);
        wrapper.add(section);
        return wrapper;
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