package StuffTheSpire.actions;

import StuffTheSpire.cards.colorless.Snapshot;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class SnapShootAction extends AbstractGameAction {
    private boolean upgrade;
    public SnapShootAction(boolean isupgraded)
    {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.isDone = false;
        this.upgrade = isupgraded;
    }

    @Override
    public void update() {
        AbstractCard c = new Snapshot();
        ((Snapshot) c).addOrb(AbstractDungeon.player.orbs.get(0));
        ((Snapshot) c).addOrb(AbstractDungeon.player.orbs.get(1));
        ((Snapshot) c).addOrb(AbstractDungeon.player.orbs.get(2));
        if(upgrade)
        {
            c.upgrade();
        }
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
        this.isDone = true;
    }
}
