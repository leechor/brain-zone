package org.licho.brain.concrete;

/**
 *
 */
public class MovementInfo {
private double double_0;

	private double rate;
	private double double_2;
	private double acceleration;
	private double accelerationDuration;
	private double double_5;
	private double double_6;
	private double double_7;
	private boolean bool_0;

    	public MovementInfo(double param0, double param1, double param2, double param3, double param4, double param5, double param6, double param7)
	{
		this.double_0 = param0;
		this.rate = param1;
		this.double_2 = param2;
		this.acceleration = param3;
		this.accelerationDuration = param4;
		this.double_5 = param5;
		this.double_6 = param6;
		this.double_7 = param7;
		this.bool_0 = false;
	}

    public double getRate(double time) {
//        double num = time - this.method_1();
//        double num2;
//        if (time < this.method_2()) {
//            num2 = num;
//        } else {
//            num2 = this.method_2() - this.method_1();
//        }
//        if (num2 > 0.0) {
//            return this.Rate + this.Acceleration * num2;
//        }
//        return this.Rate;
        return Double.NaN;
    }

    public void method_5(double expressionResult, double timeNow) {
        // TODO: 2022/2/12
    }

    public void method_6(double expressionResult, double timeNow) {

    }

    public void method_8(double expressionResult, double timeNow) {

    }

    public void method_9(double expressionResult, double timeNow) {
    }

    public double Acceleration() {
        return this.acceleration;
    }

    public double AccelerationDuration() {
			return this.accelerationDuration;
    }

    public double method_10(double timeNow) {
        return 0;
    }
}
