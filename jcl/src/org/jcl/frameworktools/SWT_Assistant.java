package org.jcl.frameworktools;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class SWT_Assistant {
    /**
     * Method for resizing the given image
     * 
     * @param image
     *            the image to be resized
     * @param width
     *            the width, width of image to be set
     * @param height
     *            the height, height of image to be set
     * @return the resized version of the image
     */
    public static Image resize(Image image, int width, int height) {
	Image scaled = new Image(Display.getDefault(), width, height);
	GC gc = new GC(scaled);
	gc.setAntialias(SWT.ON);
	gc.setInterpolation(SWT.HIGH);
	gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);

	// don't forget about me!
	gc.dispose();
	image.dispose();
	return scaled;
    }
}
