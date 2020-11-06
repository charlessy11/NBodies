import com.company.CelestialBody;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class NBody extends JPanel implements ActionListener {

    private static String dataStructure;
    private static double scale;
    private static List<CelestialBody> list;
    private Random randColor;
    private static ArrayList<Color> colors= new ArrayList<>();
    //Read the data from input file
    public void readFile(File file) throws Exception{
        //create buffered reader
        BufferedReader br = new BufferedReader(new FileReader(file));
        //read first line from input text
        dataStructure = br.readLine();
        //check if input file states "ArrayList"
        if (dataStructure.equals("ArrayList")) {
            //create ArrayList
            list = new ArrayList<>();
            //check if input file states "LinkedList"
        } else if (dataStructure.equals("LinkedList")){
            //create LinkedList
            list = new LinkedList<>();
        }
        //read line
        scale = Double.valueOf(br.readLine());
        String str;
        this.randColor = new Random();
        //loop through file
        while ((str = br.readLine()) != null) {
            String [] arr = str.split(",");
            System.out.println(Arrays.toString(arr));
            list.add(new CelestialBody(arr[0], Double.parseDouble(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Double.parseDouble(arr[4]), Double.parseDouble(arr[5]), Integer.parseInt(arr[6])));
            float r = this.randColor.nextFloat();
            float g = this.randColor.nextFloat();
            float b = this.randColor.nextFloat();
            colors.add(new Color(r,g,b));
        }
        System.out.println(list.toString());
    }
    //Set random colors to the celestial bodies
    public void paintComponent(Graphics graphics)
    {
        Timer timer = new Timer(500, this);
        super.paintComponent(graphics);
        for(int i = 0;i < list.size(); i++)
        {
            CelestialBody cb = list.get(i);
            graphics.setColor(colors.get(i));
            graphics.fillOval(cb.getxCoordinate(), cb.getyCoordinate(), cb.getSize(), cb.getSize());
        }
        timer.start();
    }
    //Calculate distance
    public double distance(double xDist, double yDist)
    {
        return (xDist - yDist) * scale;
    }

    double gravity = 6.67408e-11;
    //Calculate force of gravity between 2 celestial bodies with respect to their distance
    public double gravitationalPull(double mass1, double mass2, double distance)
    {
        return gravity * (mass1 * mass2) / (Math.pow(distance, 2));
    }
    //change positions of celestial bodies, factoring in outside forces
    public void actionPerformed(ActionEvent e) {
        double xChangeOfVelocity = 0.0;
        double yChangeOfVelocity = 0.0;
        for (int i = 0; i < list.size(); i++) {
            CelestialBody cb1 = list.get(i);
            for (int j = 0; j < list.size() && i != j; j++) {
                CelestialBody cb2 = list.get(j);
                double xDistance = distance(cb1.getxCoordinate(),cb2.getxCoordinate());
                double yDistance = distance(cb1.getyCoordinate(),cb2.getyCoordinate());
                double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
                double force = gravitationalPull(cb1.getMass(),cb2.getMass(),distance);
                double xGravity = force * xDistance / distance;
                double yGravity = force * yDistance / distance;
                //check for possible collisions and out of frame movements
                if(cb1.getxCoordinate() < cb2.getxCoordinate()) {
                    xChangeOfVelocity -= xGravity;
                }
                else if(cb1.getxCoordinate() > cb2.getxCoordinate()) {
                    xChangeOfVelocity += xGravity;
                }
                else {
                    xChangeOfVelocity = 0.0;
                }
                if(cb1.getyCoordinate()-cb2.getyCoordinate() == 0)
                {
                    yGravity = 0.0;
                }
                if(cb1.getyCoordinate() < cb2.getyCoordinate()) {
                    yChangeOfVelocity -= yGravity;
                }
                else if(cb1.getyCoordinate() > cb2.getyCoordinate()) {
                    yChangeOfVelocity += yGravity;
                }
                else {
                    yChangeOfVelocity = 0.0;
                }
            }
            //update the celestial bodies' velocity
            cb1.setxDirection(cb1.getxDirection() + xChangeOfVelocity / scale / cb1.getMass());
            cb1.setyDirection(cb1.getyDirection() + yChangeOfVelocity / scale / cb1.getMass());
            cb1.setxCoordinate((int) (cb1.getxCoordinate() + cb1.getxDirection()));
            cb1.setyCoordinate((int) (cb1.getyCoordinate() + cb1.getyDirection()));
        }
        repaint();
    }
    //Main
    public static void main (String[] args) {
        File file = new File("nbody_input.txt");
        NBody nbody = new NBody();
        try {
            nbody.readFile(file);
        } catch (Exception E) {
            System.out.println("Unable to read file.");
            return;
        }
        System.out.println(dataStructure + " " + scale);
        JFrame jf = new JFrame();
        jf.setTitle("Celestial Bodies");
        jf.setSize(768, 768);
        jf.add(nbody);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
