package testPlugin;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import testPlugin.model.bean.HarvestedBlockBean;
import testPlugin.model.sqllite.HarvestedBlockDAO;

import java.util.List;

public class HarvestTask implements Runnable {

    private final JavaPlugin plugin;

    private HarvestedBlockDAO harvestedBlockDAO;

    public HarvestTask(JavaPlugin plugin){
        this.plugin = plugin;
        harvestedBlockDAO = new HarvestedBlockDAO();
    }


    @Override
    public void run(){
        List<HarvestedBlockBean> HarvestList = harvestedBlockDAO.getRegeneratedBlocks();
        World world = this.plugin.getServer().getWorld("world");
        for (HarvestedBlockBean block:HarvestList) {
            org.bukkit.block.Block b = world.getBlockAt(block.getPosX(),block.getPosY(),block.getPosZ());
            b.setType(Material.getMaterial(block.getBlockType()));
            harvestedBlockDAO.deleteHarvestedBlock(block.getId());

        }



    }
}
