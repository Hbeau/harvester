package testPlugin.model;

import com.google.common.primitives.UnsignedInteger;

public class HarvestedBlockBean {

    private int id;
    private String blockType;
    private int posX, posY, posZ;
    private Long harvestDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlockType() {
        return blockType;
    }

    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosZ() {
        return posZ;
    }

    public void setPosZ(int posZ) {
        this.posZ = posZ;
    }

    public long getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(long harvestDate) {
        this.harvestDate = harvestDate;
    }

    public HarvestedBlockBean(){
    }

    public HarvestedBlockBean(int id, String blockType, int posX, int posY, int posZ, Long harvestDate) {
        this.id = id;
        this.blockType = blockType;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.harvestDate = harvestDate;
    }
}
