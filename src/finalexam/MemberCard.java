/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalexam;

/**
 *
 * @author User
 */
public class MemberCard {

    private String Cardnumber;
    private String Membercardhordlername;
    private int point;

    public MemberCard() {
    }

    public MemberCard(String Cardnumber, String Membercardhordlername) {
        this.Cardnumber = Cardnumber;
        this.Membercardhordlername = Membercardhordlername;
    }

    public String getCardnumber() {
        return Cardnumber;
    }

    public String getMembercardhordlername() {
        return Membercardhordlername;
    }

    public int getPoint() {
        return point;
    }

    public void setCardnumber(String Cardnumber) {
        this.Cardnumber = Cardnumber;
    }

    public void setMembercardhordlername(String Membercardhordlername) {
        this.Membercardhordlername = Membercardhordlername;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void redeemPoint(int point) {
        this.point -= point;
    }

    public boolean isRedeemable(int point) {
        if (point > this.point) {
            return false;
        } else {
            return true;
        }
    }
}
