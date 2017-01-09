/*
 * LatexLogLikeMenu.java
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
 * Menu containing LaTeX Log-Like items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class LatexLogLikeMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public LatexLogLikeMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("LatexLogLike"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\arccos", iconPath + "arccos.png"));
        add(new MathSymbolMenuItem(editorPane, "\\arcsin", iconPath + "arcsin.png"));
        add(new MathSymbolMenuItem(editorPane, "\\arctan", iconPath + "arctan.png"));
        add(new MathSymbolMenuItem(editorPane, "\\arg", iconPath + "arg.png"));
        add(new MathSymbolMenuItem(editorPane, "\\cos", iconPath + "cos.png"));
        add(new MathSymbolMenuItem(editorPane, "\\cosh", iconPath + "cosh.png"));
        add(new MathSymbolMenuItem(editorPane, "\\cot", iconPath + "cot.png"));
        add(new MathSymbolMenuItem(editorPane, "\\coth", iconPath + "coth.png"));
        add(new MathSymbolMenuItem(editorPane, "\\csc", iconPath + "csc.png"));
        add(new MathSymbolMenuItem(editorPane, "\\deg", iconPath + "deg.png"));
        add(new MathSymbolMenuItem(editorPane, "\\det", iconPath + "det.png"));
        add(new MathSymbolMenuItem(editorPane, "\\dim", iconPath + "dim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\exp", iconPath + "exp.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gcd", iconPath + "gcd.png"));
        add(new MathSymbolMenuItem(editorPane, "\\hom", iconPath + "hom.png"));
        add(new MathSymbolMenuItem(editorPane, "\\inf", iconPath + "inf.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ker", iconPath + "ker.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lg", iconPath + "lg.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lim", iconPath + "lim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\liminf", iconPath + "liminf.png"));
        add(new MathSymbolMenuItem(editorPane, "\\limsup", iconPath + "limsup.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ln", iconPath + "ln.png"));
        add(new MathSymbolMenuItem(editorPane, "\\log", iconPath + "log.png"));
        add(new MathSymbolMenuItem(editorPane, "\\max", iconPath + "max.png"));
        add(new MathSymbolMenuItem(editorPane, "\\min", iconPath + "min.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Pr", iconPath + "Pr.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sec", iconPath + "sec.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sin", iconPath + "sin.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sinh", iconPath + "sinh.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sup", iconPath + "sup.png"));
        add(new MathSymbolMenuItem(editorPane, "\\tan", iconPath + "tan.png"));
        add(new MathSymbolMenuItem(editorPane, "\\tanh", iconPath + "tanh.png"));

        fireMaxIconWidth();
    }
}
