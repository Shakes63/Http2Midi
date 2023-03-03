package com.mediajitters.Http2Midi;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PreDestroy;

import static com.mediajitters.Http2Midi.HttpToMidi.instructions;
import java.lang.*;

@RestController
public class MidiController {


    private static final Logger logger = LoggerFactory.getLogger(MidiController.class);

    private NetworkMidiConnector midiConnector = new NetworkMidiConnector(null);

    public MidiController() throws MidiUnavailableException {
        // Initialize the MIDI connector
        //listMidiDevices();
    }



    @GetMapping("/midi/noteon")
    public String noteOn(@RequestParam String deviceName, @RequestParam int channel, @RequestParam int note, @RequestParam int velocity) throws InvalidMidiDataException, MidiUnavailableException {
        // Send the note on message to the MIDI device
        midiConnector.sendNoteOn(deviceName, channel, note, velocity);
        String logmessage = String.format("\u001B[36mNote On Sent to %s Channel: %d Note: %d Velocity: %d\u001B[36m", deviceName, channel, note, velocity);
        logger.info(logmessage);
        return logmessage;
    }

    @GetMapping("/midi/noteoff")
    public String noteOff(@RequestParam String deviceName, @RequestParam int channel, @RequestParam int note) throws InvalidMidiDataException, MidiUnavailableException {
        // Send the note off message to the MIDI device
        midiConnector.sendNoteOff(deviceName, channel, note);
        String logmessage = String.format("\u001B[36mNote Off Sent to %s Channel: %d Note: %d\u001B[36m", deviceName, channel, note);
        logger.info(logmessage);
        return logmessage;
    }

    @GetMapping("/midi/cc")
    public String noteCC(@RequestParam String deviceName, @RequestParam int channel, @RequestParam int ccNumber, @RequestParam int value ) throws InvalidMidiDataException, MidiUnavailableException {
        // Send the note off message to the MIDI device
        midiConnector.sendCC(deviceName, channel, ccNumber, value);
        String logmessage = String.format("\u001B[36mCC Message Sent to %s Channel: %d CC Number: %d Value: %d\u001B[36m", deviceName, channel, ccNumber, value);
        logger.info(logmessage);
        return logmessage;
    }

    @PreDestroy
    public void cleanup() {
        // Close the MIDI connector when the application shuts down
        midiConnector.close();
    }

    @GetMapping("/midi/list")
    public static List<String> listMidiDevices() {
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        List<String> deviceNames = new ArrayList<>();
		logger.info("\nAvailable MIDI devices:");
		for (MidiDevice.Info info : infos) {
			logger.info("\u001B[32m- " + info.getName() + "\u001B[32m");
            deviceNames.add(info.getName());
		}
        return deviceNames;
	}

    @GetMapping("/midi/info")
    public String instructions()  {
    logger.info(instructions);
        return instructions;
    }

    @GetMapping("/midi/quit")
    public String quit() {
        // Quit the webserver
        String logmessage = "Shutting down webserver.";
        logger.warn(logmessage);
        System.exit(0);
        return logmessage;
    }


}

