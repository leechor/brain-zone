package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.utils.simu.IFilesStream;
import com.zdpx.cctpp.utils.simu.IFilesStreamOperator;

import java.io.InputStream;

/**
 *
 */
public class ZipProject implements IFilesStreamOperator {
    private final String fileType;

    public ZipProject(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public IFilesStream getFilesStream(InputStream stream) {
        return new ZipFileOperator(stream, this.fileType, ZipFileOperator.FileOperatorType.One);
    }


}
