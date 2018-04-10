package com.ht89;

import java.util.Random;
/**
 * Created by <b>Sam</b> on 7/20/2017.
 */
public class PreSetting {
    String[][] map = new String[11][11];
    String[][] mapOfShip = new String[12][12];
    String[] rowChar = {"S","A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    void initialize_Game(){
        begining();
        mapOfShip = random_Ships();
    }
// Khởi tạo: 1 map trống, 1 mapOfShip để sắp tàu
    void begining(){
        //In hàng kí tự
        for (int i = 0; i < map.length; i++) {
            map[i][0] = rowChar[i];
            System.out.print(map[i][0] + "  ");
        }
        System.out.println("");
        //In map
        for (int i = 1; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                //Cột đầu tiên in số từ 1-10
                if (j == 0) {
                    //Chỉ là canh cho ngay thoai
                    if (i == 10) {
                        map[0][i] = Integer.toString(i);
                        System.out.print(map[0][i] + " ");
                    }
                    else {
                        map[0][i] = Integer.toString(i);
                        System.out.print(map[0][i] + "  ");
                    }
                //Khởi tạo các ô là -
                } else {
                    map[j][i] = "-";
                    System.out.print(map[j][i] + "  ");
                }
            }
            System.out.println("");
        }
        //Copy phần - từ map sang mapOfShip
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map.length; j++) {
                mapOfShip[i][j] = map[i][j];
            }
        }
    }

//Kiểm tra vị trí, notempty = true thì  đặt thành công
    boolean check(int x, int y, ShipTypes type, int dir){
        String[][] temp = new String[12][12];
        boolean notempty = true; //biến kiểm tra kết quả đặt tàu
        //copy mapOfShip qua temp
        for (int i = 1; i < mapOfShip.length; i++) {
            for (int j = 1; j < mapOfShip.length; j++) {
                temp[i][j] = mapOfShip[i][j];
            }
        }
        //DỌC
        if (dir==0) {
            try {//Bắt ngoại lệ nếu tọa độ tàu vượt tọa độ map => tàu đặt không thành công
                for (int i = x - 1; i <= x + type.getY(); i++) {
                    for (int j = y - 1; j <= y + type.getX(); j++) {
                        //Nếu vị trí là X hoặc * thì bằng *
                        if (temp[i][j] == "-" || temp[i][j] == "*") {
                            temp[i][j] = "*";
                        }
                        //Nếu vị trí null, kiểm tra xem vị trí tàu có vào null không, nếu có thì empty = false
                        else if (temp[i][j] == null) {
                            for (int k = x; k < x + type.getY(); k++) {
                                if(temp[i][j]==mapOfShip[k][y]) notempty = false;
                                else temp[i][j] = "*";
                            }
                        }
                        // Nếu vị trí s thì không đặt tàu được,
                        // đưa temp về trạng thái ban đầu của mapOfShip
                        else if (temp[i][j] == "S") {
                            for (int k = 1; k < mapOfShip.length; k++) {
                                for (int m = 1; m < mapOfShip.length; m++) {
                                    temp[k][m] = mapOfShip[k][m];
                                }
                            }
                            notempty = false;
                        }
                    }
                    //đặt tàu không thành công thì dẹp
                    if (notempty == false) break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                notempty = false;
            }
            //Nếu đặt tàu thành công:
            if (notempty == true) {
                //copy temp sang mapOfShip
                for (int k = 1; k < temp.length; k++) {
                    for (int m = 1; m < temp.length; m++) {
                        mapOfShip[k][m] = temp[k][m];
                    }
                }
                //vị trí tàu đánh dấu là s
                for (int i = x; i < x + type.getY(); i++) {
                    mapOfShip[i][y] = "S";
                }
            }
        }
        //NGANG
        else if (dir == 1) {
            try {//Bắt ngoại lệ nếu tọa độ tàu vượt tọa độ map => tàu đặt không thành công
                for (int i = x - 1; i <= x + type.getX(); i++) {
                    for (int j = y - 1; j <= y + type.getY(); j++) {
                        //Nếu vị trí là X hoặc * thì bằng *
                        if (temp[i][j] == "-" || temp[i][j] == "*") {
                            temp[i][j] = "*";
                        }
                        //Nếu vị trí null, kiểm tra xem vị trí tàu có vào null không, nếu có thì empty = false
                        else if (temp[i][j] == null) {
                            for (int k = x; k < x + type.getY(); k++) {
                                if(temp[i][j]==mapOfShip[k][y]) notempty = false;
                                else temp[i][j] = "*";
                            }}
                        // Nếu vị trí s thì không đặt tàu được
                        // đưa temp về trạng thái ban đầu của mapOfShip
                        else if (temp[i][j] == "S") {
                            for (int k = 1; k < mapOfShip.length; k++) {
                                for (int m = 1; m < mapOfShip.length; m++) {
                                    temp[k][m] = mapOfShip[k][m];
                                }
                            }
                            notempty = false;
                        }
                    }
                    //đặt tàu không thành công thì dẹp
                    if (notempty == false) break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                notempty = false;
            }
            //Nếu đặt tàu thành công:
            if (notempty == true) {
                //Copy temp sang mapOfShip
                for (int k = 1; k < temp.length; k++) {
                    for (int m = 1; m < temp.length; m++) {
                        mapOfShip[k][m] = temp[k][m];
                    }
                }
                //Vị trí tàu đánh dấu là s
                for (int i = y; i < y + type.getY(); i++) {
                    mapOfShip[x][i] = "S";
                }
            }
        }
        return notempty;
    }
// Random tàu, trả về mapOfShip
    String[][] random_Ships() {
        Random r = new Random();
        //Khai báo các tàu
        Ships four = new Ships(ShipTypes.four, 1);
        Ships three = new Ships(ShipTypes.three, 2);
        Ships two = new Ships(ShipTypes.two, 3);
        Ships one = new Ships(ShipTypes.one, 4);
        int total = 10; //tổng số tàu là 10

        //Khi còn tàu:
        while (total > 0) {
            int r_type = r.nextInt(4) + 1;
            int x = r.nextInt(10) + 1;
            int y = r.nextInt(10) + 1;
            int dir = r.nextInt(2);
            switch (r_type){
                //Tàu 1*1
                case 1: {
                    //Nếu số tàu 1 là 0 thì random 3 loại còn lại (để giảm số lần lặp chứ hông gì)
                    if (one.num==0){ r_type = r.nextInt(3) + 2;}
                    //Nếu còn tàu 1
                    else {
                        //kiếm tra nếu đặt đc tàu vào map thì giảm số tàu 1 và tổng tàu, không thì thôi
                        if (check(x, y, ShipTypes.one, dir) == true) {
                            one.num--;
                            total--;
                        }
                        break;
                    }
                }
                //Tàu 1*2
                case 2: {
                    //Hết tàu thì random lại
                    if (two.num==0){ r_type = r.nextInt(4) + 1;}
                    //Còn tàu thì check
                    else {
                        if (check(x, y, ShipTypes.two, dir) == true) {
                            two.num --;
                            total--;
                        }
                        break;
                    }
                }
                //Tàu 1*3
                case 3: {
                    if (three.num==0){ r_type = r.nextInt(4) + 1;}
                    else {
                        if (check(x, y, ShipTypes.three, dir) == true) {
                            three.num --;
                            total--;
                        }
                        break;
                    }
                }
                //Tàu 1*4
                case 4: {
                    if (four.num==0){ r_type = r.nextInt(4) + 1;}
                    else {
                        if (check(x, y, ShipTypes.four, dir) == true) {
                            four.num --;
                            total--;
                        }
                        break;
                    }
                }
            }
        }
        return mapOfShip;
    }
}
