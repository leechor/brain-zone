package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.enu.TrafficDirection;

/**
 *
 */
public class LinkRunSpaceWrapper {
    private final LinkRunSpace linkRunSpace;
    private TrafficDirection trafficDirection;

    public LinkRunSpaceWrapper(LinkRunSpace linkRunSpace) {
        this.linkRunSpace = linkRunSpace;
    }

    public TrafficDirection getTrafficDirection() {
        return this.trafficDirection;
    }
}
