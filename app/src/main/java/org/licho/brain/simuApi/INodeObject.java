package org.licho.brain.simuApi;

/**
 *
 */
public interface INodeObject {
    ILinkObjects getOutboundLinks();

    ILinkObjects getInboundLinks();

    IFixedObject getFixed();
}
