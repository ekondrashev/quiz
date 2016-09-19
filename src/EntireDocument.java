package src;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * This class is thread safe.
 */
public final class EntireDocument implements Document {
    private File file;

    public EntireDocument(final File file) {
        this.file = file;
    }

    @Override
    public CharSequence read() throws IOException {
        StringBuilder string = new StringBuilder("");
        FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
        FileLock lock = channel.tryLock(0, file.length(), true);
        try (FileInputStream fis = new FileInputStream(file)) {
            int data;
            while ((data = fis.read()) > 0) {
                string.append((char) data);
            }
        } finally {
            lock.release();
        }
        return string.toString();
    }

    @Override
    public void save(CharSequence content) throws IOException {
        FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
        FileLock lock = channel.tryLock(0, 0, true);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                fos.write(content.charAt(i));
            }
        } finally {
            lock.release();
        }
    }
}
