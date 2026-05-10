package views;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import components.CategoryCard;
import components.GameCard;
import models.Game;
import utils.AppFont;

@SuppressWarnings("serial")
public class CategoriesView extends JPanel {

    private JLabel lblResults;
    private JPanel categoriesContainer;
    
    public CategoriesView() {
        setBackground(Color.WHITE);
        setOpaque(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initializeComponents();
    }

    private void initializeComponents() {
        add(Box.createRigidArea(new Dimension(0, 30)));

	    add(wrapSection(createResultsSection()));
    }

    private JPanel createResultsSection() {

        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setOpaque(false);

        // HEADER
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        lblResults = new JLabel("Categorías");
        lblResults.setFont(AppFont.title());
        lblResults.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(lblResults);

        // GRID
        categoriesContainer = new JPanel(new GridLayout(0, 4, 20, 20));
        categoriesContainer.setOpaque(false);

        section.add(headerPanel);
        section.add(Box.createRigidArea(new Dimension(0, 20)));
        section.add(categoriesContainer);
        section.add(Box.createRigidArea(new Dimension(0, 20)));

        return section;
    }

    public void setGames(List<Game> games) {
    	categoriesContainer.removeAll();

        if (games.isEmpty()) {
            JLabel lblNoResults = new JLabel("Sin resultados");
            lblNoResults.setFont(AppFont.subtitle());
            lblNoResults.setAlignmentX(Component.CENTER_ALIGNMENT);

            categoriesContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
            categoriesContainer.add(lblNoResults);
        } else {
        	categoriesContainer.setLayout(new GridLayout(0, 4, 20, 20));
            for (Game game : games) {
            	categoriesContainer.add(new GameCard(game));
            }
        }

        categoriesContainer.revalidate();
        categoriesContainer.repaint();
        categoriesContainer.setPreferredSize(
        		categoriesContainer.getLayout().preferredLayoutSize(categoriesContainer)
        );

        this.revalidate();
        this.repaint();
    }    
    private JPanel wrapSection(JPanel section) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 30));
        wrapper.setOpaque(false);
        wrapper.add(section);
        return wrapper;
    }

    public void setSearchText(String text) {
        lblResults.setText("Resultados para: \"" + text + "\"");
    }
    
    public void setGamesByCategories(List<String> categories) {

        categoriesContainer.removeAll();

        for (String category : categories) {
            categoriesContainer.add(new CategoryCard(category));
        }

        categoriesContainer.revalidate();
        categoriesContainer.repaint();

        categoriesContainer.setPreferredSize(
            categoriesContainer.getLayout()
                .preferredLayoutSize(categoriesContainer)
        );

        revalidate();
        repaint();
    }
}