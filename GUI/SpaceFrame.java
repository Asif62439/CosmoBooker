package GUI;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SpaceFrame extends JFrame implements MouseListener, ActionListener {

    JPanel panel;
    JLabel titleLabel, outputLabel;
	JComboBox destinationBox;
    JRadioButton rb1, rb2, rb3;
    ButtonGroup ticketGroup;
    JButton btn1;

    public SpaceFrame() {

        setTitle("SpaceX Travel Booking");
        setBounds(30, 20, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        setContentPane(panel);

        // TITLE
        titleLabel = new JLabel("INTERPLANETARY TRAVEL", SwingConstants.CENTER);
        titleLabel.setBounds(300, 40, 400, 40);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Cambria", Font.BOLD, 22));
        panel.add(titleLabel);

        // DESTINATION
        JLabel dLabel = new JLabel("Destination:");
        dLabel.setBounds(100, 120, 100, 25);
        dLabel.setForeground(Color.WHITE);
        panel.add(dLabel);
		
		
		String[] topic = { "Low Earth Orbit","Lunar Base","Mars Colony"};
        destinationBox = new JComboBox(Select);
        destinationBox.setBounds(220, 120, 200, 25);
        panel.add(destinationBox);

        // TICKET CLASS
        JLabel tLabel = new JLabel("Ticket Class:");
        tLabel.setBounds(100, 170, 100, 25);
        tLabel.setForeground(Color.WHITE);
        panel.add(tLabel);

        rb1 = new JRadioButton("Budget (Cargo Bay)");
        rb2 = new JRadioButton("Standard (Hab Pod)");
        rb3 = new JRadioButton("Luxury (Panoramic)");

        rb1.setBounds(220, 170, 220, 25);
        rb2.setBounds(220, 200, 220, 25);
        rb3.setBounds(220, 230, 220, 25);

        rb1.setOpaque(false);
        rb2.setOpaque(false);
        rb3.setOpaque(false);

        rb1.setForeground(Color.WHITE);
        rb2.setForeground(Color.WHITE);
        rb3.setForeground(Color.WHITE);

        ticketGroup = new ButtonGroup();
        ticketGroup.add(rb1);
        ticketGroup.add(rb2);
        ticketGroup.add(rb3);

        panel.add(rb1);
        panel.add(rb2);
        panel.add(rb3);

        // BUTTON
        btn1 = new JButton("Book Mission");
        btn1.setBounds(220, 280, 160, 35);
        btn1.addActionListener(this);
        btn1.addMouseListener(this);
        panel.add(btn1);

        // OUTPUT LABEL (like tutor’s olabel)
        outputLabel = new JLabel("Output:");
        outputLabel.setBounds(500, 280, 300, 35);
        outputLabel.setOpaque(true);
        outputLabel.setBackground(Color.WHITE);
        outputLabel.setForeground(Color.BLACK);
        outputLabel.addMouseListener(this);
        panel.add(outputLabel);
    }

    // ================= MOUSE EVENTS =================

    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == outputLabel) {
            outputLabel.setText("Mission label clicked!");
        }
    }

    public void mousePressed(MouseEvent me) {
        if (me.getSource() == outputLabel) {
            outputLabel.setText("Preparing mission...");
        }
    }

    
    }

    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == btn1) {
            btn1.setBackground(Color.WHITE);
            btn1.setForeground(Color.BLACK);
        }
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == btn1) {
            btn1.setBackground(Color.BLACK);
            btn1.setForeground(Color.WHITE);
        }
    }

    // ================= ACTION EVENT =================

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btn1) {

            String destination = (String) destinationBox.getSelectedItem();
            String ticket = rb1.isSelected() ? "Budget"
                          : rb2.isSelected() ? "Standard"
                          : rb3.isSelected() ? "Luxury"
                          : "Not Selected";

            outputLabel.setText("Booked: " + destination + " (" + ticket + ")");

            try {
                File file = new File("./Data/space_booking.txt");
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file, true);
                fw.write("Destination: " + destination + "\n");
                fw.write("Ticket Class: " + ticket + "\n");
                fw.write("-----------------------------\n");
                fw.close();

                JOptionPane.showMessageDialog(this, "Mission Booked!");

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "File Error!");
            }
        }
    }

