package gui.graphics.sinus;

import java.util.LinkedHashMap;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class SinusView extends VBox
{
    private Label formula;

    private Label amplitudeLabel;

    private Label frequencyLabel;

    private Label phaseLabel;

    private Label zoomLabel;

    private Slider amplitudeSlider, frequencySlider, phaseSlider, zoomSlider;

    private Path sinusCurve;

    private Line xAxis;

    private Line yAxis;

    private Pane drawingSpace;

    private SinusPresenter sinusPresenter;

    private Line upperLimit, lowerLimit;

    private GridPane sliders;

    public SinusView()
    {
        setPadding(new Insets(10, 10, 10, 10));

        formula = new Label("");
        formula.setFont(new Font("Times New Roman", 20));

        getChildren().add(formula);

        drawingSpace = new BorderPane();
        drawingSpace.setClip(sinusCurve);
        drawingSpace.setPrefWidth(400);
        drawingSpace.setPrefHeight(400);

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(drawingSpace.widthProperty());
        clip.heightProperty().bind(drawingSpace.heightProperty());
        drawingSpace.setClip(clip);

        xAxis = new Line(780 / 2, 0, 780 / 2, 300);
        yAxis = new Line(0, 300 / 2, 780, 300 / 2);

        drawingSpace.getChildren().addAll(xAxis, yAxis);

        drawingSpace.setOnMouseMoved(e -> System.out.println(e.getX() + ", " + e.getY()));

        sinusCurve = new Path();
        sinusCurve.setStroke(Color.BLACK);
        sinusCurve.setStrokeWidth(1);

        drawingSpace.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1))));

        drawingSpace.getChildren().add(sinusCurve);
        getChildren().add(drawingSpace);

        sliders = new GridPane();

        amplitudeLabel = new Label("Amplitude: ");
        amplitudeLabel.setFont(new Font("Times New Roman", 20));
        sliders.add(amplitudeLabel, 0, 0);
        amplitudeSlider = new Slider(-6.0, 6.0, 0.1);
        amplitudeSlider.setId("amplitude");
        sliders.add(amplitudeSlider, 1, 0);
        amplitudeSlider.setShowTickMarks(true);
        amplitudeSlider.setShowTickLabels(true);
        amplitudeSlider.setMinorTickCount(1);
        amplitudeSlider.setMajorTickUnit(2);
        amplitudeSlider.setPrefWidth(600);

        frequencyLabel = new Label("Frequency: ");
        frequencyLabel.setFont(new Font("Times New Roman", 20));
        sliders.add(frequencyLabel, 0, 1);
        frequencySlider = new Slider(0, 40, 0.1);
        frequencySlider.setId("frequency");
        sliders.add(frequencySlider, 1, 1);
        frequencySlider.setShowTickLabels(true);
        frequencySlider.setShowTickMarks(true);
        frequencySlider.setMinorTickCount(2);
        frequencySlider.setMajorTickUnit(10);
        frequencySlider.setPrefWidth(600);

        phaseLabel = new Label("Phase: ");
        phaseLabel.setFont(new Font("Times New Roman", 20));
        sliders.add(phaseLabel, 0, 2);
        phaseSlider = new Slider(-10, 10, 0.1);
        phaseSlider.setId("phase");
        sliders.add(phaseSlider, 1, 2);
        phaseSlider.setShowTickLabels(true);
        phaseSlider.setShowTickMarks(true);
        phaseSlider.setMajorTickUnit(5);
        phaseSlider.setPrefWidth(600);

        zoomLabel = new Label("Zoom: ");
        zoomLabel.setFont(new Font("Times New Roman", 20));
        sliders.add(zoomLabel, 0, 3);
        zoomSlider = new Slider(10, 210, 1);
        zoomSlider.setId("zoom");
        sliders.add(zoomSlider, 1, 3);
        zoomSlider.setShowTickLabels(true);
        zoomSlider.setShowTickMarks(true);
        zoomSlider.setMajorTickUnit(100);
        zoomSlider.setMinorTickCount(1);
        zoomSlider.setPrefWidth(600);

        getChildren().add(sliders);

        upperLimit = new Line(0, scaleY(1), 780, scaleY(1));
        upperLimit.setStrokeWidth(1);
        upperLimit.setStroke(Color.DARKRED);
        lowerLimit = new Line(0, scaleY(-1), 780, scaleY(-1));
        lowerLimit.setStrokeWidth(1);
        lowerLimit.setStroke(Color.DARKRED);

        drawingSpace.getChildren().addAll(upperLimit, lowerLimit);

        amplitudeSlider.valueProperty().addListener((p, o, n) -> sinusPresenter.updateAmplitude((double) n));
        zoomSlider.valueProperty().addListener((p, o, n) -> sinusPresenter.updateRange((int) Math.round((double) n)));
        phaseSlider.valueProperty().addListener((p, o, n) -> sinusPresenter.updatePhase((double) n));
        frequencySlider.valueProperty().addListener((p, o, n) -> sinusPresenter.updateFrequency((double) n));

    }

    public void updateCurve(LinkedHashMap<Double, Double> pts)
    {
        sinusCurve.getElements().clear();
        boolean set = false;
        System.out.println(pts.size());
        for (double x : pts.keySet())
        {
            if (!set)
            {
                sinusCurve.getElements().add(new MoveTo(scaleX(x), scaleY(pts.get(x))));
                set = true;
            }
            sinusCurve.getElements().add(new LineTo(scaleX(x), scaleY(pts.get(x))));

            // System.out.println("Line to:" + scaleX(x) + " , " +
            // scaleY(pts.get(x)));
        }

    }

    public double scaleX(double x)
    {

        System.out.println(779 * (0 + zoomSlider.getValue()) / (zoomSlider.getValue() + zoomSlider.getValue()));
        System.out.println(zoomSlider.getValue());
        return 780 * ((x * zoomSlider.getValue() / 100.0) + amplitudeSlider.getValue()) / (amplitudeSlider.getValue() + amplitudeSlider.getValue());
    }

    public double scaleY(double y)
    {
        return 300 * (amplitudeSlider.getValue() - (y * zoomSlider.getValue() / 100.0)) / (amplitudeSlider.getValue() + amplitudeSlider.getValue());
    }

    public void setFormula(String formula)
    {
        this.formula.setText(formula);
    }

    public void setSinusPresenter(SinusPresenter sinusPresenter)
    {
        this.sinusPresenter = sinusPresenter;
    }
}
