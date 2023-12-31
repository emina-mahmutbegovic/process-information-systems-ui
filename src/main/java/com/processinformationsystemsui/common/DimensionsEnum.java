package com.processinformationsystemsui.common;

public enum DimensionsEnum {
    oneTimesTwo(new Dimensions(1,2)),
    oneTimesOne(new Dimensions(1,1)),
    twoTimesOne(new Dimensions(2,1)),
    twoTimesTwo(new Dimensions(2, 2)),
    threeTimesOne(new Dimensions(3,1)),
    fourTimesOne(new Dimensions(4, 1)),
    fourTimesTwo(new Dimensions(4, 2));

    private final Dimensions dimensions;

    DimensionsEnum(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }
}
