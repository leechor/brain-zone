package org.licho.brain.concrete;

import org.licho.brain.utils.simu.LinkType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class NetworkWrapper {
    private static boolean bool_0 = true;
    private NetworkElementRunSpace networkElementRunSpace;
    private NodeRunSpace nodeRunSpace;
    private double double_1;
    private double[] double_0;
    private int index;
    private List<LinkRunSpace> forwardLinks = new ArrayList<>();
    private List<LinkRunSpace> backLinks = new ArrayList<>();
    private LinkRunSpace[] linkRunSpaceList;
    private double[] networkLengths;

    public NetworkWrapper(NetworkElementRunSpace networkElementRunSpace, NodeRunSpace nodeRunSpace) {
        this.networkElementRunSpace = networkElementRunSpace;
        this.nodeRunSpace = nodeRunSpace;
        this.nodeRunSpace.addNetworkWrapper(this);
    }

    public NetworkElementRunSpace getNetworkElementRunSpace() {
        return this.networkElementRunSpace;
    }

    public double getDistanceToNode(NetworkWrapper networkWrapper) {
        if (networkWrapper == this) {
            return 0.0;
        }
        if (networkWrapper == null || networkWrapper.getNetworkElementRunSpace() != this.networkElementRunSpace) {
            return Double.NaN;
        }
        if (this.double_1 < this.networkElementRunSpace.getDouble_0()) {
            this.method_11();
        }
        if (this.double_0 != null) {
            return this.double_0[networkWrapper.getIndex()];
        }
        return Double.NaN;
    }

    public int getIndex() {
        return this.index;
    }

    void method_11() {
        if (this.forwardLinks.size() > 0) {
            this.linkRunSpaceList = new LinkRunSpace[this.networkElementRunSpace.getNetworkWrappersCount()];
            this.networkLengths = new double[this.networkElementRunSpace.getNetworkWrappersCount()];
            if (NetworkWrapper.bool_0) {
                boolean[] array = new boolean[this.networkElementRunSpace.getNetworkWrappersCount()];
                NetworkWrapper.RecordOperator recordOperator = new NetworkWrapper.RecordOperator();

                for (int i = 0; i < this.networkElementRunSpace.getNetworkWrappersCount(); i++) {
                    this.networkLengths[i] = Double.POSITIVE_INFINITY;
                    recordOperator.record(i, Double.POSITIVE_INFINITY);
                }
                this.networkLengths[this.getIndex()] = 0.0;
                recordOperator.update(this.getIndex(), 0.0);
                while (recordOperator.Count() > 0) {
                    int index = recordOperator.removeFirstIndex();
                    if (Double.isInfinite(this.networkLengths[index])) {
                        break;
                    }
                    array[index] = true;
                    NetworkWrapper networkWrapper = this.networkElementRunSpace.getNetworkWrappers().get(index);
                    for (LinkRunSpace linkRunSpace : networkWrapper.getForwardLinks()) {
                        if (NetworkWrapper.smethod_0(networkWrapper, linkRunSpace)) {
                            double length = this.networkLengths[index] + linkRunSpace.Length();
                            NetworkWrapper wrapper;
                            if (linkRunSpace.getBeginNodeRunSpace() == networkWrapper.getNodeRunSpace()) {
                                wrapper =
                                        linkRunSpace.getEndNodeRunSpace().getNetworkWrapper(this.getNetworkElementRunSpace());
                            } else {
                                wrapper =
                                        linkRunSpace.getBeginNodeRunSpace().getNetworkWrapper(this.getNetworkElementRunSpace());
                            }
                            int i = wrapper.getIndex();
                            if (length < this.networkLengths[i] && !array[i]) {
                                this.networkLengths[i] = length;
                                recordOperator.update(i, length);
                                LinkRunSpace runSpace = this.linkRunSpaceList[index];
                                this.linkRunSpaceList[i] = ((runSpace == null) ? linkRunSpace : runSpace);
                            }
                        }
                    }
                }
                for (int j = 0; j < this.networkLengths.length; j++) {
                    if (Double.isInfinite(this.networkLengths[j])) {
                        this.networkLengths[j] = Double.NaN;
                    }
                }
            }
        } else {

            boolean[] array2 = new boolean[this.networkElementRunSpace.getNetworkWrappersCount()];
            for (int k = 0; k < this.networkElementRunSpace.getNetworkWrappersCount(); k++) {
                this.networkLengths[k] = Double.POSITIVE_INFINITY;
            }
            this.networkLengths[this.getIndex()] = 0.0;
            int num4 = 0;
            while (num4++ < this.networkElementRunSpace.getNetworkWrappersCount()) {
                NetworkWrapper networkWrapper = null;
                double num5 = Double.POSITIVE_INFINITY;
                for (NetworkWrapper wrapper : this.getNetworkElementRunSpace().getNetworkWrappers()) {
                    if (!array2[wrapper.getIndex()] && this.networkLengths[wrapper.getIndex()] < num5) {
                        networkWrapper = wrapper;
                        num5 = this.networkLengths[wrapper.getIndex()];
                        if (num5 == 0.0) {
                            break;
                        }
                    }
                }
                if (networkWrapper == null) {
                    break;
                }
                array2[networkWrapper.getIndex()] = true;
                for (LinkRunSpace linkRunSpace : networkWrapper.getForwardLinks()) {
                    if (NetworkWrapper.smethod_0(networkWrapper, linkRunSpace)) {
                        num5 = this.networkLengths[networkWrapper.getIndex()] + linkRunSpace.Length();
                        NetworkWrapper wrapper;
                        if (linkRunSpace.getBeginNodeRunSpace() == networkWrapper.getNodeRunSpace()) {
                            wrapper =
                                    linkRunSpace.getEndNodeRunSpace().getNetworkWrapper(this.getNetworkElementRunSpace());
                        } else {
                            wrapper =
                                    linkRunSpace.getBeginNodeRunSpace().getNetworkWrapper(this.getNetworkElementRunSpace());
                        }
                        if (num5 < this.networkLengths[wrapper.getIndex()]) {
                            this.networkLengths[wrapper.getIndex()] = num5;
                            LinkRunSpace runSpace = this.linkRunSpaceList[networkWrapper.getIndex()];
                            this.linkRunSpaceList[wrapper.getIndex()] = ((runSpace == null) ? linkRunSpace : runSpace);
                        }
                    }
                }
                for (int l = 0; l < this.networkLengths.length; l++) {
                    if (Double.isInfinite(this.networkLengths[l])) {
                        this.networkLengths[l] = Double.NaN;
                    }
                }
            }

        }
    }

    private static boolean smethod_0(NetworkWrapper networkWrapper, LinkRunSpace linkRunSpace) {

        if (networkWrapper.getNetworkElementRunSpace().getEnum48() == NetworkElementRunSpace.Enum48.Zero) {
            return true;
        }
        if (networkWrapper.getNetworkElementRunSpace().getEnum48() == NetworkElementRunSpace.Enum48.One) {
            if (linkRunSpace.getBeginNodeRunSpace() == networkWrapper.getNodeRunSpace()) {
                if (linkRunSpace.DesiredDirection().method_20() == 1 || linkRunSpace.DesiredDirection().method_20() == 0) {
                    return true;
                }
            } else if (linkRunSpace.getEndNodeRunSpace() == networkWrapper.getNodeRunSpace() && (linkRunSpace.DesiredDirection().method_20() == 2 || linkRunSpace.DesiredDirection().method_20() == 0)) {
                return true;
            }
        }
        return false;

    }

    public List<LinkRunSpace> getForwardLinks() {
        return this.forwardLinks;
    }


    public void setIndex(int size) {
        this.index = index;
    }

    public void addLinkRunSpace(LinkRunSpace linkRunSpace) {
        if (linkRunSpace.getLinkType() == LinkType.Bidirectional) {
            this.forwardLinks.add(linkRunSpace);
            this.backLinks.add(linkRunSpace);
            return;
        }
        if (linkRunSpace.getBeginNodeRunSpace() == this.getNodeRunSpace()) {
            this.forwardLinks.add(linkRunSpace);
            return;
        }
        this.backLinks.add(linkRunSpace);
    }

    public NodeRunSpace getNodeRunSpace() {
        return this.nodeRunSpace;
    }

    public LinkRunSpace link(NetworkWrapper networkWrapper) {
        if (networkWrapper == this || networkWrapper == null || networkWrapper.getNetworkElementRunSpace() != this.networkElementRunSpace) {
            return null;
        }
        if (this.double_1 < this.networkElementRunSpace.getDouble_0()) {
            this.method_11();
        }
        if (this.linkRunSpaceList != null) {
            return this.linkRunSpaceList[networkWrapper.getIndex()];
        }
        return null;
    }

    public void removeLink(LinkRunSpace linkRunSpace) {
        if (linkRunSpace.getLinkType() == LinkType.Bidirectional) {
            this.forwardLinks.remove(linkRunSpace);
            this.backLinks.remove(linkRunSpace);
            return;
        }
        if (linkRunSpace.getBeginNodeRunSpace() == this.getNodeRunSpace()) {
            this.forwardLinks.remove(linkRunSpace);
            return;
        }
        this.backLinks.remove(linkRunSpace);
    }

    public int forwardLinksCount() {
        return this.forwardLinks.size();
    }

    public int backLinksCount() {
        return this.backLinks.size();
    }

    public void reset() {
        this.networkElementRunSpace = null;
        this.nodeRunSpace.removeNetworkWrapper(this);
        this.nodeRunSpace = null;
        this.linkRunSpaceList = null;
        this.forwardLinks = null;
        this.backLinks = null;
    }

    public LinkRunSpace getBackLinkByIndex(int i) {
        return this.backLinks.get(index);
    }

    public Iterable<? extends LinkRunSpace> getBackLinksIterator() {
        return this.backLinks;
    }

    public LinkRunSpace getForwardLinkByIndex(int index) {
        return this.forwardLinks.get(index);
    }

    private class RecordOperator {
        private final Map<Double, List<Integer>> oldList = new TreeMap<>();
        private final Map<Integer, Double> records = new HashMap<>();

        public void record(int index, double value) {
            if (this.records.containsKey(index)) {
                throw new IllegalArgumentException("Duplicate Key");
            }
            this.recordByValue(value, index);
        }

        private void recordByValue(double value, int index) {
            this.records.put(index, value);
            List<Integer> list = this.oldList.computeIfAbsent(value, k -> new ArrayList<>());
            list.add(index);
        }

        @SuppressWarnings("unchecked")
        public int removeFirstIndex() {
            Map.Entry<Double, List<Integer>> keyValuePair =
                    (Map.Entry<Double, List<Integer>>) this.oldList.entrySet().toArray()[0];
            int index = keyValuePair.getValue().get(0);
            keyValuePair.getValue().remove(0);
            if (keyValuePair.getValue().isEmpty()) {
                this.oldList.remove(keyValuePair.getKey());
            }
            this.records.remove(index);
            return index;
        }

        public void update(int index, double value) {
            double key = this.records.get(index);
            List<Integer> list = this.oldList.get(key);
            if (!list.remove((Integer) index)) {
                throw new IllegalArgumentException("Not in old list");
            }
            if (list.isEmpty()) {
                this.oldList.remove(key);
            }
            this.recordByValue(value, index);
        }

        public int Count() {
            return this.records.size();
        }


    }
}
