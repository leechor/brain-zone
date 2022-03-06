package com.zdpx.cctpp.concrete;

/**
 *
 */
public interface ISearch {
	String ProjectItemName();

	String ItemName();

	String ItemTypeName();

	String GetNameForKey(Object param0);

	String GetDisplayNameForKey(Object param0);

	String SearchableValueFor(Object param0);

    void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel);
}
