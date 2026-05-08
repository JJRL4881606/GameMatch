package views;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controllers.HomeController;

@SuppressWarnings("serial")
public class MainWindow extends JFrame 
{
	private MainView HomeView;

    public MainWindow() 
    {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("GameMatch");
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());
        setContentPane(background);    

        // Agregar icono
        Image icon = Toolkit.getDefaultToolkit().getImage(
    	    getClass().getResource("/assets/img/icons/game-icon.png")
    	);
        setIconImage(icon);
         
        // Agregar el panel con scroll
        HomeView = new MainView();
        add(createViewScroll(HomeView), BorderLayout.CENTER);
        new HomeController(HomeView);
        
        this.setVisible(true);
    }
    
    private JScrollPane createViewScroll(JPanel panel) {
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }
    
    public MainView getHomeView() {
        return HomeView;
    }
}