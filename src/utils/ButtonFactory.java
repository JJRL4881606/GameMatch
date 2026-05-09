package utils;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import components.RoundedButton;

public class ButtonFactory {

    public static RoundedButton createButton(
            String text,
            String iconPath,
            String tooltip,
            int width,
            int height
    ) {

        RoundedButton btn = new RoundedButton(
                text,
                new ImageIcon(ButtonFactory.class.getResource(iconPath))
        );

        btn.setBackground(UIColors.BUTTON);
        btn.setToolTipText(tooltip);
        btn.setFont(AppFont.big());
        btn.setForeground(UIColors.BUTTON_TEXT);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(width, height));
        btn.setMaximumSize(new Dimension(width, height));

        return btn;
    }
}