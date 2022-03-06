package com.zdpx.cctpp.concrete;

import cn.hutool.core.io.IoUtil;
import com.google.common.base.Strings;
import com.zdpx.cctpp.utils.simu.IFiles;
import com.zdpx.cctpp.utils.simu.IFilesStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 */
public class ZipFileOperator implements IFilesStream {

    private ZipFile zipFile;
    private OutputStream destineOutputStream;
    private String fileType;
    private String guidDirectory;
    private FileOperatorType fileOperatorType;
    private InputStream sourceInputStream;
    private Set zipEntries;
    private Map<String, TempFileStreamTupler> nameMapTmpStream;

    public ZipFileOperator(InputStream sourceInputStream, String fileType,
                           ZipFileOperator.FileOperatorType fileOperatorType) {
        this.zipEntries = new HashSet<String>();
        this.nameMapTmpStream = new HashMap<>();
        this.sourceInputStream = sourceInputStream;
        this.fileOperatorType = fileOperatorType;
        this.fileType = fileType;
        this.guidDirectory = TempFileOperator.createTempDirectoryGuid();
        if (this.fileOperatorType == FileOperatorType.One) {
            var path = TempFileOperator.createTempFile();
            try {
                try (var destineOutputStream = Files.newOutputStream(Path.of(path))) {
                    IoUtil.copy(this.sourceInputStream, destineOutputStream);
                }
                this.zipFile = new ZipFile(path);
                Stream.of(this.zipFile).forEach(this.zipEntries::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private InOutputStream getFileStream(String fileFullPath) {
        if (Strings.isNullOrEmpty(fileFullPath)) {
            return null;
        }

        if (this.zipFile == null) {
            return null;
        }

        TempFileStreamTupler fileStreamTupler = null;
        if (this.nameMapTmpStream.containsKey(fileFullPath)) {
            return this.nameMapTmpStream.get(fileFullPath).getFileStream();
        }
        InOutputStream result = null;
        ZipEntry entry = this.zipFile.getEntry(fileFullPath);
        if (entry != null && !this.zipEntries.contains(entry.getName())) {
            try (InputStream stream = this.zipFile.getInputStream(entry)) {
                if (this.fileOperatorType == FileOperatorType.Two) {
                    return new InOutputStream(stream, null);
                }

                TempFileStreamTupler tempFileStreamTupler = new TempFileStreamTupler(new InOutputStream(stream, null));
                this.nameMapTmpStream.put(fileFullPath, tempFileStreamTupler);
                result = tempFileStreamTupler.getFileStream();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return result;
        }
        return result;
    }


    @Override
    public IFiles StreamStore() {
        return null;
    }

    @Override
    public InOutputStream OpenMainStream() {
        if (this.fileOperatorType == FileOperatorType.One ||
                this.fileOperatorType == FileOperatorType.Two) {
            return this.getFileStream(this.formatFileName());
        }
        return new InOutputStream(this.createGuidFullFileName());
    }

    private String createGuidFullFileName() {
        return this.guidDirectory + this.formatFileName();
    }

    private String formatFileName() {
        return String.format("%s.xml", this.fileType);
    }


    @Override
    public void Dispose() {

    }

    public enum FileOperatorType {
        One,
        Two,
        Three
    }
}

