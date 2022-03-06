package com.zdpx.cctpp.concrete;

/**
 *
 */
public class LibraryInfo {
    public final ILibrary Library;
    public final String string_0;

    public LibraryInfo(ILibrary library) {
        this.Library = library;
        this.string_0 = null;
    }

    public LibraryInfo(String param0) {
        this.Library = null;
        this.string_0 = param0;
    }
}
