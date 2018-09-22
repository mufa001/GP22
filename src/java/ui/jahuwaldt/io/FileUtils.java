/**
 * Please feel free to use any fragment of the code in this file that you need in your own
 * work. As far as I am concerned, it's in the public domain. No permission is necessary
 * or required. Credit is always appreciated if you use a large chunk or base a
 * significant product on one of my examples, but that's not required either.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.
 * 
 * --- Joseph A. Huwaldt
 *
 */
package java.ui.jahuwaldt.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * This is a utility class of static methods for working with files.
 * 
 * <p> Modified by: Joseph A. Huwaldt </p>
 * 
 * @author Joseph A. Huwaldt	Date: November 27, 2009
 * @version April 25, 2014
 */
public class FileUtils {

    /**
     * Return the file name of the specified file without the extension.
     *
     * @param file The file to have the name without extension returned.
     * @return The name of the specified file without the extension (if there is one).
     */
    public static String getFileNameWithoutExtension(File file) {
        String name = file.getName();
        return getFileNameWithoutExtension(name);
    }

    /**
     * Return the file name of the specified file without the extension.
     *
     * @param name The file name to have the name without extension returned.
     * @return The name of the specified file without the extension (if there is one).
     */
    public static String getFileNameWithoutExtension(String name) {
        int index = name.lastIndexOf('.');
        if (index > 0 && index <= name.length() - 2) {
            name = name.substring(0, index);
        }
        return name;
    }

    /**
     * Return the extension portion of the file's name. The extension will always be
     * returned in lower case.
     *
     * @param file The file for which the extension is to be returned.
     * @return The extension portion of the file's name or <code>null</code> if there is
     *      no extension.
     */
    public static String getExtension(File file) {
        return getExtension(file.getName());
    }

    /**
     * Return the extension portion of the file's name. The extension will always be
     * returned in lower case.
     *
     * @param name The name of the file, including the extension.
     * @return The extension portion of the file's name or <code>null</code> if there is
     *      no extension.
     */
    public static String getExtension(String name) {
        if (name != null) {
            int i = name.lastIndexOf('.');
            if (i > 0 && i < name.length() - 1)
                return name.substring(i + 1).toLowerCase();
        }
        return null;
    }

    /**
     * Copy a file from the source to the destination locations.
     *
     * @param src The source file.
     * @param dst The destination file to copy the source file to.
     */
    public static void copy(File src, File dst) throws IOException {

        // If src and dst are the same; hence no copying is required.
        if (sameFile(src, dst))
            return;

        InputStream in = null;
        OutputStream out = null;
        try {

            in = new FileInputStream(src);
            out = new FileOutputStream(dst);
            copy(in, out);

        } finally {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
        }
    }

    /**
     * Copy the input stream to the output stream.
     *
     * @param in The source input stream.
     * @param out The destination output stream.
     */
    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
    }

    /**
     * Attempt to rename a file from the source File location to the destination File
     * location. If the standard atomic rename fails, this method falls back on copying
     * the file which is dangerous as it is not atomic. The fall back will not work if the
     * source or destination is a directory and an exception is thrown in that case.
     *
     * @param src The source file to be renamed. Upon exit, this object MAY point to the
     *      same location as "dst" or it may be left unchanged. Either way, barring an
     *      exception, the file will no longer exist at the input src path location.
     * @param dst The destination path to rename the source file to.
     */
    public static void rename(File src, File dst) throws IOException {
        if (!src.exists())
            throw new FileNotFoundException("\"" + src.getPath() + "\" not found.");

        //	If the source and destination are the same, no rename is required.
        if (sameFile(src, dst))
            return;

        //	Make sure we can write to the destination location.
        if (dst.exists()) {
            //	The file already exists, can we write to it?
            if (!dst.canWrite())
                throw new IOException("Can not write to \"" + dst.getPath() + "\".");

        } else {
            //	The file does not already exist.  Can we create a file here?
            boolean success = dst.createNewFile();
            if (!success)
                throw new IOException("Can not write to \"" + dst.getPath() + "\".");
            dst.delete();
        }

        try {
            //	Try to use the atomic rename first.
            boolean success = src.renameTo(dst);
            if (!success) {
                //	Delete the destination file, if it exists.  Then wait a second and try again.
                if (dst.exists())   dst.delete();
                Thread.sleep(1000);
                success = src.renameTo(dst);

                if (!success) {
                    //	If renaming the file doesn't work, fall back on copying it.
                    //	This doesn't work for directories.
                    if (!src.isFile())
                        throw new IOException("Move failed, source is dir: \"" + src.getPath() + "\".");
                    if (dst.isDirectory())
                        throw new IOException("Move failed, destination is dir: \"" + dst.getPath() + "\".");
                    copy(src, dst);
                    src.delete();
                }
            }
        } catch (InterruptedException e) {
            throw new InterruptedIOException();
        }
    }

    /**
     * Returns a list of String objects each of which represents a line in the specified
     * file.
     *
     * @param file The file to be read in.
     * @return A list of String objects, one for each line in the specified file.
     */
    public static List<String> readlines(File file) throws IOException {
        FileInputStream instream = new FileInputStream(file);
        return readlines(instream);
    }

    /**
     * Returns a list of String objects each of which represents a line in the specified
     * input stream.
     *
     * @param instream The input stream to be read in.
     * @return A list of String objects, one for each line in the specified input stream.
     */
    public static List<String> readlines(InputStream instream) throws IOException {
        List<String> output = new ArrayList<String>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(instream));
            String aLine;
            do {
                aLine = reader.readLine();
                if (aLine != null)
                    output.add(aLine);

            } while (aLine != null);

        } finally {
            reader.close();
        }

        return output;
    }

    /**
     * Write out a list of String objects which represent a line to the specified output
     * stream. A line ending character is appended to each line.
     *
     * @param outFile The output file to be written to.
     * @param lines The list of String objects to be written out.
     * @throws IOException if there is any problem writing to the output file.
     */
    public static void writelines(File outFile, List<String> lines) throws IOException {
        FileOutputStream outstream = new FileOutputStream(outFile);
        try {
            writelines(outstream, lines);
        } finally {
            outstream.close();
        }
    }

    /**
     * Write out a list of String objects which represent a line to the specified output
     * stream. A line ending character is appended to each line.
     *
     * @param outstream The output stream to be written to.
     * @param lines The list of String objects to be written out.
     * @throws IOException if there is any problem writing to the output stream.
     */
    public static void writelines(OutputStream outstream, List<String> lines) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outstream));

        try {

            //	Write out the lines to the output file.
            int size = lines.size();
            for (int i = 0; i < size; ++i) {
                writer.write(lines.get(i));
                writer.newLine();
            }

        } finally {
            writer.close();
        }

    }

    /**
     * Returns a buffer that contains the contents of the specified file.
     *
     * @param file The file to be read into a new ByteBuffer.
     * @return The buffer containing the contents of the specified file.
     */
    public static byte[] file2Buffer(File file) throws IOException {

        //	Read the file into a byte buffer.
        if (file.length() > (long) Integer.MAX_VALUE)
            throw new IOException(file.getName() + " is to large to convert into a ByteBuffer!");

        int fileSize = (int) file.length();
        byte[] buffer = new byte[fileSize];
        FileInputStream fis = new FileInputStream(file);
        fis.read(buffer, 0, fileSize);
        fis.close();

        return buffer;
    }

    /**
     * Write the entire contents of a byte array to the specified file.
     *
     * @param buffer The buffer to be written to the file.
     * @param file The file to write the buffer to (it's existing contents will be
     * overwritten).
     */
    public static void buffer2File(byte[] buffer, File file) throws IOException {

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(buffer);
        fos.close();

    }

    /**
     * Returns a ByteBuffer that contains the contents of the specified file.
     *
     * @param file The file to be read into a new ByteBuffer.
     * @return The ByteBuffer containing the contents of the specified file.
     */
    public static ByteBuffer file2ByteBuffer(File file) throws IOException {

        //	Read the file into a byte buffer.
        if (file.length() > Integer.MAX_VALUE)
            throw new IOException(file.getName() + " is to large to convert into a ByteBuffer!");

        int fileSize = (int) file.length();
        byte[] mybytearray = new byte[fileSize];
        FileInputStream fis = new FileInputStream(file);
        fis.read(mybytearray, 0, fileSize);
        fis.close();

        ByteBuffer buffer = ByteBuffer.wrap(mybytearray);

        return buffer;
    }

    /**
     * Write the entire contents of a ByteBuffer to the specified file.
     *
     * @param buffer The buffer to be written to the file.
     * @param file The file to write the buffer to (it's existing contents will be overwritten).
     */
    public static void byteBuffer2File(ByteBuffer buffer, File file) throws IOException {
        byte[] mybytearray;
        if (buffer.hasArray())
            mybytearray = buffer.array();
        else {
            mybytearray = new byte[buffer.capacity()];
            buffer.position(0);
            buffer.get(mybytearray);
        }

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(mybytearray);
        fos.close();
    }

    /**
     * Create a temporary directory using the specified prefix.
     *
     * @param prefix The prefix string to be used in generating the file's name; must be
     *          at least three characters long.
     */
    public static File createTempDirectory(String prefix) throws IOException {

        File tempFile = File.createTempFile(prefix, "", null);
        if (!tempFile.delete() || !tempFile.mkdir())
            throw new IOException("Could not create temporary directory: " + tempFile.getPath());

        return tempFile;
    }

    /**
     * Recursively deletes the directory tree indicated by the specified path. The
     * directory and all of it's contents are deleted.
     *
     * @param path The directory to be deleted. If a plain file is passed in rather than a
     *          directory, it is simply deleted.
     */
    public static boolean deleteDirectory(File path) {
        if (path.exists() && path.isDirectory()) {
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.isDirectory())
                    deleteDirectory(file);
                else
                    file.delete();
            }
        }
        return (path.delete());
    }

    /**
     * Returns <code>true</code> if and only if the two File objects refer to the same
     * file in the file system.
     *
     * @throws IOException If an I/O error occurs, which is possible because the
     *      construction of the canonical pathname may require file system queries.
     */
    public static boolean sameFile(File f1, File f2) throws IOException {
        return f1.getCanonicalPath().equals(f2.getCanonicalPath());
    }

    /**
     * GZIP compress the src file writing to the destination file. The source &
     * destination files may be identical.
     *
     * @param src The source file to be compressed.
     * @param dst The destination file to compress the source file to. This may be
     * identical to the source location if the change is to be made in place.
     */
    public static void gzip(File src, File dst) throws IOException {

        File dst2 = dst;
        if (sameFile(src, dst))
            dst2 = File.createTempFile("gzip", null);

        InputStream in = null;
        OutputStream out = null;
        try {

            in = new FileInputStream(src);
            out = new FileOutputStream(dst2);
            gzip(in, out);

        } finally {
            if (in != null)
                in.close();
            if (out != null)
                out.close();

            if (dst2 != dst) {
                //  If we used a temporary file, move it to the originally
                //  desired location.
                rename(dst2, dst);

                //  Delete the temporary file.
                dst2.delete();
            }
        }
    }

    /**
     * Un-GZIP the compressed src file writing the uncompressed data to the destination
     * file. The source & destination files may be identical.
     *
     * @param src The GZIP compressed source file to be de-compressed.
     * @param dst The destination file to uncompress the source file to. This may be
     * identical to the source location if the change is to be made in place.
     */
    public static void ungzip(File src, File dst) throws IOException {

        File dst2 = dst;
        if (sameFile(src, dst))
            dst2 = File.createTempFile("gzip", null);

        InputStream in = null;
        OutputStream out = null;
        try {

            in = new FileInputStream(src);
            out = new FileOutputStream(dst2);
            ungzip(in, out);

        } finally {
            if (in != null)
                in.close();
            if (out != null)
                out.close();

            if (dst2 != dst) {
                //  If we used a temporary file, move it to the originally
                //  desired location.
                rename(dst2, dst);

                //  Delete the temporary file.
                dst2.delete();
            }
        }
    }

    /**
     * GZIP compress the input stream and write it to the output stream.
     *
     * @param in The source input stream.
     * @param out The destination output stream where GZIP compressed data is to be
     * written.
     */
    public static void gzip(InputStream in, OutputStream out) throws IOException {
        GZIPOutputStream gzipOut = new GZIPOutputStream(out);
        copy(in, gzipOut);
        gzipOut.finish();
    }

    /**
     * Un-GZIP the compressed input stream and write the uncompressed data to the
     * specified output stream.
     *
     * @param in The source input stream pointing to GZIP compressed data.
     * @param out The destination output stream where uncompressed data is to be written.
     */
    public static void ungzip(InputStream in, OutputStream out) throws IOException {
        GZIPInputStream gzipIn = new GZIPInputStream(in);
        copy(gzipIn, out);
    }

    /**
     * Returns
     * <code>true</code> if the specified input file is pointing at a GZIP compressed data
     * set.
     *
     * @param in The input file to be tested.
     */
    public static boolean isGZIPCompressed(File file) throws IOException {
        BufferedInputStream is = null;
        try {

            is = new BufferedInputStream(new FileInputStream(file), 4);
            return isGZIPCompressed(is);

        } finally {
            if (is != null)
                is.close();
        }
    }

    /**
     * Returns
     * <code>true</code> if the specified input stream is pointing at a GZIP compressed
     * data set.
     *
     * @param in The input stream to be tested.
     */
    public static boolean isGZIPCompressed(BufferedInputStream in) throws IOException {
        byte[] signature = new byte[2];

        in.mark(4);
        in.read(signature); //read the signature
        in.reset();

        return isGZIPCompressed(signature);
    }

    /**
     * Returns
     * <code>true</code> if the specified array of bytes represent a GZIP compressed data
     * set.
     *
     * @param bytes the array of bytes to be tested.
     */
    public static boolean isGZIPCompressed(byte[] bytes) throws IOException {
        if ((bytes == null) || (bytes.length < 2)) {
            return false;

        } else {
            return ((bytes[0] == (byte) (GZIPInputStream.GZIP_MAGIC))
                    && (bytes[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8)));
        }
    }
}
