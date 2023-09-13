package pre;

public enum InputType {
    // 0 Include: Alphas, '(', '&' ans so on
    INVALID,
    // 1
    SPACE,
    // 2 '+','-'
    SIGN,
    // 3 numbers
    DIGIT,
    // 4 '.'
    DOT,
    // 5 'e' 'E'
    EXPONENT
};
