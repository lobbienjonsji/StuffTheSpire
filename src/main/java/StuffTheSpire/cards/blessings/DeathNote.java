package StuffTheSpire.cards.blessings;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.cards.AbstractBlessing;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DeathNote extends AbstractBlessing {


    public static final String ID = StuffTheSpireMod.makeID("DeathNote");
    public static final String IMG = StuffTheSpireMod.makeCardPath("DeathNote");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = -2;
    private static final int MAGICNUMBER = 1;
    private int deathcounter;

    public DeathNote() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TARGET);
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void atTurnStart() {
        deathcounter = GameActionManager.turn;
        baseMagicNumber = deathcounter;
        magicNumber = baseMagicNumber;
        if (deathcounter == 10) {
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                flash();
                if (upgraded) {
                    for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                        if ((!monster.isDead) && (!monster.isDying)) {
                            AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(AbstractDungeon.player, 200, DamageInfo.DamageType.THORNS)));
                        }
                    }
                } else {
                    AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                    if ((!m.isDead) && (!m.isDying)) {
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(AbstractDungeon.player, 200, DamageInfo.DamageType.THORNS)));
                    }
                }
            }
        }
    }

    @Override
    public void triggerWhenDrawn() {
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 1, false));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DeathNote();
    }
}