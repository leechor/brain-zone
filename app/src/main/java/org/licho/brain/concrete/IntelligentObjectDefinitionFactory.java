package org.licho.brain.concrete;

import org.licho.brain.concrete.node.TransferNode;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.enu.StandardType;
import org.licho.brain.resource.Image;

import java.util.EnumSet;

/**
 *
 */
public class IntelligentObjectDefinitionFactory {
    private static IntelligentObjectDefinition create(SimioProjectDefinition simioProjectDefinition,
                                                      ObjectClass objectClass, String guidS, String name) {
        return IntelligentObjectDefinitionFactory.create(simioProjectDefinition, objectClass, guidS, name, null);
    }

    private static IntelligentObjectDefinition create(SimioProjectDefinition simioProjectDefinition,
                                                      ObjectClass objectClass, String guidS, String name, Image icon) {
        ActiveModel activeModel = simioProjectDefinition.createActiveModel(objectClass,
                IntelligentObjectDefinitionFactory::smethod_3);
        activeModel.Name(name);
        activeModel.Icon(icon);
        activeModel.getDefinition().setGuid(new Guid(guidS));
        activeModel.Runnable(false);
        activeModel.getDefinition().enterDefinitionCount();
        return activeModel.getDefinition();
    }

    private static void smethod_3(ActiveModel activeModel) {
        if (activeModel.getDefinition() instanceof FixedDefinition &&
                !(activeModel.getDefinition() instanceof NodeDefinition)) {
//            activeModel.getIntelligentObjectDefinition().getInternalReference()
//            .updateSameIntelligentObjectDefinition(activeModel.projectDefinition[0].getIntelligentObjectDefinition());
//            activeModel.getIntelligentObjectDefinition().getInternalReference()
//            .updateSameIntelligentObjectDefinition(activeModel.projectDefinition[1].getIntelligentObjectDefinition());
        }
    }

    public static void initStandardLibrary(SimioProjectDefinition simioProjectDefinition,
                                           EnumSet<StandardType> standardType) {
        simioProjectDefinition.Icon(Resources.StandardLibraryIcon);
        BasicNode.init((NodeDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                ObjectClass.Node, "{585953CA-C744-444b-92D9-8AF032F3E9A1}", "BasicNode", Resources.basicnode));
        TransferNode.init((NodeDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                ObjectClass.Node, "{0D40FBF0-8206-443f-B771-65F85633A2F6}", "TransferNode", Resources.transfernode));
        if (standardType.contains(StandardType.Source)) {
            Source.init((FixedDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                    ObjectClass.Fixed, "{1D0CCF22-61F9-4fc0-9B3C-01D17E71402D}", "Source", Resources.source));
        }
        if (standardType.contains(StandardType.Sink)) {
            Sink.init((FixedDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                    ObjectClass.Fixed, "{5F44138F-A5F7-40d1-AB9A-EEA7C5F0254E}", "Sink", Resources.sink));
        }
        if (standardType.contains(StandardType.Server)) {
            Server.init((FixedDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                    ObjectClass.Fixed, "{1BC342DC-9D96-49f2-AE4A-AF8F05416564}", "Server", Resources.server));
        }
        if (standardType.contains(StandardType.Workstation)) {
            Workstation.init((FixedDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                    ObjectClass.Fixed, "{35BA214C-D634-4a12-BA95-572F131B3BAB}", "Workstation", Resources.workstation));
        }
        if (standardType.contains(StandardType.Combiner)) {
            Combiner.init((FixedDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                    ObjectClass.Fixed, "{6DF5A963-685F-49fb-901C-3683E5D8C754}", "Combiner", Resources.combiner));
        }
        if (standardType.contains(StandardType.Separator)) {
            Separator.init((FixedDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                    ObjectClass.Fixed, "{91316EC9-4805-41e3-A4FF-DBEFFE5BC1EE}", "Separator", Resources.seperator));
        }
        if (standardType.contains(StandardType.Resource)) {
            Resource.init((FixedDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                    ObjectClass.Fixed, "{4AD0D0CE-2B18-4968-BC54-562ED46AC8A5}", "Resource", Resources.resource));
        }
        if (standardType.contains(StandardType.Vehicle)) {
            Vehicle.init((TransporterDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                    ObjectClass.Transporter, "{840E1952-0A07-4fce-AE96-9D4DDAA9E1EF}", "Vehicle", Resources.vehicle));
        }
        if (standardType.contains(StandardType.Worker)) {
            Worker.init((TransporterDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                    ObjectClass.Transporter, "{5F89278E-BFE6-4867-97C5-3C24C83C5DA7}", "Worker", Resources.worker));
        }
        simioProjectDefinition.updateModel(simioProjectDefinition.get(0));
        simioProjectDefinition.updateModel(simioProjectDefinition.get(0));
        Connector.init((LinkDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                ObjectClass.Link, "{078E9330-0F90-4770-A474-3CB06FAE1DB5}", "Connector"));
        PathDefinition.init((LinkDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                ObjectClass.Link, "{93D39DA5-5BA1-4ae1-A526-15BDB27D48E4}", "Path", Resources.path));
        TimePath.init((LinkDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                ObjectClass.Link, "{FF39356C-A4F9-412c-BB3D-14BAF4DC5D11}", "TimePath", Resources.timepath));
        Conveyor.init((LinkDefinition) IntelligentObjectDefinitionFactory.create(simioProjectDefinition,
                ObjectClass.Link, "{205487E1-5E9E-4b70-B80B-A6C333F7F872}", "Conveyor", Resources.conveyor));
        for (ActiveModel activeModel : simioProjectDefinition.ActiveModels) {
            activeModel.getDefinition().exitDefinitionCount();
        }
    }
}
