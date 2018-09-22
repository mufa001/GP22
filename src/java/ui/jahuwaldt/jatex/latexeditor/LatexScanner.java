/*
 * LatexScanner.java
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

package java.ui.jahuwaldt.jatex.latexeditor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple LaTeX code parser. A TeXMatch is returned with color,
 * position and length of the token match.
 *
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 10, 2013
 */
public class LatexScanner {

    private Matcher currentMatcher;
    private TeXToken currentToken;
    private final String input;
    private int pos;
    private static final List<TeXToken> TokenList = new ArrayList<TeXToken>();
    // TODO should be configurable
    private static final Color BROWN = new Color(128, 128, 0);
    private static final Color MAGENTA = new Color(192, 0, 192);
    private static final Color DARKBLUE = new Color(0, 0, 192);
//    private static final Color DARKGREEN = new Color(0,128,0);

    static {
        TokenList.add(new TeXToken("comment", Pattern.compile("%.*$"), DARKBLUE));
        TokenList.add(new TeXToken("command", Pattern.compile("\\\\[a-zA-Z]+"), BROWN));
        TokenList.add(new TeXToken("bracket", Pattern.compile("\\{|\\}|\\[|\\]"), MAGENTA));
    }

    public LatexScanner(String input) {
        this.input = input;
    }

    public boolean hasNext() {
        Matcher m;
        int start = Integer.MAX_VALUE;

        for (TeXToken t : TokenList) {
            m = t.pattern.matcher(input);
            if (m.find(pos)) {
                if (m.start() < start) // find first match
                {
                    start = m.start();
                    currentMatcher = m;
                    currentToken = t;
                }
            }

        }

        if (start != Integer.MAX_VALUE) {
            pos = currentMatcher.end();
            return true;
        }
        return false;
    }

    public TeXMatch next() {
        return new TeXMatch(currentMatcher, currentToken);
    }

    public static class TeXMatch {

        public final int start;
        public final int end;
        public final TeXToken token;

        public TeXMatch(Matcher m, TeXToken token) {
            start = m.start();
            end = m.end();
            this.token = token;
        }
    }

    public static class TeXToken {

        public final String identifier;
        public final Pattern pattern;
        public final Color color;

        public TeXToken(String identifier, Pattern pattern, Color color) {
            this.identifier = identifier;
            this.pattern = pattern;
            this.color = color;
        }
    }
}
