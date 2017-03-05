package ch.iso.m426.view;

import ch.iso.m426.model.Card;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Created by Serafima on 15.01.2017.
 */
public class CardViewPane extends BorderPane{
    public CardViewPane(BorderPane cardManagerBorderPane) {
        super();

        
        //Create Logo
        Image image = new Image("file:./pic/mtg-logo.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        
        
        CardViewPaneButtonBar buttons = new CardViewPaneButtonBar(cardManagerBorderPane);
        
        this.setCenter(imageView);
        this.setBottom(buttons);

        
    }


}
