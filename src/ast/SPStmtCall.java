package ast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;
import ast.types.ArrowType;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import ast.errors.FunctionNotExistsError;
import ast.errors.ParametersMismatchError;
import ast.errors.PassedReferenceNotVarError;
import ast.errors.SemanticError;

public class SPStmtCall extends SPStmt {

	private List<SPExp> exps;
	private String ID;
	private STEntry idEntry;

	public SPStmtCall(String ID, List<SPExp> exps) {
		this.ID = ID;
		this.exps = exps;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		idEntry = e.getIDEntry(ID);
		
		Type funT = idEntry.getType();
		
		if(idEntry == null || !EType.FUNCTION.equalsTo(funT))
			toRet.add(new FunctionNotExistsError(ID));
		else {
			List<Type> params = ((ArrowType) funT).getParamTypes();
			if(exps.size() != params.size())
				toRet.add(new ParametersMismatchError(params.size(), exps.size()));
			for(int i = 0; i < Math.min(exps.size(), params.size()); i++) {
				if(params.get(i).IsRef() && !(exps.get(i) instanceof SPExpVar)) {
					toRet.add(new PassedReferenceNotVarError(i+1, ID));
				}
			}
		}
		
		for (SPExp exp : exps)
			toRet.addAll(exp.checkSemantics(e));
		
		return toRet;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type inferType() {
		if(!EType.FUNCTION.equalsTo(idEntry.getType()))
			throw new TypeError("Called ID must be a function (actual type: " + idEntry.getType() + ")");
		
		ArrowType funT = (ArrowType) idEntry.getType();
		
		List<Type> parTs = funT.getParamTypes();
		
		for(int i = 0; i < parTs.size(); i++) {
			Type parT = parTs.get(i);
			Type expT = exps.get(i).inferType(); 
			if(!parT.equals(expT))
				throw new TypeError("#" + (i + 1) + " parameter type (" + parT + ") is not equal to expression type (" + expT + ")");
		}
		
		return funT.getReturnType();
	}

}
