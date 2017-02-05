package loretips;

import java.util.List;

public class CachedTooltip {
	protected boolean dirty = true;
	
	protected int cachedLineNum;
	protected String[] cachedLines;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void apply(List parDescription) {
		if(dirty) {
			dirty = false;
			rebuildCache();
		}
		
		for(int i = 0; i < cachedLineNum; i++) {
			parDescription.add(cachedLines[i]);
		}
	}
	
	public void rebuildCache() {
		
	}
	
	public void refetchLocalization() {
		
	}
	
	public void makeDirty() {
		dirty = true;
	}
	
	public boolean isDirty() {
		return dirty;
	}
}
