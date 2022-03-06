package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.StatisticsDataSourceInfo;

/**
 *
 */
public class MissingElementRunSpace extends AbsBaseRunSpace {
    public MissingElementRunSpace(IntelligentObjectRunSpace parent,
                                  MayApplication application, Missing missing) {
        super(parent, application, missing);
    }

}
