package StuffTheSpire.cards.green;

import StuffTheSpire.StuffTheSpireMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Repulse extends CustomCard {


    public static final String ID = StuffTheSpireMod.makeID("Repulse");
    public static final String IMG = StuffTheSpireMod.makeCardPath("Repulse.png");
    public static final CardColor COLOR = CardColor.GREEN;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADEDESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 2;
    private static final int DAMAGE = 0;
    private static final int MAGICNUMBER = 1;
    private static final int BLOCK = 12;

    public Repulse() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        for (AbstractMonster a : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!a.isDeadOrEscaped() && ((a.intent == AbstractMonster.Intent.ATTACK) || (a.intent == AbstractMonster.Intent.ATTACK_BUFF) || (a.intent == AbstractMonster.Intent.ATTACK_DEBUFF) || (a.intent == AbstractMonster.Intent.ATTACK_DEFEND))) {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeBlock(2);
            this.rawDescription = UPGRADEDESCRIPTION;
            initializeDescription();
        }
    }
}