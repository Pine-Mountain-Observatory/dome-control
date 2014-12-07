
import jssc.*;

import javax.sound.sampled.Port;

/**
 * Created by Jacob on 12/6/2014.
 */
public class PortCommunicator implements Runnable {

    /**
     * Serial Communication using 9600 baud, eight bit, no parity, and hardware control = off
     */

    protected static SerialPort mSerialPort;
    protected static boolean mLimitReached = false;
    private static boolean mAllTheWay;
    private static String mCommand;

    //Constructor
    public PortCommunicator(SerialPort port, String command, boolean allTheWay) {
        mSerialPort = port;
        mCommand = command;
        mAllTheWay = allTheWay;
    }

    /**
     * Method to open Telescope
     */

    public static void moveDome(String command, boolean allTheWay) throws SerialPortException {

        if (allTheWay) {
            while (!mLimitReached) {
                System.out.println("moveDome called");
                try {
                    mSerialPort.writeString(command);
                } catch (SerialPortException e) {
                    e.printStackTrace();
                }
                /**
                 * Send command repeatedly until limit response occurs
                 * "A" to go up on side opposite controller box, "X" is upper limit of side
                 * "a" for that side to go down, "x" is lower limit
                 * "B" for side of controller box go up, "Y" is upper limit
                 * "b" for that side to go down, "y" is lower limit
                 *
                 */

            }
            System.out.println("Limit reached");
        } else {
            try {
                mSerialPort.writeString(command);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
            /**
             * Send command once
             */
        }
    }

    @Override
    public void run() {
        try {
            mSerialPort.openPort();
            System.out.println("Port Opened");
            mSerialPort.setParams(9600, 8, 1, 0);//Check Params for this again
            int mask = SerialPort.MASK_RXCHAR; //Mask to listen to changes in data
            mSerialPort.setEventsMask(mask);
            mSerialPort.addEventListener(new SerialPortReader());
            System.out.println("Mask and SerialPortReader added");
            moveDome(mCommand, mAllTheWay);
            System.out.println("moveDome finished");
            mSerialPort.closePort();
            System.out.println("Port Closed");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }


    }


}

