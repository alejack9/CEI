package util_analysis.entries;

import support.MyCloneable;

public interface Entry extends MyCloneable {
	public boolean IsFunction();
	@Override
	public boolean equals(Object obj);
}
