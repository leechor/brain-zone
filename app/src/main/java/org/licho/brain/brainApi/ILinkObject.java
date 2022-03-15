package org.licho.brain.brainApi;

/**
 *
 */
public interface ILinkObject {
    INodeObject Begin();

    INodeObject End();

    ILinkVertices InteriorVertices();

    ILinkNetworks Networks();
}
