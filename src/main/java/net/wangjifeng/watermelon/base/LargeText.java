package net.wangjifeng.watermelon.base;

import net.wangjifeng.watermelon.util.Nils;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author: wjf
 * @date: 2021/9/23 11:44
 *
 * 针对大文本的处理类。
 */
public class LargeText implements Iterator<String> {

    /**
     * 缓冲读者
     */
    private final BufferedReader bufferedReader;

    /**
     * 当前正在读取的文本行
     */
    private String cachedLine;

    /**
     * 是否读取完毕的标志
     */
    private boolean finished = false;

    /**
     * 读取文本的构造器。
     *
     * @param reader 读者
     * @throws IllegalArgumentException IllegalArgumentException
     */
    public LargeText(final Reader reader) throws IllegalArgumentException {
        if (reader == null) {
            throw new IllegalArgumentException("Reader must not be null");
        }
        if (reader instanceof BufferedReader) {
            bufferedReader = new SimpleCastor<Reader, BufferedReader>().cast(reader) ;
        } else {
            bufferedReader = new BufferedReader(reader);
        }
    }

    /**
     * 读取文本的构造器。
     *
     * @param is 输入流
     */
    public LargeText(final InputStream is) {
        this(new InputStreamReader(Nils.requireNonNil(is, "InputStream must not be null")));
    }

    /**
     * 是否有下一行文本。
     *
     * @return 是否有下一行文本
     */
    @Override
    public boolean hasNext() {
        if (cachedLine != null) {
            return true;
        } else if (finished) {
            return false;
        } else {
            try {
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        finished = true;
                        return false;
                    } else if (isValidLine(line)) {
                        cachedLine = line;
                        return true;
                    }
                }
            } catch (final IOException ioe) {
                close();
                throw new IllegalStateException(ioe);
            }
        }
    }

    /**
     * 是否是有效行，默认是true。
     *
     * @param line 文本行
     * @return 是否是有效行
     */
    protected boolean isValidLine(final String line) {
        return true;
    }

    /**
     * 读取文本行。
     *
     * @return 文本行
     */
    @Override
    public String next() {
        return nextLine();
    }

    /**
     * 安静的关闭底层的读者reader。
     */
    public void close() {
        finished = true;
        closeQuietly(bufferedReader);
        cachedLine = null;
    }

    /**
     * 移除文本行，不支持此操作。
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove unsupported on LineIterator");
    }

    /**
     * 安静的关闭异常。
     *
     * @param closeable 可关闭
     */
    private void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            // ignore
        }
    }

    /**
     * 读取文本行。
     *
     * @return 文本行
     */
    private String nextLine() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more lines");
        }
        final String currentLine = cachedLine;
        cachedLine = null;
        return currentLine;
    }

}
