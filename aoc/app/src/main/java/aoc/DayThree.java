package aoc;

import java.util.List;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.datatransfer.StringSelection;

enum TokenType {
    DO, // 'do()'
    DONT, // 'don't()'
    MUL, // 'mul'
    OPEN_PAREN, // '('
    CLOSE_PAREN, // ')'
    COMMA, // ','
    NUMBER, // numeric literals
    EOF // end of input
}

class Token {
    TokenType type;
    String value;

    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String toString() {
        return type + "('" + value + "')";
    }
}

class Lexer {
    private int pos = 0;
    private String input;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (pos < input.length()) {
            char current = input.charAt(pos);

            if (Character.isWhitespace(current)) {
                pos++;
                continue;
            }

            if (matchKeyword("do()")) {
                tokens.add(new Token(TokenType.DO, "do()"));
            } else if (matchKeyword("don't()")) {
                tokens.add(new Token(TokenType.DONT, "don't()"));
            } else if (matchKeyword("mul")) {
                tokens.add(new Token(TokenType.MUL, "mul"));
            } else if (current == '(') {
                tokens.add(new Token(TokenType.OPEN_PAREN, "("));
                pos++;
            } else if (current == ')') {
                tokens.add(new Token(TokenType.CLOSE_PAREN, ")"));
                pos++;
            } else if (current == ',') {
                tokens.add(new Token(TokenType.COMMA, ","));
                pos++;
            } else if (Character.isDigit(current)) {
                tokens.add(readNumber());
            } else {
                pos++;
                // throw new RuntimeException("Unexpected character: " + current);
            }
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    private boolean matchKeyword(String keyword) {
        if (input.startsWith(keyword, pos)) {
            pos += keyword.length();
            return true;
        }
        return false;
    }

    private Token readNumber() {
        int start = pos;
        while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
            pos++;
        }
        return new Token(TokenType.NUMBER, input.substring(start, pos));
    }
}

class Parser {
    private int index = 0;
    private List<Token> tokens;
    boolean isOperationsEnabled = true;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token peek() {
        return tokens.get(index);
    }

    private Token consume(TokenType expectedType) {
        Token current = tokens.get(index);
        if (current.type != expectedType) {
            throw new RuntimeException("Expected " + expectedType + " but got " + current.type);
        }
        index++;
        return current;
    }
    
    private void skip() {
        while (index < tokens.size()
                && peek().type != TokenType.MUL
                && peek().type != TokenType.DONT
                && peek().type != TokenType.DO
                && peek().type != TokenType.EOF) {
            index++;
        }
    }

    public int parse() {
        int result = 0;
        while (index < tokens.size() && peek().type != TokenType.EOF) {
            Token currentToken = peek();

            switch (currentToken.type) {
                case DONT:
                    consume(TokenType.DONT);
                    isOperationsEnabled = false;
                    break;
                case DO:
                    consume(TokenType.DO);
                    isOperationsEnabled = true;
                    break;
                case MUL:
                    if (isOperationsEnabled) {
                        result += parseMultiplicationExpression();
                    } else {
                        // Skip or handle as needed
                        consume(TokenType.MUL);
                        skip(); // skip invalid expression
                    }
                    break;
                default:
                    // Skip unrecognized tokens
                    skip();
                    break;
            }
        }
        return result;
    }

    private int parseMultiplicationExpression() {
        int productSum = 0;
        // Expect MUL token
        consume(TokenType.MUL);
        // Expect OPEN_PAREN
        if (peek().type != TokenType.OPEN_PAREN) {
            skip();
            return 0;
        }
        consume(TokenType.OPEN_PAREN);

        // Parse first number
        if (peek().type != TokenType.NUMBER) {
            skip();
            return 0;
        }
        int number_x = Integer.parseInt(consume(TokenType.NUMBER).value);

        // Expect COMMA
        if (peek().type != TokenType.COMMA) {
            skip();
            return 0;
        }
        consume(TokenType.COMMA);

        // Parse second number
        if (peek().type != TokenType.NUMBER) {
            skip();
            return 0;
        }
        int number_y = Integer.parseInt(consume(TokenType.NUMBER).value);

        // Expect CLOSE_PAREN
        if (peek().type != TokenType.CLOSE_PAREN) {
            skip();
            return 0;
        }
        consume(TokenType.CLOSE_PAREN);

        // Calculate product
        productSum += number_x * number_y;
        return productSum;
    }
}

public class DayThree {

    public int getResultA() throws IOException, InterruptedException {
        int result = 0;
        String data = new String(Files.readAllBytes(Paths.get("aoc/app/src/main/resources/InputThree.txt")));

        Lexer lexer = new Lexer(data);
        List<Token> tokens = lexer.tokenize();
        Parser parser = new Parser(tokens);

        result = parser.parse();

        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(String.valueOf(result)), null);

        return result;
    }

    public int getResultB() throws IOException {
        int result = 0;
        String data = new String(Files.readAllBytes(Paths.get("aoc/app/src/main/resources/InputThree.txt")));

        Lexer lexer = new Lexer(data);
        List<Token> tokens = lexer.tokenize();
        tokens.forEach(token -> System.out.println(token));
        Parser parser = new Parser(tokens);

        result = parser.parse();

        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(new StringSelection(String.valueOf(result)), null);

        return result;
    }
}
