package org.licho.brain.element;

import org.licho.brain.concrete.AbsDefinition;
import org.licho.brain.concrete.AbsIntelligentPropertyObject;
import org.licho.brain.concrete.ActiveModel;
import org.licho.brain.concrete.BooleanPropertyRow;
import org.licho.brain.concrete.Event;
import org.licho.brain.concrete.IntelligentObjectDefinition;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.Item;
import org.licho.brain.concrete.ItemEditPolicy;
import org.licho.brain.concrete.Link;
import org.licho.brain.concrete.LinkRunSpace;
import org.licho.brain.concrete.MayApplication;
import org.licho.brain.concrete.NetworkDefinition;
import org.licho.brain.concrete.NetworkElementRunSpace;
import org.licho.brain.concrete.Request;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.node.Node;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.simioEnums.ElementScope;
import org.licho.brain.simuApi.ILinkNetworks;
import org.licho.brain.simuApi.INetworkElementObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * A Network is a list of links to which an entity may be assigned to follow via the shortest path to its destination.
 * <br>
 * A link may be in multiple networks. The Network keeps a master list of network nodes for each node that is connected
 * in the network. A node is considered connected if it has at least one incoming or outgoing link that is a member of
 * the network. Each interaction has a list of network nodes - one for each network that the node is a member (by being
 * the starting or ending node of a member link), Hence each network node is both on the master list of network nodes
 * owned by the network, and the local list of network nodes owned by the node. Each network node keeps a list of
 * outbound member links, and a count of inbound member links. When both counts are zero, the node is removed from both
 * the master list and the node list. Each network node has a vector of the shortest path links from this node to all
 * nodes
 * in the master network node list. Each vector is indexed in the same order as the master network node list owned by
 * the network. The shortest path link is found by locating the network node for the destination transfer station in
 * this master list, and then using the index of this node to index into the shortest path vector, Hence the runtime
 * computation is O(n), where n is the number of network nodes. This can be improved by implementing a compare function
 * to sort the list, and then using a binary search. The shortest path vector is only computed for network nodes with
 * 2 or more outbound links. The ComputeShortestNetwork method is called to compute the shortest path vector for each
 * of these nodes.
 */
@Setter
@Getter
public class Network extends AbsIntelligentPropertyObject implements INetworkElementObject {

    /**
     * The method used by travelers on this network to turn around at nodes in order to move in the opposite
     * direction on bidirectional links. If the method is 'Default', then the Network Turnaround Method specified for
     * the entity type will be assumed.
     */
    private EnumPropertyRow turnaroundMethod;

    /**
     * indicates whether updates of the network's shortest path calculations will automatically occur whenever the
     * DesiredDirection state of a member link changes, with the calculations based on the current DesiredDirection
     * states of all link members.
     */
    private BooleanPropertyRow autoUpdateShortestPaths;

    private EventPropertyRow updateShortestPathsEvent;

    /**
     * An occurrence of this event will update the network's shortest path calculations to be based on the current
     * DesiredDirection states of all member links.
     */
    private Event updateShortestPathEvent;

    /**
     * Specifies if statistics are to be automatically reported for this element
     */
    private boolean reportStatistics;

    /**
     * A queue of visit requests associated with this network.
     */
    private Queue<Request> visitRequestQueue;
    private List<Link> linkList = new ArrayList<>();

    public Network(NetworkDefinition networkDefinition, String name, ElementScope scope) {
        super(networkDefinition, name, scope);
    }

    public BooleanPropertyRow getAutoUpdateShortestPaths() {
        return this.autoUpdateShortestPaths;
    }

    public EventPropertyRow getUpdateShortestPathsEvent() {
        return this.updateShortestPathsEvent;
    }

    /**
     * Returns the shortest path distance between two nodes using this network. If the fromNode == toNode, the
     * function returns 0.0.
     *
     * @param fromNode
     * @param toNode
     * @return
     */
    private double distance(Node fromNode, Node toNode) {
        throw new NullPointerException("distance");
    }

    /**
     * Returns a reference to the next link on the shortest path between two nodes using this network. If the
     * fromNode == toNode, the function returns Nothing.
     *
     * @param fromNode
     * @param toNode
     * @return
     */
    private Link nextLink(Node fromNode, Node toNode) {
        throw new NullPointerException("nextLink");
    }

    /**
     * Returns a reference to the next node on the shortest path between two nodes using this network. If the
     * destination node is returned, then the two nodes are directly connected by a link. If the fromNode == toNode,
     * the function returns the node.
     *
     * @param fromNode
     * @param toNode
     * @return
     */
    private Node nextNode(Node fromNode, Node toNode) {
        throw new NullPointerException("nextNode");
    }

    /**
     * Returns 'True' if a followable travel path exists between two nodes using this network. If the fromNode ==
     * toNode, the function returns 'True'.
     *
     * @param fromNode
     * @param toNode
     * @return
     */
    private boolean pathExists(Node fromNode, Node toNode) {
        throw new NullPointerException("path Exists");
    }

    @Override
    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this.turnaroundMethod = (EnumPropertyRow) this.properties.get(index++);
        this.autoUpdateShortestPaths = (BooleanPropertyRow) this.properties.get(index++);
        this.updateShortestPathsEvent = (EventPropertyRow) this.properties.get(index++);
        return index;
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return new NetworkElementRunSpace(sourceIntelligentObjectRunSpace, application, this);
    }

    @Override
    protected AbsDefinition DefaultDefinition() {
        return null;
    }

    @Override
    public int IconIndex() {
        return 0;
    }

    @Override
    public String GetGridObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public String ProjectItemName() {
        return null;
    }

    @Override
    public String ItemName() {
        return null;
    }

    @Override
    public String ItemTypeName() {
        return null;
    }

    @Override
    public String GetNameForKey(Object param0) {
        return null;
    }

    @Override
    public String GetDisplayNameForKey(Object param0) {
        return null;
    }

    @Override
    public String SearchableValueFor(Object param0) {
        return null;
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }

    @Override
    public ILinkNetworks getLinks() {
        return null;
    }

    public void addLink(Link link) {
        if (link.networkProperties.contains(this)) {
            return;
        }
        this.linkList.add(link);
        link.networkProperties.add(this);
        if (super.Parent() != null) {
            super.Parent().NotifyAddLink(this, link);
        }
        IntelligentObjectDefinition intelligentObjectDefinition = super.Parent();
        if (intelligentObjectDefinition.activeModel != null && intelligentObjectDefinition.activeModel.canModify()) {
            NetworkElementRunSpace parent = (NetworkElementRunSpace)
                    super.GetRunSpaceRecursionOutOfParent(intelligentObjectDefinition.activeModel.MayApplication.getFixedRunSpace());
            if (parent != null) {
                parent.addLinkRunSpace((LinkRunSpace) link.GetRunSpaceRecursionOutOfParent(intelligentObjectDefinition.activeModel.MayApplication.getFixedRunSpace()));
            }
        }
    }

    public void removeLink(Link link) {
        if (!link.networkProperties.contains(this)) {
            return;
        }
        this.linkList.remove(link);
        link.networkProperties.remove(this);

        if (super.Parent() != null) {
            super.Parent().triggerPropertyLinkChanged(this, link);
        }

        IntelligentObjectDefinition parent = super.Parent();
        if (parent.activeModel != null && parent.activeModel.canModify()) {
            var p = super.GetRunSpaceRecursionOutOfParent(parent.activeModel.MayApplication.getFixedRunSpace());
            if (p instanceof NetworkElementRunSpace) {
                NetworkElementRunSpace networkElementRunSpace = (NetworkElementRunSpace) p;
                networkElementRunSpace.removeLink((LinkRunSpace)(link.GetRunSpaceRecursionOutOfParent(parent.activeModel.MayApplication.getFixedRunSpace())));
            }
        }
    }

    public class Links implements Item<Long> {

    }

}
