package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.StatisticsDataSourceInfo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class NetworkElementRunSpace extends AbsBaseRunSpace {
    private double double_0;
    private List<LinkRunSpace> linkRunSpaces = new ArrayList<>();
    private List<NetworkWrapper> networkWrappers = new ArrayList<>();
    private Enum48 enum48;

    public NetworkElementRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject,
                                  MayApplication application, AbsIntelligentPropertyObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
    }

    @Override
    public IntelligentObjectRunSpace ContextObjectRunSpace() {
        return null;
    }

    @Override
    public IntelligentObjectRunSpace AssociatedObjectRunSpace() {
        return null;
    }

    @Override
    public IntelligentObjectRunSpace ParentObjectRunSpace() {
        return null;
    }

    @Override
    public AbsIntelligentPropertyObject MyElementInstance() {
        return null;
    }

    @Override
    public StatisticsDataSourceInfo GetStatisticsDataSourceInfo(String param0) {
        return null;
    }

    @Override
    public void runtimeErrorHandler(IRunSpace param0, AbsPropertyObject param1, IntelligentObjectProperty param2,
                                    String param3) {

    }

    @Override
    public void runtimeErrorHandler(double param0, IRunSpace param1, AbsPropertyObject param2,
                                    IntelligentObjectProperty param3, String param4) {

    }

    public double getNetworkDistanceToNode(NodeRunSpace sourceNodeRunSpace, NodeRunSpace targetNodeRunSpace) {
        if (sourceNodeRunSpace == null || targetNodeRunSpace == null) {
            return Double.NaN;
        }
        if (sourceNodeRunSpace == targetNodeRunSpace) {
            return 0.0;
        }
        NetworkWrapper sourceNode = sourceNodeRunSpace.getNetworkWrapper(this);
        NetworkWrapper targetNode = targetNodeRunSpace.getNetworkWrapper(this);
        if (sourceNode == null || targetNode == null) {
            return Double.NaN;
        }
        return sourceNode.getDistanceToNode(targetNode);
    }

    public double getDouble_0() {
        return this.double_0;
    }

    public void addLinkRunSpace(LinkRunSpace linkRunSpace) {
        NodeRunSpace beginNodeRunSpace = linkRunSpace.getBeginNodeRunSpace();
        NetworkWrapper networkWrapperBegin = beginNodeRunSpace.getNetworkWrapper(this);
        if (networkWrapperBegin == null) {
            networkWrapperBegin = this.addNetworkWrapper(beginNodeRunSpace);
        }
        NodeRunSpace endNodeRunSpace = linkRunSpace.getEndNodeRunSpace();
        NetworkWrapper networkWrapperEnd = endNodeRunSpace.getNetworkWrapper(this);
        if (networkWrapperEnd == null) {
            networkWrapperEnd = this.addNetworkWrapper(endNodeRunSpace);
        }
        networkWrapperBegin.addLinkRunSpace(linkRunSpace);
        networkWrapperEnd.addLinkRunSpace(linkRunSpace);
        this.linkRunSpaces.add(linkRunSpace);
    }

    private NetworkWrapper addNetworkWrapper(NodeRunSpace nodeRunSpace) {
        NetworkWrapper networkWrapper = new NetworkWrapper(this, nodeRunSpace);
        networkWrapper.setIndex(this.networkWrappers.size());
        this.networkWrappers.add(networkWrapper);
        return networkWrapper;
    }

    public void method_18() {
        for (NetworkWrapper networkWrapper : this.networkWrappers) {
            networkWrapper.method_11();
        }
    }

    public int getNetworkWrappersCount() {
        return this.networkWrappers.size();
    }

    public List<NetworkWrapper> getNetworkWrappers() {
        return this.networkWrappers;
    }

    public NetworkElementRunSpace.Enum48 getEnum48() {
        return this.enum48;
    }

    public LinkRunSpace NextLink(NodeRunSpace fromNode, NodeRunSpace toNode) {
        if (fromNode == null || toNode == null) {
            return null;
        }
        if (fromNode == toNode) {
            return null;
        }
        NetworkWrapper firstNetworkWrapper = fromNode.getNetworkWrapper(this);
        NetworkWrapper secondNetworkWrapper = toNode.getNetworkWrapper(this);
        if (firstNetworkWrapper == null || secondNetworkWrapper == null) {
            return null;
        }
        return firstNetworkWrapper.link(secondNetworkWrapper);
    }

    public boolean PathExists(NodeRunSpace fromNode, NodeRunSpace toNode) {
        double d = this.getNetworkDistanceToNode(fromNode, toNode);
        return !Double.isNaN(d);
    }

    public int NumberItems() {
        return this.linkRunSpaces.size();
    }

    public ExpressionValue ItemAtIndex(int index) {
        return ExpressionValue.from(this.linkRunSpaces.get(index));
    }

    public List<LinkRunSpace> getLinkRunSpaceList() {
        return this.linkRunSpaces;
    }

    public void removeLink(LinkRunSpace linkRunSpace) {
        NodeRunSpace beginNodeRunSpace = linkRunSpace.getBeginNodeRunSpace();
        NetworkWrapper beginNetworkWrapper = beginNodeRunSpace.getNetworkWrapper(this);
        NodeRunSpace endNodeRunSpace = linkRunSpace.getEndNodeRunSpace();
        NetworkWrapper endNetworkWrapper = endNodeRunSpace.getNetworkWrapper(this);
        beginNetworkWrapper.removeLink(linkRunSpace);
        endNetworkWrapper.removeLink(linkRunSpace);
        this.linkRunSpaces.remove(linkRunSpace);
        if (beginNetworkWrapper.forwardLinksCount() == 0 && beginNetworkWrapper.backLinksCount() == 0) {
            this.reset(beginNetworkWrapper);
        }
        if (endNetworkWrapper.forwardLinksCount() == 0 && endNetworkWrapper.backLinksCount() == 0) {
            this.reset(endNetworkWrapper);
        }
    }

    private void reset(NetworkWrapper networkWrapper) {
        if (networkWrapper.getIndex() == this.networkWrappers.size() - 1) {
            this.networkWrappers.remove(networkWrapper.getIndex());
        } else {
            NetworkWrapper wrapper = this.networkWrappers.get(this.networkWrappers.size() - 1);
            wrapper.setIndex(networkWrapper.getIndex());
            this.networkWrappers.remove(this.networkWrappers.size() - 1);
            this.networkWrappers.add(wrapper.getIndex(), wrapper);
        }
        networkWrapper.reset();
    }

    public enum Enum48 {
        Zero,
        One
    }
}
