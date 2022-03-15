package org.licho.brain.api.extensions;

import org.licho.brain.api.IExperiment;
import org.licho.brain.api.IModel;
import org.licho.brain.api.ISimioProject;

/**
 * The context under which a design add-in is executed
 */
public interface IDesignContext {
    /**
     * The project's active model at the time the add-in is invoked, or null if the project's active item is not a
     * model.
     *
     * @return
     */
    IModel getActiveModel();

    /**
     * The active project at the time the add-in is invoked.
     *
     * @return
     */
    ISimioProject getActiveProject();

    /**
     * The project's active experiment at the time the add-in is invoked, or null if the project's active item is not
     * an experiment.
     *
     * @return
     */
    IExperiment getActiveExperiment();
}
