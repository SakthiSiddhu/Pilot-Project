package com.pilotproject.SurveyMicro.utils;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public class BlobUtils {

    /**
     * Converts a Blob to a List<Long>.
     *
     * @param blob the Blob to convert
     * @return the List<Long> represented by the Blob
     * @throws SQLException if an SQL error occurs
     * @throws IOException  if an I/O error occurs
     */
    public static List<Long> blobToListLong(Blob blob) throws SQLException, IOException {
        // Convert Blob to InputStream
        try (InputStream inputStream = blob.getBinaryStream();
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

            // Read object from InputStream
            @SuppressWarnings("unchecked")
            List<Long> list = (List<Long>) objectInputStream.readObject();
            return list;
        } catch (ClassNotFoundException e) {
            throw new IOException("Failed to deserialize List<Long> from Blob", e);
        }
    }
}

