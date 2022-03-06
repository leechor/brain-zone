package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.utils.simu.IValues;

/**
 *
 */
public class DayPatternRefs extends BindingList<DayPatternRef> implements IValues {
    private IntelligentObjectDefinition intelligentObjectFacility;

    DayPatternRefs(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.intelligentObjectFacility = intelligentObjectDefinition;
        super.AllowEdit(true);
        super.AllowNew(true);
        super.AllowRemove(true);
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                           IntelligentObjectDefinition intelligentObjectDefinition) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "DayPatternRefs", null, (XmlReader body) ->
        {
            int[] num = new int[]{-1};
            DayPatternRef dayPatternRef = DayPatternRef.readXml(xmlReader, intelligentObjectXml,
					intelligentObjectDefinition, num);
            if (dayPatternRef != null) {
                while (this.values.size() <= num[0]) {
                    this.add(new DayPatternRef(intelligentObjectDefinition, null));
                }
                this.Insert(num[0], dayPatternRef);
                return true;
            }
            return false;
        });
    }
}
