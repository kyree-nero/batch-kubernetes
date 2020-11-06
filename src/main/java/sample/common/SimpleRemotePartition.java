package sample.common;

import java.io.Serializable;

public class SimpleRemotePartition implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5912772271498080089L;
	private int fromId;
	private int toId;
	private String groupName;
	public int getFromId() {
		return fromId;
	}
	public void setFromId(int fromId) {
		this.fromId = fromId;
	}
	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Override
	public String toString() {
		return "SimpleRemotePartition [fromId=" + fromId + ", toId=" + toId + ", groupName=" + groupName + "]";
	}
	
	
}
