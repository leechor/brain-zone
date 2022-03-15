package org.licho.brain.api;

/**
 *
 */
public interface INodeObject {
    ILinkObjects getOutboundLinks();

    ILinkObjects getInboundLinks();

    IFixedObject getFixed();
}
