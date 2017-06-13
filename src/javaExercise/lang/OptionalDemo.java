package javaExercise.lang;

import java.util.Optional;

public class OptionalDemo {
	
	public static void main(String [] args) {
		Computer computer = new OptionalDemo().new Computer();
		String version = computer.getSoundcard().getUSB().getVersion(); // 哦豁
		// 正确的做法，繁琐
		version = "UNKNOWN";
		if (computer != null) {
			Soundcard soundcard = computer.getSoundcard();
			if (soundcard != null) {
				USB usb = soundcard.getUSB();
				if (usb != null) {
					version = usb.getVersion();
				}
			}
		}
		
		// 使用Optional
		version = computer
				.getSoundcardOpt()
				.flatMap(Soundcard::getUSBOpt)
				.flatMap(USB::getVersionOpt)
				.orElse("UNKNOWN");
	}
	
	class Computer {
		Soundcard soundcard;
		Soundcard getSoundcard() { return soundcard; }
		Optional<Soundcard> getSoundcardOpt() { return Optional.ofNullable(soundcard); }
	}
	
	class Soundcard {
		USB usb;
		USB getUSB() { return usb; }
		Optional<USB> getUSBOpt() { return Optional.ofNullable(usb); }
	}
	
	class USB {
		String version;
		String getVersion() { return version; }
		Optional<String> getVersionOpt() { return Optional.ofNullable(version); }
	}

}
