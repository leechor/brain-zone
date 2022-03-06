package com.zdpx.cctpp.simuApi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 */
@Setter
@Getter
@RequiredArgsConstructor
public class FacilitySize {

    public double length;
    public double width;
    public double height;

    public FacilitySize(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
}
