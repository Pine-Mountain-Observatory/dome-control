import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * Listens for changes in Serial Port and modifies boolean in port
 */
public class SerialPortReader implements SerialPortEventListener {

    //TODO Get Dome to open or close all the way before stopping
    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.isRXCHAR()) { //Checks to see if there is data coming from the serial port
            System.out.println("isRXCHAR = true");
            try {
                //TODO Add in support for 3 is for both sides, 2 is East side, 1 is West side
                String response = PortCommunicator.serialPort.readString();//reads response from serial port
                System.out.println("Response is: " + response);
                if (response.equalsIgnoreCase("X") || response.equalsIgnoreCase("Y")) {//If "x", "X", "y", "Y" it means the limit is reached on the dome
                    PortCommunicator.limitReached = true; //sets variable to true, which stops program trying to open dome
                    System.out.println("limitReached = true");
                } else if (response.equalsIgnoreCase("3") || response.equalsIgnoreCase("2") || response.equalsIgnoreCase("1")) {
                    PortCommunicator.limitReached = false;
                    System.out.println("3,2,1 limitReached = false");

                } else {
                    PortCommunicator.limitReached = false;//sets to false, so program will continue sending commands
                    System.out.println("limitReached = false");
                }
            } catch (SerialPortException e) {
                e.printStackTrace();
            }

        }
    }
}
