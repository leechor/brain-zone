package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;

/**
 *
 */
@PropertyDefinitionName("TableAndColumnProperty")
public class TableAndColumnPropertyDefinition extends StringPropertyDefinition {
    public TableAndColumnPropertyDefinition(String name) {
        super(name);
        super.DefaultString(super.NullNullString());
        this.dataFormat = DataFormat.List;
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new TableAndColumnPropertyRow(this, properties);
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.TableArrivalProperty_ClassName;
    }

    @Override
    public String GetGridObjectDescription() {
        return EngineResources.TableArrivalProperty_ClassDescription;
    }

    @Override
    IValueProvider ValueProvider() {
		// TODO: 2021/12/9
		return null;
    }
}
