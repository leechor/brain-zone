package com.zdpx.cctpp.utils.simu;

import com.zdpx.cctpp.enu.ProductComplexityLevel;

/**
 *
 */
public interface IAdvancedProperties {
    Boolean HasAdvancedProperties();

    String GetDisplayString(ProductComplexityLevel productComplexityLevel);

}
