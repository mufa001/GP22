/**
*   Please feel free to use any fragment of the code in this file that you need
*   in your own work. As far as I am concerned, it's in the public domain. No
*   permission is necessary or required. Credit is always appreciated if you
*   use a large chunk or base a significant product on one of my examples,
*   but that's not required either.
*
*   This code is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
*
*      --- Elliotte Rusty Harold
**/
package gp.math.jahuwaldt.swing;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.io.*;
import java.awt.*;

/**
 * A JTextArea component that can display text that has been streamed to it
 * using an OutputStream.
 * 
 * <p> Modified by: Joseph A. Huwaldt </p>
 * 
 * @author Elliotte Rusty Harold	Date: 1999
 * @version September 14, 2012
 *
 */
@SuppressWarnings("serial")
public class JStreamedTextArea extends JTextArea {

    private OutputStream theOutput = new TextAreaOutputStream();
    private boolean autoscroll = false;	//	Auto scroll to bottom when new content added if true.

    public JStreamedTextArea() {
        this("", 12, 40);
    }

    public JStreamedTextArea(String text) {
        this(text, 12, 40);
    }

    public JStreamedTextArea(int rows, int columns) {
        this("", rows, columns);
    }

    public JStreamedTextArea(String text, int rows, int columns) {
        super(text, rows, columns);
        this.setEditable(false);
        this.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    public OutputStream getOutputStream() {
        return theOutput;
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(120, 200);
    }

    @Override
    public void append(String str) {
        super.append(str);
        if (autoscroll) // Automatically scroll to the bottom when text is appended if requested.
        {
            scrollToBottom();
        }
    }

    /**
     * Scroll to the bottom in a way that plays nice with Swing updates and that
     * does not remove the current user's selection.
     */
    private void scrollToBottom() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    int endPosition = getDocument().getLength();
                    Rectangle bottom = modelToView(endPosition);
                    scrollRectToVisible(bottom);
                } catch (BadLocationException e) {
                    System.err.println("Could not scroll to " + e);
                }
            }
        });
    }

    /**
     * Automatically move the display to the bottom when new content is appended
     * if true. Do not automatically scroll to bottom when new content is
     * appended if false.
     *
     * @param autoscroll Set to <code>true</code> to automatically scroll to the
     * bottom when new content is appended.
     */
    public void setAutoScrollToBottom(boolean autoscroll) {
        this.autoscroll = autoscroll;
    }

    private class TextAreaOutputStream extends OutputStream {

        @Override
        public void write(int b) {

            // recall that the int should really just be a byte
            b &= 0x000000FF;

            // must convert byte to a char in order to append it
            char c = (char) b;
            append(String.valueOf(c));

        }

        @Override
        public void write(byte[] b, int offset, int length) {

            append(new String(b, offset, length));

        }
    }
}
