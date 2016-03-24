package gessi.ossecos.istarparser;

public class NodeIS {
    
    private String id;
    private String name;
    private NodeType type;
    private String iStarType;
    private String bundary;

    public String getBundary() {
		return bundary;
	}

	public void setBundary(String bundary) {
		this.bundary = bundary;
	}

	public String getiStarType() {
        return iStarType;
    }

    public void setiStarType(String iStarType) {
        this.iStarType = iStarType;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }
    
    
}
