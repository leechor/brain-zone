package org.licho.brain.concrete.step;

/**
 *
 */
public interface Messages {
    String Trace_EndTransferStep_IgnoringEndTransfer = "";
    String Trace_EndTransferStep_EndingTransferOntoLink = "";
    String Trace_CoarseStepping_EntityTransferredOntoLink = "";
    String Trace_EndTransferStep_EndingTransferIntoStation = "";
    String Trace_CoarseStepping_EntityTransferredIntoStation = "";
    String Trace_EndTransferStep_FiringTransferEvents = "";
    String Trace_CoarseStepping_EntityTransferredTo = "";

    String Trace_DestroyStep_FailedToDestroyObject = "";
    String Trace_DestroyStep_DestroyingObject = "";
}
