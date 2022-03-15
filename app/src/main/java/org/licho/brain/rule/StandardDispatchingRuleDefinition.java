package org.licho.brain.rule;

import org.licho.brain.concrete.Guid;
import org.licho.brain.concrete.IPropertyDefinitions;
import org.licho.brain.resource.Image;
import org.licho.brain.simuApi.IPropertyReaders;
import org.licho.brain.simuApi.extensions.ISelectionRule;

import java.util.UUID;

/**
 * Dynamic selection rules allow various places to select from a set of things based on some dynamic criteria.
 * <p>
 * by implementing the interfaces ISelectionRuleDefinition and ISelectionRule. For more information about creating
 * your own Dynamic Selection Rules, see Custom Simio Extensions and the file C:\Program Files\Simio\Simio API
 * Reference Guide.chm.
 * <p>
 * These rules are used in Resource allocation selection (used within many of the Standard Library objects, such as
 * Server, Resource, Combiner, Separator, Worker, Vehicle) and Workstation (Deprecated). Also used within the Routing
 * Group route request selection, TransferNode route request selection and Station entry selection.
 * <p>
 * <b>Note</b>: Most of the time you will want to use the keyword Candidate when entering an expression.
 *
 */

public class StandardDispatchingRuleDefinition implements ISelectionRuleDefinition {
    @Override
    public String Name() {
        return null;
    }

    @Override
    public String Description() {
        return null;
    }

    @Override
    public Image Icon() {
        return null;
    }

    @Override
    public Guid UniqueID() {
        return null;
    }

    @Override
    public void DefineSchema(IPropertyDefinitions propertyDefinitions) {

    }

    @Override
    public ISelectionRule CreateRule(IPropertyReaders properties) {
        return null;
    }

    /**
     * Specifies a priority dispatching rule.
     */
    public enum DispatchingRule {
        /**
         * The entity ranked nearest the front of the queue is selected.
         */
        FIRST_IN_QUEUE,
        /**
         * The entity with the largest priority state value is selected.
         */
        LARGEST_PRIORITY_VALUE,
        /**
         * The entity with the smallest priority state value is selected.
         */
        SMALLEST_PRIORITY_VALUE,
        /**
         * The entity with the earliest due date is selected.
         */
        EARLIEST_DUE_DATE,
        /**
         * The entity with the smallest critical ratio is selected. Critical ratio is the amount of time remaining to
         * complete the entity’s assigned sequence in order to meet its due date divided by the total operation time
         * remaining to complete the entity’s sequence. Assumes that each candidate entity has been assigned a
         * destination sequence.
         */
        CRITICAL_RATIO,
        /**
         * The entity with the least setup time is selected.
         */
        LEAST_SETUP_TIME,
        /**
         * The entity with the longest operation time is selected.
         */
        LONGEST_PROCESSING_TIME,
        /**
         * The entity with the shortest operation time is selected.
         */
        SHORTEST_PROCESSING_TIME,
        /**
         * The entity with the least slack time is selected. Slack time is the amount of time remaining until the
         * entity’s due date minus the total operation time remaining to complete the entity’s sequence. Assumes that
         * each candidate entity has been assigned a destination sequence.
         */
        LEAST_SLACK_TIME,
        /**
         * The entity with the least average slack time per its remaining operations is selected. Assumes that each
         * candidate entity has been assigned a destination sequence.
         */
        LEAST_SLACK_TIME_PER_OPERATION,
        /**
         * The entity with the least total operation time remaining to complete its assigned sequence is selected.
         * Assumes that each candidate entity has been assigned a destination sequence.
         */
        LEAST_WORK_REMAINING,
        /**
         * The entity with the fewest number of operations remaining to complete its assigned sequence is selected.
         * Assumes that each candidate entity has been assigned a destination sequence.
         */
        FEWEST_OPERATIONS_REMAINING,
        /**
         * The entity that has been waiting the longest time in the queue is selected.
         */
        LONGEST_TIME_WAITING,
        /**
         * The entity that has been waiting the least time in the queue is selected.
         */
        SHORTEST_TIME_WAITING,
        /**
         * The entity with the largest value of the specified expression is selected.
         */
        LARGEST_ATTRIBUTE_VALUE,
        /**
         * The entity with the smallest value of the specified expression is selected.
         */
        SMALLEST_ATTRIBUTE_VALUE,
        /**
         * The entity that has a campaign value equal to or next largest compared to the value of the last processed
         * entity is selected. If none of the waiting entities have the same or larger value, then the rule starts a
         * new campaign by selecting the entity with the smallest value.
         */
        CAMPAIGN_SEQUENCE_UP,
        /**
         * The entity that has a campaign value equal to or next smallest compared to the value of the last processed
         * entity is selected. If none of the waiting entities have the same or smaller value, then the rule starts a
         * new campaign by selecting the entity with the largest value.
         */
        CAMPAIGN_SEQUENCE_DOWN,
        /**
         * Alternates back and forth between a campaign sequence up and a campaign sequence down. If the campaign is
         * increasing, it will continue to increase until no entities remain with the same or larger campaign value.
         * When this occurs, the rule then switches to a decreasing campaign and begins selecting entities that have
         * the same or smaller campaign value. When all such entities are exhausted, the rule returns to an
         * increasing campaign strategy and the cycle repeats.
         */
        CAMPAIGN_SEQUENCE_CYCLE
    }

    /**
     * The name of the rule. This may contain any characters and is used to populate the drop-down list of selection
     * rules.
     */
    private String name = "Standard Dispatching Rule";

    /**
     * A short description of what the rule does.
     */
    private String description;

    /**
     * An icon to display for the rule in the UI.
     */
    private Image icon;

    /**
     * A unique static GUID generated for the rule.
     */
    private UUID uniqueId;


}
