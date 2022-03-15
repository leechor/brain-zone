package org.licho.brain.step;

import org.licho.brain.concrete.entity.Entity;
import org.licho.brain.concrete.OutboundLinkPreference;
import org.licho.brain.enu.LinkSelectionRule;
import org.licho.brain.enu.TokenWaitAction;
import org.licho.brain.brainEnums.TransferFromType;
import org.licho.brain.brainEnums.TransferToType;
import lombok.Getter;
import lombok.Setter;

/**
 * The Transfer step may be used to transfer an entity object between objects and between different types of
 * locations, such as free space and objects.
 * <br>
 * If transferring an entity to a node, the Transfer step automatically sets the entity's destination
 * to that node if the entity does not already have a destination set.
 * <br>
 * a user can use a Transfer step to transfer an object between object locations without the entity having to use the
 * external nodes to enter and exit objects. So, for example, you can immediately transfer an entity from inside object
 * A to a node outside object B using the Transfer step.
 * <br>
 * Whenever attempting a transfer from 'CurrentNode' to 'OutboundLink', if the entity's Current TravelMode state is
 * 'Free Space Only' or the entity has no network assigned then the transfer attempt will fail and the token exits the
 * 'Failed' exit point of the Transfer step. Whenever attempting a transfer from 'CurrentNode' to 'FreeSpace',
 * if the entity has an assigned network and its CurrentTravelMode is 'Network Only' then the transfer attempt will
 * fail and the token exits the 'Failed' exit point of the Transfer step. Whenever attempting a transfer from
 * 'CurrentNode' to 'OutboundLink', if there is only 1 possible outbound link from the node that is a member of
 * the entity's currently assigned network, and the Outbound Link Rule is 'Shortest Path' but there is no followable
 * network path to the entity's next destination, then:
 * <ul>
 * <li>
 * If the entity's CurrentTravelMode state is 'Network Only', then the Transfer step will go ahead and still transfer
 * the entity onto the sole outbound link. This behaviour allows for the possibility that a followable network path
 * emerges after the entity moves through the next downstream object.
 * </li>
 * <li>
 * If the entity's CurrentTravelMode state is 'Network If Possible', then the transfer attempt will fail and the token
 * exits the 'Failed' exit point of the Transfer step. This behavior allows the entity to then do an immediate transfer
 * from 'CurrentNode' to 'FreeSpace' in order to begin traveling to its next destination in free space.
 * </li>
 * </ul>
 * <b>Node</b>: It is only safe at this point to Transfer an entity into or out of an object through the
 * external nodes since most objects make the assumption that an entity is going to enter or exit through nodes.
 * For example, external logic might immediately Transfer an entity out of a Server object and transfer it over to
 * some other location outside the logic. However, if that happens, the tokens running inside the Server will not
 * realize that the entity was transferred out, and it will assume the entity is still inside the Server. It will
 * continue trying to process the entity and an error will occur.
 */
@Setter
@Getter
public class Transfer extends Step {
    private TransferFromType from;
    private TransferToType to;

    /**
     * the name of the station into which the entity is to be transferred, available when {@code to} 'Station'.
     */
    private String stationName;

    /**
     * The object whose facility free space the entity is to be transferred into. If unspecified, then defaults to the
     * most immediate parent object of the Transfer step that supports a facility view. This property is visible when
     * {@code to} is 'FreeSpace'.
     */
    private String facility;

    /**
     * the node from which the entity is to be transferred. this property is visible when {@code to} 'Node'.
     */
    private String nodeName;

    /**
     * The external node of the parent object from which the entity is to be transferred. This property is visible
     * when {@code to} is 'ParentExternalNode'.
     */
    private String externalNodeName;

    /**
     * The preference used by the entity to select an outbound link from its current node to its next
     * destination.
     * <br>
     * If the preference is 'Default', then the outbound link preference settings for the entity's current
     * node will be used.
     * <br>
     * If the preference is 'Any', then the specified <i>Outbound Link Rule</i> will be strictly adhered to
     * and blocked outbound links may cause the entity to wait.
     * <br>
     * If the preference is 'Available', then the specified <i>Outbound Link Rule</i> will be automatically
     * adjusted to minimize waiting time by first applying the rule only to outbound links that are
     * immediately available for entry. If no outbound links are immediately available, then the preference
     * will revert to 'Any' selection.
     * <br>
     * If the preference is 'Specific', then the entity will be transferred to the specified
     * <i>Outbound Link Name.</i>
     */
    private OutboundLinkPreference outboundLinkPreference;

    /**
     * The rule used by the entity to select an outbound link from its current node to its next destination.
     * Note that if the rule is set to 'Shortest Path', and the entity does not have an assigned
     * destination, then the rule will revert to 'By Link Weight' selection.
     */
    private LinkSelectionRule outBoundLinkRule;

    /**
     * the name of the specific outbound link that will be selected by the entity.
     */
    private String outboundLinkName;

    /**
     * The name of the container into which the entity is to be transferred. This property is visible when
     * {@code to} is 'Container'.
     */
    private String containerName;

    /**
     * The regulator used if transferring the entity's mass and volume as a continuous flow into the new
     * location. This property is visible when {@code to} is 'AssociatedObject', 'OutboundLink' or
     * 'Container'.
     */
    private String flowRegulatorName;

    /**
     * The specific entity object that is to be transferred.
     */
    private Entity entity;

    private TokenWaitAction tokenWaitAction;

    //...
}
