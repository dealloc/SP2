package be.ehb.spg3.encryption;

import be.ehb.spg3.contracts.encryption.Encryptor;

/**
 * Created by unityx on 11/27/15.
 */
public class DummyCryptor implements Encryptor {

    @Override
    public String encrypt(String str) {
        int[] chain = {
                195, 45665, 3455, 3, 909355, 257, 863
        };
        String result = "";
        int l = str.length();
        char ch;
        int ck = 0;
        for (int i = 0; i < l; i++) {
            if (ck >= chain.length - 1) ck = 0;
            ch = str.charAt(i);
            ch += chain[ck];
            result += ch;
            ck++;
        }
        return result;
    }

    @Override
    public String decrypt(String str) {
        int[] chain = {
                195, 45665, 3455, 3, 909355, 257, 863
        };
        String result = "";
        int l = str.length();
        char ch;
        int ck = 0;
        for (int i = 0; i < l; i++) {
            if (ck >= chain.length - 1) ck = 0;
            ch = str.charAt(i);
            ch -= chain[ck];
            result += ch;
            ck++;
        }
        return result;
    }

    @Override
    public String hash(String str) {
        return null;
    }
}
