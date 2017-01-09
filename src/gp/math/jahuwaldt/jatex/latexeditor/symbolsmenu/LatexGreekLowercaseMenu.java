/*
 * LatexGreekLowercaseMenu.java
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
 * Menu containing LaTeX Greek Lower Case items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class LatexGreekLowercaseMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>
     *
     */
    public LatexGreekLowercaseMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("GreekLowerCase"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\alpha", iconPath + "alpha.png"));
        add(new MathSymbolMenuItem(editorPane, "\\beta", iconPath + "beta.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gamma", iconPath + "gamma.png"));
        add(new MathSymbolMenuItem(editorPane, "\\delta", iconPath + "delta.png"));
        add(new MathSymbolMenuItem(editorPane, "\\epsilon", iconPath + "epsilon.png"));
        add(new MathSymbolMenuItem(editorPane, "\\zeta", iconPath + "zeta.png"));
        add(new MathSymbolMenuItem(editorPane, "\\eta", iconPath + "eta.png"));
        add(new MathSymbolMenuItem(editorPane, "\\theta", iconPath + "theta.png"));
        add(new MathSymbolMenuItem(editorPane, "\\iota", iconPath + "iota.png"));
        add(new MathSymbolMenuItem(editorPane, "\\kappa", iconPath + "kappa.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lambda", iconPath + "lambda.png"));
        add(new MathSymbolMenuItem(editorPane, "\\mu", iconPath + "mu.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nu", iconPath + "nu.png"));
        add(new MathSymbolMenuItem(editorPane, "\\xi", iconPath + "xi.png"));
        add(new MathSymbolMenuItem(editorPane, "\\pi", iconPath + "pi.png"));
        add(new MathSymbolMenuItem(editorPane, "\\rho", iconPath + "rho.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sigma", iconPath + "sigma.png"));
        add(new MathSymbolMenuItem(editorPane, "\\tau", iconPath + "tau.png"));
        add(new MathSymbolMenuItem(editorPane, "\\upsilon", iconPath + "upsilon.png"));
        add(new MathSymbolMenuItem(editorPane, "\\phi", iconPath + "phi.png"));
        add(new MathSymbolMenuItem(editorPane, "\\chi", iconPath + "chi.png"));
        add(new MathSymbolMenuItem(editorPane, "\\psi", iconPath + "psi.png"));
        add(new MathSymbolMenuItem(editorPane, "\\omega", iconPath + "omega.png"));

        fireMaxIconWidth();
    }
}
