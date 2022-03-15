package org.licho.brain.concrete.enu;

/**
 *
 */
public enum EntityLocationTransitionState {
    None,
    WaitingToTransferIntoNode,
    WaitingToTransferIntoStation,
    WaitingToTransferOntoLink,
    WaitingToTransferToBatchMembersQueue,
    WaitingToFlowThroughRegulator,
    FlowingThroughRegulator,
    Destroying
}
