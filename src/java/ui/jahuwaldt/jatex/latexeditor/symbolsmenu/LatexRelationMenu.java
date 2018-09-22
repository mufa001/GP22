/*
 * LatexRelationMenu.java
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
 * Menu containing LaTeX Relation items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class LatexRelationMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public LatexRelationMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("LatexRelationLabel"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\leq", iconPath + "leq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\geq", iconPath + "geq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\equiv", iconPath + "equiv.png"));
        add(new MathSymbolMenuItem(editorPane, "\\models", iconPath + "models.png"));
        add(new MathSymbolMenuItem(editorPane, "\\prec", iconPath + "prec.png"));
        add(new MathSymbolMenuItem(editorPane, "\\succ", iconPath + "succ.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sim", iconPath + "sim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\perp", iconPath + "perp.png"));
        add(new MathSymbolMenuItem(editorPane, "\\preceq", iconPath + "preceq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\succeq", iconPath + "succeq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\simeq", iconPath + "simeq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\mid", iconPath + "mid.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ll", iconPath + "ll.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gg", iconPath + "gg.png"));
        add(new MathSymbolMenuItem(editorPane, "\\asymp", iconPath + "asymp.png"));
        add(new MathSymbolMenuItem(editorPane, "\\parallel", iconPath + "parallel.png"));
        add(new MathSymbolMenuItem(editorPane, "\\subset", iconPath + "subset.png"));
        add(new MathSymbolMenuItem(editorPane, "\\supset", iconPath + "supset.png"));
        add(new MathSymbolMenuItem(editorPane, "\\approx", iconPath + "approx.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bowtie", iconPath + "bowtie.png"));
        add(new MathSymbolMenuItem(editorPane, "\\subseteq", iconPath + "subseteq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\supseteq", iconPath + "supseteq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\cong", iconPath + "cong.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Join", iconPath + "Join.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sqsubset", iconPath + "sqsubset.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sqsupset", iconPath + "sqsupset.png"));
        add(new MathSymbolMenuItem(editorPane, "\\neq", iconPath + "neq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\smile", iconPath + "smile.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sqsubseteq", iconPath + "sqsubseteq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sqsupseteq", iconPath + "sqsupseteq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\doteq", iconPath + "doteq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\frown", iconPath + "frown.png"));
        add(new MathSymbolMenuItem(editorPane, "\\in", iconPath + "in.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ni", iconPath + "ni.png"));
        add(new MathSymbolMenuItem(editorPane, "\\propto", iconPath + "propto.png"));
        add(new MathSymbolMenuItem(editorPane, "\\vdash", iconPath + "vdash.png"));
        add(new MathSymbolMenuItem(editorPane, "\\dashv", iconPath + "dashv.png"));

        fireMaxIconWidth();
    }
}
