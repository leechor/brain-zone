package org.licho.brain.concrete;

import org.licho.brain.concrete.annotation.BaseElementFunction;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.enu.BooleanType;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.TokenProcessingAction;
import org.licho.brain.event.EventArgs;
import org.licho.brain.brainEnums.ElementScope;

/**
 *
 */
public class ProcessDefinition extends AbsDefinition {

    public static ProcessDefinition processDefinition = new ProcessDefinition();

    public ProcessDefinition() {
        super("Process");
        super.Description(EngineResources.ElementDescription_Process);
        TokenClassPropertyDefinition tokenClassProperty = new TokenClassPropertyDefinition("TokenClassName");
        tokenClassProperty.DisplayName(EngineResources.Process_TokenClassName_DisplayName);
        tokenClassProperty.Description(EngineResources.Process_TokenClassName_Description);
        tokenClassProperty.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        tokenClassProperty.DefaultString("Token");
        tokenClassProperty.ComplexityLevel(ProductComplexityLevel.Advanced);
        EventPropertyDefinition stringProperty = new EventPropertyDefinition("TriggeringEventName");
        stringProperty.DisplayName(EngineResources.Process_TriggeringEventName_DisplayName);
        stringProperty.Description(EngineResources.Process_TriggeringEventName_Description);
        stringProperty.CategoryName(EngineResources.CategoryName_BasicLogic);
        stringProperty.RequiredValue(false);
        stringProperty.method_39(true);
        stringProperty.DefaultString("");
        EnumPropertyDefinition tokenActionOnAssociatedObjectDestroyed = new EnumPropertyDefinition(
                "TokenActionOnAssociatedObjectDestroyed", TokenProcessingAction.class);
        tokenActionOnAssociatedObjectDestroyed.DisplayName(EngineResources.Process_TokenActionOnAssociatedObjectDestroyed_DisplayName);
        tokenActionOnAssociatedObjectDestroyed.Description(EngineResources.Process_TokenActionOnAssociatedObjectDestroyed_Description);
        tokenActionOnAssociatedObjectDestroyed.CategoryName(EngineResources.CategoryName_AdvancedOptionsTokenActions);
        tokenActionOnAssociatedObjectDestroyed.DefaultString(TokenProcessingAction.ContinueProcess.toString());
        tokenActionOnAssociatedObjectDestroyed.ComplexityLevel(ProductComplexityLevel.Advanced);
        EnumPropertyDefinition tokenActionOnAssociatedObjectTransferRequested = new EnumPropertyDefinition(
                "TokenActionOnAssociatedObjectTransferRequested", TokenProcessingAction.class);
        tokenActionOnAssociatedObjectTransferRequested.DisplayName(EngineResources.Process_TokenActionOnAssociatedObjectTransferRequested_DisplayName);
        tokenActionOnAssociatedObjectTransferRequested.Description(EngineResources.Process_TokenActionOnAssociatedObjectTransferRequested_Description);
        tokenActionOnAssociatedObjectTransferRequested.CategoryName(EngineResources.CategoryName_AdvancedOptionsTokenActions);
        tokenActionOnAssociatedObjectTransferRequested.DefaultString(TokenProcessingAction.ContinueProcess.toString());
        tokenActionOnAssociatedObjectTransferRequested.ComplexityLevel(ProductComplexityLevel.Advanced);
        EnumPropertyDefinition initiallyEnabled = new EnumPropertyDefinition("InitiallyEnabled", BooleanType.class);
        initiallyEnabled.DisplayName(EngineResources.Process_InitiallyEnabled_DisplayName);
        initiallyEnabled.Description(EngineResources.Process_InitiallyEnabled_Description);
        initiallyEnabled.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        initiallyEnabled.DefaultString("True");
        initiallyEnabled.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().add(tokenClassProperty);
        super.getPropertyDefinitions().add(stringProperty);
        super.getPropertyDefinitions().add(tokenActionOnAssociatedObjectDestroyed);
        super.getPropertyDefinitions().add(tokenActionOnAssociatedObjectTransferRequested);
        super.getPropertyDefinitions().add(initiallyEnabled);
        BaseStatePropertyObject traceFlag = new BaseStatePropertyObject("TraceFlag", false, true,
                NumericDataType.Boolean);
        traceFlag.value = 1.0;
        BaseStatePropertyObject enabled = new BaseStatePropertyObject("Enabled", false, false, NumericDataType.Boolean);
        enabled.Description(EngineResources.Process_Enabled_Description);
        BaseStatePropertyObject returnValue = new BaseStatePropertyObject("ReturnValue", false, false);
        returnValue.Description(EngineResources.Process_ReturnValue_Description);
        returnValue.value = 1.0;
        super.getStateDefinitions().addStateProperty(traceFlag);
        super.getStateDefinitions().addStateProperty(enabled);
        super.getStateDefinitions().addStateProperty(returnValue);
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new ProcessProperty(name, ElementScope.Private);
    }

    public static ProcessProperty CreateInstanceDefault(String name) {
        return (ProcessProperty) ProcessDefinition.processDefinition.CreateInstance(name);
    }

    @Override
    public Class<?> ElementType() {
        return ProcessProperty.class;
    }

    @Override
    public Class<?> RunSpaceType() {
        return ProcessPropertyElementRunSpace.class;
    }

    @Override
    public AbsDefinition DefaultDefinition() {
        return ProcessDefinition.processDefinition;
    }

    @Override
    public StringPropertyDefinition GetPropertyForLoad(String name, IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 15 && StringHelper.equalsLocal(name,
                "TokenAssociatedObjectDestroyedAction")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(
                    "TokenActionOnAssociatedObjectDestroyed");
        }
        if (intelligentObjectXml.FileVersion() < 77) {
            if (StringHelper.equalsLocal(name, "OnAssociatedObjectDestroyed")) {
                return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(
                        "TokenActionOnAssociatedObjectDestroyed");
            }
            if (StringHelper.equalsLocal(name, "OnAssociatedObjectTransferRequested")) {
                return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(
                        "TokenActionOnAssociatedObjectTransferRequested");
            }
        }
        if (intelligentObjectXml.FileVersion() < 79 && StringHelper.equalsLocal(name, "TriggeringEvent")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("TriggeringEventName");
        }
        return super.GetPropertyForLoad(name, intelligentObjectXml);
    }

    @BaseElementFunction("NumberTokensInProcess")
    public static double smethod_7(AbsBaseRunSpace param0, IRunSpace param1) {
        ProcessPropertyElementRunSpace processPropertyElementRunSpace = (ProcessPropertyElementRunSpace) param0;
        return (double) processPropertyElementRunSpace.getNumberTokensInProcess();
    }

    public class EventArgs35 extends EventArgs {
        public EventArgs35(String param0) {
            this.string_0 = param0;
        }

        public String method_0() {
            return this.string_0;
        }

        private String string_0;
    }
}
