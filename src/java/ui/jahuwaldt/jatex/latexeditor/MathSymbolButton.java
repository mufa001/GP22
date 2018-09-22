package java.ui.jahuwaldt.jatex.latexeditor;

import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * A JButton that displays a math symbol and automatically passes the specified command
 * the LaTeX editor pane.
 * 
 * <p> Modified by: Joseph A. Huwaldt </p>
 * 
 * @author Joseph A. Huwaldt Date: May 11, 2013
 * @version May 14, 2013
 */
public class MathSymbolButton extends JButton {
    
    /**
     * Construct a button that displays a math symbol and passes a command to the specified
     * LaTeX editor pane when clicked on.
     * 
     * @param editorPane    The LaTeX editor pane to receive the command when the button is selected.
     * @param latexCommand  The LaTeX command to pass to the editor when the button is clicked.
     * @param toolTipText   The tool tip text for the button.
     * @param iconResource  The path to the image file in the resources to use for the button icon.
     */
    public MathSymbolButton(LatexEditorPane editorPane, String latexCommand, String toolTipText, String iconResource) {
        this(editorPane, latexCommand, toolTipText, toolTipText, iconResource);
    }
    
    /**
     * Construct a button that displays a math symbol and passes a command to the specified
     * LaTeX editor pane when clicked on.
     * 
     * @param editorPane    The LaTeX editor pane to receive the command when the button is selected.
     * @param latexCommand  The LaTeX command to pass to the editor when the button is clicked.
     * @param toolTipText   The tool tip text for the button.
     * @param iconResource  The path to the image file in the resources to use for the button icon.
     * @param caretOffset   The position, relative to the start of the latexCommand where the caret
     *                      should be positioned in the editor upon completion of this action.
     *                      Pass -1 to not change the caret position.
     */
    public MathSymbolButton(LatexEditorPane editorPane, String latexCommand, String toolTipText, String iconResource, int caretOffset) {
        this(editorPane, latexCommand, toolTipText, toolTipText, iconResource, caretOffset);
    }
    
    /**
     * Construct a button that displays a math symbol and passes a command to the specified
     * LaTeX editor pane when clicked on.
     * 
     * @param editorPane    The LaTeX editor pane to receive the command when the button is selected.
     * @param latexCommand  The LaTeX command to pass to the editor when the button is clicked.
     * @param label         The label for the button (only shown if there is no icon).
     * @param toolTipText   The tool tip text for the button.
     * @param iconResource  The path to the image file in the resources to use for the button icon.
     */
    public MathSymbolButton(LatexEditorPane editorPane, String latexCommand, String label, String toolTipText, String iconResource) {
        this(editorPane, latexCommand, label, toolTipText, iconResource, -1);
    }
    
    /**
     * Construct a button that displays a math symbol and passes a command to the specified
     * LaTeX editor pane when clicked on.
     * 
     * @param editorPane    The LaTeX editor pane to receive the command when the button is selected.
     * @param latexCommand  The LaTeX command to pass to the editor when the button is clicked.
     * @param label         The label for the button (only shown if there is no icon).
     * @param toolTipText   The tool tip text for the button.
     * @param iconResource  The path to the image file in the resources to use for the button icon.
     * @param caretOffset   The position, relative to the start of the latexCommand where the caret
     *                      should be positioned in the editor upon completion of this action.
     *                      Pass -1 to not change the caret position.
     */
    public MathSymbolButton(LatexEditorPane editorPane, String latexCommand, String label, String toolTipText, String iconResource, int caretOffset) {
        super(label);
        
        MathSymbolAction action = new MathSymbolAction(editorPane, toolTipText, latexCommand, iconResource, caretOffset);
        setAction(action);
        setHideActionText(true);
        if (action.icon != null) {
            setIcon(action.icon);
        } else
            setText(label);
        setToolTipText(toolTipText);
        setFocusPainted(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setMargin(new Insets(0,0,0,0));
    }
}
