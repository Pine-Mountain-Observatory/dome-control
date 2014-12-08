
import jssc.*;

/**
 * Created by Jacob on 12/6/2014.
 */
public class PortCommunicator implements Runnable {

    /**
     * Serial Communication using 9600 baud, eight bit, no parity, and hardware control = off
     */

    protected static SerialPort serialPort;
    protected static boolean limitReached = false;
    private static boolean mAllTheWay;
    private static String mCommand;

    //Constructor
    public PortCommunicator(SerialPort port, String command, boolean allTheWay) {
        serialPort = port;
        mCommand = command;
        mAllTheWay = allTheWay;
    }

    /**
     * Method to open Telescope
     */

    public static void moveDome(String command, boolean allTheWay) throws SerialPortException {
        limitReached = false;//Necessary so that moveDome will still function right after limitReached is set to true once on SerialPortReader
        if (allTheWay) {
            while (!limitReached) {
                System.out.println("moveDome called");
                try {
                    serialPort.writeString(command);
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
                serialPort.writeString(command);
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
            serialPort.openPort();
            System.out.println("Port Opened");
            serialPort.setParams(9600, 8, 1, 0);//Check Params for this again
            int mask = SerialPort.MASK_RXCHAR; //Mask to listen to changes in data
            serialPort.setEventsMask(mask);
            serialPort.addEventListener(new SerialPortReader());
            System.out.println("Mask and SerialPortReader added");
            moveDome(mCommand, mAllTheWay);
            System.out.println("moveDome finished");
            serialPort.closePort();
            System.out.println("Port Closed");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }


    }


}

