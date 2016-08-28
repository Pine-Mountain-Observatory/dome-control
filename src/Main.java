import jssc.SerialPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private static final String mEastOpenCommand = "a";
    private static final String mEastCloseCommand = "A";
    private static final String mWestOpenCommand = "b";
    private static final String mWestCloseCommand = "B";
   // private static final String mEastOpenedSignal = "x";
   // private static final String mEastClosedSignal = "X";
   // private static final String mWestOpenedSignal = "y";
   // private static final String mWestClosedSignal = "Y";

    /* Commented Out While Testing New GUI Formatting
    //Different divisions of GUI
    private static JFrame mFrame = new JFrame(mTitle);
    private static JPanel mBigButtonPanel;
    private static JPanel mEastButtonPanel;
    private static JPanel mWestButtonPanel;
    private static JPanel mFooterPanel;
   // private static JPanel mHeaderPanel;
    */

    //TODO Add StepOpen Buttons, Exit Button, Logo/Name, and Footer

    //Panel sizes
    private static final int PANEL_HEIGHT = 400;
    private static final int PANEL_WIDTH = 500;

    /* Commented Out While Testing New GUI Formatting
    //Main Buttons
    private static JButton mOpenAll = new JButton("Open Dome");
    private static JButton mCloseAll = new JButton("Close Dome");
    */

    /* Commented Out While Testing New GUI Formatting
    //East telescope Buttons
    private static JButton mOpenEastAll = new JButton("Open East");
    private static JButton mCloseEastAll = new JButton("Close East");
    private static JButton mOpenEastStep = new JButton("Step East Open");
    private static JButton mCloseEastStep = new JButton("Step East Closed");
    */

    /* Commented Out While Testing New GUI Formatting
    //West Telescope Buttons
    private static JButton mOpenWestAll = new JButton("Open West");
    private static JButton mCloseWestAll = new JButton("Close West");
    private static JButton mOpenWestStep = new JButton("Step West Open");
    private static JButton mCloseWestStep = new JButton("Step West Closed");
    */
    
    //New Format for GUI
    private static JButton mOpenEast = new JButton("Open East");
    private static JButton mOpenWest = new JButton("Open West");
    private static JButton mCloseEast = new JButton("Close East");
    private static JButton mCloseWest = new JButton("Close East");
    private static JButton mExit = new JButton("Exit");

    //Words
    private static JLabel mCopyEast = new JLabel("© Jacob Bieker 2014-" + Calendar.getInstance().get(Calendar.YEAR)); //Auto updates year of copyEast
  //  private static JLabel mEastSideSteps = new JLabel();
   // private static JLabel mWestSideSteps = new JLabel();
    public static void main(String[] args) {
        /*
        GUI
         */

        /*
        //Main button panels
        mBigButtonPanel = new JPanel();
        mBigButtonPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        mBigButtonPanel.setLayout(new GridLayout(2, 1));
        mFrame.add(mBigButtonPanel, BorderLayout.NORTH);

        //East button panels
        mEastButtonPanel = new JPanel();
        mEastButtonPanel.setPreferredSize(new Dimension(PANEL_WIDTH/2, PANEL_HEIGHT));
        mEastButtonPanel.setLayout(new GridLayout(4, 1));
        mFrame.add(mEastButtonPanel, BorderLayout.EAST);

        //West button panels
        mWestButtonPanel = new JPanel();
        mWestButtonPanel.setPreferredSize(new Dimension(PANEL_WIDTH/2, PANEL_HEIGHT));
        mWestButtonPanel.setLayout(new GridLayout(4, 1));
        mFrame.add(mWestButtonPanel, BorderLayout.WEST);

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

        //Adding Small West Buttons
        mWestButtonPanel.add(mOpenWestAll);
        mOpenWestAll.addActionListener(new OpenWestAllButtonListener());
        mWestButtonPanel.add(mCloseWestAll);
        mCloseWestAll.addActionListener(new CloseWestAllButtonListener());
        mWestButtonPanel.add(mOpenWestStep);
        mOpenWestStep.addActionListener(new OpenWestStepButtonListener());
        mWestButtonPanel.add(mCloseWestStep);
        mCloseWestStep.addActionListener(new CloseWestStepButtonListener());

        //Adding Small East Buttons
        mEastButtonPanel.add(mOpenEastAll);
        mOpenEastAll.addActionListener(new OpenEastAllButtonListener());
        mEastButtonPanel.add(mCloseEastAll);
        mCloseEastAll.addActionListener(new CloseEastAllButtonListener());
        mEastButtonPanel.add(mOpenEastStep);
        mOpenEastStep.addActionListener(new OpenEastStepButtonListener());
        mEastButtonPanel.add(mCloseEastStep);
        mCloseEastStep.addActionListener(new CloseEastStepButtonListener());
        */
        
        //New GUI
        JFrame mFrame = new JFrame();
        mFrame.setLayout(new BorderLayout());
        mFrame.setPreferredSize(PANEL_HEIGHT,PANEL_WIDTH);
		
		JPanel mButtonPanel = new JPanel();
		mButtonPanel.setLayout(new GridLayout(2,2));
		JPanel mFooterPanel = new JPanel();
		mFooterPanel.setLayout(new BorderLayout);
		
		
		mButtonPanel.add(mOpenEast);
		mButtonPanel.add(mOpenWest);
		mButtonPanel.add(mCloseEast);
		mButtonPanel.add(mCloseWest);
		add(mButtonPanel, BorderLayout.CENTER);
		add(mFooterPanel, BorderLayout.SOUTH)
		mFooterPanel.add(mExit, BorderLayout.NORTH);
		mFooterPanel.add(mCopyEast);
		add(new JLabel(new ImageIcon("images/79752561999.gif")), BorderLayout.NORTH);

        /*
        //Adding to Footer
        mFooterPanel.add(mCopyEast);
        mFooterPanel.add(new JLabel(new ImageIcon("images/79752561999.gif")));
        */

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
            portCommunicator = new PortCommunicator(SERIAL_PORT, mEastOpenCommand, true);
            portCommunicator.run();
            portCommunicator = new PortCommunicator(SERIAL_PORT, mWestOpenCommand, true);
            portCommunicator.run();
        }

    }

    private static class CloseAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mEastCloseCommand, true);
            portCommunicator.run();
            portCommunicator = new PortCommunicator(SERIAL_PORT, mWestCloseCommand, true);
            portCommunicator.run();

        }

    }


    /*****************************************************************************************************************
     * East Side Button Listeners
     */
    private static class OpenEastAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mEastOpenCommand, true);
            portCommunicator.run();
        }

    }

    private static class CloseEastAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mEastCloseCommand, true);
            portCommunicator.run();
        }

    }

    private static class CloseEastStepButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mEastCloseCommand, false);
            portCommunicator.run();
        }

    }

    private static class OpenEastStepButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mEastOpenCommand, false);
            portCommunicator.run();
        }

    }

    /******************************************************************************************************************
     * West Side Button Listeners
     */

    private static class OpenWestAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mWestOpenCommand, true);
            portCommunicator.run();
        }

    }

    private static class OpenWestStepButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mWestOpenCommand, false);
            portCommunicator.run();
        }

    }

    private static class CloseWestAllButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mWestCloseCommand, true);
            portCommunicator.run();
        }

    }

    private static class CloseWestStepButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            portCommunicator = new PortCommunicator(SERIAL_PORT, mWestCloseCommand, false);
            portCommunicator.run();
        }

    }

}
