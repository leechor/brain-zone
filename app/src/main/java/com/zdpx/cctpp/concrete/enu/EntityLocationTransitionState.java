package com.zdpx.cctpp.concrete.enu;

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
