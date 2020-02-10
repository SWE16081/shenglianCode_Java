import NodeList.LinkList;
import NodeList.Node;
import NodeList.NodeData;

public class ListTest {
    public static void main(String[] args){
        LinkList linkList=new LinkList();
        linkList.tailAddNodeList("test",1231);
        linkList.tailAddNodeList("test3",1211);
        linkList.tailAddNodeList("test4",1211);
        linkList.tailAddNodeList("test5",1211);
//        linkList.selNodeList();
        linkList.insertNodeListByindex("test2",1231,2);

        linkList.selNodeList();
//        linkList.deleteNodeListByindex(2);
//        linkList.selNodeList();
//        linkList.deleteNodeListByindex(2);
//        linkList.selNodeList();
////        linkList.deleteNodeListByindex(1);
////        linkList.selNodeList();
//        linkList.getNodeListLen();
//        linkList.reverseNodeList();
//        linkList.selNodeList();
//        Node2 head=new Node2();

}
}
