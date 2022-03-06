package com.zdpx.cctpp.concrete;

/**
 * @author licho
 * @since 2021/10/18
 */
public class Worker {
    public static void init(InternalReference internalReference, ActiveModel activeModel,
							TransporterDefinition transporterDefinition) {
        TransporterDefinition definition = new TransporterDefinition("Worker", activeModel, transporterDefinition);
        internalReference.updateSameIntelligentObjectDefinition(definition);
        Worker.init(definition);
    }

    public static void init(TransporterDefinition transporterDefinition) {

    }

}
