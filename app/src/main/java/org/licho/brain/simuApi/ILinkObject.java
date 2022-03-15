package org.licho.brain.simuApi;

/**
 *
 */
public interface ILinkObject {
    INodeObject Begin();

    INodeObject End();

    ILinkVertices InteriorVertices();

    ILinkNetworks Networks();
}
