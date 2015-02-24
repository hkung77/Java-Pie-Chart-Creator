import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
public class PieChart extends JComponent 
{
    ArrayList<PieChartSlice> list;
    PieChartSlice slice;
    Rectangle boundry;
    Rectangle legBox;
    Rectangle legend;
    Ellipse2D.Double pie;
    double angle = 0;
    String title;
    /**
      manages all the pie chart slices and the pie chart.
      creates the outline for the PieChartViewer.
     */
    public PieChart()
    {
        list = new ArrayList<PieChartSlice>();
        boundry = new Rectangle(10,60,575,300);
        legend = new Rectangle(400,90,140,260);
        pie = new Ellipse2D.Double(50,85,250,250);
        legBox = new Rectangle();
    }
    /**
      updates the start angle for each slice
     */
    public double updateAngle()
    {
        angle = 0;
        //loops to updates start angle
        for(int i = 0; i < list.size(); i++)
        {
            angle += list.get(i).getAngleExtent();
        }
        return angle;
    }
    /**
      adds slice to list
      @param name, color, size
      then repaints
      return true if slice cannot fit in chart
      else return false
     */
    public boolean addSlice(String name, Color color, double size)
    {
        size =((size*360)/100);
        if((0 < size)&&(size + updateAngle() <= 360))
        {
            slice = new PieChartSlice(name, angle, size);
            slice.setName(name);
            slice.setColor(color);
            slice.setAngleExtent(size);
            slice.setAngleStart(updateAngle());
            list.add(slice);
            repaint();
            return true;
        }
        System.out.println("Cannot add slice, PieChart is too full or slice has no size");
        return false;
    }
    /**
    Colapse the slice in arraylist.
    */
    public void collapse()
    {   double newAngle = 0; 
        for (int i = 0; i < list.size(); i++)
        {
            if(list.get(i) != null)
            {
            list.get(i).setAngleStart(newAngle);
            newAngle += list.get(i).getAngleExtent();
            }
        }
    }
    /**
      deletes the slice by click
      then updates the arraylist 
      @param xSlice
     */
    public void deleteSlice(PieChartSlice xSlice)
    {
        list.remove(xSlice);
        collapse();
        repaint();
    }
    /**
      sets title for chart
      @param String a
     */
    public void setTitle(String a)
    {
        title = a;
    }
    /**
      returns title
     */
    public String getTitle()
    {
        return title;
    }
    /**
      loops through all the slices in list to find it x and y are in the size 
     */
    PieChartSlice selectSlice(double x, double y)
    {
        PieChartSlice empty = null;
        System.out.println(x +" "+ y);
        for( int i = 0; i < list.size(); i++)
        {
            if(list.get(i).contains(x,y))
            {
                System.out.println("slice found");
                empty = list.get(i);
                return empty;
            }
        }

        System.out.println(slice.getBounds2D());
        return empty;
    }
    /**
      paintComponent for the slice + frame
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        //loops for each slice in list, and draws slice
        for(int i = 0; i < list.size(); i++)
        {
            slice = list.get(i);
            g2.setColor(slice.getColor());
            slice.draw(g2);
            g2.setColor(Color.BLACK);
            Font font = new Font("Serif",Font.PLAIN, 20);
            g2.setFont(font);
            g2.drawString(getTitle(),250,50);
        }
        //loops for each slice in list, and draws String in legend
        for(int i = 0; i < 1; i++)
        {
            int x = 0;
            for(int j = 0; j < list.size(); j++)
            {
                slice = list.get(j);
                g2.setColor(Color.BLACK);
                Font font2 = new Font("Serif",Font.PLAIN, 12);
                g2.setFont(font2);
                g2.drawString(slice.getName(),425,110+x);
                g2.setColor(slice.getColor());
                legBox.setBounds((int)legend.getX()+10, (int)legend.getY()+10+x,10,10);
                g2.fill(legBox);
                x += 20;
            }
        }
        //draws veiwer outline
        g2.setColor(Color.BLACK);
        g2.draw(boundry);
        g2.draw(legend);
        g2.draw(pie);
        g2.drawString("Legend",450,80);
    }
}

