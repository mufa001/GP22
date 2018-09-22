/*
 * AmsSpecialMenu.java
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
 * Menu containing LaTeX AMS Special items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class AmsSpecialMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public AmsSpecialMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("LatexSpecial"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\nobreakdash", null));
        add(new MathSymbolMenuItem(editorPane, "\\leftroot", null));
        add(new MathSymbolMenuItem(editorPane, "\\uproot", null));
        add(new MathSymbolMenuItem(editorPane, "\\accentedsymbol", null));
        add(new MathSymbolMenuItem(editorPane, "\\xleftarrow{ }", "\\xleftarrow{}", iconPath + "xleftarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\xrightarrow{ }", "\\xrightarrow{}", iconPath + "xrightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\overset{ }{ }", "\\overset{}{}", iconPath + "overset.png"));
        add(new MathSymbolMenuItem(editorPane, "\\underset{ }{ }", "\\underset{}{}", iconPath + "underset.png"));
        add(new MathSymbolMenuItem(editorPane, "\\dfrac{ }{ }", "\\dfrac{}{}", iconPath + "dfrac.png"));
        add(new MathSymbolMenuItem(editorPane, "\\genfrac{ }{ }{ }{ }{ }{ }", "\\genfrac{}{}{}{}{}{}", null));
        add(new MathSymbolMenuItem(editorPane, "\\tfrac{ }{ }", "\\tfrac{}{}", iconPath + "tfrac.png"));
        add(new MathSymbolMenuItem(editorPane, "\\binom{ }{ }", "\\binom{}{}", iconPath + "binom.png"));
        add(new MathSymbolMenuItem(editorPane, "\\dbinom{ }{ }", "\\dbinom{}{}", iconPath + "dbinom.png"));
        add(new MathSymbolMenuItem(editorPane, "\\tbinom{ }{ }", "\\tbinom{}{}", iconPath + "tbinom.png"));
        add(new MathSymbolMenuItem(editorPane, "\\smash", null));
        add(new MathSymbolMenuItem(editorPane, "\\eucal", null));
        add(new MathSymbolMenuItem(editorPane, "\\boldsymbol{ }", "\\boldsymbol{}", iconPath + "boldsymbol.png"));
        add(new MathSymbolMenuItem(editorPane, "\\text{ }", "\\text{}", iconPath + "text.png"));
        add(new MathSymbolMenuItem(editorPane, "\\intertext", null));
        add(new MathSymbolMenuItem(editorPane, "\\substack", null));
        add(new MathSymbolMenuItem(editorPane, "\\subarray", null));
        add(new MathSymbolMenuItem(editorPane, "\\sideset", null));

        fireMaxIconWidth();
    }
}
