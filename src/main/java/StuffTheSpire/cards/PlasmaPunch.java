package StuffTheSpire.cards;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.patches.cards.CardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class PlasmaPunch extends AbstractDecreasingCard {


    public static final String ID = StuffTheSpireMod.makeID("PlasmaPunch");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = StuffTheSpireMod.makeCardPath("PlasmaPunch.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = CardColor.COLORLESS;
    private static final int COST = 0;
    private static final int DAMAGE = 1;
    private static final int MAGICNUMBER = 4;
    private static final int MAGICNUMBER_UPGR = 1;

    public PlasmaPunch() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        tags.add(StuffTheSpire.patches.cards.CardTags.DECREASING);
    }
    @Override
    public void recharge(int amount)
    {
        baseMagicNumber += amount;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player , 1));
        if(this.magicNumber > 0) {
            baseMagicNumber--;
            magicNumber = baseMagicNumber;
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(MAGICNUMBER_UPGR);
            initializeDescription();
        }
    }
}