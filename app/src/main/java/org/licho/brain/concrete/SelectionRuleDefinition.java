package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.rule.ISelectionRuleDefinition;
import org.licho.brain.api.extensions.ISelectionRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class SelectionRuleDefinition extends GridObjectDefinition {
    private static Map<Guid, SelectionRuleDefinition> idRuleMap;

    private ISelectionRuleDefinition selectionRuleDefinition;

    private static ISelectionRuleDefinitions selectionRuleDefinitions;

    private Guid guid;

    public SelectionRuleDefinition(ISelectionRuleDefinition selectionRuleDefinition) {
        super(selectionRuleDefinition.Name());
        this.selectionRuleDefinition = selectionRuleDefinition;
        this.guid = this.selectionRuleDefinition.UniqueID();
        this.selectionRuleDefinition.DefineSchema(super.getPropertyDefinitions());
        PropertyDefinitions definitions = super.getPropertyDefinitions();
        definitions.process((StringPropertyDefinition stringPropertyDefinition) -> {
            ExpressionPropertyDefinition expressionPropertyDefinition =
                    (ExpressionPropertyDefinition) stringPropertyDefinition;
            if (expressionPropertyDefinition != null) {
                expressionPropertyDefinition.SupportForeignStates(true);
            }
        });
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new SelectionRuleInfo(this, name);
    }

    @Override
    public boolean IsJustAFactory() {
        return true;
    }

    @Override
    public void NotifyPropertyInserted(PropertyDefinitions propertyDefinitions,
                                       StringPropertyDefinition stringPropertyDefinition, int param2,
                                       Map<Properties, IntelligentObjectProperty> originalPropertyInstances) {
        super.NotifyPropertyInserted(propertyDefinitions, stringPropertyDefinition, param2, originalPropertyInstances);
        ExpressionPropertyDefinition expressionPropertyDefinition;
        if (stringPropertyDefinition instanceof ExpressionPropertyDefinition) {
            expressionPropertyDefinition = (ExpressionPropertyDefinition) stringPropertyDefinition;
            expressionPropertyDefinition.SupportForeignStates(true);
        }
    }

    protected static List<String> getSelectionRuleDefinitionNames() {
         List<String> names = new ArrayList<>();
        if (SelectionRuleDefinition.getSelectionRuleDefinitions() != null) {
            for (int i = 0; i < SelectionRuleDefinition.getSelectionRuleDefinitions().Count(); i++) {
                names.add(SelectionRuleDefinition.getSelectionRuleDefinitions().get(i).Name());
            }
        }
        return names;
    }

    public ISelectionRule CreateRule(Properties properties) {
        if (this.selectionRuleDefinition == null) {
            return null;
        }
        return this.selectionRuleDefinition.CreateRule(properties);
    }

    SelectionRuleDefinition getSelectionRuleDefinition() {
        return SelectionRuleDefinition.getIdRuleMap().get(this.guid);
    }

    protected Guid getGuid() {
        return this.guid;
    }

    private static Map<Guid, SelectionRuleDefinition> getIdRuleMap() {
        if (SelectionRuleDefinition.idRuleMap == null) {
            SelectionRuleDefinition.idRuleMap = new HashMap<>();
            if (SelectionRuleDefinition.getSelectionRuleDefinitions() != null) {
                for (int i = 0; i < SelectionRuleDefinition.getSelectionRuleDefinitions().Count(); i++) {
                    ISelectionRuleDefinition selectionRuleDefinition =
                            SelectionRuleDefinition.getSelectionRuleDefinitions().get(i);
                    SelectionRuleDefinition.idRuleMap.put(selectionRuleDefinition.UniqueID(),
                            new SelectionRuleDefinition(selectionRuleDefinition));
                }
            }
        }
        return SelectionRuleDefinition.idRuleMap;
    }

    public static void haveRule() {
        SelectionRuleDefinition.idRuleMap = null;
    }


    public static ISelectionRuleDefinitions getSelectionRuleDefinitions() {
        return SelectionRuleDefinition.selectionRuleDefinitions;
    }

    public static void setSelectionRuleDefinitions(ISelectionRuleDefinitions selectionRuleDefinitions) {
        SelectionRuleDefinition.selectionRuleDefinitions = selectionRuleDefinitions;
    }

}