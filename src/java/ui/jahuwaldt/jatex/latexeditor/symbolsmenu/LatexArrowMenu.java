/*
 * LatexArrowMenu.java
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
 * Menu containing LaTeX Arrow items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class LatexArrowMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public LatexArrowMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("LatexArrow"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\leftarrow", iconPath + "leftarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Leftarrow", iconPath + "caseLeftarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\rightarrow", iconPath + "caserightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Rightarrow", iconPath + "Rightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\leftrightarrow", iconPath + "leftrightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Leftrightarrow", iconPath + "caseLeftrightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\mapsto", iconPath + "mapsto.png"));
        add(new MathSymbolMenuItem(editorPane, "\\hookleftarrow", iconPath + "hookleftarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\leftharpoonup", iconPath + "leftharpoonup.png"));
        add(new MathSymbolMenuItem(editorPane, "\\leftharpoondown", iconPath + "leftharpoondown.png"));
        add(new MathSymbolMenuItem(editorPane, "\\longleftarrow", iconPath + "longleftarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Longleftarrow", iconPath + "caseLongleftarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\longrightarrow", iconPath + "longrightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Longrightarrow", iconPath + "caseLongrightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\longleftrightarrow", iconPath + "longleftrightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Longleftrightarrow", iconPath + "caseLongleftrightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\longmapsto", iconPath + "longmapsto.png"));
        add(new MathSymbolMenuItem(editorPane, "\\hookrightarrow", iconPath + "hookrightarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\rightharpoonup", iconPath + "rightharpoonup.png"));
        add(new MathSymbolMenuItem(editorPane, "\\rightharpoondown", iconPath + "rightharpoondown.png"));
        add(new MathSymbolMenuItem(editorPane, "\\uparrow", iconPath + "uparrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Uparrow", iconPath + "caseUparrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\downarrow", iconPath + "downarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Downarrow", iconPath + "caseDownarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\updownarrow", iconPath + "updownarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Updownarrow", iconPath + "caseUpdownarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nearrow", iconPath + "nearrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\searrow", iconPath + "searrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\swarrow", iconPath + "swarrow.png"));
        add(new MathSymbolMenuItem(editorPane, "\\nwarrow", iconPath + "nwarrow.png"));

        fireMaxIconWidth();
    }
}
