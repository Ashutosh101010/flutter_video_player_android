package io.flutter.plugins.videoplayer;
import java.io.*;
import java.security.*;
import javax.crypto.Cipher;

public class DecryptionInputStream extends InputStream {
    private InputStream encryptedInputStream;
    private Cipher rsaCipher;
    private byte[] buffer;
    private int bytesRead;

    public DecryptionInputStream(InputStream encryptedInputStream, PrivateKey privateKey) throws Exception {
        this.encryptedInputStream = encryptedInputStream;
        this.rsaCipher = Cipher.getInstance("RSA");
        this.rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        this.buffer = new byte[128]; // Adjust this buffer size as needed
        this.bytesRead = -1;
    }

    @Override
    public int read() throws IOException {
        if (bytesRead == -1) {
            return -1; // End of stream
        }

        try {
            bytesRead = encryptedInputStream.read(buffer);
            if (bytesRead == -1) {
                return -1; // End of stream
            }

            byte[] decryptedBuffer = rsaCipher.doFinal(buffer, 0, bytesRead);
            return decryptedBuffer[0] & 0xFF;
        } catch (Exception e) {
            throw new IOException("Error reading and decrypting data: " + e.getMessage());
        }
    }
}
