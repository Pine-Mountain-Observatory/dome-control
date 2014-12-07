import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * Listens for changes in Serial Port and modifies boolean in port
 */
public class SerialPortReader implements SerialPortEventListener {

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.isRXCHAR()) {
            System.out.println("isRXCHAR = true");
            try {
                String response = PortCommunicator.mSerialPort.readString();
                System.out.println("Response is: " + response);
                if (response.equalsIgnoreCase("X") || response.equalsIgnoreCase("Y")) {
                    PortCommunicator.mLimitReached = true;
                    System.out.println("mLimitReached = true");
                } else {
                    PortCommunicator.mLimitReached = false;
                    System.out.println("mLimitReached = false");
                }
            } catch (SerialPortException e) {
                e.printStackTrace();
            }

        }
    }
}
