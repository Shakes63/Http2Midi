package com.mediajitters.Http2Midi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.*;

public class NetworkMidiConnector {
    private static final Logger logger = LoggerFactory.getLogger(NetworkMidiConnector.class);

    private MidiDevice networkDevice;
    // private Receiver receiver;
    private ShortMessage message;
    
    public NetworkMidiConnector(String name) throws MidiUnavailableException {
        // // Get a list of all available MIDI devices
        // MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        
        // // Find the network MIDI device by name
        // for (MidiDevice.Info info : infos) {
        //     if (info.getName().equals(name)) {
        //         networkDevice = MidiSystem.getMidiDevice(info);
        //         break;
        //     }
        // }

        // if (networkDevice == null) {
        //     throw new MidiUnavailableException("Network MIDI device not found");
        // }
        // // Open the network MIDI device
        // networkDevice.open();
        
        // // Get a MIDI receiver from the network MIDI device
        // receiver = networkDevice.getReceiver();
        
        // Create a MIDI message to send
        message = new ShortMessage();
    }

    public static void listMidiDevices() {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        logger.info("Available MIDI devices:");
        for (MidiDevice.Info info : infos) {
            logger.info("- " + info.getName());
        }
    }
    
    
    public void sendNoteOn(String deviceName, int channel, int noteNumber, int velocity) throws InvalidMidiDataException, MidiUnavailableException {
        
        // Find the device by name
        MidiDevice device = getDeviceByName(deviceName);
        if (device == null) {
            throw new MidiUnavailableException("Device not found: " + deviceName);
        }

        // Open the device and get a receiver
        device.open();
        Receiver receiver = device.getReceiver();

        // Send a "note on" message for the specified note number and velocity
        message.setMessage(ShortMessage.NOTE_ON, channel, noteNumber, velocity);
        receiver.send(message, -1);
    }
    
    public void sendNoteOff(String deviceName, int channel, int noteNumber) throws InvalidMidiDataException, MidiUnavailableException {
        
        // Find the device by name
        MidiDevice device = getDeviceByName(deviceName);
        if (device == null) {
            throw new MidiUnavailableException("Device not found: " + deviceName);
        }

        // Open the device and get a receiver
        device.open();
        Receiver receiver = device.getReceiver();
        
        // Send a "note off" message for the specified note number
        message.setMessage(ShortMessage.NOTE_OFF, channel, noteNumber, 0);
        receiver.send(message, -1);
    }

    public void sendCC(String deviceName, int channel, int ccNumber, int value) throws InvalidMidiDataException, MidiUnavailableException {
        
        // Find the device by name
        MidiDevice device = getDeviceByName(deviceName);
        if (device == null) {
            throw new MidiUnavailableException("Device not found: " + deviceName);
        }

        // Open the device and get a receiver
        device.open();
        Receiver receiver = device.getReceiver();
        
        // Send a MIDI CC message for the specified CC number and value
        message.setMessage(ShortMessage.CONTROL_CHANGE, channel, ccNumber, value);
        receiver.send(message, -1);
    }
    
    
    public void close() {
        // Close the network MIDI device
        networkDevice.close();
    }
    
    public static MidiDevice getDeviceByName(String name) throws MidiUnavailableException {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (MidiDevice.Info info : infos) {
            if (info.getName().equals(name)) {
                return MidiSystem.getMidiDevice(info);
            }
        }
        throw new MidiUnavailableException("MIDI device " + name + " not found");
    }
}

