package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.simioEnums.ValidObjectType;

@PropertyDefinitionName("TransporterProperty")
public class TransporterPropertyDefinition extends ObjectInstancePropertyDefinition {
    public TransporterPropertyDefinition(String name) {
        super(name, ValidObjectType.Transporter);
    }
}
