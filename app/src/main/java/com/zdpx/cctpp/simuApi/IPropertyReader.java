package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IPropertyReader {
		String getName();

		String getGetStringValue(IExecutionContext context);

		double GetDoubleValue(IExecutionContext context);
}
