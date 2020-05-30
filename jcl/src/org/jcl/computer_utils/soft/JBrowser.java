package org.jcl.computer_utils.soft;

import java.io.IOException;


public class JBrowser {
    public static void main(String[] args) {
	System.out.println("HEY");	
    }
    public static void searchGooglePictures(String any) {
	try {
	    openLink("https://www.google.ru/search?&q=" + any + "&tbm=isch");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static void searchGoogle(String any) {
	try {
	    openLink("https://www.google.ru/search?&q=" + any);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static void openLink(String browser, String Link) {
	JCommand.executeCommand("start " + browser + " " + Link);
    }

    /**
     * Method that open a given link
     * 
     * @category OS independent
     * @param Link
     *            - a given link to be opened
     * @throws IOException
     */
    public static void openLink(String Link) throws IOException {
	OsDetector.OSType ostype = OsDetector.getOperatingSystemType();

	switch (ostype) {

	case Windows:

	    JCommand.executeCommand("rundll32 url.dll,FileProtocolHandler " + Link);
	    break;

	case MacOS:

	    JCommand.executeCommand("open " + Link);
	    break;

	case Unix:
	case Linux:

	    String[] browsers = { "epiphany", "firefox", "mozilla", "konqueror", "netscape", "opera", "links", "lynx" };

	    StringBuffer cmd = new StringBuffer();
	    for (int i = 0; i < browsers.length; i++)
		if (i == 0)
		    cmd.append(String.format("%s \"%s\"", browsers[i], Link));
		else
		    cmd.append(String.format(" || %s \"%s\"", browsers[i], Link));
	    // If the first didn't work, try the next browser and so on

	    JCommand.executeCommand(new String[] { "sh", "-c", cmd.toString() });
	    break;
	case Other:
	    break;
	}

    }

}
