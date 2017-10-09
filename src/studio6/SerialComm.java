package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	
	
	
	public static void main(String args[]) throws SerialPortException
	{
		SerialComm COM3 = new SerialComm("COM3");
		COM3.setDebug(true);
		while(true)
		{
			if(COM3.available())
			{
				int a=COM3.readByte();
				
				if(a!=10 && a!=13)
				{
					char b = (char)a;
					System.out.print(b);
				}
				else if(a == 13)
				{
					System.out.println();
				}
				
			}
		}
	}
	
	
	
	
	
	
	
	
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method from Studio 5
	public void writeByte(byte input) throws SerialPortException
	{
		byte output = input;
		if(output!=10 ||output!=13)
		{	
			if (debug)
			{
			port.writeByte(output);
			System.out.println("["+input+"]");
			System.out.println("<"+output+">");
			}
			else 
			{
				port.writeByte(output);
			}
		}
		
	}
	
	// TODO: Add available() method
	public boolean available() throws SerialPortException
	{
		if(port.getInputBufferBytesCount()>0)
		{
			return true;
		}
		return false;
	}
	
	// TODO: Add readByte() method	
	public byte readByte() throws SerialPortException
	{
		if(debug)
		{
			byte a = port.readBytes(1)[0];
			System.out.println(String.format("%02x", a));
			return a;
		}
		else
		{
			return port.readBytes(1)[0];
		}
	}
	
	// TODO: Add a main() method
}
