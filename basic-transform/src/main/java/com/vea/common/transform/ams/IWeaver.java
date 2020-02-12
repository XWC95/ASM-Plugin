package com.vea.common.transform.ams;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Vea
 * @since 2019-01
 */
public interface IWeaver {
    /**
     * Check a certain file is weavable
     */
    boolean isWeavableClass(String filePath) throws IOException;

    /**
     * Weave single class to byte array
     */
    byte[] weaveSingleClassToByteArray(InputStream inputStream) throws IOException;
}
