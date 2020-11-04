import com.company.CelestialBody;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.*;

public class NBody extends JPanel implements ActionListener {

    private static String dataStructure;
    private static double scale;
    private static List<CelestialBody> list;
    private Random randColor;
    private static ArrayList<Color> colors= new ArrayList<>();

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

    public void paintComponent(Graphics graphics)
    {
        Timer timer = new Timer(500, this);
//        Graphics2D gg = (Graphics2D) graphics;
        super.paintComponent(graphics);
//        int i = 0;
        for(int i = 0;i < list.size(); i++)
        {
            CelestialBody cb = list.get(i);
//            xCoord = cb.getxCoordinate();
//            double xDir = cb.getxDirection();
//            xCoord = xCoord + (int) xDir;
//            yCoord = cb.getyCoordinate();
//            double yDir = cb.getyDirection();
//            yCoord = yCoord + (int) yDir;
            graphics.setColor(colors.get(i));
            graphics.fillOval(cb.getxCoordinate(), cb.getyCoordinate(), cb.getSize(), cb.getSize());
//            gg.fillOval(xCoord, yCoord, 30, 30);
        }
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        int xCoord, yCoord;
        int xCoord2, yCoord2;
        double xDir, yDir;
        double xDir2, yDir2;
        double mass1, mass2;
        double distance, force;
        double gravity = 6.674 * Math.pow(10, -11);
        for (int i = 0; i < list.size(); i++) {
            CelestialBody cb = list.get(i);
            mass1 = cb.getMass();

            int j = 0;
            CelestialBody cb2 = list.get(j);
            for (j = 1; j < list.size() && i != j; j++) {
                cb2 = list.get(j);
                mass2 = cb2.getMass();
                distance = Math.sqrt(Math.pow((cb2.getyCoordinate() - cb.getyCoordinate()), 2) + Math.pow((cb2.getxCoordinate() - cb.getxCoordinate()), 2));
                force = gravity * (mass1*mass2)/distance;

                if(cb.getxCoordinate() > cb2.getxCoordinate()) {
                    cb.setxDirection(cb.getxDirection() + force);
                }
                else if (cb.getxCoordinate() < cb2.getxCoordinate()) {
                    cb.setxDirection(cb.getxDirection() - force);
                }
                if(cb.getyCoordinate() > cb2.getyCoordinate()) {
                    cb.setyDirection(cb.getyDirection() + force);
                }
                else if (cb.getyCoordinate() < cb2.getyCoordinate()) {
                    cb.setyDirection(cb.getyDirection() - force);
                }
                xCoord2 = cb2.getxCoordinate();
                xDir2 = cb2.getxDirection();
                cb2.setxCoordinate((int)(xCoord2 + xDir2));

                yCoord2 = cb2.getyCoordinate();
                yDir2 = cb2.getyDirection();
                cb.setyCoordinate((int)(yCoord2 + yDir2));

                if (xCoord2 < 0 || xCoord2 > 740) {
                    cb.setxCoordinate((int)(xCoord2 - xDir2));
                    cb.setyCoordinate((int)(yCoord2 - yDir2));
                }
                repaint();
            }
            xCoord = cb.getxCoordinate();
            xDir = cb.getxDirection();
            cb.setxCoordinate((int)(xCoord + xDir));

            yCoord = cb.getyCoordinate();
            yDir = cb.getyDirection();
            cb.setyCoordinate((int)(yCoord + yDir));

            if (xCoord < 0 || xCoord > 740) {
                cb.setxCoordinate((int)(xCoord - xDir));
                cb.setyCoordinate((int)(yCoord - yDir));
            }
            repaint();
        }
    }

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
        jf.setSize(768, 768); // Window size defined in the class
        jf.add(nbody); // This appears below "setVisible" in the video
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


//    public Double gravityFormula(CelestialBody p1, CelestialBody p2)
//    {
//        final double gravConstant = 9.8;
//        double xDistance = (p1.getxCoordinate()-p2.getxCoordinate())*scale;
//        double yDistance = (p1.getyCoordinate()-p2.getyCoordinate())*scale;
//        double trueDistance = Math.sqrt((xDistance*xDistance)+(yDistance*yDistance));
//        double acceleration = (gravConstant*((p1.getSize()*p2.getSize()))/trueDistance);
//        return acceleration;
//    }