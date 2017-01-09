/*
 * MathSymbolMenuC.java
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
 * Menu containing LaTeX Miscellaneous items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class LatexMiscellaneousMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public LatexMiscellaneousMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("LatexMiscLabel"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\nabla", iconPath + "nabla.png"));
        add(new MathSymbolMenuItem(editorPane, "\\aleph", iconPath + "aleph.png"));
        add(new MathSymbolMenuItem(editorPane, "\\prime", iconPath + "prime.png"));
        add(new MathSymbolMenuItem(editorPane, "\\forall", iconPath + "forall.png"));
        add(new MathSymbolMenuItem(editorPane, "\\infty", iconPath + "infty.png"));
        add(new MathSymbolMenuItem(editorPane, "\\hbar", iconPath + "hbar.png"));
        add(new MathSymbolMenuItem(editorPane, "\\emptyset", iconPath + "emptyset.png"));
        add(new MathSymbolMenuItem(editorPane, "\\exists", iconPath + "exists.png"));
        add(new MathSymbolMenuItem(editorPane, "\\surd", iconPath + "surd.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Box", iconPath + "Box.png"));
        add(new MathSymbolMenuItem(editorPane, "\\triangle", iconPath + "triangle.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Diamond", iconPath + "caseDiamond.png"));
        add(new MathSymbolMenuItem(editorPane, "\\imath", iconPath + "imath.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ell", iconPath + "ell.png"));
        add(new MathSymbolMenuItem(editorPane, "\\neg", iconPath + "neg.png"));
        add(new MathSymbolMenuItem(editorPane, "\\not", iconPath + "not.png"));
        add(new MathSymbolMenuItem(editorPane, "\\top", iconPath + "top.png"));
        add(new MathSymbolMenuItem(editorPane, "\\flat", iconPath + "flat.png"));
        add(new MathSymbolMenuItem(editorPane, "\\natural", iconPath + "natural.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sharp", iconPath + "sharp.png"));
        add(new MathSymbolMenuItem(editorPane, "\\wp", iconPath + "wp.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bot", iconPath + "bot.png"));
        add(new MathSymbolMenuItem(editorPane, "\\clubsuit", iconPath + "clubsuit.png"));
        add(new MathSymbolMenuItem(editorPane, "\\diamondsuit", iconPath + "diamondsuit.png"));
        add(new MathSymbolMenuItem(editorPane, "\\heartsuit", iconPath + "heartsuit.png"));
        add(new MathSymbolMenuItem(editorPane, "\\spadesuit", iconPath + "spadesuit.png"));
        add(new MathSymbolMenuItem(editorPane, "\\mho", iconPath + "mho.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Re", iconPath + "Re.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Im", iconPath + "Im.png"));
        add(new MathSymbolMenuItem(editorPane, "\\angle", iconPath + "angle.png"));
        add(new MathSymbolMenuItem(editorPane, "\\partial", iconPath + "partial.png"));

        fireMaxIconWidth();
    }
}
