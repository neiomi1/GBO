package gui.graphics.sinus;

import java.util.LinkedHashMap;
import java.util.stream.IntStream;

public class SinusModel
{
    private LinkedHashMap<Double, Double> points;

    private double amplitude;

    private double frequency;

    private double phase;

    private int range;

    public SinusModel()
    {
        points = new LinkedHashMap<Double, Double>();
        amplitude = 1;
        frequency = 1;
        phase = 0;
        range = 10;
        updateModel();

    }

    public void updateModel()
    {
        points.clear();
        for (double n : IntStream.rangeClosed((int) (-100 * (frequency + amplitude + 1)), (int) (100 * (frequency + amplitude + 1))).mapToDouble(i -> i / 100.0).toArray())
        {
            points.put(n, calculatePoint(n));
        }
    }

    public String getFormula()
    {
        return String.format("%.2f * sin(%.2f * x + %.2f)", amplitude, frequency, phase);
    }

    public LinkedHashMap<Double, Double> getPoints()
    {
        updateModel();
        return this.points;
    }

    public double calculatePoint(double x)
    {
        return amplitude * Math.sin(frequency * x + phase);
    }

    public void setAmplitude(double amplitude)
    {
        this.amplitude = amplitude;
    }

    public void setFrequency(double frequency)
    {
        this.frequency = frequency;
    }

    public void setPhase(double phase)
    {
        this.phase = phase;
    }

    public void setRange(int range)
    {
        this.range = range;
    }

    public double getAmplitude()
    {
        return amplitude;
    }

    public double getFrequency()
    {
        return frequency;
    }

    public double getPhase()
    {
        return phase;
    }

    public int getRange()
    {
        return range;
    }

    public static void main(String[] args)
    {
        SinusModel model = new SinusModel();
        model.updateModel();

        System.out.println(model.points.size());
        for (double n : model.points.keySet())
        {
            System.out.println(String.format("X: %f\tY: %f", n, model.points.get(n)));
        }
    }
}
