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
            try {
                String response = PortCommunicator.mSerialPort.readString();
                if (response.equalsIgnoreCase("X") || response.equalsIgnoreCase("Y")) {
                    PortCommunicator.mLimitReached = true;
                } else {
                    PortCommunicator.mLimitReached = false;
                }
            } catch (SerialPortException e) {
                e.printStackTrace();
            }

        }
    }
}
