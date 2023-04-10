package ayato.objects.addtions;

import ayato.objects.MyObjects;
import ayato.objects.entity.Entity;
import ayato.util.Action;

public class HPController implements ObjectAddon{
    private Action ifHPMax, ifHPHalf, ifHPNone;
    public HPController(Action ifHPMax, Action ifHPHalf, Action ifHPNone){
        this.ifHPMax = ifHPMax;
        this.ifHPHalf = ifHPHalf;
        this.ifHPNone = ifHPNone;

    }
    @Override
    public void application(MyObjects object) {
        Entity entity = (Entity) object;
        if(entity.hp == entity.mHp && ifHPMax != null){
            ifHPMax.action(entity.hp);
        }else if (entity.hp == entity.mHp / 2 && ifHPHalf != null){
            ifHPHalf.action(entity.hp);
        }else if(entity.hp <= 0 && ifHPNone != null) {
            ifHPNone.action(entity.hp);
        }
    }
}
