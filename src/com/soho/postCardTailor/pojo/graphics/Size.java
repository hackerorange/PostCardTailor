package com.soho.postCardTailor.pojo.graphics;

import com.soho.postCardTailor.exception.SizeFormatWrongException;

/**
 * 尺寸
 */
public class Size {
    private Integer width;
    private Integer height;

    public Size(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public Size() {
    }

    @SuppressWarnings({"SuspiciousNameCombination", "WeakerAccess"})
    public static Size parseSize(String size) throws SizeFormatWrongException {
        Size result = new Size();
        if (size.contains("×")) {
            String[] tmpSize = size.split("×");
            result.width = Integer.parseInt(tmpSize[0]);
            result.height = Integer.parseInt(tmpSize[1]);
        } else {
            throw new SizeFormatWrongException();
        }
        return result;
    }


    public Integer getWidth() {
        if (width < height) {
            swap();
        }
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        if (width < height) {
            swap();
        }
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 交换宽度和高度
     */
    @SuppressWarnings("SuspiciousNameCombination")
    private void swap() {
        Integer tmp;
        tmp = height;
        height = width;
        width = tmp;
    }

    @Override
    public String toString() {
        if (this.width < this.height) {
            this.swap();
        }
        return width + "×" + height;
    }
}
