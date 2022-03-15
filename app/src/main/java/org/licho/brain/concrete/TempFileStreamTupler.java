package org.licho.brain.concrete;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Pair;

import java.io.File;

/**
 *
 */
public class TempFileStreamTupler {

    private InOutputStream tmpStream;
    private String filePath;

    public TempFileStreamTupler(InOutputStream stream) {
        Pair<InOutputStream, String> tuple = TempFileStreamTupler.getStreamNameTmpTuple(stream);
        this.tmpStream = tuple.getKey();
        this.filePath = tuple.getValue();
    }

    public InOutputStream getFileStream() {
        if (!new File(this.filePath).exists()) {
            var tuple = TempFileStreamTupler.getStreamNameTmpTuple(this.tmpStream);
            this.tmpStream = tuple.getKey();
            this.filePath = tuple.getValue();
        }
        return new FileInOutputStream(this.filePath);
    }

    private static Pair<InOutputStream, String> getStreamNameTmpTuple(InOutputStream stream) {
        String tmpFile = TempFileOperator.createTempFile();
        InOutputStream fileStream = new InOutputStream(tmpFile);
        IoUtil.copy(stream.inputStream, fileStream.getOutputStream());

        return new Pair<>(fileStream, tmpFile);
    }
}