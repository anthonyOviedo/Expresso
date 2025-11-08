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
    : 'fun' ID '(' paramDeclList? ')' ':' typeRef '=' NEWLINE* expr comment? # funStat
    | 'let' ID (':' typeRef)? '=' NEWLINE* expr comment?      # letStat
    | PRINT expr comment?           # printStat
    | 'data' ID dataBlock comment?    # dataStat
    | expr comment?                   # exprStat
    | comment                         # commentStat
    ;

dataBlock
    : ('='?) '{' NEWLINE* constructorList? NEWLINE* '}'
    ;

constructorList
    : dataConstructor (NEWLINE* ',' NEWLINE* dataConstructor)* NEWLINE* ','?
    ;

dataConstructor
    : ID ( '(' dataFieldList? ')' | ':' typeRef )?
    ;

dataFieldList
    : dataField (NEWLINE* ',' NEWLINE* dataField)*
    ;

dataField
    : ID (':' typeRef)?
    ;

typeRef
    : 'int'
    | 'float'
    | 'double'
    | 'string'
    | 'boolean'
    | 'any'
    | ID
    ;

paramDeclList
    : paramDecl (',' paramDecl)*
    ;

paramDecl
    : ID ':' typeRef
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
    | '^' ID '(' argumentList? ')'    # ctorCall
    | expr '(' argumentList? ')'      # Call
    | expr op=('*'|'/') expr          # MulDiv
    | expr op=('+'|'-') expr          # AddSub
    | expr op=('>='|'<='|'>'|'<') expr # Relation
    | expr op=('=='|'!=') expr        # Equality
    | expr '?' expr ':' expr          # Ternary
    | params '->' expr                # Lambda
    | expr ':' typeRef                # TypeCast
    | 'match' expr (
        'with' matchCase ('|' matchCase)*
        | '{' NEWLINE* matchCase (matchCaseSeparator matchCase)* matchCaseSeparator? '}'
      ) # matchExpr
    | FLOAT_E                         # EulerFloat
    | FLOAT                           # Float
    | INT                             # Int
    | STRING                          # StringLit
    | ID                              # IdRef
    | '(' expr ')'                    # Parens
    ;

matchCase
    : ('case')? pattern matchArrow expr
    | 'default' matchArrow expr
    ;

matchArrow
    : '->'
    | '=>'
    ;

matchCaseSeparator
    : NEWLINE+ (',' NEWLINE*)?
    | ',' NEWLINE*
    ;

pattern
    : '_'                             # wildcardPattern
    | ID ('(' patternParamList? ')')? # constructorPattern
    ;

patternParamList
    : patternParam (',' patternParam)*
    ;

patternParam
    : '_'
    | ID
    ;

argumentList
    : expr (',' expr)*
    ;

params
    : lambdaParam
    | '(' (lambdaParam (',' lambdaParam)*)? ')'
    ;

lambdaParam
    : ID (':' typeRef)?
    ;

// ---- Lexer ----
PRINT: [Pp][Rr][Ii][Nn][Tt];
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
