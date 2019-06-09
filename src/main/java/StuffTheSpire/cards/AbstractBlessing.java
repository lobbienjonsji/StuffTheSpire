package StuffTheSpire.cards;

import StuffTheSpire.StuffTheSpireMod;
import StuffTheSpire.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractBlessing extends AbstractCard {


    public static final String ID = StuffTheSpireMod.makeID("Vigor");
    public static final CardColor COLOR = StuffTheSpire.patches.cards.CardColors.BLESSING;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardType TYPE = StuffTheSpire.patches.cards.CardTypes.BLESSING;
    public static String IMG;
    public static Texture texture = TextureLoader.getTexture("StuffTheSpireResources/images/512/bg_skill_default_gray.png");
    public static Texture CardPortrait;
    public static Texture CardPortraitLarge;
    public static String IMGLARGE;

    public AbstractBlessing(String ID, String NAME, String IMG, int COST, String DESCRIPTION, AbstractCard.CardTarget TARGET) {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        String IMGSMALL = IMG + ".png";
        CardPortrait = TextureLoader.getTexture(IMGSMALL);
        IMGLARGE = IMG + "_p.png";
        CardPortraitLarge = TextureLoader.getTexture(IMGLARGE);
        this.portrait = new TextureAtlas.AtlasRegion(CardPortrait, 0, 0, CardPortrait.getWidth(), CardPortrait.getHeight());
        this.portraitImg = CardPortraitLarge;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }

    @Override
    public Texture getCardBg() {
        return texture;
    }


    public Texture getCardPortrait() {
        return ImageMaster.loadImage(IMGLARGE);
    }

    @Override
    public abstract AbstractCard makeCopy();

    /*@SpireOverride
    private void renderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
            sb.draw(img, drawX, drawY, 256.0F, 256.0F, 512.0F, 512.0F, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 512, 512, false, false);
    }*/

    /*@SpireOverride
    private void renderEnergy(SpriteBatch sb) {
        if (this.cost > -2 && !this.isLocked && this.isSeen) {
            Logger logger = new Logger("lel");
            logger.info("ääääääääääääääääääää");
            float drawX = this.current_x - 256.0F;
            float drawY = this.current_y - 256.0F;
            this.renderHelper(sb, Color.WHITE.cpy(), ImageMaster.CARD_COLORLESS_ORB, drawX, drawY);
            }

            Color costColor = Color.WHITE.cpy();
            if (AbstractDungeon.player != null && AbstractDungeon.player.hand.contains(this) && !this.hasEnoughEnergy()) {
                costColor = Color.RED;
            } else if (this.isCostModified || this.isCostModifiedForTurn || this.freeToPlayOnce) {
                costColor = Color.GREEN;
            }

            costColor.a = this.transparency;
            String text = Integer.toString(this.costForTurn);
            BitmapFont font = this.getEnergyFont();
            if ((this.type != AbstractCard.CardType.STATUS || this.cardID.equals("Slimed")) && (this.color != AbstractCard.CardColor.CURSE || this.cardID.equals("Pride"))) {
                FontHelper.renderRotatedText(sb, font, text, this.current_x, this.current_y, -132.0F * this.drawScale * Settings.scale, 192.0F * this.drawScale * Settings.scale, this.angle, false, costColor);
            }

        }*/
    private BitmapFont getEnergyFont() {
        FontHelper.cardEnergyFont_L.getData().setScale(this.drawScale);
        return FontHelper.cardEnergyFont_L;
    }

}