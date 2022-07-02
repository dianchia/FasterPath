package FasterPath.patches;

import FasterPath.FasterPath;
import necesse.engine.localization.Localization;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.level.gameTile.PathTiledTile;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(target = PathTiledTile.class, name = "getItemTooltips", arguments = {InventoryItem.class, PlayerMob.class})
public class PathTiledTileGetItemTooltipsPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.This PathTiledTile thisPathTiledTile,@Advice.Argument(1)PlayerMob mob, @Advice.Return(readOnly = false)ListGameTooltips tooltips) {
        tooltips.removeLast();
        tooltips.add(Localization.translate("itemtooltip", "stonepathtip", "speedModifier", thisPathTiledTile.getSpeedModifier(mob).value));
    }
}
