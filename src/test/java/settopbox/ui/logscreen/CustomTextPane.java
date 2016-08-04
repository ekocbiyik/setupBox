package settopbox.ui.logscreen;

import javax.swing.*;
import javax.swing.text.*;


/**
 * Created by enbiya on 12.04.2016.
 */
public class CustomTextPane extends JTextPane {

   // Bu class logscreen ekranında bilgiler yazılırken
   // tek satırda yazılması için oluşturuldu
    // normal jtextpane kullanırken uzun satırlar wrap oluyor

    private boolean lineWrap;

    public CustomTextPane(final boolean lineWrap) {
        this.lineWrap = lineWrap;

        if (lineWrap)
            setEditorKit(new WrapEditorKit());
    }


    @Override
    public boolean getScrollableTracksViewportWidth() {
        if (lineWrap)
            return super.getScrollableTracksViewportWidth();
        else
            return getParent() == null || getUI().getPreferredSize(this).width <= getParent().getSize().width;
    }


    private class WrapEditorKit extends StyledEditorKit {
        private final ViewFactory defaultFactory = new WrapColumnFactory();

        @Override
        public ViewFactory getViewFactory() {
            return defaultFactory;
        }
    }


    private class WrapColumnFactory implements ViewFactory {

        public View create(final Element element) {

            final String kind = element.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new WrapLabelView(element);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(element);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(element, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(element);
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(element);
                }
            }

            return new LabelView(element);
        }
    }

    private class WrapLabelView extends LabelView {

        public WrapLabelView(final Element element) {
            super(element);
        }

        @Override
        public float getMinimumSpan(final int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0;
                case View.Y_AXIS:
                    return super.getMinimumSpan(axis);
                default:
                    throw new IllegalArgumentException("Invalid axis: " + axis);
            }
        }
    }
}