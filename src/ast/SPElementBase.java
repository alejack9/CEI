package ast;

import java.util.List;

import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.SemanticError;
import util_analysis.Environment;

public interface SPElementBase {

	/**performs a semantic check for controlling that all declared variables exist
	 * @param e is the current environment where the information about existent variables is stored
	 * @return a list of the semantic problems found
	 */
	public List<SemanticError> checkSemantics(Environment e);
	
	public Type inferType(Environment e) throws SemanticError;
	
	/**
	 * performs behavioral type inference for Simple programs
	 * @param e is the current environment where the information about existent variables is stored
	 * @return the behavior of the expression
	 */
	public BTBase inferBehavior(Environment e) throws SemanticError;
}
