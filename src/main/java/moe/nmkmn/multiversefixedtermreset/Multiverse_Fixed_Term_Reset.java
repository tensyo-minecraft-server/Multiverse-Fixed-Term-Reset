package moe.nmkmn.multiversefixedtermreset;

import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.*;


public final class Multiverse_Fixed_Term_Reset extends JavaPlugin {

    @Override
    public void onEnable() {
        MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");

        new BukkitRunnable() {
            boolean executed = false;

            @Override
            public void run() {
                Date date = new Date();
                TimeZone timeZone = TimeZone.getTimeZone("Asia/Tokyo");
                SimpleDateFormat dateFormat = new SimpleDateFormat();

                dateFormat.setTimeZone(timeZone);
                dateFormat.format(date);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);

                int day = calendar.get(Calendar.DATE);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                if (dayOfWeek == Calendar.SATURDAY && hour == 4 && minute == 0 && !executed) {
                    getLogger().info("world_1_o, world_1_n reset start");
                    executed = true;
                    Objects.requireNonNull(core).getMVWorldManager().regenWorld("world_1_o", true, true, null, true);
                    Objects.requireNonNull(core).getMVWorldManager().regenWorld("world_1_n", true, true, null, true);
                }

                if (day == 1 && hour == 4 && minute == 0 && !executed) {
                    getLogger().info("world_1_e reset start");
                    executed = true;
                    Objects.requireNonNull(core).getMVWorldManager().regenWorld("world_1_e", true, true, null, true);
                }

                if (dayOfWeek == Calendar.SATURDAY && hour == 4 && minute == 1 && executed) {
                    executed = false;
                }

                if (day == 1 && hour == 4 && minute == 1 && executed) {
                    executed = false;
                }
            }
        }.runTaskTimer(this, 0L, 20L);
    }
}
