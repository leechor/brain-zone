package com.zdpx.cctpp.concrete;

/**
 *
 */
public class ReportErrorWrapper implements IReport {
    public void ReportError(String error, IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace) {
        throw new RuntimeException(error);
    }
}
