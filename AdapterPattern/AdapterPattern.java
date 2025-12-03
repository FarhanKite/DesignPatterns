/*
HDMI → VGA Adapter Pattern — Example (Java)

Scenario:
- Your Laptop outputs HDMI (digital signal).
- Your Projector accepts only VGA (analog signal).
- The Adapter makes the laptop (HDMI client) work with the VGA projector.

Key rule of Adapter Pattern:
- The adapter implements the interface expected by the client.
- Laptop expects HDMI, so adapter implements HDMI.
*/

// --------------------------------------------------------------

/*
Code Explanation:
1. Client expects HDMI
- The laptop can only send video through HDMI.
- It doesn’t know anything about VGA.

2. VGA Projector does not accept HDMI
- It requires analog VGA signals.

3. Adapter implements HDMI
- Because that’s what the laptop expects.

Inside the adapter:
- Digital HDMI data is received
- Converted to analog
- Forwarded to the VGA projector

4. Result
- Laptop (HDMI) → Adapter → Projector (VGA)
Everything works even though the interfaces are incompatible.

This is the core purpose of the Adapter Pattern:
- “To allow two incompatible interfaces to work together without modifying their source code.”
*/

interface HDMI {
    void transmitDigitalVideo(String data);
}

class VGAProjector {

    public void showAnalogVideo(String data) {
        System.out.println("Displaying ANALOG video: " + data);
    }
}

class HDMItoVGAAdapter implements HDMI {

    private final VGAProjector vgaProjector;

    public HDMItoVGAAdapter(VGAProjector vgaProjector) {
        this.vgaProjector = vgaProjector;
    }

    @Override
    public void transmitDigitalVideo(String data) {
        System.out.println("Adapter converting DIGITAL (HDMI) → ANALOG (VGA)...");
        String analogData = convertToAnalog(data);
        vgaProjector.showAnalogVideo(analogData);
    }

    private String convertToAnalog(String digitalData) {
        // simulated conversion process
        return "Analog-" + digitalData;
    }
}

class Laptop {

    private final HDMI hdmiPort;

    public Laptop(HDMI hdmiPort) {
        this.hdmiPort = hdmiPort;
    }

    public void playVideo(String videoData) {
        System.out.println("Laptop sending DIGITAL video via HDMI...");
        hdmiPort.transmitDigitalVideo(videoData);
    }
}

class AdapterPattern {
    public static void main(String[] args) {

        // Old VGA projector only supports analog signal
        VGAProjector projector = new VGAProjector();

        // Adapter makes VGA projector compatible with HDMI laptop
        HDMI adapter = new HDMItoVGAAdapter(projector);

        // Laptop only understands HDMI devices
        Laptop laptop = new Laptop(adapter);

        laptop.playVideo("Movie.mp4");
    }
}
