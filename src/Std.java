public class Std{
    private int snum,age,total_credits;
    private String sname,major,level;

    public Std(int propertyID,String propertName, String propertyLocation,String propertyRoomNo,int propertyArea, int propertyYear)
    {
        this.snum=propertyID;
        this.sname=propertName;
        this.major=propertyLocation;
        this.level=propertyRoomNo;
        this.age=propertyArea;
        this.total_credits=propertyYear;
    }
    public int getSnum(){
        return snum;
    }
    public String getPropRoomNo(){
        return level;
    }
    public int getTotal_credits(){
        return total_credits;
    }
    public int getPropArea(){
        return age;
    }
    public String getMajor(){
        return major;
    }
    public String getSname(){
        return sname;
    }

}
