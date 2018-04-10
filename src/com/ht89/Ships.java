package com.ht89;

/**
 * Created by <b>Sam</b> on 7/20/2017.
 */
public class Ships {
    /**
     * Model Ship:
     *  <b>type:</b> loai tau
     *  <b>num:</b> so luong
     */
    ShipTypes type = ShipTypes.four;
     int num = 1;

    public Ships(){}

    public Ships(ShipTypes type, int num){
        this.type = type;
        this.num = num;
    }

    public ShipTypes getType() {
        return type;
    }

    public int getNum() {
        return num;
    }
}
