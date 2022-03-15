package org.licho.brain.concrete;

/**
 *
 */
public class MovementState {
    public Location location;
    public Direction direction;
    public Orientation orientation;
	private MovementInfo movementInfo;

    public double getRate(double time) {
		return this.movementInfo.getRate(time);
    }
}
