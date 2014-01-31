package fr.icodem.lab.util;

import sun.nio.cs.StreamEncoder;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.HashMap;

public class ZipManager {


    public void unzipFiles(String archiveFile) throws Exception {
        Path zipPath = Paths.get(archiveFile);
        Path basePath = zipPath.getParent();// zip file directory

        try (FileSystem archive = getArchiveFileSystem(zipPath);) {
            for (Path rootPath : archive.getRootDirectories()) {
                try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(rootPath);) {
                    for (Path entry : dirStream) {
                        Path outPath = basePath.resolve(entry.getFileName().toString());
                        Files.copy(entry, outPath);
                    }
                }
            }
        }
    }

    public void zipAndRemoveFiles(String zipFile, String... files) throws Exception {
        zipFiles(zipFile, files);

        // remove original files
        for (String file : files) {
            Path filePath = Paths.get(file);
            Files.deleteIfExists(filePath);
        }
    }

    public void zipFiles(String zipFile, String... files) throws Exception {
        Path zipPath = Paths.get(zipFile);

        try (FileSystem archive = createArchiveFileSystem(zipPath);) {
            for (String file : files) {
                Path filePath = Paths.get(file);
                Path filePathInZip = archive.getPath(filePath.getFileName().toString());
                Files.copy(filePath, filePathInZip);
            }
        }
    }

    private FileSystem getArchiveFileSystem(Path path) throws IOException {
        // convert the filename to a URI
        final URI uri = URI.create("jar:file:" + path.toUri().getPath());
        return FileSystems.newFileSystem(uri, new HashMap<String, String>() {
            {
                put("create", "false");
            }
        });
    }

    private FileSystem createArchiveFileSystem(Path path) throws IOException {
        // delete old archive if exists
        if (Files.exists(path)) Files.delete(path);

        // convert the filename to a URI
        final URI uri = URI.create("jar:file:" + path.toUri().getPath());
        return FileSystems.newFileSystem(uri, new HashMap<String, String>() {
            {
                put("create", "true");
            }
        });
    }

}
