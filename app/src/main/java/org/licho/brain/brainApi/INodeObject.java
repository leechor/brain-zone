package org.licho.brain.brainApi;

/**
 *
 */
public interface INodeObject {
    ILinkObjects getOutboundLinks();

    ILinkObjects getInboundLinks();

    IFixedObject getFixed();
}
