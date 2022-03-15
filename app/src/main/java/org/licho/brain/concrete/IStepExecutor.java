package org.licho.brain.concrete;


import org.licho.brain.IFunction.Action;
import org.licho.brain.simioEnums.TransferFromType;
import org.licho.brain.simioEnums.TransferToType;

/**
 *
 */
public interface IStepExecutor {
	IStepExecutor imethod_0();
//	IStepExecutor add(Action<T> initializer);
	IStepExecutor add(Action<SubStepWrapper> buildAction);
	IStepExecutor getLabelStep(String label);
	ProcessProperty run();
	IStepExecutor imethod_5(String param0);
	IStepExecutor Assign(String param0, String param1);
	IStepExecutor imethod_6(String param0);
	IStepExecutor Create(String param0);
	IStepExecutor imethod_7();
	IStepExecutor imethod_8(TransferFromType transferFromType, TransferToType transferToType, String param2);
	IStepExecutor imethod_9(TransferFromType transferFromType, TransferToType transferToType, String param2, String param3);
	IStepExecutor imethod_10(String param0);
	IStepExecutor imethod_11(String param0);
	IStepExecutor imethod_12(String param0);

}
