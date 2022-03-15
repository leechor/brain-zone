package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class MissingElementRunSpace extends AbsBaseRunSpace {
    public MissingElementRunSpace(IntelligentObjectRunSpace parent,
                                  MayApplication application, Missing missing) {
        super(parent, application, missing);
    }

}
