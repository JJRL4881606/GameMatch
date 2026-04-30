package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import components.RoundedMenuBar;
import utils.AppFont;
import utils.UIColors;

@SuppressWarnings("serial")
public class HomeView extends JPanel{
	
	public JMenuItem btnHome;

	public HomeView() {
	    UIManager.put("Menu.selectionBackground", UIColors.HOVER);
	    UIManager.put("MenuItem.selectionBackground", UIColors.HOVER);
	    UIManager.put("MenuBar.highlight", UIColors.HOVER);
	    UIManager.put("Menu.borderPainted", false);
	    UIManager.put("MenuItem.borderPainted", false);

	    this.setBackground(new Color(100,149,237)); 
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
    
    public JPanel headerCenterSection(){
    	JPanel panel = createTransparentPanel();
    	panel.setLayout(new GridBagLayout());
    	
        JPanel titleBlock = new JPanel();
        titleBlock.setLayout(new BoxLayout(titleBlock, BoxLayout.Y_AXIS));
        titleBlock.setOpaque(false);

        titleBlock.add(createTitle());
        titleBlock.add(createSubtitle());

        panel.add(titleBlock);

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
    
    public JPanel createTitle(){
    	JPanel panel = createTransparentPanel();
    	panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = new ImageIcon(getClass().getResource("/img/game-icon.png"));
        Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(img));

        JLabel lblTitle = new JLabel("GameMatch");
        lblTitle.setBorder(new EmptyBorder(0,15,0,0));
        lblTitle.setFont(AppFont.title());
		lblTitle.setForeground(UIColors.HOME_TITLE);

        panel.add(logo);
        panel.add(lblTitle);

        return panel;
    }
    
    public JPanel createSubtitle() {
    	JPanel panel = createTransparentPanel();
    	panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    	
        JLabel lblSubtitle = new JLabel("Bienvenido a GameMatch");
        lblSubtitle.setBorder(new EmptyBorder(20, 20, 0, 20)); 
        lblSubtitle.setFont(AppFont.subtitle());
        lblSubtitle.setAlignmentX(CENTER_ALIGNMENT);
        lblSubtitle.setForeground(UIColors.HOME_TITLE);

        panel.add(lblSubtitle);
		return panel;
    }
    
    public JMenuBar createMenu() {
    	RoundedMenuBar mb = new RoundedMenuBar();
    	mb.setFont(AppFont.big());
    	mb.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
    	mb.setOpaque(false);
    	mb.setBackground(new Color(0,0,0,0));
    	
        // SISTEMA
        JMenu sistema = new JMenu("Sistema");
        applyHover(sistema);
        sistema.setOpaque(true);
        sistema.setBorder(new EmptyBorder(5,15,5,15));
        sistema.setMnemonic(KeyEvent.VK_S);
        mb.add(sistema);

        btnHome = new JMenuItem("Inicio");
        btnHome.setMnemonic(KeyEvent.VK_I);
        sistema.add(btnHome);
        
        // HABITACIONES
    	JMenu juegos = new JMenu("Juegos");
    	applyHover(juegos);
    	juegos.setOpaque(true);
    	juegos.setBorder(new EmptyBorder(5,15,5,15));
    	juegos.setMnemonic(KeyEvent.VK_J);
        mb.add(juegos);

        JMenuItem verJuegos = new JMenuItem("Ver juegos");
        verJuegos.setMnemonic(KeyEvent.VK_V);
        juegos.add(verJuegos);

        // RESERVAS
        JMenu categorias = new JMenu("Categorías");
        applyHover(categorias);
        categorias.setOpaque(true);
        categorias.setBorder(new EmptyBorder(5,15,5,15));
        categorias.setMnemonic(KeyEvent.VK_C);
        mb.add(categorias);

        JMenuItem verCategorias = new JMenuItem("Ver categorías");
        verCategorias.setMnemonic(KeyEvent.VK_V);
        categorias.add(verCategorias);

        // INFORMACIÓN
        JMenu buscar = new JMenu("Buscar");
        applyHover(buscar);
        buscar.setOpaque(true);
        buscar.setBorder(new EmptyBorder(5,15,5,15));
        buscar.setMnemonic(KeyEvent.VK_B);
        mb.add(buscar);

        JMenuItem busqueda = new JMenuItem("Buscar un juego");
        busqueda.setMnemonic(KeyEvent.VK_B);
        buscar.add(busqueda);

        return mb;
    }

    //CONTENT
    public JPanel content(){
    	JPanel contentPanel = new JPanel();
    	contentPanel.setBackground(new Color(0,0,0));
    			
    	return contentPanel;
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
    
    //hover
    private void applyHover(JMenu menu) {
        Color normal = new Color(255,255,255);

        menu.setOpaque(true);
        menu.setBackground(normal);
        menu.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                menu.setBackground(UIColors.HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menu.setBackground(normal);
            }
        });
    }

    private JPanel createTransparentPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        return panel;
    }

}
