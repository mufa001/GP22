/*
 * MathSymbolMenuItem.java
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

import gp.math.jahuwaldt.jatex.latexeditor.MathSymbolAction;
import gp.math.jahuwaldt.jatex.latexeditor.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

/**
 * A menu item that contains a LaTeX math symbol and passes the LaTeX
 * code for that symbol to the specified editor when chosen.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class MathSymbolMenuItem extends JMenuItem implements PropertyChangeListener {

    private MathSymbolAction action;

    /**
     * Creates a new instance of MathSymbolMenuItem where
     * <code>label</code> and
     * <code>latexCommand</code> are identical.
     *
     * @param editorPane
     * @param latexCommand
     * @param iconResource
     */
    public MathSymbolMenuItem(LatexEditorPane editorPane, String latexCommand, String iconResource) {
        this(editorPane, latexCommand, latexCommand, iconResource);
    }

    /**
     * Creates a new instance of MathSymbolMenuItem
     *
     * @param editorPane
     * @param label
     * @param latexCommand
     * @param iconResource
     */
    public MathSymbolMenuItem(LatexEditorPane editorPane, String label, String latexCommand, String iconResource) {
        action = new MathSymbolAction(editorPane, label, latexCommand, iconResource);
        setAction(action);

        setHorizontalTextPosition(SwingConstants.TRAILING);
    }

    /**
     *
     */
    public int getIconWidth() {
        if (action.icon == null)
            return 0;
        return action.icon.getIconWidth();
    }

    /**
     *
     * @param evt
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (action.icon != null && "MaxIconWidth".equals(evt.getPropertyName())) {
            action.icon.setIconWidth(((Integer) evt.getNewValue()).intValue());
        }
    }
}
