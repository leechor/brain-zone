package org.licho.brain.element;

import org.licho.brain.concrete.AbsDefinition;
import org.licho.brain.concrete.AbsIntelligentPropertyObject;
import org.licho.brain.concrete.ActiveModel;
import org.licho.brain.concrete.ElementPropertyRow;
import org.licho.brain.concrete.ExpressionPropertyRow;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.ItemEditPolicy;
import org.licho.brain.concrete.MayApplication;
import org.licho.brain.concrete.RideStation;
import org.licho.brain.concrete.SelectionRulePropertyRow;
import org.licho.brain.concrete.StationDefinition;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.simioEnums.ElementScope;
import org.licho.brain.step.EndTransfer;
import org.licho.brain.step.Transfer;


/**
 * The <b>Station</b> element may be used to define a capacity constrained location for holding entities representing
 * discrete items. The {@link Transfer} step is used to transfer entities into and out of a station, the
 * {@link EndTransfer} step should be used to end the entity transfer into a Station.
 * <br>
 * If the <i>Balk Entering Decision Type</i> is ‘Blocked’, then an entity’s transfer attempt into the station will be
 * allowed to proceed as normal. This includes being redirected to another station or to a parent external node if
 * the station’s capacity is zero and a redirect location has been specified. However, a late priority event will
 * also be scheduled on the current events calendar for the entity. When that event is processed, the following
 * conditions for the entity will be checked:
 * <ul>
 *     <li>Was the entity’s Transferring event fired? </li>
 *     <li>
 *         Is there a queue item associated with the entity in a station’s entry queue, link’s entry queue, node’s
 *         entry queue, or a routing group’s route request queue?
 *     </li>
 * </ul>
 * <p>
 * If the answer to both of the above questions is true, then the entity is determined to have immediately attempted
 * a discrete transfer that was blocked, and it will balk. The queue item associated with the entity’s blocked
 * transfer attempt will be automatically cancelled (reneged) and the station’s <i>On Balked Entering Process</i> then
 * executed.
 * <hr>
 * Station elements are built into some Standard Library objects. Some examples are: Server.InputBuffer,
 * Server.Processing, Server.OutputBuffer, Combiner.ParentInputBuffer, Separator.MemberOutputBuffer, Vehicle
 * .RideStation, etc.
 * <br>
 * The contents of a station queue can be accessed by utilizing the 'Contents' state of the station. For example,
 * 'Server1.InputBuffer.Contents' displays the contents of the queue at the Server1 InputBuffer station.
 * <br>
 * The Station element's Contents queue also has several read-only parameters (not for all queues, only Station
 * content queues). This includes Volume, Volume.Rate, Weight and Weight.Rate. These parameters represent the
 * cumulative Weight and Volume of the contents of the queue. Since they are parameters, they can also be used in a
 * Monitor, so you can see when the volume of objects in a given station exceeds a specified value. As an example,
 * 'Server1.OutputBuffer.Contents.Weight' would provide the cumulative weight of all the entities waiting in the
 * output buffer of Server1.
 */

public class Station extends AbsIntelligentPropertyObject {


    private ExpressionPropertyRow initialCapacity;
    private ExpressionPropertyRow costPerUse;
    private ExpressionPropertyRow holdingCostRate;
    private EnumPropertyRow entryRankingRule;
    private ExpressionPropertyRow entryRankingExpression;
    private SelectionRulePropertyRow dynamicSelectionRule;
    private EnumPropertyRow contentsRankingRule;
    private ExpressionPropertyRow contentsRankingExpression;
    private ElementPropertyRow redirectStation;
    private ElementPropertyRow parentCostCenter;

    public Station(StationDefinition stationDefinition, String name, ElementScope scope) {
        super(stationDefinition, name, scope);
    }

    @Override
    protected AbsDefinition DefaultDefinition() {
        return null;
    }

    @Override
    public int IconIndex() {
        return 10;
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

    public ExpressionPropertyRow getInitialCapacity() {
        return this.initialCapacity;
    }

    public EnumPropertyRow getEntryRankingRule() {
        return this.entryRankingRule;
    }

    public ExpressionPropertyRow getEntryRankingExpression() {
        return this.entryRankingExpression;
    }

    public SelectionRulePropertyRow getDynamicSelectionRule() {
        return this.dynamicSelectionRule;
    }

    public EnumPropertyRow getContentsRankingRule() {
        return this.contentsRankingRule;
    }

    public ExpressionPropertyRow getContentsRankingExpression() {
        return this.contentsRankingExpression;
    }

    public ElementPropertyRow getRedirectStation() {
        return this.redirectStation;
    }

    public ElementPropertyRow getParentCostCenter() {
        return this.parentCostCenter;
    }

    public ExpressionPropertyRow getCostPerUse() {
        return this.costPerUse;
    }

    public ExpressionPropertyRow getHoldingCostRate() {
        return this.holdingCostRate;
    }

    @Override
    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this.initialCapacity = (ExpressionPropertyRow) this.properties.get(index++);
        this.entryRankingRule = (EnumPropertyRow) this.properties.get(index++);
        this.entryRankingExpression = (ExpressionPropertyRow) this.properties.get(index++);
        this.dynamicSelectionRule = (SelectionRulePropertyRow) this.properties.get(index++);
        this.contentsRankingRule = (EnumPropertyRow) this.properties.get(index++);
        this.contentsRankingExpression = (ExpressionPropertyRow) this.properties.get(index++);
        this.redirectStation = (ElementPropertyRow) this.properties.get(index++);
        this.parentCostCenter = (ElementPropertyRow) this.properties.get(index++);
        this.costPerUse = (ExpressionPropertyRow) this.properties.get(index++);
        this.holdingCostRate = (ExpressionPropertyRow) this.properties.get(index++);
        return index;
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return new RideStation(sourceIntelligentObjectRunSpace, application, this);
    }

}
