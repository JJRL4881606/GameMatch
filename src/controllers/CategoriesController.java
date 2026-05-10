package controllers;

import java.io.IOException;
import java.util.List;

import repository.GameRepository;
import views.CategoriesView;
import views.MainView;

public class CategoriesController {

    private MainView mainView;
    private CategoriesView view;
    private GameRepository repository;

    public CategoriesController(MainView mainView) {

        this.mainView = mainView;
        this.view = mainView.categoriesPanel;
        this.repository = new GameRepository();

        loadCategories();
        initListeners();
    }

    private void initListeners() {
    	mainView.menuCategories.addActionListener(e -> {
    	    mainView.showView(MainView.CATEGORIES);
    	});
    }

    private void loadCategories() {

        try {

            List<String> categories =
            		repository.getCategories();
            view.setGamesByCategories(categories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}