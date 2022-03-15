package org.licho.brain.simuApi;

/**
 *
 */
public interface IPropertyReader {
		String getName();

		String getGetStringValue(IExecutionContext context);

		double GetDoubleValue(IExecutionContext context);
}
