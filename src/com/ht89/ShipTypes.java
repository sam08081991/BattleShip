package com.ht89;

/**
 * Created by <b>Sam</b> on 7/20/2017.
 */
public enum ShipTypes {
    // 4 loai tau, a va b la kich thuoc
    four(1,4), three(1,3), two(1,2), one(1,1);

    private int a,b;
    ShipTypes(int a, int b ){
        this.a = a;
        this.b = b;
    }

    public int getX() {
        return a;
    }

    public int getY() {
        return b;
    }

}
