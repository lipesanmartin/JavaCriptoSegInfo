package tests;

import utils.HashingUtil;

public class TestHash {
    public static void main(String[] args) {
        String senha = "laika";
        String senhaHash = HashingUtil.hashSha128(senha);
        System.out.println(senhaHash);
    }

}
