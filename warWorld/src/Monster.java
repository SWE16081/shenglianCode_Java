/**
 * Monster类
 */
public class Monster {
    String name;
    int life;
    int maxLife;
    int attack;
    int defend;
    int sendExp;
    boolean isDead;
    boolean isRage;     //是否狂暴

    /**
     * 构造函数，初始化Monster参数
     * @param name
     * @param maxLife
     * @param attack
     * @param defend
     * @param sendExp
     */
    public Monster(String name,int maxLife,int attack,int defend,int sendExp){
        this.name = name;
        this.life = maxLife;
        this.maxLife = maxLife;
        this.attack = attack;
        this.defend = defend;
        this.sendExp = sendExp;
        this.isDead = false;
        this.isRage = false;
    }

    /**
     * Monster受伤方法
     * @param loseLife
     */
    public void injured(int loseLife){
        //判断攻击是否大于防御，当攻击大于防御时扣血
        if (loseLife-defend >= 0){
            System.out.println(name + ":我被扣了" + (loseLife-defend) + "点血");
            this.life -= (loseLife-defend);
        }
        //判断Monster是否死亡
        if(this.life < 0){
            System.out.println(name + "：我死了");
            isDead = true;
        }
        //判断Monster是否狂暴
        if(this.life < this.maxLife/2){
            isRage = true;
        }
    }

    /**
     * Monster打架方法
     * @param k
     */
    public void fight(Knight k){
        //判断Monster是否死亡，如果已经死亡则无法发起攻击，直接结束
        if(this.isDead){
            System.out.println(name + ":我死了。。。");
            return;
        }
        //获取Monster的攻击力，并且判断Monster是否狂暴，狂暴则攻击力翻倍
        int attack = this.attack;
        if (isRage){
            attack *= 2;
            System.out.println(name + ":我狂暴了，现在攻击力是：" + attack);
        }else {
            System.out.println(name + ":揍死你");
        }
        k.injured(attack);
    }

    public void show(){
        System.out.println("--------------------");
        System.out.println(name + ",attack:" + attack + ",defend:" + defend + ",life:" + life + ",maxlife:" + maxLife);
    }
}
