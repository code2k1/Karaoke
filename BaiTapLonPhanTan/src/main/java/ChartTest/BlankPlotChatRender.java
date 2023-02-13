package ChartTest;

import java.awt.Graphics2D;

@SuppressWarnings("hiding")
public abstract class BlankPlotChatRender<SeriesSize> {

    public abstract String getLabelText(int index);

    public abstract void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index);
}