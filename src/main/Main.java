package main;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.formdev.flatlaf.FlatLightLaf;
import utils.AppFont;
import views.MainWindow;

public class Main {

    @SuppressWarnings("unused")
	public static void main(String[] args) {
    	FlatLightLaf.setup();
        UIManager.put("defaultFont", new FontUIResource(AppFont.normal()));
        MainWindow ventanita = new MainWindow();
    }

}
