package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.enu.UnitType;
import com.zdpx.cctpp.simioEnums.ElementScope;

/**
 *
 */
public class CostCenterDefinition extends AbsDefinition {
    public static final CostCenterDefinition Instance = new CostCenterDefinition();

    public CostCenterDefinition() {
        super("CostCenter");

        super.Description(EngineResources.ElementDescription_CostCenter);
        super.getPropertyDefinitions().getPropertyDefinitionList().clear();
        PropertyDefinitionFacade financials = new PropertyDefinitionFacade();
        financials.Name(EngineResources.CategoryName_Financials);
        financials.Description(EngineResources.CategoryName_Financials);
        financials.InitiallyExpanded(true);

        PropertyDefinitionFacade resultsClassification = new PropertyDefinitionFacade();
        resultsClassification.Name(EngineResources.CategoryName_ResultsClassification);
        resultsClassification.Description(EngineResources.CategoryName_ResultsClassification);
        resultsClassification.InitiallyExpanded(false);

        super.getPropertyDefinitions().getPropertyDefinitionList().add(financials);
        super.getPropertyDefinitions().getPropertyDefinitionList().add(resultsClassification);
        ElementPropertyDefinition parentCostCenter = new ElementPropertyDefinition("ParentCostCenter",
                CostCenter.class);
        parentCostCenter.DisplayName(EngineResources.CostCenter_ParentCostCenter_DisplayName);
        parentCostCenter.Description(EngineResources.CostCenter_ParentCostCenter_Description);
        parentCostCenter.DefaultString("");
        parentCostCenter.CategoryName(EngineResources.CategoryName_Financials);
        parentCostCenter.RequiredValue(false);
        ExpressionPropertyDefinition initialCost = new ExpressionPropertyDefinition("InitialCost");
        initialCost.DisplayName(EngineResources.CostCenter_InitialCost_DisplayName);
        initialCost.Description(EngineResources.CostCenter_InitialCost_Description);
        initialCost.DefaultString("0.0");
        initialCost.CategoryName(EngineResources.CategoryName_Financials);
        initialCost.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Currency);
        ExpressionPropertyDefinition initialCostRate = new ExpressionPropertyDefinition("InitialCostRate");
        initialCostRate.DisplayName(EngineResources.CostCenter_InitialCostRate_DisplayName);
        initialCostRate.Description(EngineResources.CostCenter_InitialCostRate_Description);
        initialCostRate.DefaultString("0.0");
        initialCostRate.CategoryName(EngineResources.CategoryName_Financials);
        initialCostRate.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.CurrencyPerTimeUnit);
        StringPropertyDefinition dataSource = new StringPropertyDefinition("DataSource");
        dataSource.DisplayName(EngineResources.CostCenter_DataSource_DisplayName);
        dataSource.Description(EngineResources.CostCenter_DataSource_Description);
        dataSource.CategoryName(EngineResources.CategoryName_ResultsClassification);
        dataSource.RequiredValue(false);
        dataSource.DefaultString("");
        StringPropertyDefinition category = new StringPropertyDefinition("Category");
        category.DisplayName(EngineResources.CostCenter_Category_DisplayName);
        category.Description(EngineResources.CostCenter_Category_Description);
        category.CategoryName(EngineResources.CategoryName_ResultsClassification);
        category.RequiredValue(false);
        category.DefaultString("");
        StringPropertyDefinition dataItem = new StringPropertyDefinition("DataItem");
        dataItem.DisplayName(EngineResources.CostCenter_DataItem_DisplayName);
        dataItem.Description(EngineResources.CostCenter_DataItem_Description);
        dataItem.CategoryName(EngineResources.CategoryName_ResultsClassification);
        dataItem.RequiredValue(false);
        dataItem.DefaultString("");
        super.getPropertyDefinitions().add(parentCostCenter);
        super.getPropertyDefinitions().add(initialCost);
        super.getPropertyDefinitions().add(initialCostRate);
        super.getPropertyDefinitions().add(dataSource);
        super.getPropertyDefinitions().add(category);
        super.getPropertyDefinitions().add(dataItem);
        CostStatePropertyObject firstOrderStateProperty = new CostStatePropertyObject("Cost", false, false);
        firstOrderStateProperty.Description(EngineResources.CostCenter_CostState_Description);
        firstOrderStateProperty.UnitType(UnitType.Currency);
        firstOrderStateProperty.updateParameter(0, EngineResources.CostCenter_CostRateParameter_Description);
        super.getStateDefinitions().addStateProperty(firstOrderStateProperty);
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new CostCenter(this, name, ElementScope.Private);
    }

    public static CostCenter create(String name) {
        return (CostCenter) CostCenterDefinition.Instance.CreateInstance(name);
    }

    @Override
    public Class<?> ElementType() {
        return CostCenter.class;
    }

    @Override
    public Class<?> RunSpaceType() {
        return CostCenterElementRunSpace.class;
    }

    @Override
    public AbsDefinition DefaultDefinition() {
        return CostCenterDefinition.Instance;
    }
}
