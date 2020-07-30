package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.TypeErrorsStorage;

public abstract class SPExpBinArithm extends SPExpBin {
	
	protected SPExpBinArithm(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}
	
	@Override
	public final Type inferType() {
		Type leftSideT = leftSide.inferType();
		if(!EType.INT.equalsTo(leftSideT))
			TypeErrorsStorage.addError(new TypeError("Left expression in operation \"" + this.getOp() + "\" must return int. It returns \"" + leftSideT + "\" instead", leftSide.line, leftSide.column));
		Type rightSideT = rightSide.inferType();
		if(!EType.INT.equalsTo(rightSideT))
			TypeErrorsStorage.addError(new TypeError("Right expression in operation \"" + this.getOp() + "\" must return int. It returns \"" + rightSideT + "\" instead", rightSide.line, rightSide.column));
		
		return EType.INT.getType();
	}


	@Override
	public final void _codeGen(int nl, CustomStringBuilder sb) { String prev = ""; for(int i = 0; i <= nl; i++) prev += "\t";
		sb.newLine(prev, "# SpExpBinArithm ", cGenOperator());
		leftSide._codeGen(nl, sb);
		sb.newLine(prev, "push $a0\r\n");
		rightSide._codeGen(nl, sb);
		sb.newLine(prev, "lw $t1 0($sp)");
		sb.newLine(prev, cGenOperator(), " $a0 $t1 $a0");
		sb.newLine(prev, "pop");
	}

	
	public abstract String cGenOperator();
}
