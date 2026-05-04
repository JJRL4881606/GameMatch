package controllers;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import utils.FormUtils;
import utils.Validator;
import views.HomeView;
import views.MainView;

public class HomeController {

	private MainView mainView;
	private HomeView view;

	public HomeController(MainView mainView) {        
	    this.mainView = mainView;
	    this.view = mainView.homePanel;
	    addListeners();
	}
	
	public boolean validateSearch() {
		String search = view.getSearch();
	    
	    if (search.isEmpty()) {
	        view.setSearchError("El juego es obligatorio");
	        return false;
	    } else if (!Validator.isValidGame(search)) {
	        view.setSearchError("Solo se permiten letras y ");
	        return false;
	    }
	    
	    view.clearSearchError();
		return true;
	}
    
    private void addListeners() {

    	view.getBtnSearch().addActionListener(e -> {

    	    if (validateSearch()) {
    	        mainView.showView(MainView.SEARCH);
    	    }
    	});
    	
        view.getTxtSearch().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validateSearch(); }
            public void removeUpdate(DocumentEvent e) { validateSearch(); }
            public void changedUpdate(DocumentEvent e) { validateSearch(); }
        });
        
        mainView.btnHome.addActionListener(e -> {
            mainView.showView(MainView.HOME);
        });
                
        FormUtils.addFocusEffect(view.getTxtSearch(), view.getLblEmailError());
    }

}
