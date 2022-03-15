package org.licho.brain.concrete;

/**
 *
 */
public interface IIdentityName {
	boolean IsValidIdentifier(String param0, StringBuffer error);

	String GetUniqueName(String param0);
}
