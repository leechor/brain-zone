package org.licho.brain.concrete.entity;

import org.licho.brain.concrete.AgentElementRunSpace;
import org.licho.brain.concrete.ContainerElementRunSpace;
import org.licho.brain.concrete.Event;
import org.licho.brain.concrete.IntelligentObject;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.LinkRunSpace;
import org.licho.brain.concrete.MayApplication;
import org.licho.brain.concrete.NetworkElementRunSpace;
import org.licho.brain.concrete.NodeRunSpace;
import org.licho.brain.concrete.ProductionLot;
import org.licho.brain.concrete.Queue;
import org.licho.brain.concrete.RegulatorOperator;
import org.licho.brain.concrete.RideStation;
import org.licho.brain.concrete.SequenceStep;
import org.licho.brain.concrete.TransporterRunSpace;
import org.licho.brain.concrete.enu.EntityLocationTransitionState;
import org.licho.brain.concrete.enu.EntityLocationType;
import org.licho.brain.concrete.enu.TrafficDirection;
import org.licho.brain.concrete.enu.TravelerEdgeType;
import org.licho.brain.enu.IEnumMask;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class EntityRunSpace extends AgentElementRunSpace {
    private NodeRunSpace currentNode;
    private NodeRunSpace previousNode;
    private NodeRunSpace destinationNode;
    private Queue<EntityRunSpaceTravelerEdge> leadingEdge;
    private Queue<EntityRunSpaceTravelerEdge> tailingTravelEdge;
    public List<OccupiedLink> occupiedLinks = new ArrayList<>(2);
    public List<OccupiedNode> occupiedNodes = new ArrayList<>(2);

    private LinkRunSpace currentConnectorLink;
    private LinkRunSpace lastLinkUsed;
    private RideStation currentStation;
    private NetworkElementRunSpace currentNetwork;
    private EntityLocationType entityLocationType;
    public IntelligentObjectRunSpace CurrentParentObjectRunSpace;
    private EntityRunSpace.Enum35 enum35;
    private TransporterRunSpace reservedTransporter;
    private ProductionLot productionLot;
    private int sequenceCurrentJobStepIndex;
    private SequenceStep sequenceStep;
    private ThroughRegulator throughRegulator;
    private EntityLocationTransitionState entityLocationTransitionState;
    private RegulatorOperator regulatorOperator;
    private ContainerElementRunSpace containerElementRunSpace;

    public EntityRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject, MayApplication application
            , IntelligentObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
    }

    public NodeRunSpace CurrentNode() {
        return this.currentNode;
    }

    public void CurrentNode(NodeRunSpace value) {
        if (value != this.currentNode) {
            if (this.currentNode != null) {
                this.previousNode = this.currentNode;
            }
            this.currentNode = value;
        }
    }

    public NodeRunSpace getPreviousNode() {
        return this.previousNode;
    }

    public NodeRunSpace DestinationNode() {
        return this.destinationNode;
    }

    public void DestinationNode(NodeRunSpace value) {

    }

    public Queue<EntityRunSpaceTravelerEdge> getLeadingEdge() {
        return this.leadingEdge;
    }

    public LinkRunSpace currentLink() {
        if (this.occupiedLinks.size() == 0) {
            return null;
        }
        return this.occupiedLinks.get(this.occupiedLinks.size() - 1).LinkRunSpace;
    }

    public Queue<EntityRunSpaceTravelerEdge> getTailingTravelEdge() {
        return this.tailingTravelEdge;
    }

    public LinkRunSpace trailingLink() {
        if (this.occupiedLinks.isEmpty()) {
            return null;
        }
        return this.occupiedLinks.get(0).LinkRunSpace;
    }

    public LinkRunSpace getCurrentConnectorLink() {
        return this.currentConnectorLink;
    }

    public LinkRunSpace getLastLinkUsed() {
        return this.lastLinkUsed;
    }

    public EntityRunSpace getNextEntityAheadOnLink() {
        if (this.getLeadingEdge() != null) {
            for (Queue<EntityRunSpaceTravelerEdge> queue = this.getLeadingEdge().getAheadQueue(); queue != null; queue = queue.getAheadQueue()) {
                if (queue.current().getTravelerEdgeType() == TravelerEdgeType.TrailingEdge) {
                    return queue.current().getEntityRunSpace();
                }
            }
        }
        return null;
    }


    public EntityRunSpace getNextEntityBehindOnLink() {
        if (this.getTailingTravelEdge() != null) {
            for (Queue<EntityRunSpaceTravelerEdge> queue = this.getTailingTravelEdge().getBehindQueue(); queue != null; queue = queue.getBehindQueue()) {
                if (queue.current().getTravelerEdgeType() == TravelerEdgeType.LeadingEdge) {
                    return queue.current().getEntityRunSpace();
                }
            }
        }
        return null;
    }

    public RideStation CurrentStation() {
        return this.currentStation;
    }

    public NetworkElementRunSpace CurrentNetwork() {
        return this.currentNetwork;
    }

    public boolean IsParked() {
        return this.entityLocationType == EntityLocationType.Station && this.CurrentStation().isParked();
    }

    public EntityLocationType getEntityLocationType() {
        return this.entityLocationType;
    }


    public boolean IsWaitingRide() {
        return this.isEntryStatus(512);
    }

    private boolean isEntryStatus(int enum35) {
        return (this.enum35.mask() & enum35) == enum35;
    }

    public TransporterRunSpace ReservedTransporter() {
        return this.reservedTransporter;
    }

    public boolean IsRiding() {
        return this.isEntryStatus(32);
    }

    public ProductionLot getProductionLot() {
        return this.productionLot;
    }

    public int getSequenceCurrentJobStepIndex() {
        return this.sequenceCurrentJobStepIndex;
    }

    public SequenceStep getSequenceStep() {
        if (this.sequenceStep == null) {
            return new SequenceStep(this);
        }
        return this.sequenceStep;
    }

    public NodeRunSpace getAssignedSequence(int i) {
        return null;
    }

    public double getSequenceDueDate() {
        double result;
        //todo
        return 0;
    }

    @Override
    public double GetNetworkDistanceToNode(NodeRunSpace nodeRunSpace) {
        if (nodeRunSpace == null) {
            return Double.NaN;
        }
        if (this.CurrentNetwork() != null) {
            NodeRunSpace node = null;
            if (this.CurrentNode() != null) {
                node = this.CurrentNode();
            } else if (this.getEntityLocationType() == EntityLocationType.Link || this.getEntityLocationType() == EntityLocationType.EndOfLink) {
                node = ((this.currentLink().getLinkRunSpaceWrapper().getTrafficDirection() == TrafficDirection.Forward) ? this.currentLink().getEndNodeRunSpace() : this.currentLink().getBeginNodeRunSpace());
            }
            double num = this.CurrentNetwork().getNetworkDistanceToNode(node, nodeRunSpace);
            if (this.getEntityLocationType() == EntityLocationType.Link) {
                num += this.currentLink().Length() - super.Movement().method_26();
            }
            return num;
        }
        return super.getDistanceTo(nodeRunSpace);
    }


    public double getNetworkDistanceToNextEntityAheadOnLink() {
        EntityRunSpace entityAheadOnLink = this.getNextEntityAheadOnLink();
        if (entityAheadOnLink != null) {
            double num =
                    entityAheadOnLink.Movement().method_26() - super.Movement().method_26() - entityAheadOnLink.Length() + entityAheadOnLink.getAllLinksLength();
            if (num <= 1E-10) {
                num = 0.0;
            }
            return num;
        }
        return Double.NaN;
    }

    private double getAllLinksLength() {
        // TODO: 2021/12/9
        return 0;
    }

    public boolean isEntryStatus() {
        return this.isEntryStatus(1024);
    }

    public EntityLocationTransitionState getEntityLocationTransitionState() {
        if (this.isThroughRegulator()) {
            return EntityLocationTransitionState.FlowingThroughRegulator;
        }
        return this.entityLocationTransitionState;
    }

    private boolean isThroughRegulator() {
        return this.throughRegulator != null;
    }

    public boolean haveRegulatorOperator() {
        return this.regulatorOperator != null;
    }

    public Event getEngagedEvent() {
        return this.events[1];
    }

    public Event getTransferringEvent() {
        return this.events[2];
    }


    public Event getTransferredEvent() {
        return this.events[3];
    }

    @Override
    public String getName() {
        String type = "[" + this.getEntityLocationType().toString() + "]";
        switch (this.getEntityLocationType()) {
            case FreeSpace:
                return type + " " + this.CurrentFacilityRunSpace().HierarchicalDisplayName();
            case Node:
                return type + " " + this.CurrentNode().HierarchicalDisplayName();
            case Station:
                return type + " " + this.CurrentStation().HierarchicalDisplayName();
            case Link:
            case EndOfLink:
                return type + " " + this.currentLink().HierarchicalDisplayName();
            case BatchMembersQueue:
                return type + " " + this.CurrentParentObjectRunSpace.Name() + ".BatchMembers";
            case Container:
                return type + " " + this.getContainerElementRunSpace().HierarchicalDisplayName();
            default:
                return type;
        }
    }

    public ContainerElementRunSpace getContainerElementRunSpace() {
        return this.containerElementRunSpace;
    }


    public enum Enum35 implements IEnumMask {
        IsRiding, // 5
        WaitingRide, // = 1 << 8
        EntryStatus // 1 << 10
        ;

        Enum35() {
            this.mask = (1 << ordinal());
        }

        private final int mask;

        @Override
        public int mask() {
            return this.mask;
        }
    }

    public class OccupiedLink {
        public final LinkRunSpace LinkRunSpace;
        public final double double_0;

        public OccupiedLink(LinkRunSpace linkRunSpace, double param1) {
            this.LinkRunSpace = linkRunSpace;
            this.double_0 = param1;
        }
    }

    public class OccupiedNode {
        public final NodeRunSpace NodeRunSpace;
        public final double double_0;
        public final boolean bool_0;

        public OccupiedNode(NodeRunSpace nodeRunSpace, double param1, boolean param2) {
            this.NodeRunSpace = nodeRunSpace;
            this.double_0 = param1;
            this.bool_0 = param2;
        }
    }


}
