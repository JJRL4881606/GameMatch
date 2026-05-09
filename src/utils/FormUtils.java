package utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import components.RoundedBorder;
import components.RoundedTextField;

public class FormUtils {

    public static JPanel createField(String labelText, JComponent field, JLabel errorLabel, String placeholder, int width, int height) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        panel.setPreferredSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));

        if (labelText != null) {
            JLabel label = new JLabel(labelText);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
        }

        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setPreferredSize(new Dimension(width, height));
        field.setMaximumSize(new Dimension(width, height));
        field.setMinimumSize(new Dimension(width, height));

        if (field instanceof JTextField) {
            ((JTextField) field).putClientProperty(
                "JTextField.placeholderText",
                placeholder
            );
        }

        errorLabel.setForeground(UIColors.ERROR);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(field);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(errorLabel);

        return panel;
    }
    
    public static JLabel createErrorLabel() {
        JLabel label = new JLabel();
        label.setForeground(UIColors.ERROR);
        label.setFont(AppFont.small());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
    
    public static JTextField createTextField() {
    	JTextField field = new RoundedTextField();
    	field.setFont(AppFont.big());
        field.setBackground(UIColors.FIELD);
        field.setBorder(normalBorder);
        return field;
    }
    
    //bordes
    public static Border normalBorder = new RoundedBorder(UIColors.FIELD_BORDER, 2, 20);

	public static Border redBorder = new RoundedBorder(UIColors.ERROR, 2, 20);

	public static Border focusBorder = new RoundedBorder(UIColors.BACKGROUND, 2, 20);
                
    //image icon
    public static ImageIcon loadIcon(String path, int size) {
        try {
            Image img = ImageIO.read(FormUtils.class.getResource(path));
            img = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            System.out.println("No está la imagen del ícono");
            return null;
        }
    }
    
    public static ImageIcon loadRectangularIcon(String path, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(
                    FormUtils.class.getResource(path)
            );

            Image scaled = icon.getImage().getScaledInstance(
                    width,
                    height,
                    Image.SCALE_SMOOTH
            );

            return new ImageIcon(scaled);

        } catch (Exception e) {
            System.out.println("Error cargando imagen: " + path);
            return null;
        }
    }
    
    public static void addFocusEffect(JComponent field, JLabel errorLabel) {
        field.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(FormUtils.focusBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (errorLabel != null && !errorLabel.getText().isEmpty()) {
                    field.setBorder(FormUtils.redBorder);
                } else {
                    field.setBorder(FormUtils.normalBorder);
                }
            }
        });
    }	
    
	//manejar errores
    public static void showError(JLabel label, JComponent field, String message) {
        label.setText(message);
        field.setBorder(FormUtils.redBorder);
    }

    public static void clearError(JLabel label, JComponent field) {
        label.setText("");
        field.setBorder(FormUtils.normalBorder);
    }

    public static void clearLabel(JLabel label) {
        label.setText("");
    }

}