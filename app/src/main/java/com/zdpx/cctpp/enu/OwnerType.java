package com.zdpx.cctpp.enu;

/**
 * Found in the Owner property of the Seize Step, this determines which object will be allocated capacity of the
 * seized object. In other words, which object will perform the seized - the parent object of the token or the
 * associated object of the token.
 */
public enum OwnerType {
    ParentObject,
    AssociatedObject,
    SpecificObject,
}
