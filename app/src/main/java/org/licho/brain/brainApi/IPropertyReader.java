package org.licho.brain.brainApi;

/**
 *
 */
public interface IPropertyReader {
		String getName();

		String getGetStringValue(IExecutionContext context);

		double GetDoubleValue(IExecutionContext context);
}
