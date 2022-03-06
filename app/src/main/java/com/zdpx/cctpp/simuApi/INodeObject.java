package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface INodeObject {
    ILinkObjects getOutboundLinks();

    ILinkObjects getInboundLinks();

    IFixedObject getFixed();
}
