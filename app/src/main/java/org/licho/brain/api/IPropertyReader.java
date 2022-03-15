package org.licho.brain.api;

/**
 *
 */
public interface IPropertyReader {
		String getName();

		String getGetStringValue(IExecutionContext context);

		double GetDoubleValue(IExecutionContext context);
}
