import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PieChartViewer 
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        final int FRAME_WIDTH  = 600;
        final int FRAME_HEIGHT = 500;

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("SuperEllipse Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // We will study component layout in GUI's. For now just copy this.
        frame.setLayout(new BorderLayout());
        JPanel panel2 = new JPanel();
        JPanel panel = new JPanel();
        // Add panel to the frame
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(panel,BorderLayout.NORTH);
        controlPanel.add(panel2,BorderLayout.CENTER);
        frame.add(controlPanel,BorderLayout.NORTH);

        // Create our superellipse component and add to the frame
        final PieChart chart = new PieChart();
        frame.add(chart,BorderLayout.CENTER);

        int FIELD_WIDTH = 3;
        final JLabel nameLabel = new JLabel("Slice Name:");
        final JTextField nameField = new JTextField(FIELD_WIDTH+2);
        final JLabel sizeLabel = new JLabel("Percent:");
        final JTextField sizeField = new JTextField(FIELD_WIDTH);

        //Sets color TextField
        final JLabel redLabel = new JLabel("Red:");
        final JTextField red = new JTextField(FIELD_WIDTH);
        red.setText("0");
        final JLabel greenLabel = new JLabel("Green:");
        final JTextField green = new JTextField(FIELD_WIDTH);
        green.setText("0");
        final JLabel blueLabel = new JLabel("Blue:");
        final JTextField blue = new JTextField(FIELD_WIDTH);
        blue.setText("0");
        final JLabel titleLabel = new JLabel("Title");
        final JTextField title = new JTextField(FIELD_WIDTH+5);

        panel2.add(nameLabel);
        panel2.add(nameField);
        panel.add(sizeLabel);
        panel.add(sizeField);
        panel.add(redLabel);
        panel.add(red);
        panel.add(greenLabel);
        panel.add(green);
        panel.add(blueLabel);
        panel.add(blue);
        panel2.add(titleLabel);
        panel2.add(title);

        JButton button = new JButton("Add Slice");

        class addSliceListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                String g = green.getText();
                int intG = Integer.parseInt(g);
                String r = red.getText();
                int intR = Integer.parseInt(r);
                String b = blue.getText();
                int intB = Integer.parseInt(b);

                String name = nameField.getText();
                String size = sizeField.getText();
                double doubleSize = Double.parseDouble(size);

                String a = title.getText();
                chart.setTitle(a);
                Color color = new Color(intR,intG,intB);
                chart.addSlice(name, color, doubleSize);
            }
        }

        addSliceListener listener = new addSliceListener();
        button.addActionListener(listener);
        panel2.add(button);
        class mousePressedListener implements MouseListener
        {   
            public void mousePressed(MouseEvent event)
            {
                double eventx = event.getX();
                double eventy = event.getY() - 92;
                System.out.println(eventx + "," + eventy);
                chart.deleteSlice(chart.selectSlice(eventx, eventy));
            }
            public void mouseReleased(MouseEvent event){}
            public void mouseClicked(MouseEvent event){}
            public void mouseEntered(MouseEvent event){}
            public void mouseExited(MouseEvent event){}
        }
        MouseListener mlistener = new mousePressedListener();
        frame.addMouseListener(mlistener);
        frame.setVisible(true);
       // frame.setResizable(false);
    }
}
