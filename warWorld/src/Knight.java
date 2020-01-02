/**
 * Knight类
 */
public class Knight {
    String name;
    int maxLife;
    int life;
    int attack;
    int defend;
    int cirticalRate;   //暴击几率
    int cirticalVal;    //暴击值
    int missRate;       //闪避几率
    int exp;            //经验值
    int level;          //等级
    int levelExp;       //下一级所需经验值
    boolean isDead;

    /**
     * 构造函数，初始化Knight参数
     * @param name
     */
    public Knight(String name){
        this.name = name;
        maxLife = 100;
        life = 100;
        attack = 25;
        defend = 15;
        cirticalRate = 10;
        cirticalVal = 100;
        missRate = 8;
        exp = 0;
        level = 1;
        isDead = false;
        levelExp = 30;
    }

    /**
     * 计算闪避几率和暴击几率的方法
     * @param rate
     * @return
     */
    public boolean rateCheck(int rate){
        int ran = (int)(Math.random()*100);
        if(ran < rate){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 受伤判定
     * @param loseLife
     */
    public void injured(int loseLife){
        //判断是否闪避成功
        boolean isMiss = rateCheck(this.missRate);
        if(isMiss){     //闪避成功
            System.out.println(name + ":打不到我，气不气！！");
        }else {
            if(loseLife > defend){   //攻击力大于防御
                System.out.println(name + ":糟糕，扣了" + (loseLife-defend) +"点血");
                this.life -= (loseLife-defend);
            }else {                 //攻击力小于防御
                System.out.println(name + ":就这点攻击力，打不破我的防御");
            }
        }
        if(this.life<0){
            this.isDead = true;
            System.out.println(name + "：就这样结束了！");
        }
    }

    /**
     * 检查是否升级
     */
    public void checkUpgrade(){
        if(this.exp >= levelExp){
            upgrade();
            System.out.println(name + ":我升级啦！现在" + level + "级,攻击力：" + attack);
        }
    }

    /**
     * 升级方法
     */
    public void upgrade(){
        System.out.println("当当当！" + name + "升级了！");
        attack *=1.2;
        defend *=1.2;
        maxLife *=1.2;
        life = maxLife;
        cirticalRate *= 1.5;
        cirticalVal *= 1.2;
        missRate *= 1.2;
        levelExp *=1.5;
        level++;
        exp = 0;
    }
    public void show(){
        System.out.println("------------------------------------------");
        System.out.println(name + ",level:" + level + ",attack:" + attack + ",defend" + defend + ",life:" + life + ",maxlife:" + maxLife);
    }


    /**
     * Knight打架方法
     * @param m
     */
    public void fight(Monster m){
        //判断Monster和Knight是否死亡，如果没有死亡则循环执行fight方法
        while(!this.isDead && !m.isDead){
            int attack = this.attack;
            boolean isCirtical = rateCheck(cirticalRate);
            //判断是否暴击,根据是否暴击来确定攻击值
            if(isCirtical){
                System.out.println(name + "：暴击啦！！！一刀999，砍死你！！！");
                attack = attack + (cirticalVal/100)*attack;
            }else {
                System.out.println(name + ":糟糕！没出暴击。。。。");
            }
            m.injured(attack);      //让Monster受伤
            if(!m.isDead){          //判断Monster是否死亡，如没有死亡则反击
                m.fight(this);
            }else {                 //如果Monster死亡，Knight会获得经验值，并且检查是否升级
                this.exp += m.sendExp;
                checkUpgrade();
            }
            System.out.println("-----------------------------");
            System.out.println(name + "血量值：" + this.life);
            System.out.println(m.name + "血量值：" + m.life);
            System.out.println("-----------------------------");
        }
    }

}
