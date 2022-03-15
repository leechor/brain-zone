package org.licho.brain.concrete;

/**
 * @author licho
 * @since 2021/10/18
 */
public class Vehicle {
    public static void init(InternalReference internalReference, ActiveModel activeModel,
                            TransporterDefinition transporterDefinition) {
        TransporterDefinition definition = new TransporterDefinition("Vehicle", activeModel, transporterDefinition);
        internalReference.updateSameIntelligentObjectDefinition(definition);
        Vehicle.init(definition);
    }

    public static void init(TransporterDefinition transporterDefinition) {

    }
}
