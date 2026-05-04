package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import components.UnderlineMenu;
import utils.AppFont;
import utils.UIColors;

@SuppressWarnings("serial")
public class MainView extends JPanel{
		
	public static final String HOME = "HOME";
	public static final String SEARCH = "SEARCH";
	
	public JMenuItem btnHome;
	public JMenuItem btnSearch;
	public HomeView homePanel;
	public SearchView searchPanel;

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

    public void initializeComponents() 
    {	    
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

        ImageIcon icon = new ImageIcon(getClass().getResource("/img/logos/GameMatch.png"));
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
        JMenu sistema = new UnderlineMenu("Sistema");
        sistema.setMnemonic(KeyEvent.VK_S);
        mb.add(sistema);

        btnHome = new JMenuItem("Inicio");
        btnHome.setMnemonic(KeyEvent.VK_I);
        sistema.add(btnHome);
        
    	JMenu juegos = new UnderlineMenu("Juegos");
    	juegos.setMnemonic(KeyEvent.VK_J);
        mb.add(juegos);

        JMenu categorias = new UnderlineMenu("Categorías");
        categorias.setMnemonic(KeyEvent.VK_C);
        mb.add(categorias);

        btnSearch = new UnderlineMenu("Buscar");
        btnSearch.setMnemonic(KeyEvent.VK_B);
        mb.add(btnSearch);

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
        container = new JPanel(cardLayout);
                
        homePanel = new HomeView();
        searchPanel = new SearchView();
        
        container.add(homePanel, HOME);
        container.add(searchPanel, SEARCH);
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

}
