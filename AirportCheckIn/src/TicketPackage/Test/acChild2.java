package TicketPackage.Test;

/**
 * Ciaran O'Boyle
 */
public class acChild2{
    String name1;
    String name2;
    String name3;
    String name4;
    String name5;

    public acChild2(String AcChild2name1,String AcChild2name2, String AcChild2name3, String AcChild2name4, String AcChild2name5) {
        String name1 = AcChild2name1;
        String name2 = AcChild2name2;
        String name3 = AcChild2name3;
        String name4 = AcChild2name4;
        String name5 = AcChild2name5;
        }

    public acChild2() {

    }
    public void setName1(String AcChild2name1){name1=AcChild2name1;}
    public String getName1(){return name1;}
    public void setName2(String AcChild2name2){name2=AcChild2name2;}
    public String getName2(){return name2;}
    public void setName3(String AcChild2name3){name3=AcChild2name3;}
    public String getName3(){return name3;}
    public void setName4(String AcChild2name4){name4=AcChild2name4;}
    public String getName4(){return name4;}
    public void setName5(String AcChild2name5){name5=AcChild2name5;}
    public String getName5(){return name5;}
    public void showAllDetails(){

    System.out.print(name1+name2+name3+name4+name5);
}
}


