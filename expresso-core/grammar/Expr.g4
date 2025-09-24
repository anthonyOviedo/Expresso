grammar Expr;


// Punto de entrada: cero o más sentencias terminadas por NEWLINE
prog : stat* EOF ;


// Una sentencia puede ser una expresión -> imprimir, o línea vacía
stat : expr NEWLINE         # printExpr
       | NEWLINE            # blank
;


// Expresiones con precedencia y unario '-'
expr :    '-' expr 					# unaryMinus
		| expr op=('*'|'/') expr 	# MulDiv
		| expr op=('+'|'-') expr 	# AddSub
		| INT # int
		| '(' expr ')' 				# parens
;


// LEXER
INT : [0-9]+ ;
NEWLINE: ('\r'? '\n') ;
WS : [ \t]+ -> skip ;