package org.jcl.computer_utils.soft;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JCommand {
    public static void shutdown(boolean forcequit) {
	if (forcequit) {
	    executeCommand("Shutdown.exe -s -t 00");
	} else {
	    executeCommand("Shutdown.exe -s");
	}
    }

    public static void setHibernateMode(boolean hiber) {
	executeCommand("powercfg -hibernate " + ((hiber) ? "on" : "off"));

    }

    public static void restart() {
	executeCommand("Shutdown.exe -r -t 00");
    }

    public static void hibernate() {
	executeCommand("Rundll32.exe Powrprof.dll,SetSuspendState");
    }

    public static void lockComputer() {
	executeCommand("Rundll32.exe user32.dll,LockWorkStation");
    }

    public static void sleep() {
	executeCommand("Rundll32.exe powrprof.dll,SetSuspendState Sleep");
    }

    public static String getCommandExecutionOutput(String command) {
	String s = "";
	try {

	    InputStream input = executeCommand(command).getInputStream();
	    BufferedInputStream buffer = new BufferedInputStream(input);
	    BufferedReader commandResult = new BufferedReader(new InputStreamReader(buffer));

	    for (String line = ""; line != null; line = commandResult.readLine()) {
		s += line.trim() + "\n";
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return s.trim();
    }

    public static Process executeCommand(String command) {
	Runtime runtime = Runtime.getRuntime();
	try {
	    return runtime.exec(command);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static Process executeCommand(String[] command) {
	Runtime runtime = Runtime.getRuntime();
	try {
	    return runtime.exec(command);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * To create a directory in Java <br>
     * P.S Both method mkdir() and mkdirs() are returning a boolean value to
     * indicate the operation status : true if succeed, false otherwise.
     * 
     * @param directory
     *            - the path of directory to be created
     * @throws NullPointerException
     *             - if the given parameter directory is null
     */
    public static void mkdirect(String directory) throws NullPointerException {
	//TODO MAKE CREATE DIRECTORY METHOD IN JFILES
	// 1.1 Creates a single directory.
	new File(directory).mkdirs();

	// 1.2 Creates a directory and all its sub-directories together.
	// new File("C:\\Directory2\\Sub2\\Sub-Sub2").mkdirs();

    }
    /**
     * Method that opens the given file using Runtime method
     * 
     * @param file
     *            the file to be opened
     */
    public static boolean open(File file) {
	if (file.exists()) {
	    try {
		Runtime.getRuntime().exec(file.getAbsolutePath());
	    } catch (IOException e) {
		System.err.println("@SFiles$openFile(File) : boolean - " + e.getMessage());
	    }
	    return true;
	} else {
	    return false;
	}
    }
    /**
     * Method for the parent thread to sleep
     * 
     * @param time
     *            the amount of time to sleep
     */
    public static void pause(int time) {
	try {
	    Thread.sleep(time);
	} catch (InterruptedException e) {
	    System.err.println("@SFiles$pause(int) : void - " + e.getMessage());
	}
    }
}
