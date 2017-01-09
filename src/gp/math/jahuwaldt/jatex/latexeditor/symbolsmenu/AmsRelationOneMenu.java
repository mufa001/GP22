/*
 * AmsRelationOneMenu.java
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
 * Menu containing LaTeX AMS Relation #1 items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class AmsRelationOneMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public AmsRelationOneMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("AmsRelation1"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\leqq", iconPath + "leqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\leqslant", iconPath + "leqslant.png"));
        add(new MathSymbolMenuItem(editorPane, "\\eqslantless", iconPath + "eqslantless.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lesssim", iconPath + "lesssim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lessapprox", iconPath + "lessapprox.png"));
        add(new MathSymbolMenuItem(editorPane, "\\approxeq", iconPath + "approxeq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lessdot", iconPath + "lessdot.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lll", iconPath + "lll.png"));
        add(new MathSymbolMenuItem(editorPane, "\\llless", iconPath + "llless.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lessgtr", iconPath + "lessgtr.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lesseqgtr", iconPath + "lesseqgtr.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lesseqqgtr", iconPath + "lesseqqgtr.png"));
        add(new MathSymbolMenuItem(editorPane, "\\doteqdot", iconPath + "doteqdot.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Doteq", iconPath + "caseDoteq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\risingdotseq", iconPath + "risingdotseq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\fallingdotseq", iconPath + "fallingdotseq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\backsim", iconPath + "backsim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\backsimeq", iconPath + "backsimeq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\subseteqq", iconPath + "subseteqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Subset", iconPath + "caseSubset.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sqsubset", iconPath + "sqsubset.png"));
        add(new MathSymbolMenuItem(editorPane, "\\preccurlyeq", iconPath + "preccurlyeq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\curlyeqprec", iconPath + "curlyeqprec.png"));
        add(new MathSymbolMenuItem(editorPane, "\\precsim", iconPath + "precsim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\precapprox", iconPath + "precapprox.png"));
        add(new MathSymbolMenuItem(editorPane, "\\vartriangleleft", iconPath + "vartriangleleft.png"));
        add(new MathSymbolMenuItem(editorPane, "\\trianglelefteq", iconPath + "trianglelefteq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\vDash", iconPath + "caseDvDash.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Vvdash", iconPath + "Vvdash.png"));
        add(new MathSymbolMenuItem(editorPane, "\\smallsmile", iconPath + "smallsmile.png"));
        add(new MathSymbolMenuItem(editorPane, "\\smallfrown", iconPath + "smallfrown.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bumpeq", iconPath + "bumpeq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Bumpeq", iconPath + "caseBumpeq.png"));

        fireMaxIconWidth();
    }
}
