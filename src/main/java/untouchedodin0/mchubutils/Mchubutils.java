package untouchedodin0.mchubutils;


import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback.EVENT;

import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ideas for mod
 * <p>
 * Info overlays for ability cooldowns. More accurate average money per hour overlay. Blocks broken
 * per second in current time, reminders to claim kits, gkits and /daily.
 */


public class Mchubutils implements ModInitializer {

  // This logger is used to write text to the console and the log file.
  // It is considered best practice to use your mod id as the logger's name.
  // That way, it's clear which mod wrote info, warnings, and errors.
  public static final Logger LOGGER = LoggerFactory.getLogger("mchubutils");

  private static final MinecraftClient minecraft = MinecraftClient.getInstance();

  @Override
  public void onInitialize() {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

  }

  private String extractTotalValue(Text message) {
    String string = message.getString();
    // Split the message
    String[] lines = string.split("\n");
    for (String line : lines) {
      if (line.contains("Total:")) {
        // Extract the value after "Total:"
        String[] parts = line.split(":");
        if (parts.length > 1) {
          return parts[1].trim().replace(",", ""); // Trim any leading or trailing whitespace
        }
      }
    }
    return String.valueOf(1);
  }
}