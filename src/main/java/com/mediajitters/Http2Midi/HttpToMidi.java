package com.mediajitters.Http2Midi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HttpToMidi {

	private static final Logger logger = LoggerFactory.getLogger(HttpToMidi.class);
	public static String instructions = """
			***************************************************************************************************************************

			There are 4 different commands that you can send to this web server.\s
			There is MIDI Note On, MIDI Note Off, MIDI CC Message, and List MIDI Devices.\s

			In order to sent a Note On you will need to provide these parameters to /midi/noteon:
			deviceName: Name of the device you are wanting to send the message to in a string format exactly like you see when running the list devices command.
			channel: Number from 0 - 15 indicating the channel number you want to connect to.
			note: Number from 0 - 128 corresponding to the different midi notes.
			velocity: Number from 0 - 128 indicating the velocity.
			Example: http://localhost:8080/midi/noteon?deviceName=Propresenter&channel=0&note=52&velocity=128

			In order to sent a Note Off you will need to provide these parameters to /midi/noteoff:
			deviceName: Name of the device you are wanting to send the message to in a string format exactly like you see when running the list devices command.
			channel: Number from 0 - 15 indicating the channel number you want to connect to.
			note: Number from 0 - 128 corresponding to the different midi notes.
			Example: http://localhost:8080/midi/noteoff?deviceName=Propresenter&channel=0&note=52

			In order to sent a CC Message you will need to provide these parameters to /midi/cc:
			deviceName: Name of the device you are wanting to send the message to in a string format exactly like you see when running the list devices command.
			channel: Number from 0 - 15 indicating the channel number you want to connect to.
			ccNumber: Number from 0 - 128 corresponding to the different midi notes.
			value: Number from 0 - 128 corresponding to the different midi notes.
			Example: http://localhost:8080/midi/cc?deviceName=Propresenter&channel=0&ccNumber=15&value=33

			In order to list out the available midi devices you will need to send a request to /midi/list
			Example: http://localhost:8080/midi/list
			
			In order to get these instructions again you will need to send a request to /midi/info
			Example: http://localhost:8080/midi/info
			
			***********************************************************************************************************************
			""";


	public static void main(String[] args) throws MidiUnavailableException {
		SpringApplication.run(HttpToMidi.class, args);
		logger.info(instructions);

		 
		MidiController.listMidiDevices();

		
	}

}
