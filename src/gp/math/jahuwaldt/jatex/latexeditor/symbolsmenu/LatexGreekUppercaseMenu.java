/*
 * LatexGreekUppercaseMenu.java
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
 * Menu containing LaTeX Greek Upper Case items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class LatexGreekUppercaseMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public LatexGreekUppercaseMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("GreekUpperCase"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\Gamma", iconPath + "caseGamma.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Delta", iconPath + "caseDelta.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Lambda", iconPath + "caseLambda.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Phi", iconPath + "casePhi.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Pi", iconPath + "casePi.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Psi", iconPath + "casePsi.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Sigma", iconPath + "caseSigma.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Theta", iconPath + "caseTheta.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Upsilon", iconPath + "caseUpsilon.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Xi", iconPath + "caseXi.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Omega", iconPath + "caseOmega.png"));

        fireMaxIconWidth();
    }
}
