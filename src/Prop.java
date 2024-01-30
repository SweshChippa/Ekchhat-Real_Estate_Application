public class Prop{
    private int propID,porpRoomNo,propYear,propArea;
    private String propName,propLocation;

    public Prop(int propertyID,String propertName, String propertyLocation,int propertyRoomNo,int propertyArea, int propertyYear)
    {
        this.propID=propertyID;
        this.propName=propertName;
        this.propLocation=propertyLocation;
        this.porpRoomNo=propertyRoomNo;
        this.propArea=propertyArea;
        this.propYear=propertyYear;
    }
    public int getPropID(){
        return propID;
    }
    public int getPropRoomNo(){
        return porpRoomNo;
    }
    public int getPropYear(){
        return propYear;
    }
    public int getPropArea(){
        return propArea;
    }
    public String getPropLocation(){
        return propLocation;
    }
    public String getPropName(){
        return propName;
    }

}
