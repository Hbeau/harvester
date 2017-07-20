package testPlugin.model.bean;


import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;

public class BlockComportementBean {
    private String blockType;
    private int metadata;
    private int respawndelay;
    private Material RemplacmenetBlock;
    private ItemStack itemGiven;

    public BlockComportementBean(String blockType, int metadata, int respawndelay, String remplacmenetBlock, String item) {
        this.blockType = blockType;
        this.metadata = metadata;
        this.respawndelay = respawndelay;
        RemplacmenetBlock = Material.getMaterial(remplacmenetBlock);
        this.itemGiven = new ItemStack(Material.getMaterial(item));
    }
}

