package org.licho.brain.concrete;

/**
 *
 */
public class FreeSpaceRunSpace extends IntelligentObjectRunSpace {

    private Population population;

    public FreeSpaceRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject,
                             MayApplication application, IntelligentObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
    }

    public Movement getMovement() {
        return (Movement) this.absBaseTraces[5];
    }

    public DoubleTable getDesiredSpeed() {
        return (DoubleTable) this.absBaseTraces[6];
    }


    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }
}
