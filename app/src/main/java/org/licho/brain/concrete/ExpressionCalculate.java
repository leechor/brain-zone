package org.licho.brain.concrete;

import org.licho.brain.utils.simu.IIntelligentObjectDefinitionOperator;

/**
 *
 */
public class ExpressionCalculate {
    	public static IExpression createExpression(String expression,
                                                   IntelligentObjectDefinition intelligentObjectDefinition,
                                                   boolean param2, boolean param3, boolean param4, StringBuilder string_0)
	{
		return ExpressionCalculate.smethod_3(expression, intelligentObjectDefinition, null, null, param2, param3, param4, string_0);
	}

	// Token: 0x06003674 RID: 13940 RVA: 0x0002D937 File Offset: 0x0002BB37
	public static IExpression smethod_1(String expression, IntelligentObjectDefinition intelligentObjectDefinition,
                                        IntelligentObjectDefinition param2, boolean param3, boolean param4,
                                        boolean param5,
                                        StringBuilder string_0)
	{
		return ExpressionCalculate.smethod_3(expression, intelligentObjectDefinition, null, param2, param3, param4, param5, string_0);
	}

	// Token: 0x06003675 RID: 13941 RVA: 0x0002D94E File Offset: 0x0002BB4E
	public static IExpression smethod_2(String expression, IntelligentObjectDefinition intelligentObjectDefinition,
                                        IIntelligentObjectDefinitionOperator param2, boolean param3, boolean param4,
                                        boolean param5, StringBuilder string_0)
	{
		return ExpressionCalculate.smethod_3(expression, intelligentObjectDefinition, param2, null, param3, param4, param5, string_0);
	}

	// Token: 0x06003676 RID: 13942 RVA: 0x0015330C File Offset: 0x0015150C
	public static IExpression smethod_3(String expression, IntelligentObjectDefinition intelligentObjectDefinition,
                                        IIntelligentObjectDefinitionOperator param2,
                                        IntelligentObjectDefinition param3, boolean param4, boolean param5,
                                        boolean param6,
                                        StringBuilder string_0)
	{
		return null;
	}
}
