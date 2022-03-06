package com.zdpx.cctpp.simuApi.enu;

/**
 *
 */
public interface IStatisticsReturnValueSummary extends Comparable<IStatisticsReturnValueSummary>{
		String ScenarioName();
		String ObjectType();
		String ObjectName();
		String DataSource();
		String StatisticCategory();
		String StatisticType();
		String DataItem();
		Double Average();
		Double Minimum();
		Double Maximum();
		Double HalfWidth();
		Double StandardDeviation();
		String StatisticTypeForDisplay();
		Number AverageForDisplay();
		Number MinimumForDisplay();
		Number MaximumForDisplay();
		Number HalfWidthForDisplay();
		Number StandardDeviationForDisplay();

}
