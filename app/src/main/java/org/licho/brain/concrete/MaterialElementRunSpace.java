package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class MaterialElementRunSpace extends AbsBaseRunSpace {
    	private double quantityProduced;
	private double quantityConsumed;
	private double materialCostCharged;
	private double minimumQuantityAvailable;
	private double maximumQuantityAvailable;

    public MaterialElementRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject,
                                   MayApplication application, AbsIntelligentPropertyObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
    }

    public double getAverageQuantityAvailable()
	{
        // TODO: 2022/2/8
        return 0.0;
	}

	public double getMinimumQuantityAvailable()
	{
		return this.minimumQuantityAvailable;
	}

	public double getMaximumQuantityAvailable()
	{
		return this.maximumQuantityAvailable;
	}

	public double getQuantityProduced()
	{
		return this.quantityProduced;
	}

	public double getQuantityConsumed()
	{
		return this.quantityConsumed;
	}

	public double getMaterialCostCharged()
	{
		return this.materialCostCharged;
	}
}
