package views;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controllers.HomeController;
import controllers.MainController;

@SuppressWarnings("serial")
public class MainWindow extends JFrame 
{
	private MainView mainView;
	private JScrollPane scroll;

    public MainWindow() 
    {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("GameMatch");
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
        mainView = new MainView();
        scroll = createViewScroll(mainView);

        background.add(scroll, BorderLayout.CENTER);
        new MainController(mainView, this);
        new HomeController(mainView);
        
        this.setVisible(true);
    }
    
    private JScrollPane createViewScroll(JPanel panel) {
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }
    
    public MainView getMainView() {
        return mainView;
    }
    
	public JScrollPane getScroll() {
	    return scroll;
	}

}