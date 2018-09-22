/*
 * AmsArrowMenu.java
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
 * Menu containing LaTeX AMS Arrow items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class AmsArrowMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public AmsArrowMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("AmsArrowLabel"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\dashrightarrow", iconPath + "dashrightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\dashleftarrow", iconPath + "dashleftarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\leftleftarrows", iconPath + "leftleftarrows.png"));
        add(new MathSymbolMenuItem(editorPane, "\\leftrightarrows", iconPath + "leftrightarrows.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Lleftarrow", iconPath + "Lleftarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\twoheadleftarrow", iconPath + "twoheadleftarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\leftarrowtail", iconPath + "leftarrowtail.png"));
        add(new MathSymbolMenuItem(editorPane, "\\looparrowleft", iconPath + "looparrowleft.png"));
        add(new MathSymbolMenuItem(editorPane, "\\leftrightharpoons", iconPath + "leftrightharpoons.png"));
        add(new MathSymbolMenuItem(editorPane, "\\curvearrowleft", iconPath + "curvearrowleft.png"));
        add(new MathSymbolMenuItem(editorPane, "\\circlearrowleft", iconPath + "circlearrowleft.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Lsh", iconPath + "Lsh.png"));
        add(new MathSymbolMenuItem(editorPane, "\\upuparrows", iconPath + "upuparrows.png"));
        add(new MathSymbolMenuItem(editorPane, "\\upharpoonleft", iconPath + "upharpoonleft.png"));
        add(new MathSymbolMenuItem(editorPane, "\\downharpoonleft", iconPath + "downharpoonleft.png"));
        add(new MathSymbolMenuItem(editorPane, "\\multimap", iconPath + "multimap.png"));
        add(new MathSymbolMenuItem(editorPane, "\\leftrightsquigarrow", iconPath + "leftrightsquigarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\looparrowright", iconPath + "looparrowright.png"));
        add(new MathSymbolMenuItem(editorPane, "\\rightleftharpoons", iconPath + "rightleftharpoons.png"));
        add(new MathSymbolMenuItem(editorPane, "\\curvearrowright", iconPath + "curvearrowright.png"));
        add(new MathSymbolMenuItem(editorPane, "\\circlearrowright", iconPath + "circlearrowright.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Rsh", iconPath + "Rsh.png"));
        add(new MathSymbolMenuItem(editorPane, "\\downdownarrows", iconPath + "downdownarrows.png"));
        add(new MathSymbolMenuItem(editorPane, "\\upharpoonright", iconPath + "upharpoonright.png"));
        add(new MathSymbolMenuItem(editorPane, "\\downharpoonright", iconPath + "downharpoonright.png"));
        add(new MathSymbolMenuItem(editorPane, "\\rightsquigarrow", iconPath + "rightsquigarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Rrightarrow", iconPath + "Rrightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\rightrightarrows", iconPath + "rightrightarrows.png"));
        add(new MathSymbolMenuItem(editorPane, "\\twoheadrightarrow", iconPath + "twoheadrightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\restriction", iconPath + "restriction.png"));
        add(new MathSymbolMenuItem(editorPane, "\\rightleftarrows", iconPath + "rightleftarrows.png"));
        add(new MathSymbolMenuItem(editorPane, "\\rightarrowtail", iconPath + "rightarrowtail.png"));

        fireMaxIconWidth();
    }
}
