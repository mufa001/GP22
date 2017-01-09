/*
 * LatexEditorKit.java
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

package gp.math.jahuwaldt.jatex.latexeditor;

import javax.swing.text.DefaultEditorKit;
import javax.swing.text.ViewFactory;

/**
 * View is responsible for rendering a particular Element, and EditorKit is responsible
 * for a ViewFactory that is able to decide what View should be created based on an
 * Element.
 *
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 10, 2013
 */
public class LatexEditorKit extends DefaultEditorKit {

    private LatexStyleContext styleContext;

    /**
     * Get the MIME type of the data that this kit represents support for. This kit
     * supports the type
     * <code>text/latex</code>.
     */
    @Override
    public String getContentType() {
        return "text/latex";
    }

    /**
     * Fetches a factory that is suitable for producing views of any models that are
     * produced by this kit.
     *
     * @return the view factory
     */
    @Override
    public final ViewFactory getViewFactory() {
        if (styleContext == null) {
            styleContext = new LatexStyleContext();
        }
        return styleContext;
    }
}
