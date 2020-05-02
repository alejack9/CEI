package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

public class SimpleReturnExp extends SimpleStmtReturnRule {
	SimpleExp exp;

	public SimpleReturnExp(SimpleExp exp) {
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return exp.checkSemantics(e);
		
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO unimplemented
		return null;
	}

}
