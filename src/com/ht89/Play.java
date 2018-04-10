package com.ht89;
import java.util.Scanner;

/**
 * Created by <b>Sam</b> on 7/23/2017.
 */
public class Play {
    PreSetting newmap = new PreSetting();
    Scanner sc = new Scanner(System.in);
    int n1,n2, guess=0, bingo=0;
    String[][] mapOfUser = new String[12][12];
    String s1,s2;//s1 la phan chu, s2 la phan so
    String[] chars = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    String[] num = {"1", "2","3","4","5","6","7","8","9"};
    String ten = "10";


    public void Play(){
        newmap.initialize_Game();
        for (int i = 1; i < newmap.map.length; i++) {
            for (int j = 1; j < newmap.map.length; j++) {
                mapOfUser[i][j] = newmap.map[i][j];
            }
        }
        System.out.println(" ");
        System.out.println("Map of ships:");
        print_Map(newmap.mapOfShip);
        System.out.println(" ");
        System.out.println("Nhập tọa độ:");
        String input = sc.nextLine();
        boolean notend = true;
        while(notend){
            boolean testIQ = false;
            while (testIQ ==false){
                if (check_input(input)==false){
                    System.out.println("Nhập bậy rồi~ Ex: b1, B1, 1b, 1B (from A-J, 1-10)");
                    System.out.println("Nhập tọa độ:");
                    input = sc.nextLine();
                }
                else testIQ = true;
            }
            if(check_Result(n1,n2)==true){
                if(win(mapOfUser)){
                    System.out.println("Ấu dê! Nghỉ chơi~");
                    System.out.println("Bạn đã đoán tổng cộng " + guess + " lần :3");
                    print_Map(mapOfUser);
                    notend = false;
                }
                else {
                    System.out.println("************************************");
                    System.out.println("Map of ships: \n");
                    print_Map(newmap.mapOfShip);
                    System.out.println("Map of player: \n");
                    print_Map(mapOfUser);
                    System.out.println(" ");
                    System.out.println("Bạn đã đoán " + guess + " và trúng " + bingo + " :3");
                    System.out.println("Nhập tọa độ:");
                    input = sc.nextLine();
                }
            }
            else {
                System.out.println("************************************");
                System.out.println("Nhập tọa độ:");
                input = sc.nextLine();
            }
        }
    }

    /**Check tọa độ user nhập có hợp lệ hay không
     * lén lén lưu tọa độ gồm 2 phần chữ <b>s1</b> và số <b>s2</b>
     */
    boolean check_input(String input){
        String check = input.toUpperCase();
        boolean test = true;
        //Xet do dai chuoi input
        switch(check.length()){
            //Bang 2
            case 2: {
                //s1 la ki tu dau tien cua chuoi
                s1 = check.substring(0,1);
                for (String st:chars) {
                    //Neu s1 la chu cai
                    if (s1.equals(st)){
                        //s2 se la ki tu thu 2 cua chuoi, la so
                        s2 = check.substring(1);
                        //neu s2 thuoc tu 1-9 thi test = true -> break
                        //  ngc lai test = false
                        for (String number:num) {
                            if (s2.equals(number)) {
                                test = true;
                                break;
                            }
                            else test = false;
                        }
                        //Neu test = true -> break
                        if (test) break;
                    }
                    else test = false;
                }

                if (test==false) {
                    // Neu s1 la so, s2=s1 de s2 la so
                    s2=s1.substring(0);
                    for (String number:num) {
                        //s2 thuoc 1-9
                        if(s2.equals(number)){
                            //s1 la ki tu thu 2, la chu
                            s1 = check.substring(1);
                            //Neu s1 thuoc mang chars thi test = true ->break
                            // ngc lai test = false
                            for (String st:chars) {
                                if (s1.equals(st)) {
                                    test = true;
                                    break;
                                }
                                else test = false;
                            }
                            //Neu test =true -> break
                            if (test) break;
                        }
                        else test = false;
                    }
                }
                break;
            }
            //Bang 3
            case 3:{
                //s1 bang 2 ki tu dau (nghia la so 10)
                s1 = check.substring(0,2);
                //neu s1 bang 10
                if (s1.equals(ten)){
                    //s2=s1 de d2 la so
                    s2=s1.substring(0);
                    //s1 la chu
                    s1 = check.substring(2);
                    //Neu s1 thuoc mang chars, test = true -> break
                    //ngc lai test = false
                    for (String st:chars) {
                        if (s1.equals(st)) {
                            test = true;
                            break;
                        }
                        else test = false;
                    }
                }
                //Neu s1 != 10
                else {
                    //s1 la chu
                    s1 = check.substring(0,1);
                    //Neu s1 thuoc mang chars
                    for (String st:chars) {
                        if(s1.equals(st)){
                            //s2 phai la 10, test = true ->break
                            //ngc lai test = false
                            s2=check.substring(1);
                            if(s2.equals(ten)) {
                                test = true;
                                break;
                            }
                            else test = false;
                        }
                        //neu s1 khong la 10, khong la chu thi test = false
                        else test = false;
                    }
                }
                break;
            }
            default: test = false;
        }
        if (test){
            convert(s1,s2);
//            System.out.println("s1 = " + s1+" va "+"s2 = " + s2);
//            System.out.println("n1 = " + n1+" va "+"n2 = " + n2);
        }
//        System.out.println(test);
        return test;
    }

    /**
     * Hàm chuyển String sang int
     */
    void convert(String s1, String s2){
        for (int i = 0; i < chars.length ; i++) {
            if (s1.equals(chars[i])) n1 = i+1;
        }
        for (int i = 0; i < num.length ; i++) {
            if (s2.equals(num[i])) n2 = i+1;
        }
        if (s2.equals(ten)) n2 = 10;
    }
    /**
     * Hàm kiểm tra tàu đã bị bắn chưa, neu da bi ban thi tra ve huong tau
     */
    String check_Ship(int n1, int n2){
        //Check 2 ben, neu check 1 va check2 true thì checkship true
        boolean check1 = false, check2=false, checkship = false;
        String dir = null;//hướng tàu
        //Hướng ngang
            //Nếu ô bên trái, bên phải là S hoac $ => ngang
        if (newmap.mapOfShip[n1-1][n2]=="S"
                ||newmap.mapOfShip[n1+1][n2]=="S"
                ||newmap.mapOfShip[n1-1][n2]=="$"
                ||newmap.mapOfShip[n1+1][n2]=="$"){
            //Dò mìn:
            //Dò bên phải đi từ n1 đến 10
            for (int i=n1; i<11; i++) {
                //Nếu ô bên phải i là S thì tàu chưa bị bắn
                if (newmap.mapOfShip[i+1][n2] == "S") break;
                //Nếu ô bên phải i là * hoặc null hoặc # thì các ô bên phải n1 đã bị bắn hết -> check1 = true
                else if (newmap.mapOfShip[i+1][n2] == "*" ||newmap.mapOfShip[i+1][n2] == null||newmap.mapOfShip[i+1][n2] == "#") {
                    check1 = true;
                    break;
                }
            }
            //Nếu check1 = true(bên phải), xét tiếp check2(bên trái)
            if (check1) {
                //Dò bên trái từ n1 về 1
                for (int i = n1; i>0 ; i--) {
                    //Nếu ô bên trái i là S thì tàu chưa bị bắn
                    if (newmap.mapOfShip[i-1][n2] == "S") break;
                    //Nếu ô bên trái i là * hoặc null hoặc # thì các ô bên phải n1 đã bị bắn hết -> check2 = true
                    else if (newmap.mapOfShip[i-1][n2] == "*"||newmap.mapOfShip[i+1][n2] == null||newmap.mapOfShip[i+1][n2] == "#") {
                        check2 = true;
                        break;
                    }
                }
            }
            if (check1==true && check2==true) {
                checkship = true;
                dir = "N";
            }
        }
        //Hướng dọc
        else if(newmap.mapOfShip[n1][n2+1]=="S"
                ||newmap.mapOfShip[n1][n2-1]=="S"
                ||newmap.mapOfShip[n1][n2-1]=="$"
                ||newmap.mapOfShip[n1][n2+1]=="$"){
            //Dò mìn:
            //Dò ở trên từ n2 đến 10
            for (int i = n2; i < 11 ; i++) {
                //Nếu ô trên i là S thì tàu chưa bị bắn
                if(newmap.mapOfShip[n1][i+1]=="S") break;
                //Nếu ô trên i là * hoac null hoac # thì các ô bên trên n2 đều đã bị bắn -> check1=true
                else if(newmap.mapOfShip[n1][i+1]=="*" || newmap.mapOfShip[n1][i+1]==null||newmap.mapOfShip[n1][i+1]=="#"){
                    check1 = true;
                    break;
                }
            }
            //Nếu check1=true(ở trên), xét tiếp check2(ở dưới)
            if(check1){
                //Dò ở dưới n2 về 1
                for (int i = n2; i > 0 ; i--) {
                    //Nếu ở dưới i là S thì tàu chưa
                    if(newmap.mapOfShip[n1][i-1] == "S") break;
                    //Nếu ở dưới i là * hoac null hoac # thì tất cả ô dưới n2 đều đã bị bắn -> check2 = true
                    else if(newmap.mapOfShip[n1][i-1]=="*" || newmap.mapOfShip[n1][i-1] == null||newmap.mapOfShip[n1][i-1] == "#"){
                        check2=true;
                        break;
                    }
                }
            }
            if (check1==true && check2==true) {
                checkship = true;
                dir = "D";
            }
        }
        //Nếu bốn bề đều khác S hoặc $ thì đó là tàu 1*1, dir là N hay D gì cũng được
        else if((newmap.mapOfShip[n1][n2+1]!="S"||newmap.mapOfShip[n1][n2+1]!="$")
                &&(newmap.mapOfShip[n1][n2-1]!="S"||newmap.mapOfShip[n1][n2-1]!="$")
                &&(newmap.mapOfShip[n1-1][n2]!="S"||newmap.mapOfShip[n1-1][n2]!="$")
                &&(newmap.mapOfShip[n1+1][n2]!="S"||newmap.mapOfShip[n1+1][n2]!="$")){
            dir = "N";

        }
        return dir;
    }
    /**
     * Hàm check kết quả tọa độ nhập vào bắn được/bắn trúng/ bắn không trúng
     * Nếu trúng tàu thì khoanh vùng + thông báo
     * Nếu chưa trúng tàu thì đánh dấu ô đã bắn
     * Nếu ô đó không bắn được thì thông báo
     */
    boolean check_Result(int n1, int n2){
        boolean test_result = true;
        //Nếu ô bắn là S
        if (newmap.mapOfShip[n1][n2]=="S"){
            //Gán ô đó là $ ở 2 map
            mapOfUser[n1][n2] = "$";
            newmap.mapOfShip[n1][n2] = "$";
            bingo ++;
            guess ++;
            //Nếu trúng tàu nằm ngang
            if(check_Ship(n1,n2)=="N"){
                int n3=0, n4=0;//n3 là điểm cuối tàu, n4 là điểm đầu tàu
                System.out.println("Tàu chìm :3");
                //Tìm tọa độ cuối
                for (int i = n1; i < 11 ; i++) {
                    if(newmap.mapOfShip[i+1][n2]=="*" ||newmap.mapOfShip[i+1][n2]==null ||newmap.mapOfShip[i+1][n2]=="#"){
                        mapOfUser[i+1][n2] = "#";
                        newmap.mapOfShip[i+1][n2] = "#";
                        n3 = i;
                        break;
                    }
                }
                //Tìm tọa độ đầu
                for (int i = n1; i > 0 ; i--) {
                    if(newmap.mapOfShip[i-1][n2]=="*" ||newmap.mapOfShip[i-1][n2]==null||newmap.mapOfShip[i-1][n2]=="#"){
                        mapOfUser[i-1][n2] = "#";
                        newmap.mapOfShip[i-1][n2] = "#";
                        n4 = i;
                        break;
                    }
                }
                //Khoanh vùng tàu chìm ở cả 2 map
                for (int i = n4-1; i <= n3+1 ; i++) {
                    mapOfUser[i][n2-1] = "#";
                    mapOfUser[i][n2+1] = "#";
                    newmap.mapOfShip[i][n2-1] = "#";
                    newmap.mapOfShip[i][n2+1] = "#";
                }
            }
            //Nếu trúng tàu nằm dọc
            else if(check_Ship(n1,n2) == "D"){
                int n3 = 0, n4 = 0;
                System.out.println("Tàu chìm :3");
                //Tìm tọa độ cuối
                for (int i = n2; i < 11 ; i++) {
                    if(newmap.mapOfShip[n1][i+1]=="*" ||newmap.mapOfShip[n1][i+1]==null||newmap.mapOfShip[n1][i+1]=="#"){
                        mapOfUser[n1][i+1] = "#";
                        newmap.mapOfShip[n1][i+1] = "#";
                        n3 = i;
                        break;
                    }
                }
                //Tìm tọa độ đầu
                for (int i = n2; i > 0 ; i--) {
                    if(newmap.mapOfShip[n1][i-1]=="*" ||newmap.mapOfShip[n1][i-1]==null|newmap.mapOfShip[n1][i-1]=="#"){
                        mapOfUser[n1][i-1] = "#";
                        newmap.mapOfShip[n1][i-1] = "#";
                        n4 = i;
                        break;
                    }
                }
                //Khoanh vùng tàu chìm
                for (int i = n4-1; i <=n3+1  ; i++) {
                    mapOfUser[n1-1][i] = "#";
                    mapOfUser[n1+1][i] = "#";
                    newmap.mapOfShip[n1-1][i] = "#";
                    newmap.mapOfShip[n1+1][i] = "#";
                }
            }

        }
        //Nếu ô bắn là * hoặc - thì tăng guess và đánh dấu # ở 2 map
        else if(newmap.mapOfShip[n1][n2]=="*"||newmap.mapOfShip[n1][n2]=="-"){
            guess ++;
            mapOfUser[n1][n2]="#";
            newmap.mapOfShip[n1][n2]="#";
        }
        //Nếu trúng ô bắn rồi thì test_result = false
        else if(newmap.mapOfShip[n1][n2]=="$" ||newmap.mapOfShip[n1][n2]=="#"){
            test_result= false;
            System.out.println("Chỗ này có maaa ~.~");
        }

        return test_result;
    }
    /**
     * Hàm check win, đếm đủ 20 ký tự $ thì win
     */
    boolean win(String[][] map){
        boolean winner = false;
        int count = 0;
        for (int i = 1; i <11 ; i++) {
            for (int j = 1; j <11 ; j++) {
                if (map[i][j].equals("$")) count ++;
            }
        }
        if (count==20) winner = true;
        return winner;
    }
    /**
     * Hàm in map
     */
    void print_Map(String[][] map ){
        String[][] temp = new String[12][12];
        String[] rowChar = {"S","A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        //Copy map sang temp
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                temp[i][j] = map[i][j];
            }
        }
        //In hàng kí tự
        for (int i = 0; i < map.length-1; i++) {
            map[i][0] = rowChar[i];
            System.out.print(map[i][0] + "  ");
        }
        System.out.println("");
        //In map
        for (int i = 1; i < temp.length-1; i++) {
            for (int j = 0; j < temp.length-1; j++) {
                //Cot dau tien in so tư 1-10
                if (j == 0) {
                    //Chi la canh cho ngay thoai
                    if (i == 10) {
                        temp[0][i] = Integer.toString(i);
                        System.out.print(temp[0][i] + " ");
                    }
                    else {
                        temp[0][i] = Integer.toString(i);
                        System.out.print(temp[0][i] + "  ");
                    }
                    //Khoi tao cac o la -
                } else {
                    System.out.print(temp[j][i] + "  ");
                }
            }
            System.out.println("");
        }
    }
}

