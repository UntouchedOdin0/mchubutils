package untouchedodin0.mchubutils.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.network.ServerInfo.ServerType;
import net.minecraft.text.PlainTextContent;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

  @Unique
  private static final String IP = "mchub.com";
  protected TitleScreenMixin(Text title) {
    super(title);
  }

  @Inject(at = @At("RETURN"), method = "initWidgetsNormal")
  private void addCustomButton(int y, int spacingY, CallbackInfo callbackInfo) {

    this.addDrawableChild(ButtonWidget.builder(Text.of("Play MCHub"), (button) -> {
      MinecraftClient minecraftClient = MinecraftClient.getInstance();
      PlainTextContent serverAddress = PlainTextContent.of(IP);
      ServerInfo serverInfo = new ServerInfo("mchub", serverAddress.string(), ServerType.OTHER);
      ConnectScreen.connect(new TitleScreen(), minecraftClient,
          ServerAddress.parse(serverAddress.string()), serverInfo, false);
    }).dimensions(this.width / 2 - 100 + 205, y, 75, 20).build());
  }
}
