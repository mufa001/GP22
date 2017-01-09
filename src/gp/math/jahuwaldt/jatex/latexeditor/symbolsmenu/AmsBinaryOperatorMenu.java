/*
 * AmsBinaryOperatorMenu.java
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
 * Menu containing LaTeX AMS Binary Operator items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class AmsBinaryOperatorMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public AmsBinaryOperatorMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("AmsBinaryOperatorLabel"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\dotplus", iconPath + "dotplus.png"));
        add(new MathSymbolMenuItem(editorPane, "\\smallsetminus", iconPath + "smallsetminus.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Cap", iconPath + "caseCap.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Cup", iconPath + "caseCup.png"));
        add(new MathSymbolMenuItem(editorPane, "\\barwedge", iconPath + "barwedge.png"));
        add(new MathSymbolMenuItem(editorPane, "\\veebar", iconPath + "veebar.png"));
        add(new MathSymbolMenuItem(editorPane, "\\doublebarwedge", iconPath + "doublebarwedge.png"));
        add(new MathSymbolMenuItem(editorPane, "\\boxminus", iconPath + "boxminus.png"));
        add(new MathSymbolMenuItem(editorPane, "\\boxtimes", iconPath + "boxtimes.png"));
        add(new MathSymbolMenuItem(editorPane, "\\boxdot", iconPath + "boxdot.png"));
        add(new MathSymbolMenuItem(editorPane, "\\boxplus", iconPath + "boxplus.png"));
        add(new MathSymbolMenuItem(editorPane, "\\divideontimes", iconPath + "divideontimes.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ltimes", iconPath + "ltimes.png"));
        add(new MathSymbolMenuItem(editorPane, "\\rtimes", iconPath + "rtimes.png"));
        add(new MathSymbolMenuItem(editorPane, "\\leftthreetimes", iconPath + "leftthreetimes.png"));
        add(new MathSymbolMenuItem(editorPane, "\\rightthreetimes", iconPath + "rightthreetimes.png"));
        add(new MathSymbolMenuItem(editorPane, "\\curlywedge", iconPath + "curlywedge.png"));
        add(new MathSymbolMenuItem(editorPane, "\\curlyvee", iconPath + "curlyvee.png"));
        add(new MathSymbolMenuItem(editorPane, "\\circleddash", iconPath + "circleddash.png"));
        add(new MathSymbolMenuItem(editorPane, "\\circledast", iconPath + "circledast.png"));
        add(new MathSymbolMenuItem(editorPane, "\\circledcirc", iconPath + "circledcirc.png"));
        add(new MathSymbolMenuItem(editorPane, "\\centerdot", iconPath + "centerdot.png"));
        add(new MathSymbolMenuItem(editorPane, "\\intercal", iconPath + "intercal.png"));

        fireMaxIconWidth();
    }
}
