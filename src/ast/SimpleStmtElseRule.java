package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

public class SimpleStmtElseRule extends SimpleStmt {
	SimpleStmtBlock block;

	public SimpleStmtElseRule(SimpleStmtBlock block) {
		this.block = block;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
	
		return block.checkSemantics(e);
		
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO unimplemented
		return null;
	}

}
