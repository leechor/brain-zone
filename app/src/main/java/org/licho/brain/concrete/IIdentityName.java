package org.licho.brain.concrete;


/**
 * All object base,
 */
public interface IIdentityName {
    boolean IsValidIdentifier(String param0, StringBuffer error);

    String GetUniqueName(String param0);
}
