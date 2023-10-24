package io.flutter.plugins.videoplayer;
import java.io.*;
import java.security.*;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class DecryptionInputStream extends InputStream {
    private InputStream encryptedInputStream;
    private Cipher cipher;
    private byte[] buffer;
    private int bytesRead;

    public DecryptionInputStream(InputStream encryptedInputStream, SecretKey privateKey) throws Exception {
        this.encryptedInputStream = encryptedInputStream;
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        this.cipher.init(Cipher.DECRYPT_MODE, privateKey);
        this.buffer = new byte[128]; // Adjust this buffer size as needed
        this.bytesRead = -1;
    }


    @Override
    public int read() throws IOException {
        System.out.println("read()");
        if (bytesRead == -1) {
            return -1; // End of stream
        }

        try {
            bytesRead = encryptedInputStream.read(buffer);
            if (bytesRead == -1) {
                return -1; // End of stream
            }

            byte[] decryptedBuffer = cipher.doFinal(buffer, 0, bytesRead);
            return decryptedBuffer[0] & 0xFF;
        } catch (Exception e) {
            throw new IOException("Error reading and decrypting data: " + e.getMessage());
        }
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
      System.out.println("read byte[] b, int off, int len hhhhhhhhhhh" + Arrays.toString(b) +" "+off+" "+len );

        try {
            byte[] decryptedBuffer = cipher.doFinal(b, off, len);
            return decryptedBuffer[0] & 0xFF;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int read(byte[] b) throws IOException {
      System.out.println("read byte[] b");

        try {


            byte[] decryptedBuffer = cipher.doFinal(b);
            return decryptedBuffer[0] & 0xFF;
        } catch (Exception e) {
            throw new IOException("Error reading and decrypting data: " + e.getMessage());
        }
    }
}
