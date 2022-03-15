package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.enu.StatisticsDataSourceInfo;
import org.licho.brain.utils.simu.IStatisticsDataSource;

import java.util.Objects;

/**
 *
 */
public class Identifier implements Comparable<Identifier> {
    public final String scenarioName;
    public final String objectType;
    public final String contextObjectName;
    public final String dataSource;
    public final String statisticCategory;
    public final String statisticType;
    public final String dataItem;
    private int id;

    public Identifier(String scenarioName, String dataItem, String statisticType, String statisticCategory,
                      IStatisticsDataSource statisticsDataSource, String contextObjectName) {
        this.scenarioName = scenarioName;
        this.dataItem = dataItem;
        this.statisticType = statisticType;
        this.statisticCategory = statisticCategory;
        StatisticsDataSourceInfo statisticsDataSourceInfo =
                statisticsDataSource.GetStatisticsDataSourceInfo(contextObjectName);
        this.dataSource = statisticsDataSourceInfo.name;
        this.contextObjectName = statisticsDataSourceInfo.contextObjectName;
        this.objectType = statisticsDataSourceInfo.propertyDefinitionName;
        this.id = Identifier.generateId(scenarioName, dataItem, statisticType, statisticCategory, this.dataSource,
                this.contextObjectName, this.objectType);
    }

    public Identifier(String scenarioName, String dataItem, String statisticType, String statisticCategory,
                      String dataSource, String contextObjectName, String objectType) {
        this.scenarioName = scenarioName;
        this.dataItem = dataItem;
        this.statisticType = statisticType;
        this.statisticCategory = statisticCategory;
        this.dataSource = dataSource;
        this.contextObjectName = contextObjectName;
        this.objectType = objectType;
        this.id = Identifier.generateId(scenarioName, dataItem, statisticType, statisticCategory, dataSource,
                contextObjectName, objectType);
    }

    public static Identifier readXml(XmlReader xmlReader) {
        String scenario = xmlReader.GetAttribute("Scenario");
        String objectType = xmlReader.GetAttribute("ObjectType");
        String objectName = xmlReader.GetAttribute("ObjectName");
        String dataSource = xmlReader.GetAttribute("DataSource");
        String category = xmlReader.GetAttribute("Category");
        String type = xmlReader.GetAttribute("Type");
        String dataItem = xmlReader.GetAttribute("DataItem");
        return new Identifier(scenario, dataItem, type, category, dataSource, objectName, objectType);
    }

    private static int generateId(String scenarioName, String dataItem, String statisticType,
                                  String statisticCategory, String name, String contextObjectName,
                                  String propertyDefinitionName) {
        int num = 17;
        if (scenarioName != null) {
            num = num * 23 + scenarioName.hashCode();
        }
        if (propertyDefinitionName != null) {
            num = num * 23 + propertyDefinitionName.hashCode();
        }
        if (contextObjectName != null) {
            num = num * 23 + contextObjectName.hashCode();
        }
        if (name != null) {
            num = num * 23 + name.hashCode();
        }
        if (statisticCategory != null) {
            num = num * 23 + statisticCategory.hashCode();
        }
        if (statisticType != null) {
            num = num * 23 + statisticType.hashCode();
        }
        if (dataItem != null) {
            num = num * 23 + dataItem.hashCode();
        }
        return num;
    }


    @Override
    public int compareTo(Identifier identifier) {
        int num = this.scenarioName.compareTo(identifier.scenarioName);
        if (num == 0) {
            num = this.objectType.compareTo(identifier.objectType);
        }
        if (num == 0) {
            num = this.contextObjectName.compareTo(identifier.contextObjectName);
        }
        if (num == 0) {
            num = this.dataSource.compareTo(identifier.dataSource);
        }
        if (num == 0) {
            num = this.statisticCategory.compareTo(identifier.statisticCategory);
        }
        if (num == 0) {
            num = this.dataItem.compareTo(identifier.dataItem);
        }
        if (num == 0) {
            num = this.statisticType.compareTo(identifier.statisticType);
        }
        return num;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return id == that.id && Objects.equals(scenarioName, that.scenarioName) && Objects.equals(objectType,
                that.objectType) && Objects.equals(contextObjectName, that.contextObjectName) && Objects.equals(dataSource, that.dataSource) && Objects.equals(statisticCategory, that.statisticCategory) && Objects.equals(statisticType, that.statisticType) && Objects.equals(dataItem, that.dataItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scenarioName, objectType, contextObjectName, dataSource, statisticCategory, statisticType
                , dataItem, id);
    }
}
