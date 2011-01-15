package ru.snslabs.plugin.search.scanner.impl;

import ru.snslabs.plugin.search.scanner.*;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Warning!! Class uses static map as cache of files to be scanned. Not threadsafe!!!
 */
public class RegexpScanner implements Scanner {
    private int groupIndex = -1;
    private Pattern pattern;
    private ScannerDescription scannerDesc;
    private final Charset CHARSET = Charset.forName("ISO-8859-15");
    private final CharsetDecoder DECODER = CHARSET.newDecoder();
    private List<Filter> filters = new ArrayList<Filter>();

    private static Map<Object, String> cache = new HashMap<Object, String>();

    /**
     * Constructs regexp scanner with specified type
     *
     * @param typeDesc
     */
    public RegexpScanner(String typeDesc) {
        this.scannerDesc = new ScannerDescription(typeDesc);
    }

    public List<? extends ScanResult> scan(InputStream inputStream, long length, Object ref) {
        if (length > (long) Integer.MAX_VALUE) {
            throw new RuntimeException("File is too large. Cannot be larger then " + Integer.MAX_VALUE + " bytes.");
        }
        // using cache to avoid creating additional strings for multiple regexp scanners
        String charBuffer = cache.get(ref);
        if (charBuffer == null) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) length);
            try {
                int readLength = inputStream.read(byteBuffer.array());
                if (readLength != byteBuffer.array().length) {
                    System.out.println("WARNING!!! Read " + readLength + " expected " + byteBuffer.array().length);
                }
                // eleminating carriage return symbols to make offsets matches with IDEA PsiFiles strategy
                charBuffer = new String(DECODER.decode(byteBuffer).array()).replaceAll("\r", "");
                cache.put(ref, charBuffer);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return scanCharBuffer(charBuffer, ref);
    }

    private List<ScanResult> scanCharBuffer(String charBuffer, Object ref) {
        ArrayList<ScanResult> result = new ArrayList<ScanResult>();

        Matcher matcher = pattern.matcher(charBuffer);
        while (matcher.find()) {
            String entry = (groupIndex == -1) ? matcher.group() : matcher.group(groupIndex);

            if (filters.size() > 0) {
                boolean pass = true;
                for (Filter f : filters) {
                    if (!f.accept(entry)) {
                        pass = false;
                        break;
                    }
                }
                if (!pass) {
                    // if any of filter failed - skip this entry
                    continue;
                }
            }
            int startOffset = (groupIndex == -1) ? matcher.start() : matcher.start(groupIndex);
            int endOffset = (groupIndex == -1) ? matcher.end() : matcher.end(groupIndex);
            result.add(AbstractScanResult.createScanResult(ref, 0, entry, scannerDesc, startOffset,
                    endOffset));
        }
        return result;
    }

    public void setRegexp(String regexp) {
        pattern = Pattern.compile(regexp);
    }

    public void setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }
}
