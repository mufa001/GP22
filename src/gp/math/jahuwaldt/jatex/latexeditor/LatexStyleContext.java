/*
 * LatexStyleContext.java
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

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Segment;
import javax.swing.text.StyleContext;
import javax.swing.text.Utilities;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.WrappedPlainView;

/**
 * A collection of styles used to render LaTeX text. This class also acts as a factory
 * for the views used to represent the LaTeX documents. Since the rendering styles are
 * based upon view preferences, the views need a way to gain access to the style settings
 * which is facilitated by implementing the factory in the style storage.
 *
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 10, 2013
 */
public class LatexStyleContext extends StyleContext implements ViewFactory {

    private static final Logger log = Logger.getLogger(LatexStyleContext.class.getName());

    /**
     * ViewFactory method
     */
    @Override
    public View create(Element elem) {
        return new LatexView(elem, true);
    }

    /**
     * View that uses the lexical information to determine the style characteristics of
     * the text that it renders. This simply colorizes the various tokens and assumes a
     * constant font family and size.
     */
    static class LatexView extends WrappedPlainView {

        /**
         * Construct a simple colorized view of latex text.
         */
        LatexView(Element elem, boolean wordWrap) {
            super(elem, wordWrap);
        }

        /**
         * Renders the given range in the model as normal unselected text. This is
         * implemented to paint colors based upon the token-to-color translations.
         *
         * @param g the graphics context
         * @param x the starting X coordinate
         * @param y the starting Y coordinate
         * @param p0 the beginning position in the model
         * @param p1 the ending position in the model
         * @returns the location of the end of the range
         * @exception BadLocationException if the range is invalid
         */
        @Override
        protected int drawUnselectedText(Graphics g, int x, int y, int p0, int p1)
                throws BadLocationException {
            int s0;
            int s1;
            int pos = 0;

            Document doc = getDocument();
            Segment segment = getLineBuffer();

            Element element = getLineElement(p0);
            int e0 = element.getStartOffset();

            //log.finest(">>>p0,p1:"+p0 +","+ p1+ "   off1,off2:" + element.getStartOffset()+"," + (element.getEndOffset() -1));
            String elementText = doc.getText(e0, element.getEndOffset() - 1 - e0);
            //log.finest("   elementText:'"+ elementText+"'");


            LatexScanner scanner = new LatexScanner(elementText);

            while (scanner.hasNext()) {
                LatexScanner.TeXMatch m = scanner.next();

                s0 = e0 + m.start;   // adjusted start
                s1 = e0 + m.end;     // adjusted end

                if (s1 < p0)
                    continue;   // outside this segment
                if (s0 > p1)
                    break;

                if (s0 < p0)
                    s0 = p0;
                if (s1 > p1)
                    s1 = p1;

                //log.finest("   s0,s1:"+s0+","+s1+ "   pos:"+pos);

                g.setColor(Color.BLACK);
                //log.finest("   1:   "+ (p0 + pos) + "," +(s0 - p0 - pos) );
                doc.getText(p0 + pos, s0 - p0 - pos, segment);
                //log.finest("   seg:'" + segment +"'");
                x = Utilities.drawTabbedText(segment, x, y, g, this, p0 + pos);

                g.setColor(m.token.color);
                //log.finest("   2:   " + s0 +"," + (s1-s0));
                doc.getText(s0, s1 - s0, segment);
                //log.finest(" seg:'" + segment +"'");
                x = Utilities.drawTabbedText(segment, x, y, g, this, s0);

                pos = s1 - p0;
                //log.finest("   pos:"+pos);
            }

            if (p0 + pos < p1) {
                //log.finest("   p0:"+p0+" + pos:"+pos+" < p1:"+p1);
                g.setColor(Color.BLACK);
                //log.finest("   3:   " + (p0 + pos) + "," + (p1 - (p0 + pos)) );
                doc.getText(p0 + pos, p1 - (p0 + pos), segment);
                //log.finest(" seg:'" + segment +"'");

                //log.finest("   len:" + segment.toString().length());
                x = Utilities.drawTabbedText(segment, x, y, g, this, p0 + pos);
            }

            return x;
        }

        /**
         * Get Element at position
         * <code>p</code>
         */
        public Element getLineElement(int p) throws BadLocationException {
            Element root = getDocument().getDefaultRootElement();

            return root.getElement(root.getElementIndex(p));
        }
    }
}
