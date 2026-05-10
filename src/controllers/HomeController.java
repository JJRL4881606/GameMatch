package controllers;

import java.io.IOException;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.Game;
import repository.GameRepository;
import utils.FormUtils;
import utils.Validator;
import views.HomeView;
import views.MainView;

public class HomeController {

    private MainView mainView;
    private HomeView view;
    private GameRepository repository;

    public HomeController(MainView mainView) {
        this.mainView = mainView;
        this.view = mainView.homePanel;
        this.repository = new GameRepository();

        loadGames();
        initListeners();
    }
    
    private void initListeners() {

    	view.getBtnSearch().addActionListener(e -> {
    	    if (validateSearch()) {
    	        handleSearch();
    	    }
    	});
    	
        view.getTxtSearch().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { validateSearch(); }
            public void removeUpdate(DocumentEvent e) { validateSearch(); }
            public void changedUpdate(DocumentEvent e) { validateSearch(); }
        });

        FormUtils.addFocusEffect(
                view.getTxtSearch(),
                view.getLblEmailError()
        );
        
        view.getTxtSearch().addActionListener(e -> {
            view.getBtnSearch().doClick();
        });
    }

    private void loadGames() {
        try {
            List<Game> games = repository.getGames();

            List<Game> featured = games.stream()
                    .filter(Game::getFeatured)
                    .toList();

            SwingUtilities.invokeLater(() -> {
                view.setGames(featured);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void handleSearch() {
    	List<Game> results;
		try {
			results = repository.search(view.getSearch());
	    	mainView.searchPanel.setGames(results);
	    	mainView.searchPanel.setSearchText(view.getSearch());
	    	mainView.menuHome.setEnabled(true);
	    	mainView.showView(MainView.SEARCH);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public boolean validateSearch() {
        String search = view.getSearch();

        if (search.isEmpty()) {
            view.setSearchError("Ingresa un juego");
            return false;

        } else if (!Validator.isValidGame(search)) {
            view.setSearchError("Caracteres no permitidos");
            return false;
        }

        view.clearSearchError();
        return true;
    }
}