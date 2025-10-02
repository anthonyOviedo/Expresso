grammar Expr;

// ---- Parser ----
prog: stat* EOF ;

stat
    : 'let' ID '=' expr NEWLINE       # letStat
    | 'print' expr NEWLINE            # printStat
    | expr NEWLINE                    # exprStat
    | NEWLINE                         # blankStat
    ;

expr
    : '-' expr                        # unaryMinus
    | expr '**' expr                  # power              
    | expr op=('*'|'/') expr          # MulDiv
    | expr op=('+'|'-') expr          # AddSub
    | expr '?' expr ':' expr          # ternary
    | params '->' expr                # lambda
    | INT                             # int
    | ID                              # idRef
    | '(' expr ')'                    # parens
    ;

params
    : ID
    | '(' (ID (',' ID)*)? ')'
    ;

// ---- Lexer ----
LET: 'let' ;
PRINT: 'print' ;

ID: [a-zA-Z_] [a-zA-Z_0-9]* ;

INT: [0-9]+ ;

NEWLINE: ('\r'? '\n') ;

WS: [ \t\r]+ -> skip ;

LINE_COMMENT: '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT: '/*' .*? '*/' -> skip ;

