package tutorial.woche_09;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class TransparentShape extends Application
{

    private double xOffset, yOffset;

    @Override
    public void start(Stage stage) throws Exception
    {
        Polygon poly = new Polygon(200, 0, 400, 200, 0, 200);
        poly.setFill(Color.GREEN);

        poly.setOnMousePressed(e ->
        {
         
        });

        // scene.setFill(null);
        // stage.initStyle(StageStyle.TRANSPARENT);

        EventHandler<MouseEvent> mousePressed = (MouseEvent e) -> {
            xOffset = e.getScreenX();
            yOffset = e.getScreenY();   
        };
        
        EventHandler<MouseEvent> mouseDragged = (MouseEvent e) -> {
          stage.setX(e.getScreenX() - xOffset);
          stage.setY(e.getScreenY() - yOffset);
        }
        
        // TODO Auto-generated method stub

    }

}
