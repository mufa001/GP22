/*
 * LatexBinaryOperatorMenu.java
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
public class LatexBinaryOperatorMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public LatexBinaryOperatorMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("LatexBinaryOperator"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\pm", iconPath + "pm.png"));
        add(new MathSymbolMenuItem(editorPane, "\\mp", iconPath + "mp.png"));
        add(new MathSymbolMenuItem(editorPane, "\\times", iconPath + "times.png"));
        add(new MathSymbolMenuItem(editorPane, "\\div", iconPath + "div.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ast", iconPath + "ast.png"));
        add(new MathSymbolMenuItem(editorPane, "\\star", iconPath + "star.png"));
        add(new MathSymbolMenuItem(editorPane, "\\circ", iconPath + "circ.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bullet", iconPath + "bullet.png"));
        add(new MathSymbolMenuItem(editorPane, "\\cdot", iconPath + "cdot.png"));
        add(new MathSymbolMenuItem(editorPane, "\\cap", iconPath + "cap.png"));
        add(new MathSymbolMenuItem(editorPane, "\\cup", iconPath + "cup.png"));
        add(new MathSymbolMenuItem(editorPane, "\\uplus", iconPath + "uplus.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sqcap", iconPath + "sqcap.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sqcup", iconPath + "sqcup.png"));
        add(new MathSymbolMenuItem(editorPane, "\\vee", iconPath + "vee.png"));
        add(new MathSymbolMenuItem(editorPane, "\\wedge", iconPath + "wedge.png"));
        add(new MathSymbolMenuItem(editorPane, "\\setminus", iconPath + "setminus.png"));
        add(new MathSymbolMenuItem(editorPane, "\\wr", iconPath + "wr.png"));
        add(new MathSymbolMenuItem(editorPane, "\\diamond", iconPath + "diamond.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bigtriangleup", iconPath + "bigtriangleup.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bigtriangledown", iconPath + "bigtriangledown.png"));
        add(new MathSymbolMenuItem(editorPane, "\\triangleleft", iconPath + "triangleleft.png"));
        add(new MathSymbolMenuItem(editorPane, "\\triangleright", iconPath + "triangleright.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lhd", iconPath + "lhd.png"));
        add(new MathSymbolMenuItem(editorPane, "\\rhd", iconPath + "rhd.png"));
        add(new MathSymbolMenuItem(editorPane, "\\unlhd", iconPath + "unlhd.png"));
        add(new MathSymbolMenuItem(editorPane, "\\unrhd", iconPath + "unrhd.png"));
        add(new MathSymbolMenuItem(editorPane, "\\oplus", iconPath + "oplus.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ominus", iconPath + "ominus.png"));
        add(new MathSymbolMenuItem(editorPane, "\\otimes", iconPath + "otimes.png"));
        add(new MathSymbolMenuItem(editorPane, "\\oslash", iconPath + "oslash.png"));
        add(new MathSymbolMenuItem(editorPane, "\\odot", iconPath + "odot.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bigcirc", iconPath + "bigcirc.png"));
        add(new MathSymbolMenuItem(editorPane, "\\dagger", iconPath + "dagger.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ddagger", iconPath + "ddagger.png"));

        fireMaxIconWidth();
    }
}
