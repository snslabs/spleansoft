package com.luxoft.itci.i18n.plugin.scanner;

import com.intellij.openapi.vfs.VirtualFile;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpScanner {
    private boolean checkComments = false;
    private int groupIndex = -1;
    private Pattern pattern;
    private String typeDesc;
    private final Charset CHARSET = Charset.forName("ISO-8859-15");
    private final CharsetDecoder DECODER = CHARSET.newDecoder();

    /**
     * Constructs regexp scanner with specified type
     * @param typeDesc
     */
    public RegexpScanner(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public List<? extends ScanResult> scan(VirtualFile virtualFile) {
        long length = virtualFile.getLength();
        if (length > (long) Integer.MAX_VALUE) {
            throw new RuntimeException("File is too large. Cannot be larger then " + Integer.MAX_VALUE + " bytes.");
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) length);
        try {
            InputStream inputStream = virtualFile.getInputStream();
            int readLength = inputStream.read(byteBuffer.array());
            if (readLength != byteBuffer.array().length) {
                System.out.println("WARNING!!! Read " + readLength + " expected " + byteBuffer.array().length);
            }
            // eleminating carriage return symbols to make offsets matches with IDEA PsiFiles strategy
            String charBuffer = new String(DECODER.decode(byteBuffer).array()).replaceAll("\r","");
            return scanCharBuffer(charBuffer, virtualFile);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<ScanResult> scanCharBuffer(String charBuffer, VirtualFile virtualFile) {
        ArrayList<ScanResult> result = new ArrayList<ScanResult>();

        Matcher matcher = pattern.matcher(charBuffer);
        while (matcher.find()) {
            String entry = (groupIndex == -1) ? matcher.group() : matcher.group(groupIndex);
            List<String> detectedComments = null;
            if (checkComments) {
                detectedComments = detectComments(charBuffer, entry);
            }
            if (!checkComments || detectedComments == null) {
                String typeDescWithComments = typeDesc;
                if (!checkComments && detectedComments != null) {
                    typeDescWithComments = typeDescWithComments + "(" + detectedComments + ")";
                }
                int startOffset = (groupIndex == -1) ? matcher.start() : matcher.start(groupIndex);
                int endOffset = (groupIndex == -1) ? matcher.end() : matcher.end(groupIndex);
                result.add(new ScanResult(virtualFile, 0, entry, typeDescWithComments, startOffset, endOffset));
            }
        }
        return result;
    }

    private List<String> detectComments(String entireFile, String entry) {
        // todo: implement comments detection
        return null;
    }

    public void setRegexp(String regexp) {
        pattern = Pattern.compile(regexp);
    }

    public void setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
    }
}
