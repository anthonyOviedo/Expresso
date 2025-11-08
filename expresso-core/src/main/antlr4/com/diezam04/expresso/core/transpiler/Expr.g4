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
    : 'fun' ID '(' paramDeclList? ')' ':' type '=' expr comment? # funStat
    | 'let' ID '=' expr comment?      # letStat
    | 'print' expr comment?           # printStat
    | expr comment?                   # exprStat
    | comment                         # commentStat
    ;

paramDeclList
    : paramDecl (',' paramDecl)*
    ;

paramDecl
    : ID ':' type
    ;

type
    : 'int'
    | 'float'
    | 'string'
    ;

comment
    : LINE_COMMENT
    | BLOCK_COMMENT
    ;

expr
    : '-' expr                        # unaryMinus
    | expr '**' expr                  # power
    | expr '(' argumentList? ')'      # Call
    | expr op=('*'|'/') expr          # MulDiv
    | expr op=('+'|'-') expr          # AddSub
    | expr op=('>='|'<='|'>'|'<') expr # Relation
    | expr op=('=='|'!=') expr        # Equality
    | expr '?' expr ':' expr          # Ternary
    | params '->' expr                # Lambda
    | FLOAT_E                         # EulerFloat
    | FLOAT                           # Float
    | INT                             # Int
    | STRING                          # StringLit
    | ID                              # IdRef
    | '(' expr ')'                    # Parens
    ;

argumentList
    : expr (',' expr)*
    ;

params
    : lambdaParam
    | '(' (lambdaParam (',' lambdaParam)*)? ')'
    ;

lambdaParam
    : ID (':' type)?
    ;

// ---- Lexer ----
ID: [a-zA-Z_] [a-zA-Z_0-9]*;
FLOAT_E
    : (DIGITS '.' DIGITS* | DIGITS '.' | DIGITS | '.' DIGITS) 'e'
    ;
FLOAT
    : DIGITS '.' DIGITS* EXPONENT?
    | '.' DIGITS EXPONENT?
    ;
INT: DIGITS;
STRING: '"' ( '\\' [btnr"\\] | ~["\\\r\n] )* '"';
NEWLINE: ('\r'? '\n');
WS: [ \t\r]+ -> skip;
LINE_COMMENT: '//' ~[\r\n]*;
BLOCK_COMMENT: '/*' .*? '*/';

fragment DIGITS: [0-9]+;
fragment EXPONENT: [eE] [+\-]? DIGITS;
