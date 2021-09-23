package net.wangjifeng.watermelon.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author: wjf
 * @date: 2021/9/23 11:34
 *
 * 操作IO流的工具。
 */
public class IOs {

    /**
     * 默认缓冲区大小
     */
    public static final int BUFFER_SIZE = 4096;

    /**
     * 将输入流复制到输出流。
     *
     * @param in 输入流
     * @param out 输出流
     * @return 已复制的字节数
     * @throws IOException
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        Nils.requireNonNil(in, "No InputStream specified");
        Nils.requireNonNil(out, "No OutputStream specified");

        int byteCount = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
            byteCount += bytesRead;
        }
        out.flush();
        return byteCount;
    }

    /**
     * 将输入流复制到输出流，从start位一直复制到end位。
     *
     * @param in 输入流
     * @param out 输出流
     * @param start 起始位
     * @param end 结束位
     * @return 已复制的字节数
     * @throws IOException
     */
    public static long copyRange(InputStream in, OutputStream out, long start, long end) throws IOException {
        Nils.requireNonNil(in, "No InputStream specified");
        Nils.requireNonNil(out, "No OutputStream specified");

        long skipped = in.skip(start);
        if (skipped < start) {
            throw new IOException("Skipped only " + skipped + " bytes out of " + start + " required");
        }

        long bytesToCopy = end - start + 1;
        byte[] buffer = new byte[BUFFER_SIZE];
        while (bytesToCopy > 0) {
            int bytesRead = in.read(buffer);
            if (bytesRead == -1) {
                break;
            }
            else if (bytesRead <= bytesToCopy) {
                out.write(buffer, 0, bytesRead);
                bytesToCopy -= bytesRead;
            }
            else {
                out.write(buffer, 0, (int) bytesToCopy);
                bytesToCopy = 0;
            }
        }
        return (end - start + 1 - bytesToCopy);
    }

}
