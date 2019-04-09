package StuffTheSpire.cards;

import StuffTheSpire.StuffTheSpireMod;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.WhirlwindAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;

public class Pyroclast extends AbstractDecreasingCard {


    public static final String ID = StuffTheSpireMod.makeID("Pyroclast");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = StuffTheSpireMod.makeCardPath("Attack.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = CardColor.RED;
    private static final int COST = 1;
    private static final int DAMAGE = 1;
    private static final int MAGICNUMBER = 6;
    private static final int BLOCK = 0;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;


    public Pyroclast() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGICNUMBER;
        magicNumber = baseMagicNumber;
        this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[1];
        this.isMultiDamage = true;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < magicNumber; i++) {
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if ((!monster.isDead) && (!monster.isDying)) {
                    if (monster != null) {
                        AbstractDungeon.actionManager.addToBottom(new VFXAction(new FireballEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, monster.hb.cX, monster.hb.cY), 0.1F));
                        AbstractDungeon.actionManager.addToBottom(new SFXAction("GHOST_ORB_IGNITE_1", 0.3F));
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
                    }
                }
            }
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Burn(), 1));
        if(this.magicNumber > 0) {
            baseMagicNumber--;
            magicNumber = baseMagicNumber;
        }
        if(this.magicNumber == 1)
        {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
        else
        {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }

    @Override
    public void recharge(int amount) {
        magicNumber = baseMagicNumber;
        if(this.magicNumber == 1)
        {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
        else
        {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
        initializeDescription();
    }
}