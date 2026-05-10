package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import components.UnderlineMenuItem;
import utils.AppFont;
import utils.UIColors;

@SuppressWarnings("serial")
public class MainView extends JPanel{
		
	public static final String HOME = "HOME";
	public static final String SEARCH = "SEARCH";
	public static final String GAMES = "GAMES";
	public static final String CATEGORIES = "CATEGORIES";
	
	public UnderlineMenuItem menuHome;
	public UnderlineMenuItem menuGames;
	public UnderlineMenuItem menuCategories;
	public HomeView homePanel;
	public SearchView searchPanel;
	public GamesView gamesPanel;
	public CategoriesView categoriesPanel;

	private CardLayout cardLayout;
	private JPanel container;
	

	public MainView() {
	    UIManager.put("Menu.borderPainted", false);
	    UIManager.put("MenuItem.borderPainted", false);
	    UIManager.put("Menu.selectionBackground", UIColors.HEADER);
	    UIManager.put("Menu.selectionForeground", Color.WHITE);
	    UIManager.put("MenuItem.selectionBackground", UIColors.HEADER);
	    UIManager.put("MenuItem.selectionForeground", Color.WHITE);

	    setLayout(new BorderLayout());
	    initializeComponents();
	    setVisible(true);
	}

    public void initializeComponents() {	    
        add(headerSection(), BorderLayout.NORTH);
        add(content(), BorderLayout.CENTER);
        add(inferiorSection(), BorderLayout.SOUTH);
    }

    //HEADER
    public JPanel headerSection() {
        JPanel superiorPanel = new JPanel();
        superiorPanel.setLayout(new GridLayout(1,3));
        superiorPanel.setBackground(UIColors.HEADER);
        superiorPanel.setBorder(new EmptyBorder(30,30,35,30));

        superiorPanel.add(headerLeftSection());
        superiorPanel.add(headerCenterSection());
        superiorPanel.add(headerRightSection());

        return superiorPanel;
    }
    
    public JPanel headerCenterSection() {
        JPanel panel = createTransparentPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ImageIcon icon = new ImageIcon(getClass().getResource("/assets/img/logos/GameMatch.png"));
        Image img = icon.getImage().getScaledInstance(400, 90, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));

        panel.add(logo);

        return panel;
    }
    
    public JPanel headerRightSection(){
        return createTransparentPanel();
    }
    
    public JPanel headerLeftSection(){
    	JPanel panel = createTransparentPanel();
    	panel.setLayout(new GridBagLayout());
    	
        JMenuBar menu = createMenu();
        panel.add(menu);

        return panel;
    }    
    
    public JMenuBar createMenu() {
    	JMenuBar mb = new JMenuBar();
    	mb.setFont(AppFont.big());
    	mb.setForeground(Color.white);
    	mb.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
    	mb.setOpaque(true);
    	mb.setBackground(UIColors.HEADER);
    	
        // SISTEMA
        menuHome = new UnderlineMenuItem("Inicio");
        menuHome.setMnemonic(KeyEvent.VK_I);
        mb.add(menuHome);
        
    	menuGames = new UnderlineMenuItem("Juegos");
    	menuGames.setMnemonic(KeyEvent.VK_J);
        mb.add(menuGames);

        menuCategories = new UnderlineMenuItem("Categorías");
        menuCategories.setMnemonic(KeyEvent.VK_C);
        mb.add(menuCategories);

        return mb;
    }

    //CONTENT
    public JPanel content(){
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        createViews();
        contentPanel.add(container, BorderLayout.CENTER);

        return contentPanel;
    }

    private void createViews() {
        cardLayout = new CardLayout();

        container = new JPanel(cardLayout) {
            @Override
            public Dimension getPreferredSize() {
                for (Component c : getComponents()) {
                    if (c.isVisible()) {
                        return c.getPreferredSize();
                    }
                }
                return super.getPreferredSize();
            }
        };

        homePanel = new HomeView();
        searchPanel = new SearchView();
        gamesPanel = new GamesView();
        categoriesPanel = new CategoriesView();

        container.add(homePanel, HOME);
        container.add(searchPanel, SEARCH);
        container.add(gamesPanel, GAMES);
        container.add(categoriesPanel, CATEGORIES);
    }
        
    //INFERIOR
    public JPanel inferiorSection() {
        JPanel inferiorPanel = new JPanel();
        inferiorPanel.setBackground(UIColors.HEADER);
        inferiorPanel.setBorder(new EmptyBorder(30,30,30,30));

        JLabel lblCopy = new JLabel("Copyright © 2026 GameMatch. All rights reserved");
        lblCopy.setFont(AppFont.normal());
        lblCopy.setForeground(UIColors.HOME_TITLE);

        inferiorPanel.add(lblCopy);

        return inferiorPanel;
    }
    
    private JPanel createTransparentPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        return panel;
    }
    
    public void showView(String name) {
        cardLayout.show(container, name);
    }
}
