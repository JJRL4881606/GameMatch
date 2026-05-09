package views;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
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

        lblResults = new JLabel("Resultados");
        lblResults.setFont(AppFont.title());
        lblResults.setAlignmentX(CENTER_ALIGNMENT);

        add(lblResults);
        add(Box.createRigidArea(new Dimension(0, 30)));

        gamesContainer = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 20, 20)
        );
        gamesContainer.setOpaque(false);

        add(gamesContainer);
    }

    public void setGames(List<Game> games) {

        gamesContainer.removeAll();

        for (Game game : games) {
            GameCard card = new GameCard(game);
            gamesContainer.add(card);
        }

        revalidate();
        repaint();
    }

    public void setSearchText(String text) {
        lblResults.setText(
                "Resultados para: \"" + text + "\""
        );
    }
}