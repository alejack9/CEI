package ast;

import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import behavioural_analysis.BTPrint;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPStmtPrint extends SPStmt {

	private SPExp exp;

	public SPStmtPrint(SPExp exp) {
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return exp.checkSemantics(e);
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		
		return new BTPrint(exp.getValue(e));
	}

	@Override
	public Type inferType() {
		if(EType.VOID.equalsTo(exp.inferType()))
			throw new TypeError("Cannot print void expression");
		return EType.VOID.getType();
	}

}
