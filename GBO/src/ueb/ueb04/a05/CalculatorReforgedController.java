package ueb.ueb04.a05;

import javax.script.ScriptException;

import gui.calculator.Computation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorReforgedController
{
    @FXML
    private TextField display;

    private Computation c = new Computation();

    public void buttonClicked(ActionEvent e)
    {
        Button b = (Button) e.getSource();
        display.setText(display.getText() + b.getText());
    }

    public void clear(ActionEvent e)
    {
        display.setText("");
    }

    public void delete(ActionEvent e)
    {

        try
        {
            display.setText(display.getText().substring(0, display.getText().length() - 1));
        }
        catch (RuntimeException r)
        {

        }
    }

    public void calculate(ActionEvent e)
    {

        if (!"".equals(display.getText()))
        {
            try
            {
                System.out.print("=");
                display.setText(display.getText() + "=" + c.evaluate(display.getText()));
            }
            catch (ScriptException e1)
            {
                display.setText(display.getText() + "=>FEHLER");
            }
        }
    }
}
