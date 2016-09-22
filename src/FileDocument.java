package src;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;

/**
 * This class is thread safe.
 */
public final class FileDocument implements Document {
    private File file;

    public FileDocument(final File file) {
        this.file = file;
    }

    @Override
    public CharSequence read() throws IOException {
        byte[] allBytes;
        try (FileChannel channel = new RandomAccessFile(file, "r").getChannel();
             FileLock lock = channel.tryLock(0, file.length(), true)) {
            allBytes = Files.readAllBytes(file.toPath());
        }
        return new String(allBytes);
    }

    @Override
    public void write(CharSequence content) throws IOException {
        try (FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
             FileLock lock = channel.tryLock(0, 0, true)) {
            Files.write(file.toPath(), content.toString().getBytes());
        }
    }
}
