package StuffTheSpire.cards;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ${NAME} extends AbstractDefaultCard {

  
    public static final String ID = StuffTheSpireMod.makeID("${NAME}");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = StuffTheSpireMod.makeCardPath("${NAME}.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final int COST =  ${COST};
    private static final int UPGRADED_COST =  ${UPGRADED_COST};
    private static final int DAMAGE = ${DAMAGE};
    private static final int UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE};
    private static final int MAGICNUMBER = ${MAGICNUMBER};
    private static final int MAGICNUMBER_UPGR = ${MAGICNUMBER_UPGRADED_INCREASE};
    public  ${NAME}() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(MAGICNUMBER_UPGR);
            initializeDescription();
        }
    }
}