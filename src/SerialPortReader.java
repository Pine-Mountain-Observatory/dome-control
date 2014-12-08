import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * Listens for changes in Serial Port and modifies boolean in port
 */
public class SerialPortReader implements SerialPortEventListener {

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.isRXCHAR()) { //Checks to see if there is data coming from the serial port
            System.out.println("isRXCHAR = true");
            try {
                String response = PortCommunicator.serialPort.readString();//reads response from serial port
                System.out.println("Response is: " + response);
                if (response.equalsIgnoreCase("X") || response.equalsIgnoreCase("Y")) {//If "x", "X", "y", "Y" it means the limit is reached on the dome
                    PortCommunicator.limitReached = true; //sets variable to true, which stops program trying to open dome
                    System.out.println("limitReached = true");
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
