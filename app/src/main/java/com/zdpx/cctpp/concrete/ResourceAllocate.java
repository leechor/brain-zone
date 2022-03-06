package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.ExpressionValue;

/**
 *
 */
public class ResourceAllocate {
    private IntelligentObjectRunSpace owner;
    private double resourceAllocatedNum;
    private IntelligentObjectRunSpace resourceOwnersFirstItem;

    public IntelligentObjectRunSpace getOwner() {
		return this.owner;
    }

    public double getResourceAllocated() {
		return this.resourceAllocatedNum;
    }

    public IntelligentObjectRunSpace getResourceOwnersFirstItem() {
        		return this.resourceOwnersFirstItem;

    }
}
