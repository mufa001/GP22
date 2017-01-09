/*
 * AmsMiscellaneousMenu.java
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
 * Menu containing LaTeX AMS Miscellaneous items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class AmsMiscellaneousMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public AmsMiscellaneousMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("AmsMiscLabel"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\hbar", iconPath + "hbar.png"));
        add(new MathSymbolMenuItem(editorPane, "\\hslash", iconPath + "hslash.png"));
        add(new MathSymbolMenuItem(editorPane, "\\vartriangle", iconPath + "vartriangle.png"));
        add(new MathSymbolMenuItem(editorPane, "\\triangledown", iconPath + "triangledown.png"));
        add(new MathSymbolMenuItem(editorPane, "\\square", iconPath + "square.png"));
        add(new MathSymbolMenuItem(editorPane, "\\lozenge", iconPath + "lozenge.png"));
        add(new MathSymbolMenuItem(editorPane, "\\circledS", iconPath + "circledS.png"));
        add(new MathSymbolMenuItem(editorPane, "\\angle", iconPath + "angle.png"));
        add(new MathSymbolMenuItem(editorPane, "\\measuredangle", iconPath + "measuredangle.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nexists", iconPath + "nexists.png"));
        add(new MathSymbolMenuItem(editorPane, "\\mho", iconPath + "mho.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Finv", iconPath + "Finv.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Game", iconPath + "Game.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Bbbk", iconPath + "Bbbk.png"));
        add(new MathSymbolMenuItem(editorPane, "\\backprime", iconPath + "backprime.png"));
        add(new MathSymbolMenuItem(editorPane, "\\varnothing", iconPath + "varnothing.png"));
        add(new MathSymbolMenuItem(editorPane, "\\blacktriangle", iconPath + "blacktriangle.png"));
        add(new MathSymbolMenuItem(editorPane, "\\blacktriangledown", iconPath + "blacktriangledown.png"));
        add(new MathSymbolMenuItem(editorPane, "\\blacksquare", iconPath + "blacksquare.png"));
        add(new MathSymbolMenuItem(editorPane, "\\blacklozenge", iconPath + "blacklozenge.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bigstar", iconPath + "bigstar.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sphericalangle", iconPath + "sphericalangle.png"));
        add(new MathSymbolMenuItem(editorPane, "\\complement", iconPath + "complement.png"));
        add(new MathSymbolMenuItem(editorPane, "\\eth", iconPath + "eth.png"));
        add(new MathSymbolMenuItem(editorPane, "\\diagup", iconPath + "diagup.png"));
        add(new MathSymbolMenuItem(editorPane, "\\diagdown", iconPath + "diagdown.png"));

        fireMaxIconWidth();
    }
}
