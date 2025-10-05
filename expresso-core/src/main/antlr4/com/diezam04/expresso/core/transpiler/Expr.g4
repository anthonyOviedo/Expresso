grammar Expr;

// ---- Parser ----
prog
    : NEWLINE*
      (stat NEWLINE+)*
      stat?
      NEWLINE*
      EOF
    ;

stat
    : 'let' ID '=' expr comment?      # letStat
    | 'print' expr comment?           # printStat
    | expr comment?                   # exprStat
    | comment                         # commentStat
    ;

comment
    : LINE_COMMENT
    | BLOCK_COMMENT
    ;

expr
    : '-' expr                        # unaryMinus
    | expr '**' expr                  # power
    | expr op=('*'|'/') expr          # MulDiv
    | expr op=('+'|'-') expr          # AddSub
    | expr '(' argumentList? ')'      # Call
    | expr '?' expr ':' expr          # Ternary
    | params '->' expr                # Lambda
    | INT                             # Int
    | ID                              # IdRef
    | '(' expr ')'                    # Parens
    ;

argumentList
    : expr (',' expr)*
    ;

params
    : ID
    | '(' (ID (',' ID)*)? ')'
    ;

// ---- Lexer ----
ID: [a-zA-Z_] [a-zA-Z_0-9]*;
INT: [0-9]+;
NEWLINE: ('\r'? '\n');
WS: [ \t\r]+ -> skip;
LINE_COMMENT: '//' ~[\r\n]*;
BLOCK_COMMENT: '/*' .*? '*/';
