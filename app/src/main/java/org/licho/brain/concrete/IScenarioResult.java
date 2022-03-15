package org.licho.brain.concrete;

/**
 *
 */
public interface IScenarioResult {
		String ObjectType();
		String ObjectName();
		String DataSource();
		String StatisticCategory();
		String StatisticType();
		String DataItem();
		double Average();
		double Minimum();
		double Maximum();
		double HalfWidth();
		double StandardDeviation();

}
