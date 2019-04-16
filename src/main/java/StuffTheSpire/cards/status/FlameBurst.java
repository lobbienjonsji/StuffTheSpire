package StuffTheSpire.cards.status;

import StuffTheSpire.StuffTheSpireMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FlameBurst extends CustomCard {


    public static final String ID = StuffTheSpireMod.makeID("FlameBurst");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = StuffTheSpireMod.makeCardPath("Skill.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.STATUS;
    public static final CardColor COLOR = CardColor.COLORLESS;
    private static final int COST = -2;
    private static final int DAMAGE = 0;
    private static final int MAGICNUMBER = 1;
    private static final int BLOCK = 0;

    public FlameBurst() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        this.isEthereal = true;
    }
    public void triggerWhenDrawn()
    {
        AbstractDungeon.actionManager.addToBottom(new SetDontTriggerAction(this, false));
        if ((AbstractDungeon.player.hasPower("Evolve")) && (!AbstractDungeon.player.hasPower("No Draw")))
        {
            AbstractDungeon.player.getPower("Evolve").flash();
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player,
                    AbstractDungeon.player.getPower("Evolve").amount));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if ((!this.dontTriggerOnUseCard) && (p.hasRelic("Medical Kit"))) {
            useMedicalKit(p);
        }
    }
    @Override
    public void triggerOnExhaust()
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null,

                DamageInfo.createDamageMatrix(1, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}