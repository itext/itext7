package com.itextpdf.model.border;

import com.itextpdf.canvas.PdfCanvas;
import com.itextpdf.canvas.color.DeviceCmyk;
import com.itextpdf.canvas.color.DeviceGray;
import com.itextpdf.canvas.color.DeviceRgb;

public class InsetBorder extends Border3D {

    public InsetBorder(float width) {
        super(width);
    }

    public InsetBorder(DeviceRgb color, float width) {
        super(color, width);
    }

    public InsetBorder(DeviceCmyk color, float width) {
        super(color, width);
    }

    public InsetBorder(DeviceGray color, float width) {
        super(color, width);
    }

    @Override
    protected void setInnerHalfColor(PdfCanvas canvas, Side side) {
        switch (side) {
            case TOP:
            case LEFT:
                canvas.setFillColor(getDarkerColor());
                break;
            case BOTTOM:
            case RIGHT:
                canvas.setFillColor(getColor());
                break;
        }
    }

    @Override
    protected void setOuterHalfColor(PdfCanvas canvas, Side side) {
        switch (side) {
            case TOP:
            case LEFT:
                canvas.setFillColor(getDarkerColor());
                break;
            case BOTTOM:
            case RIGHT:
                canvas.setFillColor(getColor());
                break;
        }
    }
}
