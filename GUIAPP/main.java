package GUIAPP;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JOptionPane;

public class main extends JFrame {

    // An object of Algorithm
    Algorithm m = new Algorithm();

    // Some useless stuff created by eclipse
    private static final long serialVersionUID = 1L;

    // Message to display when user click HELP button
    String helpMessage = "<html><br><b>Information Management of Water Supply : </b><br>" +
            "<br>First, add all the required Pipe (vertices)." +
            "<br>A Pipe will be created at the point where you click on Map." +
            "<br>Pipe will be named in increasing number starting from 0, automatically." +
            "<br>After creating all the Nodes, click on the Begin Button." +
            "<br>Then join any two Pipes." +
            "<br>Enter the name of the Pipe in \"From\" and \"To\" textfield to join the two Nodes." +
            "<br>After creating all the Pipe Line, enter \"Source\" Pipe and \"Destination\" Pipe to" +
            "<br>Calculate the Shortest distance between them." +
            "<br><br><i><b>Developed By :<font color=\"blue\"> Satish Pradhan</font>" +
            "<br>Email : pradhansatish939@gmail.com <font color=\"blue\"></font></i><b></html>";






    // Adjacency matrix (used to get minimum distance and path)
    int[][] adjacency;

    // Some GUI components
    JPanel upperPanel, lowerPanel, quotePanel, bottomPanel,  resultPanel,sidePanel;
    JButton setDistance, calculate, start, help, reset, SaveData, Info,logout;
    JTextField fromTextfield, toTextfield, initialTextfield, finalTextfield, valueTextfield,costTextfield;
    JLabel fromLabel, toLabel, initialLabel, finalLabel, valueLabel, costlabel, resultLabel, quote, helpLabel,control;

    // Variable that will be used to name Nodes
    int count = 0;

    // ArrayList to store x-y co-ordinates of Nodes
    ArrayList<Integer> x_pos = new ArrayList<Integer>();
    ArrayList<Integer> y_pos = new ArrayList<Integer>();

    // Constructor
    public main() throws IOException {


        String input = "";

        BufferedReader reader = new BufferedReader(new FileReader("GUIAPP/info.txt"));
        String line = null;
        while ((line = reader.readLine()) != null) {

            input += line + "\n";
        }
        reader.close();

        JTextArea textArea = new JTextArea(input);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane.setPreferredSize( new Dimension( 800,600 )) ;



        setLayout(new BorderLayout());
        Font textFont = new Font("Nunito", Font.PLAIN, 18);

        // RouteManagement Icon's Image
        try{
            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("abc.png"));
            ImageIcon icon = new ImageIcon(image);
            setIconImage(icon.getImage());
        }catch(Exception e){}

        // Quote Panel ( green one )
        quote = new JLabel("Information Management of Water Supply", SwingConstants.CENTER);

        quote.setFont(new Font("Bell MT", Font.ITALIC, 24));
        quote.setForeground(Color.WHITE);

        quotePanel = new JPanel();
        quotePanel.setBackground(new Color(234, 229, 9));
        quotePanel.add(quote);
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(44, 51, 51));
        sidePanel.setLayout(new GridLayout(0, 1));

        // Working Panel ( gray one )

        upperPanel = new JPanel();
        JLabel imagelabel = new JLabel();
        imagelabel.setIcon(new ImageIcon("GUIAPP/map.jpg"));
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(imagelabel);
//        upperPanel.setBounds(0,0,1920,1080);

//        upperPanel.setBackground(new Color(102, 204, 0)); // Alternative (191, 201, 202)

        // Bottom Panel ( pink and blue one )


        // Pink Panel
        resultLabel = new JLabel("Connect Pipe from Source to House...   ( Click HELP for More Information )", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Nunito", Font.PLAIN, 20));
        resultLabel.setForeground(Color.WHITE);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBackground(new Color(125, 206, 19));
        resultPanel.add(resultLabel, BorderLayout.CENTER);





        // Blue Panel
        fromTextfield = new JTextField(2);
        toTextfield = new JTextField(2);
        initialTextfield = new JTextField(2);
        finalTextfield = new JTextField(2);
        valueTextfield = new JTextField(3);
        costTextfield = new JTextField(4);

        fromLabel = new JLabel("From:", SwingConstants.CENTER);
        fromLabel.setFont(textFont);
        toLabel = new JLabel("To:", SwingConstants.CENTER);
        toLabel.setFont(textFont);
        initialLabel = new JLabel("Source:", SwingConstants.CENTER);
        initialLabel.setFont(textFont);
        finalLabel = new JLabel("Destination:", SwingConstants.CENTER);
        finalLabel.setFont(textFont);
        valueLabel = new JLabel("Distance:", SwingConstants.CENTER);
        valueLabel.setFont(textFont);
        costlabel = new JLabel("Info:", SwingConstants.CENTER);
        costlabel.setFont(textFont);

        setDistance = new JButton("Set Distance");
        setDistance.setFont(new Font("Bell MT", Font.BOLD, 14));
        setDistance.setBackground(new Color(238, 209, 128));
        SaveData = new JButton("Save Data");
        SaveData.setFont(new Font("Bell MT", Font.BOLD, 14));
        SaveData.setBackground(new Color(238, 209, 128));
        calculate = new JButton("Short Distance");
        calculate.setFont(new Font("Bell MT", Font.BOLD, 14));
        calculate.setBackground(new Color(238, 209, 128));
        start = new JButton("Begin");
        start.setFont(new Font("Bell MT", Font.BOLD, 25));
        start.setBackground(new Color(232, 170, 66));
        reset = new JButton("Reset");
        reset.setFont(new Font("Bell MT", Font.BOLD, 25));
        reset.setHorizontalAlignment(SwingConstants.CENTER);
        reset.setPreferredSize(new Dimension(100, 200));
        reset.setBackground(new Color(238, 209, 128));
        logout = new JButton("Quit");
        logout.setFont(new Font("Bell MT", Font.BOLD, 25));
        logout.setHorizontalAlignment(SwingConstants.CENTER);
        logout.setPreferredSize(new Dimension(100, 200));
        logout.setBackground(new Color(209, 81, 45));
        Info = new JButton("See Information");
        Info.setFont(new Font("Bell MT", Font.BOLD, 20));
        Info.setBackground(new Color(238, 209, 128));
        Info.setHorizontalAlignment(SwingConstants.CENTER);
        Info.setPreferredSize(new Dimension(100, 200));
        help = new JButton("HELP");
        help.setFont(new Font("Bell MT", Font.BOLD, 25));
        help.setBackground(new Color(238, 209, 128));
        help.setHorizontalAlignment(SwingConstants.CENTER);
        help.setPreferredSize(new Dimension(200, 200));
        helpLabel = new JLabel(helpMessage);
        helpLabel.setFont(new Font("Nunito", Font.PLAIN, 20));


        fromTextfield.setEnabled(false);
        toTextfield.setEnabled(false);
        valueTextfield.setEnabled(false);
        costTextfield.setEnabled(false);
        setDistance.setEnabled(false);
        SaveData.setEnabled(false);
        initialTextfield.setEnabled(false);
        finalTextfield.setEnabled(false);
        calculate.setEnabled(false);

        lowerPanel = new JPanel();
        lowerPanel.setBackground(new Color(212, 246, 204));
        lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        sidePanel.add(start);
        lowerPanel.add(fromLabel);
        lowerPanel.add(fromTextfield);
        lowerPanel.add(toLabel);
        lowerPanel.add(toTextfield);
        lowerPanel.add(valueLabel);
        lowerPanel.add(valueTextfield);
        lowerPanel.add(costlabel);
        lowerPanel.add(costTextfield);
        lowerPanel.add(SaveData);
        lowerPanel.add(setDistance);
        lowerPanel.add(new JSeparator(SwingConstants.VERTICAL));
        lowerPanel.add(initialLabel);
        lowerPanel.add(initialTextfield);
        lowerPanel.add(finalLabel);
        lowerPanel.add(finalTextfield);
        lowerPanel.add(calculate);
        sidePanel.add(Info);
        sidePanel.add(help);
        sidePanel.add(reset);
        sidePanel.add(logout);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        bottomPanel.add(lowerPanel, BorderLayout.CENTER);
        bottomPanel.add(resultPanel, BorderLayout.NORTH);

        add(upperPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(quotePanel, BorderLayout.NORTH);
        add(sidePanel, BorderLayout.EAST);




        // when you click Start button, this will happen ...
        start.addActionListener(e -> {
            adjacency = new int[count][count];
            fromTextfield.setText("");
            toTextfield.setText("");
            valueTextfield.setText("");
            costTextfield.setText("");
            fromTextfield.requestFocus();
            start.setEnabled(false);
            fromTextfield.setEnabled(true);
            toTextfield.setEnabled(true);
            valueTextfield.setEnabled(true);
            costTextfield.setEnabled(true);
            setDistance.setEnabled(true);
            SaveData.setEnabled(true);
            initialTextfield.setEnabled(true);
            finalTextfield.setEnabled(true);
            calculate.setEnabled(true);
        });

        // when you hit enter in fromTextfield, control will move to toTextfield
        fromTextfield.addActionListener(text -> toTextfield.requestFocus());

        // when you hit enter in toTextfield, control will move to valueTextfield
        toTextfield.addActionListener(text -> valueTextfield.requestFocus());
        // when you hit enter in valueTextField, control will move to costTextfield
        valueTextfield.addActionListener(text -> costTextfield.requestFocus());

        // when you hit enter in valueTextfield, control will click setDistance button
        valueTextfield.addActionListener(text -> {
            setDistance.doClick();
            fromTextfield.requestFocus();
            fromTextfield.setText("");
            toTextfield.setText("");
            valueTextfield.setText("");
            costTextfield.setText("");
        });

        // It will draw a line b/w two nodes with the given value
        setDistance.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ee){
                try{

                    Integer fromInt = Integer.parseInt(fromTextfield.getText());
                    Integer toInt = Integer.parseInt(toTextfield.getText());
                    Integer value = Integer.parseInt(valueTextfield.getText());
                    String cost = costTextfield.getText();

                    // To check if user input a negative number or
                    // a number which is not yet have been assigned to any node
                    if(fromInt > count-1 || toInt > count-1 || value < 0 || fromInt < 0 || toInt < 0){
                        JOptionPane.showMessageDialog(null, "Invalid Input", " Error", JOptionPane.ERROR_MESSAGE);
                        return;
                        // if user enters same number in both fields
                    }else if(fromInt == toInt){
                        JOptionPane.showMessageDialog(null, "Loops are not Allowed", " Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    drawLine(fromInt, toInt, value);
                    fromTextfield.requestFocus();
                    fromTextfield.setText("");
                    toTextfield.setText("");
                    valueTextfield.setText("");
                    costTextfield.setText("");
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Invalid Input", " Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });     // It will draw a line b/w two nodes with the given value
        SaveData.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ee) {


                String from = fromTextfield.getText();
                String to = toTextfield.getText();
                String value = valueTextfield.getText();
                String cost = costTextfield.getText();
                try {
                    FileWriter Writer = new FileWriter("GUIAPP/info.txt", true);
                    Writer.write("From: " + from + " To: " + to + "  Distance: " + value +" Km " +"  Info: "+ cost );
                    Writer.write(System.getProperty("line.separator"));
                    Writer.close();
                    JOptionPane.showMessageDialog(null,"Insertion Successfully");
                    setVisible(true);

                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(null,"Insertion Failed");
                }
            }
        });


        // when you hit enter in initialTextfield, control will move to finalTextfield
        initialTextfield.addActionListener(text -> finalTextfield.requestFocus());

        // when you hit enter in finalTextfield, control will click calculate button
        finalTextfield.addActionListener(text -> calculate.doClick());

        // It will calculate the required distance, Dijkstra algorithm works here
        calculate.addActionListener(ee -> {

            try{

                Integer source = Integer.parseInt(initialTextfield.getText());
                Integer destination = Integer.parseInt(finalTextfield.getText());

                // To check if user input a negative number or
                // a number which is higher than the number of nodes
                if (source > count-1 || destination > count-1 || source < 0 || destination < 0){
                    JOptionPane.showMessageDialog(null, "Invalid Input", " Error", JOptionPane.ERROR_MESSAGE);
                    initialTextfield.setText("");
                    finalTextfield.setText("");
                    initialTextfield.requestFocus();
                    return;
                }

                initialTextfield.setText("");
                finalTextfield.setText("");

                // main algorithm, m is the object of class Algorithm
                m.Dijkstra(adjacency, source, destination);

                // if there is no path b/w source and destination
                if (m.distance == Integer.MAX_VALUE)
                    resultLabel.setText("There is no way to go from " + source +" to " + destination);
                else{
                    showColor(m.path, source);
                }

                fromTextfield.setEnabled(false);
                toTextfield.setEnabled(false);
                valueTextfield.setEnabled(false);
                costTextfield.setEnabled(false);
                setDistance.setEnabled(false);
                SaveData.setEnabled(false);
                initialTextfield.setEnabled(false);
                finalTextfield.setEnabled(false);
                calculate.setEnabled(false);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Invalid Input", " Error", JOptionPane.ERROR_MESSAGE);
                initialTextfield.setText("");
                finalTextfield.setText("");
                initialTextfield.requestFocus();
            }

        });

        // when you click on the g panel, this will happen ...
        upperPanel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if(start.isEnabled()){
                    int flag = 0;

                    // to get the coordinate of the point where we clicked
                    int x = e.getX() - 6;
                    int y = e.getY() + 15;
                    x_pos.add(x + 2);
                    y_pos.add(y + 40);

                    // if you click on the same position more than once
                    for (int w=0; w<x_pos.size()-1; w++){
                        if(x+2 == x_pos.get(w) && y+40 == y_pos.get(w)){
                            JOptionPane.showMessageDialog(null, "Node Already present here", "Error", JOptionPane.ERROR_MESSAGE);
                            x_pos.remove(x_pos.size()-1);
                            y_pos.remove(y_pos.size()-1);
                            flag = 1;
                            return;
                        }
                    }
                    //System.out.println(x+","+y+","+count);
                    if(flag == 0)
                        drawNode(count++, x, y);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}

        });

        // reset button listener
        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                count = 0;
                adjacency = new int[0][0];
                x_pos.clear();
                y_pos.clear();
                start.setEnabled(true);
                fromTextfield.setEnabled(false);
                toTextfield.setEnabled(false);
                valueTextfield.setEnabled(false);
                costTextfield.setEnabled(false);
                setDistance.setEnabled(false);
                SaveData.setEnabled(false);
                initialTextfield.setEnabled(false);
                finalTextfield.setEnabled(false);
                calculate.setEnabled(false);
                resultLabel.setText("Enter Data Below... ( click HELP for more info )");
                m.reset();
                repaint();
            }
        });
        // reset button listener
        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == logout) {
                    System.exit(0);
                }

            }
        });
        // help button listener
        help.addActionListener(e -> JOptionPane.showMessageDialog(null, helpLabel, " How to Use ?", JOptionPane.INFORMATION_MESSAGE));        // help button listener
        Info.addActionListener(e ->JOptionPane.showMessageDialog(null,  scrollPane, "Transportation Information",
                JOptionPane.PLAIN_MESSAGE));

    }

    // Method to change color of the required path (green color)
    private void showColor(ArrayList<Integer> path, int source){
        drawLineChangeColor(source, path.get(0));
        drawNode(source, x_pos.get(source)-2, y_pos.get(source)-41);
        int i;
        for (i=0; i<path.size()-1; i++){
            drawLineChangeColor(path.get(i), path.get(i+1));
            drawNode(path.get(i), x_pos.get(path.get(i))-2, y_pos.get(path.get(i))-41);
        }
        drawNode(path.get(i), x_pos.get(path.get(i))-2, y_pos.get(path.get(i))-41);


    }

    // Method to draw the node at the given coordinate
    private void drawNode(int count, int x, int y){
        Graphics g = this.getGraphics();
        Graphics2D graphics2d = (Graphics2D) g;
        g.setColor(new Color(111, 150, 255));
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.fillOval(x-13, y+10, 45, 45);
        Font font = new Font("Verdana", Font.PLAIN, 25);
        g.setFont(font);
        g.setColor(Color.WHITE);
        String text = count + "";
        if (count > 9)
            g.drawString(text, x-6, y+41);
        else
            g.drawString(text, x+2, y+41);
    }

    // Method to draw a line b/w two nodes with the given value as its weight
    private void drawLine(int from, int to, int value){
        if(adjacency[from][to] != 0){
            JOptionPane.showMessageDialog(null, "Can't Overwrite", " Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        adjacency[from][to] = value ;
        Graphics g = this.getGraphics();
        Graphics2D graphics2d = (Graphics2D) g;
        g.setColor(new Color(111, 150, 255));
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawLine(x_pos.get(from)+4, y_pos.get(from)-4, x_pos.get(to)+4, y_pos.get(to)-4);
        String st = value + " Km" ;
        int x = (((x_pos.get(from) + x_pos.get(to))/2) + (x_pos.get(to)))/2;
        int y = (((y_pos.get(from) + y_pos.get(to))/2) + (y_pos.get(to)))/2;
        Font font = new Font("Verdana", Font.BOLD, 20);
        g.setColor(Color.RED);
        g.setFont(font);
        g.drawString(st, x, y);
        drawNode(from, x_pos.get(from)-2, y_pos.get(from)-41);
        drawNode(to, x_pos.get(to)-2, y_pos.get(to)-41);
    }

    // Re-draw the lines which has now green color
    private void drawLineChangeColor(int from, int to){
        Graphics g = this.getGraphics();
        Graphics2D graphics2d = (Graphics2D) g;
        g.setColor(new Color(0, 130, 0));
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawLine(x_pos.get(from)+4, y_pos.get(from)-4, x_pos.get(to)+4, y_pos.get(to)-4);
    }

//     Main Method
    public static void main(String[] args) throws IOException {

        // To select the Nimbus's Look and Feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }



        main cs = new main();
        cs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cs.setVisible(true);
        cs.setLocation(0,0);
        cs.setSize(1200,800);
        cs.setResizable(true);
        cs.setTitle("Water Supply GUI");


    }


}