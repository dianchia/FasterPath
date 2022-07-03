package FasterPath.patches;

import FasterPath.FasterPath;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.modifiers.ModifierValue;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.level.gameTile.PathTiledTile;
import net.bytebuddy.asm.Advice;


@ModMethodPatch(target = PathTiledTile.class, name = "getSpeedModifier", arguments = {Mob.class})
public class PathTiledTileGetSpeedModifierPatch {
    @Advice.OnMethodExit
    static void onExit(@Advice.Argument(0)Mob mob, @Advice.Return(readOnly = false) ModifierValue<Float> modifierValue) {
        modifierValue = mob.isFlying()
                ? new ModifierValue<>(BuffModifiers.SPEED, 0.0F)
                : new ModifierValue<Float>(BuffModifiers.SPEED, modifierValue.value + FasterPath.config.getSpeedModifier());
    }
}
