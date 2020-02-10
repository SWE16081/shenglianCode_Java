package NodeList;

public class Node {
    public  NodeData nodeData;//保存数据
    public  Node next;//用于保存下一个节点
    public Node() {
        this.nodeData=null;
        this.next = null;
    }

    public Node(NodeData nodeData) {
        this.nodeData = nodeData;
        this.next = null;
    }

    public NodeData getNodeData() {
        return nodeData;
    }

    public void setNodeData(NodeData nodeData) {
        this.nodeData = nodeData;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
