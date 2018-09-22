/*
 * AmsNegatedRelationOneMenu.java
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

package java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu;

import java.ui.jahuwaldt.jatex.latexeditor.LatexEditorPane;

/**
 * Menu containing LaTeX AMS Negated Relationship #1 items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class AmsNegatedRelationOneMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public AmsNegatedRelationOneMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("AmsNegRelation1"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\nless", iconPath + "nless.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nleq", iconPath + "nleq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nleqslant", iconPath + "nleqslant.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nleqq", iconPath + "nleqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lneq", iconPath + "lneq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lneqq", iconPath + "lneqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lvertneqq", iconPath + "lvertneqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lnsim", iconPath + "lnsim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lnapprox", iconPath + "lnapprox.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nprec", iconPath + "nprec.png"));
        add(new MathSymbolMenuItem(editorPane, "\\npreceq", iconPath + "npreceq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\precnsim", iconPath + "precnsim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\precnapprox", iconPath + "precnapprox.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nsim", iconPath + "nsim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nshortmid", iconPath + "nshortmid.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nmid", iconPath + "nmid.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nvdash", iconPath + "nvdash.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nvDash", iconPath + "casenvDash.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ntriangleleft", iconPath + "ntriangleleft.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ntrianglelefteq", iconPath + "ntrianglelefteq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nsubseteq", iconPath + "nsubseteq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\subsetneq", iconPath + "subsetneq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\varsubsetneq", iconPath + "varsubsetneq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\subsetneqq", iconPath + "subsetneqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\varsubsetneqq", iconPath + "varsubsetneqq.png"));

        fireMaxIconWidth();
    }
}
