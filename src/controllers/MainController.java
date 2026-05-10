package controllers;

import java.awt.Point;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

import views.MainView;
import views.MainWindow;

public class MainController {
    private MainView view;
	private MainWindow frame;

	public MainController(MainView view, MainWindow frame) {
	    this.view = view;
	    this.frame = frame;
	    
	    initListeners();
	}
	
	private void initListeners() {		
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        handleClose();
		    }
		    
		    public void windowOpened(WindowEvent e) {
		        resetScroll();
		    }
		});
				
		view.menuHome.addActionListener(e -> {
		    view.showView(MainView.HOME);

		    updateMenuState(MainView.HOME);

		    frame.revalidate();
		    frame.repaint();

		    resetScroll();
		});
		
		view.menuGames.addActionListener(e -> {
		    view.showView(MainView.GAMES);

		    updateMenuState(MainView.GAMES);

		    frame.revalidate();
		    frame.repaint();

		    resetScroll();
		});
		
		view.menuCategories.addActionListener(e -> {
		    view.showView(MainView.CATEGORIES);

		    updateMenuState(MainView.CATEGORIES);

		    frame.revalidate();
		    frame.repaint();

		    resetScroll();
		});

	}
	
    private void handleClose() {
        Window window = SwingUtilities.getWindowAncestor(view);
        if (window != null) window.dispose();
    }
    
    private void updateMenuState(String viewName) {
    	view.menuHome.setEnabled(!viewName.equals(MainView.HOME));
    	view.menuGames.setEnabled(!viewName.equals(MainView.GAMES));
    	view.menuCategories.setEnabled(!viewName.equals(MainView.CATEGORIES));
    }
    
	private void resetScroll() {
	    SwingUtilities.invokeLater(() -> {
	        frame.getScroll().getViewport().setViewPosition(new Point(0, 0));
	    });
	}
}
