package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.annotations.DisplayName;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.utils.simu.BoundsType;
import org.licho.brain.utils.simu.StatisticType;

import java.util.Objects;

/**
 *
 */
public class TableTargetPerformanceSummaryResult {
    private Table table;
    private Target target;
    private BoundsType boundsType;
    private StatisticType statistic;
    private double totalValue;
    static String XML_ELEMENT_NAME = "TargetPerformanceSummaryResult";

    public TableTargetPerformanceSummaryResult(Table table, Target target, BoundsType boundsType,
											   StatisticType statistic,
                                               double value) {
        this.table = table;
        this.target = target;
        this.boundsType = boundsType;
        this.statistic = statistic;
        this.totalValue = value;
    }

    @DisplayName("Table Name")
    public String TableName() {
        return this.table.Name();
    }

    @DisplayName("Target Name")
    public String TargetName() {
        return this.target.Name();
    }

    @DisplayName("Data Item")
    public String DataItem() {
        switch (this.boundsType) {
            case Within:
                return String.format(EngineResources.ResultDataItem_NumberTargetResultStatus,
                        this.target.getWithinBoundsStatus().replace(" ", ""));
            case Above:
                return String.format(EngineResources.ResultDataItem_NumberTargetResultStatus,
                        this.target.getAboveBoundsStatus().replace(" ", ""));
            case Below:
                return String.format(EngineResources.ResultDataItem_NumberTargetResultStatus,
                        this.target.getBelowBoundsStatus().replace(" ", ""));
            case NoValue:
                return String.format(EngineResources.ResultDataItem_NumberTargetResultStatus,
                        this.target.getNoValueStatus().replace(" ", ""));
            default:
                return "";
        }
    }

    @DisplayName("Statistic")
    public String Statistic() {
        switch (this.statistic) {
            case Percent:
                return StatisticTypeName.Percent;
            case Total:
                return StatisticTypeName.Total;
            default:
                return "";
        }
    }

    @DisplayName("Total")
    public double Total() {
        return this.totalValue;

    }

    void WriteToXml(XmlWriter writer) {
//			writer.WriteStartElement(TableTargetPerformanceSummaryResult.XML_ELEMENT_NAME);
//			SomeXmlOperator.WriteAttributeStringWhenNotEmpty(writer, "TableName", this.TableName);
//			SomeXmlOperator.WriteAttributeStringWhenNotEmpty(writer, "TargetName", this.TargetName);
//			SomeXmlOperator.smethod_24<BoundsType>(writer, "BoundsType", this.boundsType);
//			SomeXmlOperator.smethod_24<StatisticType>(writer, "Statistic", this.statistic);
//			SomeXmlOperator.smethod_3(writer, "Total", this.Total, double.MinValue);
//			writer.WriteEndElement();
    }

    static TableTargetPerformanceSummaryResult CreateFromXml(XmlReader reader, IntelligentObjectXml readContext,
                                                             ActiveModel model) {
        if (Objects.equals(reader.Name(), TableTargetPerformanceSummaryResult.XML_ELEMENT_NAME)) {
            String[] tableName = new String[1];
            String[] targetName = new String[1];
            BoundsType[] bounds = new BoundsType[1];
            StatisticType[] statistic = new StatisticType[1];
            double[] total = new double[1];

            boolean result = SomeXmlOperator.xmlReaderElementOperator(reader,
                    TableTargetPerformanceSummaryResult.XML_ELEMENT_NAME, (XmlReader attrReader) ->
                    {
                        SomeXmlOperator.readXmlAttributeString(attrReader, "TableName", (String t) ->
                                tableName[0] = t);
                        SomeXmlOperator.readXmlAttributeString(attrReader, "TargetName", (String t) ->
                                targetName[0] = t);
                        SomeXmlOperator.readEnumAttribute(attrReader, "BoundsType",
                                (BoundsType t) -> bounds[0] = t, BoundsType.class);
                        SomeXmlOperator.readEnumAttribute(attrReader, "Statistic",
                                (StatisticType t) -> statistic[0] = t, StatisticType.class);
                        SomeXmlOperator.readXmlDoubleAttribute(attrReader, "Total", t -> total[0] = t);
                    }, null);

            if ( result && !Strings.isNullOrEmpty(tableName[0])) {

                Table table = model.getIntelligentObjectDefinition().Tables().getTableByName(tableName[0]);
                if (table != null && !Strings.isNullOrEmpty(targetName[0])) {
                    Target targetByName = table.Schema().Targets().getTargetByName(targetName[0]);
                    if (targetByName != null && bounds[0] != null && statistic[0] != null && !Double.isNaN(total[0])) {
                        return new TableTargetPerformanceSummaryResult(table, targetByName, bounds[0], statistic[0],
                                total[0]);
                    }
                }
            }
        }
        return null;
    }
}
