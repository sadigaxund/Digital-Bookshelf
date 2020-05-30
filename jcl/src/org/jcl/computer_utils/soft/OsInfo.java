package org.jcl.computer_utils.soft;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.jcl.files.JFilenames;

public class OsInfo {
    /**
     * Method that detects operating system of the host device
     * 
     * @param Link
     * @throws IOException
     * 
     */
    public static String getOSName() {
	return System.getProperty("os.name");
    }

    public static String getFileSystemSizeInfo() {
	String result = "";
	/* Get a list of all filesystem roots on this system */
	File[] roots = File.listRoots();

	/* For each filesystem root, print some info */
	for (File root : roots) {
	    result += "\n";
	    result += ("File system root: " + root.getAbsolutePath()) + "\n";
	    result += ("Total space (bytes): " + JFilenames.formatFileSize(root.getTotalSpace(), 2)) + "\n";
	    result += ("Free space (bytes): " + JFilenames.formatFileSize(root.getFreeSpace(), 2)) + "\n";
	    result += ("Usable space (bytes): " + JFilenames.formatFileSize(root.getUsableSpace(), 2)) + "\n";
	}
	return result.trim();
    }

    public static String getIpAdress() {
	try {
	    return "" + InetAddress.getLocalHost().getHostAddress();
	} catch (UnknownHostException e) {
	}
	return null;
    }

}
