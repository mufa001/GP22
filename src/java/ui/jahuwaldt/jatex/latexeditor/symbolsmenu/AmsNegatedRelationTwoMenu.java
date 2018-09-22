/*
 * AmsNegatedRelationTwoMenu.java
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
 * Menu containing LaTeX AMS Negated Relation #2 items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class AmsNegatedRelationTwoMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public AmsNegatedRelationTwoMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("AmsNegRelation2"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\ngtr", iconPath + "ngtr.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ngeq", iconPath + "ngeq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ngeqslant", iconPath + "ngeqslant.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ngeqq", iconPath + "ngeqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gneq", iconPath + "gneq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gneqq", iconPath + "gneqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gvertneqq", iconPath + "gvertneqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gnsim", iconPath + "gnsim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gnapprox", iconPath + "gnapprox.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nsucc", iconPath + "nsucc.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nsucceq", iconPath + "nsucceq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\succnsim", iconPath + "succnsim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\succnapprox", iconPath + "succnapprox.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ncong", iconPath + "ncong.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nshortparallel", iconPath + "nshortparallel.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nparallel", iconPath + "nparallel.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nvDash", iconPath + "casenvDash.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nVDash", iconPath + "casecasenVDash.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ntriangleright", iconPath + "ntriangleright.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ntrianglerighteq", iconPath + "ntrianglerighteq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nsupseteq", iconPath + "nsupseteq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nsupseteqq", iconPath + "nsupseteqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\supsetneq", iconPath + "supsetneq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\varsupsetneq", iconPath + "varsupsetneq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\supsetneqq", iconPath + "supsetneqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\varsupsetneqq", iconPath + "varsupsetneqq.png"));

        fireMaxIconWidth();
    }
}
