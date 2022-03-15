package org.licho.brain.utils.simu;

import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.NodeRunSpace;
import org.licho.brain.concrete.entity.EntityRunSpace;

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
