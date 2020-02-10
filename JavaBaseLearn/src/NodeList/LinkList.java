package NodeList;

public class LinkList {
    Node head=new Node();//定义头结点


    /*
    * 单链表常见操作
    * */
    //头插法添加链表
    /*
    * 每新插入一个结点放在头结点的后面
    * 创建的链表为逆序
    * */
    public void headAddNodeList(String name,int number){
        NodeData nodeData=new NodeData(name,number);
        Node node=new Node(nodeData);
        node.next=head.next;
        head.next=node;
    }
    //尾插法添加链表
    /*
    * 每新插入一个结点都放在尾节点的后面
    * 创建的链表为正序
    * */
    public void tailAddNodeList(String name,int number ){
        NodeData nodeData=new NodeData(name,number);
        Node node=new Node(nodeData);
        Node temp=head;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=node;
        //node.next=null;
    }

    /*
    * 链表遍历
    * */
    public void selNodeList(){
        Node temp=head;
        int cnt=1;
        while (temp.next!=null){
            temp=temp.next;
            NodeData nodeData=temp.getNodeData();
            System.out.println("第"+cnt+"个结点_名字："+nodeData.getName()+"__数字:"+nodeData.getNumber());
            cnt++;
        }
    }

    /*
    * 将数据插入到指定位置
    *
    * */
    public void insertNodeListByindex(String name,int number,int index){
        Node temp=head;
        int cnt=1;
        while(index!=cnt&&temp.next!=null){
            temp=temp.next;
            cnt++;
        }
        NodeData nodeData=new NodeData(name,number);
        Node node=new Node(nodeData);
        node.next=temp.next;
        temp.next=node;
    }
    /*
    * 删除某个位置的链表
    * */
    public void deleteNodeListByindex(int index){

        int cnt=1;
        Node pre=head;//记录删除的前一个结点
        Node cur=pre.next;//记录删除的当前节点
        while(index!=cnt&&cur.next!=null){//找到要删除的结点
            pre=cur;//记录要删除结点的前一个结点
            cur=pre.next;
            cnt++;
        }
            pre.next=cur.next;
    }

    /*
    * 返回链表长度
    * */
    public void getNodeListLen(){
        int cnt=0;
        Node temp=head;
        while(temp.next!=null){
            temp=temp.next;
            cnt++;
        }
        System.out.println("链表长度为："+cnt);
    }
    /*
    * 头插法链表反转
    * 就地反转 别不1->2->3 将1，2指针由2指向1
    * head 1 2 3
    * head 2 1 3
    * head 3 2 1
    * */
    public void reverseNodeList(){
        Node cur=head.next;
        Node n=cur.next;
        //头插法反转结点
        Node Phead=new Node();//反转头结点
        while(cur.next!=null){
            cur.next=n.next;
            n.next=head.next;
            head.next=n;
            n=cur.next;
        }
    }


    /*
    * 递归反转
    * */
//    public Node2 reverseNodeByrecursive(Node2 cur){
//       if(cur.next==null)
//           return cur;
//        Node2 new_cur=reverseNodeByrecursive(cur.next);
//        new_cur.next=cur;
//        cur.next=null;
//        return cur;
//    }
    public Node reverseList(Node head) {
        if (head == null || head.next == null)
            return head;
        Node next = head.next;
        Node new_head = reverseList(next);
        next.next = head;
        head.next = null;
        return new_head;
    }
}
