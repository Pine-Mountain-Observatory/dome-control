import jssc.SerialPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

/**
 * Created by Jacob on 12/5/2014.
 */
public class Main {

    //Title and to connect to hardware
    private static final SerialPort SERIAL_PORT = new SerialPort("COM3");
    private static final String mTitle = "OORCC - Robbins";

    //Serial Port Communicator
    private static PortCommunicator portCommunicator;

    //Commands for telescope
    private static final String mRightOpenCommand = "a";
    private static final String mRightCloseCommand = "A";
    private static final String mLeftOpenCommand = "b";
    private static final String mLeftCloseCommand = "B";
   // private static final String mRightOpenedSignal = "x";
   // private static final String mRightClosedSignal = "X";
   // private static final String mLeftOpenedSignal = "y";
   // private static final String mLeftClosedSignal = "Y";

    //Different divisions of GUI
    private static JFrame mFrame = new JFrame(mTitle);
    private static JPanel mBigButtonPanel;
    private static JPanel mRightButtonPanel;
    private static JPanel mLeftButtonPanel;
    private static JPanel mFooterPanel;
   // private static JPanel mHeaderPanel;

    //Panel sizes
    private static final int PANEL_HEIGHT = 400;
    private static final int PANEL_WIDTH = 500;

    //Main Buttons
    private static JButton mOpenAll = new JButton("Open Dome");
    private static JButton mCloseAll = new JButton("Close Dome");

    //Right telescope Buttons
    private static JButton mOpenRightAll = new JButton("Open Right");
    private static JButton mCloseRightAll = new JButton("Close Right");
    private static JButton mOpenRightStep = new JButton("Step Right Open");
    private static JButton mCloseRightStep = new JButton("Step Right Closed");

    //Left Telescope Buttons
    private static JButton mOpenLeftAll = new JButton("Open Left");
    private static JButton mCloseLeftAll = new JButton("Close Left");
    private static JButton mOpenLeftStep = new JButton("Step Left Open");
    private static JButton mCloseLeftStep = new JButton("Step Left Closed");

    //Words
    private static JLabel mCopyright = new JLabel("© Jacob Bieker " + Calendar.getInstance().get(Calendar.YEAR)); //Auto updates year of copyright
  //  private static JLabel mRightSideSteps = new JLabel();
   // private static JLabel mLeftSideSteps = new JLabel();

    public static void main(String[] args) {

        /*
        GUI
         */


        //Main button panels
        mBigButtonPanel = new JPanel();
        mBigButtonPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        mBigButtonPanel.setLayout(new GridLayout(2, 1));
        mFrame.add(mBigButtonPanel, BorderLayout.NORTH);

        //Right button panels
        mRightButtonPanel = new JPanel();
        mRightButtonPanel.setPreferredSize(new Dimension(PANEL_WIDTH/2, PANEL_HEIGHT));
        mRightButtonPanel.setLayout(new GridLayout(4, 1));
        mFrame.add(mRightButtonPanel, BorderLayout.EAST);

        //Left button panels
        mLeftButtonPanel = new JPanel();
        mLeftButtonPanel.setPreferredSize(new Dimension(PANEL_WIDTH/2, PANEL_HEIGHT));
        mLeftButtonPanel.setLayout(new GridLayout(4, 1));
        mFrame.add(mLeftButtonPanel, BorderLayout.WEST);

        //Footer Panel
        mFooterPanel = new JPanel();
        mFooterPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT/5));
        mFooterPanel.setLayout(new GridLayout(1, 4));
        mFrame.add(mFooterPanel, BorderLayout.SOUTH);


        //Adding Main Buttons
        mBigButtonPanel.add(mOpenAll);
        mOpenAll.addActionListener(new OpenAllButtonListener());
        mBigButtonPanel.add(mCloseAll);
        mCloseAll.addActionListener(new CloseAllButtonListener());

        //Adding Small Left Buttons
        mLeftButtonPanel.add(mOpenLeftAll);
        mOpenLeftAll.addActionListener(new OpenLeftAllButtonListener());
        mLeftButtonPanel.add(mCloseLeftAll);
        mCloseLeftAll.addActionListener(new CloseLeftAllButtonListener());
        mLeftButtonPanel.add(mOpenLeftStep);
        mOpenLeftStep.addActionListener(new OpenLeftStepButtonListener());
        mLeftButtonPanel.add(mCloseLeftStep);
        mCloseLeftStep.addActionListener(new CloseLeftStepButtonListener());

        //Adding Small Right Buttons
        mRightButtonPanel.add(mOpenRightAll);
        mOpenRightAll.addActionListener(new OpenRightAllButtonListener());
        mRightButtonPanel.add(mCloseRightAll);
        mCloseRightAll.addActionListener(new CloseRightAllButtonListener());
        mRightButtonPanel.add(mOpenRightStep);
        mOpenRightStep.addActionListener(new OpenRightStepButtonListener());
        mRightButtonPanel.add(mCloseRightStep);
        mCloseRightStep.addActionListener(new CloseRightStepButtonListener());

        //Adding to Footer
        mFooterPanel.add(mCopyright);
        mFooterPanel.add(new JLabel(new ImageIcon("C:\\Users\\Jacob\\IdeaProjects\\OORCC_Robbins\\images\\79752561999.gif")));

        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.pack();
        mFrame.setVisible(true);


    }



    /*******************************************************************************************************************
     * Main Button Listeners
     */
    private static class OpenAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mRightOpenCommand, true);
            portCommunicator.run();
            portCommunicator = new PortCommunicator(SERIAL_PORT, mLeftOpenCommand, true);
            portCommunicator.run();

        }

    }

    private static class CloseAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mRightCloseCommand, true);
            portCommunicator.run();
            portCommunicator = new PortCommunicator(SERIAL_PORT, mLeftCloseCommand, true);
            portCommunicator.run();


        }

    }


    /*****************************************************************************************************************
     * Right Side Button Listeners
     */
    private static class OpenRightAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mRightOpenCommand, true);
            portCommunicator.run();

        }

    }

    private static class CloseRightAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mRightCloseCommand, true);
            portCommunicator.run();

        }

    }

    private static class CloseRightStepButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mRightCloseCommand, false);
            portCommunicator.run();

        }

    }

    private static class OpenRightStepButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mRightOpenCommand, false);
            portCommunicator.run();

        }

    }

    /******************************************************************************************************************
     * Left Side Button Listeners
     */

    private static class OpenLeftAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mLeftOpenCommand, true);
            portCommunicator.run();

        }

    }

    private static class OpenLeftStepButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mLeftOpenCommand, false);
            portCommunicator.run();

        }

    }

    private static class CloseLeftAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mLeftCloseCommand, true);
            portCommunicator.run();

        }

    }

    private static class CloseLeftStepButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mLeftCloseCommand, false);
            portCommunicator.run();

        }

    }

}
