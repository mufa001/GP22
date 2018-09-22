/*
 * AmsRelationTwoMenu.java
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
 * Menu containing LaTeX AMS Relation #2 items.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class AmsRelationTwoMenu extends AbstractMathSymbolMenu {

    /**
     * Constructs a new
     * <code>JMenu</code>.
     *
     * @param editorPane
     */
    public AmsRelationTwoMenu(LatexEditorPane editorPane) {
        super(RESOURCES.getString("AmsRelation2"));
        String iconPath = RESOURCES.getString("iconPath");

        add(new MathSymbolMenuItem(editorPane, "\\geqq", iconPath + "geqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\geqslant", iconPath + "geqslant.png"));
        add(new MathSymbolMenuItem(editorPane, "\\eqslantgtr", iconPath + "eqslantgtr.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gtrsim", iconPath + "gtrsim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gtrapprox", iconPath + "gtrapprox.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gtrdot", iconPath + "gtrdot.png"));
        add(new MathSymbolMenuItem(editorPane, "\\ggg", iconPath + "ggg.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gggtr", iconPath + "gggtr.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gtrless", iconPath + "gtrless.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gtreqless", iconPath + "gtreqless.png"));
        add(new MathSymbolMenuItem(editorPane, "\\gtreqqless", iconPath + "gtreqqless.png"));
        add(new MathSymbolMenuItem(editorPane, "\\eqcirc", iconPath + "eqcirc.png"));
        add(new MathSymbolMenuItem(editorPane, "\\circeq", iconPath + "circeq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\triangleq", iconPath + "triangleq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\thicksim", iconPath + "thicksim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\thickapprox", iconPath + "thickapprox.png"));
        add(new MathSymbolMenuItem(editorPane, "\\supseteqq", iconPath + "supseteqq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Supset", iconPath + "caseSupset.png"));
        add(new MathSymbolMenuItem(editorPane, "\\sqsupset", iconPath + "sqsupset.png"));
        add(new MathSymbolMenuItem(editorPane, "\\succcurlyeq", iconPath + "succcurlyeq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\curlyeqsucc", iconPath + "curlyeqsucc.png"));
        add(new MathSymbolMenuItem(editorPane, "\\succsim", iconPath + "succsim.png"));
        add(new MathSymbolMenuItem(editorPane, "\\succapprox", iconPath + "succapprox.png"));
        add(new MathSymbolMenuItem(editorPane, "\\vartriangleright", iconPath + "vartriangleright.png"));
        add(new MathSymbolMenuItem(editorPane, "\\trianglerighteq", iconPath + "trianglerighteq.png"));
        add(new MathSymbolMenuItem(editorPane, "\\Vdash", iconPath + "caseVVdash.png"));
        add(new MathSymbolMenuItem(editorPane, "\\shortmid", iconPath + "shortmid.png"));
        add(new MathSymbolMenuItem(editorPane, "\\shortparallel", iconPath + "shortparallel.png"));
        add(new MathSymbolMenuItem(editorPane, "\\between", iconPath + "between.png"));
        add(new MathSymbolMenuItem(editorPane, "\\pitchfork", iconPath + "pitchfork.png"));
        add(new MathSymbolMenuItem(editorPane, "\\varpropto", iconPath + "varpropto.png"));
        add(new MathSymbolMenuItem(editorPane, "\\blacktriangleleft", iconPath + "blacktriangleleft.png"));
        add(new MathSymbolMenuItem(editorPane, "\\therefore", iconPath + "therefore.png"));
        add(new MathSymbolMenuItem(editorPane, "\\backepsilon", iconPath + "backepsilon.png"));
        add(new MathSymbolMenuItem(editorPane, "\\blacktriangleright", iconPath + "blacktriangleright.png"));
        add(new MathSymbolMenuItem(editorPane, "\\because", iconPath + "because.png"));

        fireMaxIconWidth();
    }
}
