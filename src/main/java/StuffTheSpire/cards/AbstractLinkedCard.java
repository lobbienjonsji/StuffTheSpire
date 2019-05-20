package StuffTheSpire.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractLinkedCard extends CustomCard {

    private boolean chainplayed = false;

    public AbstractLinkedCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public void chain() {
        chainplayed = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.miscRng), 0));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        chainplayed = false;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return chainplayed == true;
    }
}
