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

import components.GameCard;
import models.Game;
import utils.AppFont;

@SuppressWarnings("serial")
public class SearchView extends JPanel {

    private JLabel lblResults;
    private JPanel gamesContainer;

    public SearchView() {
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

        lblResults = new JLabel("Resultados");
        lblResults.setFont(AppFont.title());
        lblResults.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(lblResults);

        // GRID
        gamesContainer = new JPanel(new GridLayout(0, 4, 20, 20));
        gamesContainer.setOpaque(false);

        section.add(headerPanel);
        section.add(Box.createRigidArea(new Dimension(0, 20)));
        section.add(gamesContainer);
        section.add(Box.createRigidArea(new Dimension(0, 20)));

        return section;
    }

    public void setGames(List<Game> games) {

        gamesContainer.removeAll();

        for (Game game : games) {
            gamesContainer.add(new GameCard(game));
        }

        gamesContainer.revalidate();
        gamesContainer.repaint();
        
        gamesContainer.setPreferredSize(gamesContainer.getLayout().preferredLayoutSize(gamesContainer));

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
}