package components;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import utils.AppFont;
import utils.ButtonFactory;
import utils.UIColors;

@SuppressWarnings("serial")
public class CategoryCard extends RoundedPanel {

    private String category;
    private RoundedButton btnViewGames;

    public CategoryCard(String category) {
        super(25);
        this.category = category;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(UIColors.CARD);
        setPreferredSize(new Dimension(240, 160));
        setMaximumSize(new Dimension(240, 160));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        initializeComponents();
    }

    private void initializeComponents() {
        JLabel lblName = new JLabel(category);
        lblName.setFont(AppFont.subtitle());
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnViewGames = ButtonFactory.createButton(
                "Ver juegos",
                "/assets/img/btn-icons/button-search-icon.png",
                "Ver juegos de esta categoría",
                180,
                55
        );
        btnViewGames.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(lblName);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(btnViewGames);
        add(Box.createVerticalGlue());
    }

    public String getCategory() {
        return category;
    }

    public RoundedButton getBtnViewGames() {
        return btnViewGames;
    }
}