package me.candelamena.image_plugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class Image_plugin extends JavaPlugin implements CommandExecutor {
    public static final Color TAN_BROWN     = new Color(222, 184, 135);
    public static final Color PALE_ORANGE   = new Color(255, 228, 181);


    @Override
    public void onEnable() {
        this.getCommand("buildimage").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this.");
            return true;
        }

        if (args.length > 2 || args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /buildimage <image-path-or-url> [local]");
            return true;
        }

        Player player = (Player) sender;
        String pathOrUrl = args[0];
        boolean isLocal = args.length == 2 && args[1].equalsIgnoreCase("local");

        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                BufferedImage image;

                if (isLocal) {
                    File file = new File(getDataFolder(), "../../images/"+pathOrUrl).getCanonicalFile();
                    if (!file.exists()) {
                        player.sendMessage(ChatColor.RED + "El archivo local no existe!");
                        return;
                    }
                    image = ImageIO.read(file);
                } else {
                    // Carga desde URL
                    InputStream input = new URL(pathOrUrl).openStream();
                    image = ImageIO.read(input);
                }

                if (image == null) {
                    player.sendMessage(ChatColor.RED + "No se pudo cargar la imagen!");
                    return;
                }

                int maxSize = 64;
                int width = Math.min(image.getWidth(), maxSize);
                int height = Math.min(image.getHeight(), maxSize);

                Image scaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = resized.createGraphics();
                g.drawImage(scaled, 0, 0, null);
                g.dispose();

                Bukkit.getScheduler().runTask(this, () -> {
                    buildImage(resized, player);
                    player.sendMessage(ChatColor.GREEN + "Imagen construida!");
                });

            } catch (Exception e) {
                e.printStackTrace();
                player.sendMessage(ChatColor.RED + "Error al cargar la imagen.");
            }
        });

        return true;
    }
    private void buildImage(BufferedImage image, Player player) {
        Location loc = player.getLocation();
        World world = player.getWorld();

        Map<Material, Color> palette = new HashMap<>();

        palette.put(Material.WHITE_CONCRETE, Color.WHITE);
        palette.put(Material.BLACK_WOOL, new Color(8, 8, 8));            

        palette.put(Material.TERRACOTTA, TAN_BROWN);
        palette.put(Material.SANDSTONE, PALE_ORANGE);

        palette.put(Material.BLUE_WOOL, new Color(25, 25, 112));        
        palette.put(Material.LIGHT_BLUE_WOOL, new Color(135, 206, 250)); 
        palette.put(Material.CYAN_WOOL, new Color(0, 255, 255));       

        palette.put(Material.WHITE_CONCRETE_POWDER, new Color(255, 250, 250));      
        palette.put(Material.PINK_CONCRETE_POWDER, new Color(255, 192, 203));       
        palette.put(Material.MAGENTA_CONCRETE_POWDER, new Color(238, 130, 238));    
        palette.put(Material.LIGHT_GRAY_CONCRETE_POWDER, new Color(211, 211, 211)); 
        palette.put(Material.YELLOW_CONCRETE_POWDER, new Color(255, 255, 224));     
        palette.put(Material.RED_CONCRETE_POWDER, new Color(220, 20, 60));          

        palette.put(Material.AIR, new Color(0,0,0,0)); 
        palette.put(Material.GRASS_BLOCK, new Color(127, 178, 56));
        palette.put(Material.SAND, new Color(247, 233, 163));
        palette.put(Material.WHITE_WOOL, new Color(199, 199, 199));
        palette.put(Material.TNT, new Color(255, 0, 0));
        palette.put(Material.ICE, new Color(160, 160, 255));
        palette.put(Material.IRON_BLOCK, new Color(167, 167, 167));
        palette.put(Material.OAK_LEAVES, new Color(0, 124, 0));
        palette.put(Material.SNOW_BLOCK, new Color(255, 255, 255));
        palette.put(Material.CLAY, new Color(164, 168, 184));
        palette.put(Material.DIRT, new Color(151, 109, 77));
        palette.put(Material.STONE, new Color(112, 112, 112));
        palette.put(Material.WATER, new Color(64, 64, 255));
        palette.put(Material.OAK_WOOD, new Color(143, 119, 72));
        palette.put(Material.QUARTZ_BLOCK, new Color(255, 252, 245));

        palette.put(Material.ORANGE_CONCRETE, new Color(216, 127, 51));
        palette.put(Material.MAGENTA_CONCRETE, new Color(178, 76, 216));
        palette.put(Material.LIGHT_BLUE_CONCRETE, new Color(102, 153, 216));
        palette.put(Material.YELLOW_CONCRETE, new Color(229, 229, 51));
        palette.put(Material.LIME_CONCRETE, new Color(127, 204, 25));
        palette.put(Material.PINK_CONCRETE, new Color(242, 127, 165));
        palette.put(Material.GRAY_CONCRETE, new Color(76, 76, 76));
        palette.put(Material.LIGHT_GRAY_CONCRETE, new Color(153, 153, 153));
        palette.put(Material.CYAN_CONCRETE, new Color(76, 127, 153));
        palette.put(Material.PURPLE_CONCRETE, new Color(127, 63, 178));
        palette.put(Material.BLUE_CONCRETE, new Color(51, 76, 178));
        palette.put(Material.BROWN_CONCRETE, new Color(102, 76, 51));
        palette.put(Material.GREEN_CONCRETE, new Color(102, 127, 51));
        palette.put(Material.RED_CONCRETE, new Color(153, 51, 51));
        palette.put(Material.BLACK_CONCRETE, new Color(25, 25, 25));
        palette.put(Material.GOLD_BLOCK, new Color(250, 238, 77));
        palette.put(Material.DIAMOND_BLOCK, new Color(92, 219, 213));
        palette.put(Material.LAPIS_BLOCK, new Color(74, 128, 255));
        palette.put(Material.EMERALD_BLOCK, new Color(0, 217, 58));

        palette.put(Material.WHITE_TERRACOTTA, new Color(209, 177, 161));
        palette.put(Material.ORANGE_TERRACOTTA, new Color(159, 82, 36));
        palette.put(Material.MAGENTA_TERRACOTTA, new Color(149, 87, 108));
        palette.put(Material.LIGHT_BLUE_TERRACOTTA, new Color(112, 108, 138));
        palette.put(Material.YELLOW_TERRACOTTA, new Color(186, 133, 36));
        palette.put(Material.LIME_TERRACOTTA, new Color(103, 117, 53));
        palette.put(Material.PINK_TERRACOTTA, new Color(160, 77, 78));
        palette.put(Material.GRAY_TERRACOTTA, new Color(57, 41, 35));
        palette.put(Material.LIGHT_GRAY_TERRACOTTA, new Color(135, 107, 98));
        palette.put(Material.CYAN_TERRACOTTA, new Color(87, 92, 92));
        palette.put(Material.PURPLE_TERRACOTTA, new Color(122, 73, 88));
        palette.put(Material.BLUE_TERRACOTTA, new Color(76, 62, 92));
        palette.put(Material.BROWN_TERRACOTTA, new Color(76, 50, 35));
        palette.put(Material.GREEN_TERRACOTTA, new Color(76, 82, 42));
        palette.put(Material.RED_TERRACOTTA, new Color(142, 60, 46));
        palette.put(Material.BLACK_TERRACOTTA, new Color(37, 22, 16));

        palette.put(Material.CRIMSON_NYLIUM, new Color(189, 48, 49));
        palette.put(Material.CRIMSON_STEM, new Color(48, 63, 97));
        palette.put(Material.CRIMSON_HYPHAE, new Color(92, 25, 29));
        palette.put(Material.WARPED_NYLIUM, new Color(22, 126, 134));
        palette.put(Material.WARPED_STEM, new Color(58, 142, 140));
        palette.put(Material.WARPED_HYPHAE, new Color(86, 44, 62));
        palette.put(Material.WARPED_WART_BLOCK, new Color(20, 180, 133));

        palette.put(Material.DEEPSLATE, new Color(100, 100, 100));
        palette.put(Material.RAW_IRON_BLOCK, new Color(216, 175, 147));
        palette.put(Material.GLOW_LICHEN, new Color(127, 167, 150));
        palette.put(Material.PODZOL, new Color(129, 86, 49));
        palette.put(Material.NETHERRACK, new Color(112, 2, 0));


        int startX = loc.getBlockX();
        int startY = loc.getBlockY();
        int startZ = loc.getBlockZ() + 2;


        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                Color pixel = new Color(rgb);

                Material best = getClosestBlock(pixel, palette);

                Location blockLoc = new Location(world, startX + x, startY + (image.getHeight() - y), startZ);

                if (needsSupport(best)) {
                    Location below = blockLoc.clone().add(0, -1, 0);
                    if (below.getBlock().getType() == Material.AIR) {
                        below.getBlock().setType(Material.STONE);
                    }
                }

                blockLoc.getBlock().setType(best);
            }
        }
    }


    private boolean needsSupport(Material mat) {
        return mat == Material.SAND
                || mat == Material.GRAVEL
                || mat == Material.RED_SAND
                || mat == Material.WHITE_CONCRETE_POWDER
                || mat == Material.ORANGE_CONCRETE_POWDER
                || mat == Material.MAGENTA_CONCRETE_POWDER
                || mat == Material.LIGHT_BLUE_CONCRETE_POWDER
                || mat == Material.YELLOW_CONCRETE_POWDER
                || mat == Material.LIME_CONCRETE_POWDER
                || mat == Material.PINK_CONCRETE_POWDER
                || mat == Material.GRAY_CONCRETE_POWDER
                || mat == Material.LIGHT_GRAY_CONCRETE_POWDER
                || mat == Material.CYAN_CONCRETE_POWDER
                || mat == Material.PURPLE_CONCRETE_POWDER
                || mat == Material.BLUE_CONCRETE_POWDER
                || mat == Material.BROWN_CONCRETE_POWDER
                || mat == Material.GREEN_CONCRETE_POWDER
                || mat == Material.RED_CONCRETE_POWDER
                || mat == Material.BLACK_CONCRETE_POWDER;
    }

    private Material getClosestBlock(Color color, Map<Material, Color> palette) {
        double bestDistance = Double.MAX_VALUE;
        Material bestMaterial = Material.WHITE_CONCRETE;
        for (Map.Entry<Material, Color> entry : palette.entrySet()) {
            double dist = colorDistance(color, entry.getValue());
            if (dist < bestDistance) {
                bestDistance = dist; bestMaterial = entry.getKey();
            }
        }
        return bestMaterial;
    }

    private double colorDistance(Color c1, Color c2) {
        int rDiff = c1.getRed() - c2.getRed();
        int gDiff = c1.getGreen() - c2.getGreen();
        int bDiff = c1.getBlue() - c2.getBlue();
        return rDiff * rDiff + gDiff * gDiff + bDiff * bDiff;
    }
}
