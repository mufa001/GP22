/*
 * LatexConstructMenu.java
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
 * Menu containing LaTeX Construct items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class LatexConstructMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public LatexConstructMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("LatexConstruct"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\widetilde{ }", "\\widetilde{}", iconPath + "widetilde.png"));
        add(new MathSymbolMenuItem(editorPane, "\\widehat{ }", "\\widehat{}", iconPath + "widehat.png"));
        add(new MathSymbolMenuItem(editorPane, "\\overleftarrow{ }", "\\overleftarrow{}", iconPath + "overleftarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\overrightarrow{ }", "\\overrightarrow{}", iconPath + "overrightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\overline{ }", "\\overline{}", iconPath + "overline.png"));
        add(new MathSymbolMenuItem(editorPane, "\\underline{ }", "\\underline{}", iconPath + "underline.png"));
        add(new MathSymbolMenuItem(editorPane, "\\overbrace{ }", "\\overbrace{}", iconPath + "overbrace.png"));
        add(new MathSymbolMenuItem(editorPane, "\\underbrace{ }", "\\underbrace{}", iconPath + "underbrace.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sqrt{ }", "\\sqrt{}", iconPath + "sqrt.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sqrt[ ]{ }", "\\sqrt[]{}", iconPath + "sqrtn.png"));
        add(new MathSymbolMenuItem(editorPane, "\\frac{ }{ }", "\\frac{}{}", iconPath + "frac.png"));

        fireMaxIconWidth();
    }
}
