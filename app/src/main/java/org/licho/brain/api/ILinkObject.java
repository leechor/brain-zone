package org.licho.brain.api;

/**
 *
 */
public interface ILinkObject {
    INodeObject Begin();

    INodeObject End();

    ILinkVertices InteriorVertices();

    ILinkNetworks Networks();
}
