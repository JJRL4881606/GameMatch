package components;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import models.Game;
import utils.AppFont;
import utils.ButtonFactory;
import utils.UIColors;

@SuppressWarnings("serial")
public class GameCard extends RoundedPanel {

    private RoundedButton btnViewGame;
    private Game game;

    public GameCard(Game game) {
        super(25);
        this.game = game;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(UIColors.CARD);
        setPreferredSize(new Dimension(240, 380));
        setMaximumSize(new Dimension(240, 380));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        initializeComponents();
    }

    private void initializeComponents() {
        // Imagen
        RoundedImagePanel imagePanel =
                new RoundedImagePanel(
                        game.getImagePath(),
                        170,
                        250,
                        20
                );
        imagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nombre
        JLabel lblName = new JLabel(game.getName());
        lblName.setFont(AppFont.subtitle());
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón
        btnViewGame = ButtonFactory.createButton(
                "Ver juego",
                "/assets/img/btn-icons/button-search-icon.png",
                "Ver información",
                180,
                55
        );

        btnViewGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar componentes
        add(imagePanel);
        add(Box.createVerticalGlue());
        add(lblName);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(btnViewGame);
    }

    public RoundedButton getBtnViewGame() {
        return btnViewGame;
    }

    public Game getGame() {
        return game;
    }
}