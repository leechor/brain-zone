package org.licho.brain.concrete;

import org.licho.brain.simioEnums.ValidObjectType;

@PropertyDefinitionName("TransporterProperty")
public class TransporterPropertyDefinition extends ObjectInstancePropertyDefinition {
    public TransporterPropertyDefinition(String name) {
        super(name, ValidObjectType.Transporter);
    }
}
