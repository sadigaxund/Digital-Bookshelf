package org.jcl.computer_utils.soft;

import java.util.Locale;


/**
 * helper class to check the operating system this Java VM runs in
 */
public class OsDetector {
	/**
	 * types of Operating Systems
	 */
	public enum OSType {
	    Windows, MacOS, Linux, Unix, Other
	};

	// cached result of OS detection
	protected static OSType detectedOS;

	/**
	 * detect the operating system from the os.name System property and cache the
	 * result
	 * 
	 * @returns - the operating system detected
	 */
	public static OSType getOperatingSystemType() {
	    if (detectedOS == null) {
		String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
		if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
		    detectedOS = OSType.MacOS;
		} else if (OS.indexOf("win") >= 0) {
		    detectedOS = OSType.Windows;
		} else if (OS.indexOf("nux") >= 0) {
		    detectedOS = OSType.Linux;
		} else if (OS.indexOf("nix") >= 0) {
		    detectedOS = OSType.Unix;
		} else {
		    detectedOS = OSType.Other;
		}
	    }
	    return detectedOS;
	}
}