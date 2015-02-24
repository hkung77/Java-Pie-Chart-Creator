import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Arc2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class PieChartSlice extends Arc2D.Double
{
    final double x = 50;
    final double y = 85;
    final double w = 250;
    final double h = 250;
    double angle;
    String name;
    Color color;
    /**
        creates the slice for a pie chart.
        @param name, angleStart, and extent
    */
    public PieChartSlice(String name,double angle, double extent)
    {
        super();
        this.name = name;
        this.angle = angle;
        setArc(x,y,w,h,0,0,Arc2D.PIE);
    }
    /**
        creates and empty slice.
    */
    public PieChartSlice()
    {
    }
    /**
        sets the name for the slice
        @param name
    */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
    returns name
    */
    public String getName()
    {
        return name;
    }
    /**
    sets color for each slice
    */
    public void setColor(Color color)
    {
        this.color = color;
    }
    /**
    returns color
    */
    public Color getColor()
    {
        return color;
    }
    /**
    sets the starting angle for slice 
    @param angle
    */
    public void setAngleStart(double angle)
    {
        super.setAngleStart(angle);
    }
    /**
    returns the starting angle
    */
    public double getAngleStart()
    {
        return super.getAngleStart();
    }
    /**
      sets the size for each slice
      @param extent
     */
    public void setAngleExtent(double extent)
    {
        super.setAngleExtent(extent);
    }
    /**
      returns the size for each slice
     */
    public double getAngleExtent()
    {
        return super.getAngleExtent();
    }
    /**
    draws each slice
    */
    public void draw(Graphics2D g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fill(this);   

    }
}
