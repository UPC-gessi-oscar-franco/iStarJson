package gessi.ossecos.istarparser;

public class EdgeIS {
    private String sNode;
    private String fNode;
    private String label;
    private String type;

    public EdgeIS() {
        label="";
    }

    public String getsNode() {
        return sNode;
    }

    public void setsNode(String sNode) {
        this.sNode = sNode;
    }

    public String getfNode() {
        return fNode;
    }

    public void setfNode(String fNode) {
        this.fNode = fNode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String string) {
        this.type = string;
    }
    
    
}

