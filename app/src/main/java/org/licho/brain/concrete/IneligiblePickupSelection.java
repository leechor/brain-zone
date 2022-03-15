package org.licho.brain.concrete;

import org.licho.brain.concrete.entity.EntityRunSpace;
import org.licho.brain.utils.simu.IAssociatedObjectRunSpace;
import org.licho.brain.utils.simu.IVisitRequest;

/**
 *
 */
public class IneligiblePickupSelection implements IAssociatedObjectRunSpace, IVisitRequest {
    @Override
    public IntelligentObjectRunSpace AssociatedObjectRunSpace() {
        return null;
    }

    @Override
    public IntelligentObjectRunSpace imethod_0() {
        return null;
    }

    @Override
    public double imethod_1() {
        return 0;
    }

    @Override
    public NodeRunSpace imethod_2() {
        return null;
    }

    @Override
    public boolean imethod_3() {
        return false;
    }

    @Override
    public boolean imethod_4(EntityRunSpace param0, TokenRunSpace param1) {
        return false;
    }

    @Override
    public boolean imethod_5(EntityRunSpace param0, TokenRunSpace param1) {
        return false;
    }

    @Override
    public void imethod_6(TokenRunSpace param0, EntityRunSpace param1) {

    }

    @Override
    public void imethod_7() {

    }

    public boolean method_4(TransporterRunSpace transporterRunSpace, TokenRunSpace runSpace, boolean b) {
        // TODO: 2022/1/29 
        return false;
    }
}
