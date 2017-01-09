/*
 * LatexAccentMenu.java
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

import gp.math.jahuwaldt.jatex.latexeditor.LatexEditorPane;

/**
 * Menu containing LaTeX Accent items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class LatexAccentMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public LatexAccentMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("LatexAccent"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\hat{ }", "\\hat{}", iconPath + "hat.png"));
        add(new MathSymbolMenuItem(editorPane, "\\acute{ }", "\\acute{}", iconPath + "acute.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bar{ }", "\\bar{}", iconPath + "bar.png"));
        add(new MathSymbolMenuItem(editorPane, "\\dot{ }", "\\dot{}", iconPath + "dot.png"));
        add(new MathSymbolMenuItem(editorPane, "\\breve{ }", "\\breve{}", iconPath + "breve.png"));
        add(new MathSymbolMenuItem(editorPane, "\\check{ }", "\\check{}", iconPath + "check.png"));
        add(new MathSymbolMenuItem(editorPane, "\\grave{ }", "\\grave{}", iconPath + "grave.png"));
        add(new MathSymbolMenuItem(editorPane, "\\vec{ }", "\\vec{}", iconPath + "vec.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ddot{ }", "\\ddot{}", iconPath + "ddot.png"));
        add(new MathSymbolMenuItem(editorPane, "\\tilde{ }", "\\tilde{}", iconPath + "tilde.png"));

        fireMaxIconWidth();
    }
}
