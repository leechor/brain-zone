package org.licho.brain.concrete;

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
