package org.licho.brain.concrete;

/**
 * Experiments are used to define a set of scenarios to be executed using the model. They are executed in batch mode.
 * This is typically done (once a model has been validated) to make production runs that compare one or more
 * variations of the system.
 * <p>
 * Each scenario has a set of control variables (e.g. size of each input buffer) and output responses (e.g. the
 * throughput, waiting times, etc.). The control variables are the values assigned to properties of the associated
 * model.  Before adding an experiment you typically add properties to your main model to serve as the controls that
 * you want to change for each scenario. Since most models contain random components (e.g. service times, failures,
 * etc.) replications of the scenario are required to allow the computation of confidence intervals on the results.
 * <p>
 * For every data table with 2 or more data bindings in the model, there is a column within the Experiment Design
 * view of an experiment. The header of a column is the table name and the cell values are a drop down of the named
 * bindings for that table. For a particular scenario, a user can set the various bindings to be used per table. The
 * default value of the cell for a new scenario will be the currently active databinding for that table. When using
 * the OptQuest add-in, Simio also uses the default active binding for each table.
 * <p>
 * An experiment is not owned by a model. Rather, an experiment references and uses a model (is “bound” to a model).
 * This means that, if a model is removed from a project, any experiments that referenced and used that model may
 * remain in the project to preserve experiment results. Users should be able to distribute experiments between
 * projects.
 * <p>
 * Within the Analysis section of the Experiment properties window, a user can specify a warmup period for the run,
 * as well as a confidence level.
 */
public class Experiment {
}
