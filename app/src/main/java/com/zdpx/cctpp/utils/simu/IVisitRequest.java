package com.zdpx.cctpp.utils.simu;

import com.zdpx.cctpp.concrete.TokenRunSpace;
import com.zdpx.cctpp.concrete.IntelligentObjectRunSpace;
import com.zdpx.cctpp.concrete.NodeRunSpace;
import com.zdpx.cctpp.concrete.entity.EntityRunSpace;

/**
 *
 */
public interface IVisitRequest {
    IntelligentObjectRunSpace imethod_0();

    double imethod_1();

    NodeRunSpace imethod_2();

    boolean imethod_3();

    boolean imethod_4(EntityRunSpace param0, TokenRunSpace param1);

    boolean imethod_5(EntityRunSpace param0, TokenRunSpace param1);

    void imethod_6(TokenRunSpace param0, EntityRunSpace param1);

    void imethod_7();
}
