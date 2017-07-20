package testPlugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import  org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import testPlugin.model.DatabaseHandler;
import testPlugin.model.HarvestedBlockBean;
import testPlugin.model.HarvestedBlockDAO;

import java.sql.Timestamp;

public class main extends JavaPlugin implements Listener{
    @Override
    public void onEnable() {
        getLogger().info("onEnable has been invoked");
        getServer().getPluginManager().registerEvents(this,this);
        BukkitScheduler bukkitScheduler = getServer().getScheduler();

        bukkitScheduler.scheduleSyncRepeatingTask(this, new HarvestTask(this),20,200);
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        HarvestedBlockDAO dao = new HarvestedBlockDAO();
        Block block = event.getBlock();
        String blockType = block.getType().name();
        Location l = block.getLocation();
        Boolean blockInBase = dao.isBlockPresent(l.getBlockX(), l.getBlockY(), l.getBlockZ());
        event.setCancelled(true);
        if (blockInBase) {
            getServer().broadcastMessage("deja miné");
        } else {
            getServer().broadcastMessage("pas encore miné");

            HarvestedBlockBean harvestedBlock = new HarvestedBlockBean();
            harvestedBlock.setBlockType(blockType);
            harvestedBlock.setPosX(l.getBlockX());
            harvestedBlock.setPosY(l.getBlockY());
            harvestedBlock.setPosZ(l.getBlockZ());
            harvestedBlock.setHarvestDate((new Timestamp(System.currentTimeMillis())).getTime() + 20);


            dao.insert(harvestedBlock);

            block.setType(Material.STONE);

        }
    }


}
