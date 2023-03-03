# Http2Midi
<h2>Small web server that translates Http Get requests into Midi Messages.</h2>

<h3 style=color:dodgerblue>There are 6 different commands that you can send to this web server.<br>
There is MIDI Note On, MIDI Note Off, MIDI CC Message, List MIDI Devices, Info, and Quit.</h3>

<h3 style=color:dodgerblue>In order to sent a Note On you will need to provide these parameters to /midi/noteon:</h3>
<strong style=color:red;>deviceName</strong>: Name of the device you are wanting to send the message to in a string format exactly like you see when running the list devices command.<br>
<strong style=color:red;>channel</strong>: Number from 0 - 15 indicating the channel number you want to connect to.<br>
<strong style=color:red;>note</strong>: Number from 0 - 128 corresponding to the different midi notes.<br>
<strong style=color:red;>velocity</strong>: Number from 0 - 128 indicating the velocity.<br>
<strong style=color:red;>Example</strong>: http://localhost:8080/midi/noteon?deviceName=Propresenter&channel=0&note=52&velocity=128

<h3 style=color:dodgerblue>In order to sent a Note Off you will need to provide these parameters to /midi/noteoff:</h3>
<strong style=color:red;>deviceName</strong>: Name of the device you are wanting to send the message to in a string format exactly like you see when running the list devices command.<br>
<strong style=color:red;>channel</strong>: Number from 0 - 15 indicating the channel number you want to connect to.<br>
<strong style=color:red;>note</strong>: Number from 0 - 128 corresponding to the different midi notes.<br>
<strong style=color:red;>Example</strong>: http://localhost:8080/midi/noteoff?deviceName=Propresenter&channel=0&note=52<br>

<h3 style=color:dodgerblue>In order to sent a CC Message you will need to provide these parameters to /midi/cc:</h3>
<strong style=color:red;>deviceName</strong>: Name of the device you are wanting to send the message to in a string format exactly like you see when running the list devices command.<br>
<strong style=color:red;>channel</strong>: Number from 0 - 15 indicating the channel number you want to connect to.<br>
<strong style=color:red;>ccNumber</strong>: Number from 0 - 128 corresponding to the different midi notes.<br>
<strong style=color:red;>value</strong>: Number from 0 - 128 corresponding to the different midi notes.<br>
<strong style=color:red;>Example</strong>: http://localhost:8080/midi/cc?deviceName=Propresenter&channel=0&ccNumber=15&value=33

<h3 style=color:dodgerblue>In order to list out the available midi devices you will need to send a request to /midi/list</h3>
<strong style=color:red;>Example</strong>: http://localhost:8080/midi/list

<h3 style=color:dodgerblue>In order to get these instructions again you will need to send a request to /midi/info</h3>
<strong style=color:red;>Example</strong>: http://localhost:8080/midi/info

<h3 style=color:dodgerblue>To shutdown the webserver send a request to /midi/quit</h3>
<strong style=color:red;>Example</strong>: http://localhost:8080/midi/quit
      
