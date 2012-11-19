package effect;

import actor.Actor;

public class EffectPackage {
    Effect effect;
    boolean effect_player;
    Actor effected_actor;

    public EffectPackage(Effect e, boolean p, Actor a) {
        effect = e;
        effect_player = p;
        effected_actor = a;
    }

    public boolean effectsPlayer() {
        return effect_player;
    }

    public Actor getActor() {
        return effected_actor;
    }

    public Effect getEffect() {
        return effect;
    }
}
