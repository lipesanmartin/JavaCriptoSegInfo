package tests;

import utils.HashingUtil;

public class TestHash {
    public static void main(String[] args) {
        String senha = "laikaboss";
        String senhaHash = HashingUtil.hashSha128(senha);
        System.out.println(senhaHash);
    }

}
