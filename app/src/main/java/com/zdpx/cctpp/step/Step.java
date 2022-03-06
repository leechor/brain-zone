package com.zdpx.cctpp.step;

/**
 * Steps are used to define logic within your simulation model. Each step performs an action such as seize, delay,
 * decide, or wait. Steps are stateless, but may change the state of an element/token/entity/object.
 * <p></p>
 * They include the Common Steps, All Steps and User-Defined Steps.
 * <p></p>
 * <b>Note</b>: Tokens execute Steps in a process. A Step often acts on either the token’s <b>AssociatedObject’</b>‘ or
 * the <b>‘ParentObject’</b>. The Parent object is the object that contains the process where the step is being used,
 * which is often the overall Model. The Associated object is typically an object that the token is representing,
 * such as an entity.
 */
public class Step {
}
