package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TokenBag {
    private final List<Token> tokens;
    private final Random random;

    public TokenBag(int n) {
        tokens = new ArrayList<>();
        random = new Random();

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                tokens.add(new Token(i, j));
            }
        }
    }

    public synchronized Token drawToken() {
        if (tokens.isEmpty()) {
            return null;
        }

        int index = random.nextInt(tokens.size());
        return tokens.remove(index);
    }
}