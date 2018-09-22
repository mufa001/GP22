/*
 * LatexVariableSizeMenu.java
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
 * Menu containing LaTeX Variable Size items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class LatexVariableSizeMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public LatexVariableSizeMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("LatexVarSizeLabel"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\sum", iconPath + "sum.png"));
        add(new MathSymbolMenuItem(editorPane, "\\prod", iconPath + "prod.png"));
        add(new MathSymbolMenuItem(editorPane, "\\int", iconPath + "int.png"));
        add(new MathSymbolMenuItem(editorPane, "\\oint", iconPath + "oint.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bigcap", iconPath + "bigcap.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bigcup", iconPath + "bigcup.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bigsqcup", iconPath + "bigsqcup.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bigvee", iconPath + "bigvee.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bigwedge", iconPath + "bigwedge.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bigodot", iconPath + "bigodot.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bigotimes", iconPath + "bigotimes.png"));
        add(new MathSymbolMenuItem(editorPane, "\\bigoplus", iconPath + "bigoplus.png"));
        add(new MathSymbolMenuItem(editorPane, "\\biguplus", iconPath + "biguplus.png"));

        fireMaxIconWidth();
    }
}
