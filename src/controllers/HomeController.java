package controllers;

import java.io.IOException;
import java.util.List;

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
        addListeners();
    }

    private void loadGames() {
        try {
            List<Game> games = repository.getGames();

            List<Game> featured = games.stream()
                    .filter(Game::getFeatured)
                    .toList();

            javax.swing.SwingUtilities.invokeLater(() -> {
                view.setGames(featured);
            });

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
            view.setSearchError("Solo se permiten letras");
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

        FormUtils.addFocusEffect(
                view.getTxtSearch(),
                view.getLblEmailError()
        );
    }
}