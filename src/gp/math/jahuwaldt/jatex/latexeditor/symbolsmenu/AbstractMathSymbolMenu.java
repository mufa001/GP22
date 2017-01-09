/*
 * AbstractMathSymbolMenu.java
 *
 * Copyright (C) 2006 Jess Thrysoee
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package gp.math.jahuwaldt.jatex.latexeditor.symbolsmenu;

import java.awt.Component;
import java.beans.PropertyChangeSupport;
import java.util.ResourceBundle;
import javax.swing.JMenu;

/**
 * An abstract menu that will contain items representing LaTeX math symbols.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public abstract class AbstractMathSymbolMenu extends JMenu {

    /**
     * The resource bundle for this package.
     */
    public static final ResourceBundle RESOURCES =
            ResourceBundle.getBundle("gp.math.jahuwaldt.jatex.latexeditor.symbolsmenu.resources", java.util.Locale.getDefault());
    private final PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param s
     */
    public AbstractMathSymbolMenu(String s) {
        super(s);
    }

    /**
     *
     * @param item
     */
    public void add(MathSymbolMenuItem item) {
        super.add(item);
        propertyChange.addPropertyChangeListener(item);
    }

    /**
     * Inform all menu components of the max icon width amongst them. The components
     * should then update their icon width to this max value. This is done to align the
     * item labels.
     */
    protected void fireMaxIconWidth() {
        int maxIconWidth = 0;

        for (Component item : getMenuComponents()) {
            if (item instanceof MathSymbolMenuItem) {
                MathSymbolMenuItem symbolItem = (MathSymbolMenuItem) item;

                if (symbolItem.getIconWidth() > maxIconWidth) {
                    maxIconWidth = symbolItem.getIconWidth();
                }
            }
        }
        propertyChange.firePropertyChange("MaxIconWidth", "", Integer.valueOf(maxIconWidth));
    }
}
