package NodeList2;



public class Node2 {
    public NodeData2 nodeData;//保存数据
    public Node2 next;//用于保存下一个节点
//    public Node2() {
//        this.nodeData=null;
//        this.next = null;
//    }

    public Node2(NodeData2 nodeData) {
        this.nodeData = nodeData;

    }

    public NodeData2 getNodeData() {
        return nodeData;
    }

    public void setNodeData(NodeData2 nodeData) {
        this.nodeData = nodeData;
    }

    public Node2 getNext() {
        return next;
    }

    public void setNext(Node2 next) {
        this.next = next;
    }
}
