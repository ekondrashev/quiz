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
        FileChannel channel = null;
        FileLock lock = null;
        try {
            channel = new RandomAccessFile(file, "r").getChannel();
            lock = channel.tryLock(0, file.length(), true);
            allBytes = Files.readAllBytes(file.toPath());
        } finally {
            if (lock != null && lock.isValid()) {
                lock.release();
                lock.close();
            }
            if (channel != null && channel.isOpen()) {
                channel.close();
            }
        }
        return new String(allBytes);
    }

    @Override
    public void write(CharSequence content) throws IOException {
        FileChannel channel = null;
        FileLock lock = null;
        try {
            channel = new RandomAccessFile(file, "rw").getChannel();
            lock = channel.tryLock(0, 0, true);
            Files.write(file.toPath(), content.toString().getBytes());
        } finally {
            if (lock != null && lock.isValid()) {
                lock.release();
                lock.close();
            }
            if (channel != null && channel.isOpen()) {
                channel.close();
            }
        }
    }
}
