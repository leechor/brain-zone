package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface ILinkObject {
    INodeObject Begin();

    INodeObject End();

    ILinkVertices InteriorVertices();

    ILinkNetworks Networks();
}
